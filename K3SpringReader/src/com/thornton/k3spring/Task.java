package com.thornton.k3spring;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Task item.  Associated with the QR code and contains all the details of the QR Code
 * @author JT
 *
 */
public class Task implements Serializable{

	/**Unique id of the object*/
	private static final long serialVersionUID = -5104294068493842844L;

	/**Format of dates going into the database*/
	private static final String FORMAT = "MM/dd/yyyy hh:mm a";

	/**String to add new line characters*/
	private static final String NEW_LINE = "\n";

	/**Int for the value of the task not being started*/
	public static final int NOT_STARTED = 0;

	/**Int for the value of the task being complete*/
	public static final int COMPLETE = 2;

	/**Int for the value of the task being in progress*/
	public static final int IN_PROGRESS = 1;

	/**Id of the task*/
	private String id;

	/**Description of the task*/
	private String tasks;

	/**Start time of the task*/
	private String start;

	/**End time of the task*/
	private String end;

	/**Status of the task*/
	private int status;

	/**Table id of the task*/
	private int tableId;

	/**
	 * Constructor for the task
	 * @param tableId - table id of the task
	 */
	public Task(final int tableId){
		this.setTableId(tableId);
	}

	/**
	 * Constructor  for the task
	 * @param taskString - string to create the task from
	 */
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

	/**
	 * 
	 * @return the time
	 */
	private String getTime() {
		final Date date = new Date();
		return new SimpleDateFormat(FORMAT).format(date);
	}

	/**
	 * Mark the task complete
	 */
	public void markComplete(){
		this.setStatus(COMPLETE);
	}

	/**
	 * Set the end time
	 */
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
