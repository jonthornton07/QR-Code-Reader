package com.thornton.k3spring;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class LauncherActivity extends Activity{

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_launcher, menu);
		return true;
	}

	public void scan(final View v){
		final Intent i = new Intent(this, ScannerActivity.class);
		this.startActivity(i);
	}
}
