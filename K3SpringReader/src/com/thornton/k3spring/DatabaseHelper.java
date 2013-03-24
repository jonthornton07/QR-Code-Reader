package com.thornton.k3spring;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper for the program. Creates, deletes and stores data in the database.
 * @author JT
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper{

	/**Name of the database*/
	public static final String DATABASE_NAME = "tasks.db";

	/**Version of the database*/
	public static final int  DATABASE_VERSION = 1;

	/**
	 * Constructor for the class
	 * @param context - Activity context
	 */
	public DatabaseHelper(final Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Create the tables in the database
	 * @param db - database created
	 */
	@Override
	public void onCreate(final SQLiteDatabase db) {
		db.execSQL(TaskTable.CREATE);
	}

	/**
	 * Called when the data base is being upgraded
	 * @param db - database
	 * @param oldVersion - old db version name
	 * @param newVersion - new db version
	 */
	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
		db.execSQL(TaskTable.DROP);
		onCreate(db);
	}

	/**
	 * Add a task to the database
	 * @param task - task to add to the database
	 * @return the id of the task
	 */
	public long addTask(final Task task){
		final ContentValues cv = new ContentValues();
		cv.put(TaskTable.TASK_ID, task.getId());
		cv.put(TaskTable.DESC, task.getTasks());
		cv.put(TaskTable.START, task.getStart());
		cv.put(TaskTable.END, task.getEnd());
		cv.put(TaskTable.STATUS, task.getStatus());

		final SQLiteDatabase db = getWritableDatabase();
		return db.insert(TaskTable.NAME, TaskTable.TASK_ID, cv);

	}

	/**
	 * Update the tasks in the database to have an end time
	 * @param task - task to give an end time to
	 */
	public void updateAllTasksToComplete(final Task task){
		final String where = TaskTable.ID + "=? and " + TaskTable.STATUS + "=" + Task.IN_PROGRESS ;
		final String[] whereArgs = new String[] {Integer.toString(task.getTableId())};


		final ContentValues cv = new ContentValues();
		cv.put(TaskTable.END, task.getEnd());
		cv.put(TaskTable.STATUS, task.getStatus());

		final SQLiteDatabase db = getWritableDatabase();
		db.update(TaskTable.NAME, cv, where, whereArgs);
	}

	/**
	 * Get all the tasks in the database with an id
	 * @param id - id of the tasks
	 * @return all the tasks in the database with an id
	 */
	public List<Task> getTasksFromId(final String id){
		final SQLiteDatabase db = getWritableDatabase();
		final String where = TaskTable.TASK_ID + "=? and (" + TaskTable.END + " IS NULL OR " + TaskTable.END + "= '')";
		final String[] whereArgs = new String[] {id};

		final String[] cols = new String[]{TaskTable.ID, TaskTable.TASK_ID, TaskTable.DESC, TaskTable.START, TaskTable.END, TaskTable.STATUS};

		final Cursor c = db.query(TaskTable.NAME, cols, where, whereArgs, null, null, TaskTable.TASK_ID);

		final List<Task> tasks = new ArrayList<Task>();

		while(c.moveToNext()){
			tasks.add(extractTaskFromCursor(c));
		}

		c.close();

		return tasks;
	}

	/**
	 * Get all the tasks that are in a day
	 * @param day - day to get tasks for
	 * @return all the tasks that are in a day
	 */
	public List<Task> getAllTasksByDay(final String day){
		final SQLiteDatabase db = getWritableDatabase();
		final String where = TaskTable.START + " like '" + day + "%'";

		final String[] cols = new String[]{TaskTable.ID, TaskTable.TASK_ID, TaskTable.DESC, TaskTable.START, TaskTable.END, TaskTable.STATUS};

		final Cursor c = db.query(TaskTable.NAME, cols, where, null, null, null, TaskTable.TASK_ID);

		final List<Task> tasks = new ArrayList<Task>();

		while(c.moveToNext()){
			tasks.add(extractTaskFromCursor(c));
		}

		c.close();

		return tasks;
	}

	/**
	 * Extract the task from the retrieved data
	 * @param c - cursor containing the data
	 * @return the task from the cursor
	 */
	private Task extractTaskFromCursor(final Cursor c) {
		final Task task = new Task(c.getInt(c.getColumnIndex(TaskTable.ID)));
		task.setId(c.getString(c.getColumnIndex(TaskTable.TASK_ID)));
		task.setStart(c.getString(c.getColumnIndex(TaskTable.START)));
		task.setTasks(c.getString(c.getColumnIndex(TaskTable.DESC)));
		task.setEnd(c.getString(c.getColumnIndex(TaskTable.END)));
		task.setStatus(c.getInt(c.getColumnIndex(TaskTable.STATUS)));
		return task;
	}
}
