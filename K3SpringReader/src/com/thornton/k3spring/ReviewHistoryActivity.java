package com.thornton.k3spring;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Review history activity.  This is shown when the history button is clicked.
 * @author JT
 *
 */
public class ReviewHistoryActivity extends Activity{

	/**List of tasks for the day*/
	private List<Task> tasks;

	/**
	 * Create the activity.  Part of the activity lifecycle
	 * @param savedInstanceState - saved state of the application is null unless the activity is
	 * brought from the background
	 */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_history_activity);

		final DatabaseHelper helper = new DatabaseHelper(this);
		final String format = "MM/dd/yyyy";
		final Date date = new Date();
		tasks = helper.getAllTasksByDay(new SimpleDateFormat(format).format(date));
		final TextView content = (TextView) findViewById(R.id.content);
		content.setText(createHistoryString());
	}

	/**
	 * Create the history String used to show the history as well as send the email
	 * @return the string of history
	 */
	private String createHistoryString(){
		final StringBuilder builder = new StringBuilder();
		final String format = "MM/dd/yyyy";
		final Date date = new Date();
		builder.append(SharedPreferencesWrapper.getString(this, SharedPreferencesWrapper.NAME_KEY) + " " + new SimpleDateFormat(format).format(date));
		builder.append("\n");
		builder.append("\n");
		for(int i = (tasks.size()-1); i >= 0; i--){
			final Task task = tasks.get(i);
			builder.append(task.getStart() + " Start" + "\n");
			builder.append(task.getId() + "\n" + task.getTasks() + "\n");
			if(null != task.getEnd()){
				builder.append(task.getEnd());
				if(Task.COMPLETE == task.getStatus()){
					builder.append(" Completed" + "\n");
				}else{
					builder.append(" Stop" + "\n");
				}
				builder.append("\n");
			}
		}


		return builder.toString();
	}

	/**
	 * Method used to send an email. This is called from the XML layout android:onClick of the send report button.
	 * @param v - view that was clicked on.
	 */
	public void sendEmail(final View v){
		final Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		final String format = "MM/dd/yyyy";
		final Date date = new Date();
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"kankakeespring@gmail.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, SharedPreferencesWrapper.getString(this, SharedPreferencesWrapper.NAME_KEY) + " " + new SimpleDateFormat(format).format(date));
		i.putExtra(Intent.EXTRA_TEXT   , createHistoryString());
		startActivity(i);
	}

}
