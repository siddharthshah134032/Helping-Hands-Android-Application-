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
import android.widget.TextView;
import android.widget.TimePicker;

public class ViewActivity extends Activity {
	private String array_spinner[];
	String deleter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
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
        Intent i = getIntent();
        String[] strr = i.getStringArrayExtra("Data2");
        EditText tex1 = (EditText)findViewById(R.id.etName);
        deleter = strr[0];
        tex1.setText(strr[0], TextView.BufferType.EDITABLE);
        EditText tex2 = (EditText)findViewById(R.id.etDescription);
        tex2.setText(strr[1],TextView.BufferType.EDITABLE);
        TimePicker tp = (TimePicker)findViewById(R.id.tpTime);
        String[] arr=strr[2].split(":");
        tp.setCurrentHour(Integer.parseInt(arr[0]));
        tp.setCurrentMinute(Integer.parseInt(arr[1]));
        arr = strr[3].split("-");
        DatePicker dp = (DatePicker)findViewById(R.id.dpDate);
        dp.updateDate(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]), Integer.parseInt(arr[0]));
       if(strr[4].equals("5 minutes"))
        s.setSelection(0);
       if(strr[4].equals("10 minutes"))
           s.setSelection(1);
       if(strr[4].equals("15 minutes"))
           s.setSelection(2);
       if(strr[4].equals("20 minutes"))
           s.setSelection(3);
       if(strr[4].equals("25 minutes"))
           s.setSelection(4);
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view, menu);
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
        intent.putExtra("UPDATE", str);
        intent.putExtra("deleter", deleter);
        startActivity(intent);
	}

}
