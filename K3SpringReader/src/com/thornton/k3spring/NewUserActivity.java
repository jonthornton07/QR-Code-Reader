package com.thornton.k3spring;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Activity launched when the application does not have a saved user
 * @author JT
 *
 */
public class NewUserActivity extends Activity{

	/**
	 * Create the activity.  Part of the activity lifecycle
	 * @param savedInstanceState - saved state of the application is null unless the activity is
	 * brought from the background
	 */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_user_activity);
	}

	/**
	 * Save the user id entered in the text box
	 * @param v - vied calling this method (save button in the layout
	 */
	public void save(final View v){
		final EditText name = (EditText) findViewById(R.id.name);
		SharedPreferencesWrapper.saveString(this, SharedPreferencesWrapper.NAME_KEY, name.getText().toString());
		this.finish();
	}
}
