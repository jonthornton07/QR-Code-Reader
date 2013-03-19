package com.thornton.k3spring;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
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
				//results.setText(contents);
				final Task newTask = new Task(contents);
				newTask.toString();

				final DatabaseHelper helper = new DatabaseHelper(this);
				final List<Task> tasks = helper.getTasksFromId(newTask.getId());
				if(tasks.isEmpty()){
					helper.addTask(newTask);
				}else{
					for(final Task task : tasks){
						task.markComplete();
						helper.updateAllTasksToComplete(task);
					}
				}
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
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
