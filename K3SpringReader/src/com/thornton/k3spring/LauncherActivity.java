package com.thornton.k3spring;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LauncherActivity extends Activity{

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
	}

	@Override
	public void onResume(){
		super.onResume();
		if(SharedPreferencesWrapper.getString(this, SharedPreferencesWrapper.NAME_KEY).equals(SharedPreferencesWrapper.DEFAULT_STRING)){
			launchNewUserActivity();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_launcher, menu);
		return true;
	}

	private void launchNewUserActivity(){
		final Intent intent = new Intent(this, NewUserActivity.class);
		this.startActivity(intent);
	}

	public void scan(final View v){
		final Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");//for Qr code, its "QR_CODE_MODE" instead of "PRODUCT_MODE"
		intent.putExtra("SAVE_HISTORY", false);//this stops saving ur barcode in barcode scanner app's history
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				final String contents = data.getStringExtra("SCAN_RESULT"); //this is the result
				final Task newTask = new Task(contents);
				newTask.toString();

				final DatabaseHelper helper = new DatabaseHelper(this);
				final List<Task> tasks = helper.getTasksFromId(newTask.getId());
				if(tasks.isEmpty()){
					helper.addTask(newTask);
				}else{
					newTask.markComplete();
					for(final Task task : tasks){
						task.markComplete();
						helper.updateAllTasksToComplete(task);
					}
				}
				updateTaskUI(newTask);
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}

	private void updateTaskUI(final Task task){
		final TextView status = (TextView) findViewById(R.id.task_status);
		final TextView statusLabel = (TextView) findViewById(R.id.task_status_label);
		final TextView desc = (TextView) findViewById(R.id.task_desc);
		final TextView descLabel = (TextView) findViewById(R.id.task_desc_label);
		final TextView start = (TextView) findViewById(R.id.task_start);
		final TextView startLabel = (TextView) findViewById(R.id.task_start_label);
		final TextView end = (TextView) findViewById(R.id.task_end);
		final TextView endLabel = (TextView) findViewById(R.id.task_end_label);
		findViewById(R.id.no_tasks_started).setVisibility(View.GONE);
		status.setVisibility(View.VISIBLE);
		desc.setVisibility(View.VISIBLE);
		start.setVisibility(View.VISIBLE);
		statusLabel.setVisibility(View.VISIBLE);
		descLabel.setVisibility(View.VISIBLE);
		startLabel.setVisibility(View.VISIBLE);

		if(task.getStatus() == Task.IN_PROGRESS){
			status.setText(getString(R.string.in_progress));
			endLabel.setVisibility(View.GONE);
			end.setVisibility(View.GONE);
		}else{
			status.setText(getString(R.string.completed));
			endLabel.setVisibility(View.VISIBLE);
			end.setVisibility(View.VISIBLE);
			end.setText(task.getEnd());
		}
		desc.setText(task.getTasks());
		start.setText(task.getStart());
	}

	private void launchScannerMarket(){
		final Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
		final Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
		startActivity(marketIntent);
	}

	public void viewHistory(final View v){
		Toast.makeText(this, "History is uunder development", 2000).show();
	}
}
