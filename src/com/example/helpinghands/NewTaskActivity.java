package com.example.helpinghands;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class NewTaskActivity extends Activity {
	private String array_spinner[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_task);
		DatePicker date = (DatePicker)findViewById(R.id.dpDate);
		date.setCalendarViewShown(false);
		array_spinner=new String[5];
        array_spinner[0]="5 minutes";
        array_spinner[1]="10 minutes";
        array_spinner[2]="15 minutes";
        array_spinner[3]="20 minutes";
        array_spinner[4]="25 minutes";
        Spinner s = (Spinner) findViewById(R.id.reminder);
        ArrayAdapter adapter = new ArrayAdapter(this,
        android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_task, menu);
		return true;
	}
	
	public void sendMessage(View view) {
    	Intent intent = new Intent(this, RoutineTasks.class);
        EditText editText1 = (EditText) findViewById(R.id.etName);
        EditText editText2 = (EditText) findViewById(R.id.etDescription);
        TimePicker tp = (TimePicker) findViewById(R.id.tpTime);
        tp.clearFocus();
        DatePicker dp = (DatePicker) findViewById(R.id.dpDate);
        dp.clearFocus();
        Spinner sp = (Spinner) findViewById(R.id.reminder);
        sp.clearFocus();
        String[] str = new String[]{editText1.getText().toString(),editText2.getText().toString(),tp.getCurrentHour().toString()+":"+tp.getCurrentMinute().toString(),dp.getDayOfMonth()+"-"+dp.getMonth()+"-"+dp.getYear(),sp.getSelectedItem().toString()};
        intent.putExtra("DATA", str);
        startActivity(intent);
}
}
