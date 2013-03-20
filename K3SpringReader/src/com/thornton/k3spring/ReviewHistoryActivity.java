package com.thornton.k3spring;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ReviewHistoryActivity extends Activity{

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_history_activity);

		final DatabaseHelper helper = new DatabaseHelper(this);
		final String format = "MM/dd/yyyy";
		final Date date = new Date();
		final List<Task> tasks = helper.getAllTasksByDay(new SimpleDateFormat(format).format(date));
		final TextView content = (TextView) findViewById(R.id.content);
		content.setText(createHistoryString(tasks));
	}

	private String createHistoryString(final List<Task> tasks){
		final StringBuilder builder = new StringBuilder();
		final String format = "MM/dd/yyyy";
		final Date date = new Date();
		builder.append(SharedPreferencesWrapper.getString(this, SharedPreferencesWrapper.NAME_KEY) + " " + new SimpleDateFormat(format).format(date));

		builder.append("\n");
		builder.append("\n");
		for(final Task task : tasks){
			builder.append(task.getStart() + " Start" + "\n");
			builder.append(task.getId() + "\n" + task.getTasks() + "\n");
			builder.append(task.getEnd() + " Stop" + "\n");
			builder.append("\n");
		}


		return builder.toString();
	}

}
