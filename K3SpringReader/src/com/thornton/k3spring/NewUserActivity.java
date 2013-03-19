package com.thornton.k3spring;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


public class NewUserActivity extends Activity{

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_user_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_launcher, menu);
		return true;
	}

	public void save(final View v){
		final EditText name = (EditText) findViewById(R.id.name);
		SharedPreferencesWrapper.saveString(this, SharedPreferencesWrapper.NAME_KEY, name.getText().toString());
		this.finish();
	}
}
