package com.thornton.k3spring;

public class TaskTable {

	public static final String NAME = "task";

	public static final String ID = "_id";

	public static final String TASK_ID = "task_id";

	public static final String DESC = "desc";

	public static final String STATUS = "status";

	public static final String START = "start";

	public static final String END = "end";

	public static final String CREATE = "CREATE TABLE " + NAME +
			" (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK_ID + " INT, " +
			DESC + " VARCHAR(255), " + START + " VARCHAR(255), " +
			END + " VARCHAR(255), " + STATUS + " INT);";

	public static final String DROP = "DROP TABLE IF EXISTS " + NAME;
}
