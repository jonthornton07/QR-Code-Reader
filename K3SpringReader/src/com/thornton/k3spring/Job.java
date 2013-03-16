package com.thornton.k3spring;

public class Job {
	
	private String id;
	
	private String[] tasks;
	
	private long start;
	
	private long end;
	
	public Job(String id, String[] tasks, long start){
		this.id = id;
		this.tasks = tasks;
		this.start = start;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getTasks() {
		return tasks;
	}

	public void setTasks(String[] tasks) {
		this.tasks = tasks;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

}
