package com.thornton.k3spring;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

	private static final String FORMAT = "MM/dd/yyyy hh:mm a";

	private static final String NEW_LINE = "\n";

	public static final int NOT_STARTED = 0;

	public static final int COMPLETE = 2;

	public static final int IN_PROGRESS = 1;

	private String id;

	private String tasks;

	private String start;

	private String end;

	private int status;

	private int tableId;

	public Task(final int tableId){
		this.setTableId(tableId);
	}

	public Task(final String taskString){
		final String[] results = taskString.split(NEW_LINE);
		this.id = results[0];
		if(results.length > 1){
			final StringBuilder builder = new StringBuilder();
			for(int index = 1; index < results.length; index++){
				builder.append(results[index] +  NEW_LINE);
			}
			tasks = builder.toString();
		}
		this.status = IN_PROGRESS;
		this.start = getTime();
	}

	private String getTime() {
		final Date date = new Date();
		return new SimpleDateFormat(FORMAT).format(date);
	}

	public void markComplete(){
		this.setStatus(COMPLETE);
	}

	public void markEndTime(){
		this.setEnd(getTime());
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getTasks() {
		return tasks;
	}

	public void setTasks(final String tasks) {
		this.tasks = tasks;
	}

	public String getStart() {
		return start;
	}

	public void setStart(final String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(final String end) {
		this.end = end;
	}


	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final int status) {
		this.status = status;
	}

	/**
	 * @return the tableId
	 */
	public int getTableId() {
		return tableId;
	}

	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(final int tableId) {
		this.tableId = tableId;
	}

}
