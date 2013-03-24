package com.thornton.k3spring;

/**
 * Object that represents the Task table in the database of the application.
 * Used to map column names to the database table.
 * 
 * @author JT
 *
 */
public class TaskTable {

	/**Name of the table*/
	public static final String NAME = "task";

	/**Primary key column name*/
	public static final String ID = "_id";

	/**Task id column name*/
	public static final String TASK_ID = "task_id";

	/**Description of the task column name*/
	public static final String DESC = "desc";

	/**Status column of the table*/
	public static final String STATUS = "status";

	/**Start date column of the table*/
	public static final String START = "start";

	/**End date column of the table*/
	public static final String END = "end";

	/**String used to create the table when the database created*/
	public static final String CREATE = "CREATE TABLE " + NAME +
			" (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK_ID + " INT, " +
			DESC + " VARCHAR(255), " + START + " VARCHAR(255), " +
			END + " VARCHAR(255), " + STATUS + " INT);";

	/**String used to drop the table when the application is updated*/
	public static final String DROP = "DROP TABLE IF EXISTS " + NAME;
}
