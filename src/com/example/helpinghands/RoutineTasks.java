package com.example.helpinghands;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.data.RoutineData;

public class RoutineTasks extends Activity {

	static RoutineData r = null;
	String[] arrstring = null;
	String[] titlestring = null;
	int length = 0;
	RoutineData head;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent = getIntent();
		if(intent.getStringArrayExtra("UPDATE")!=null)
		{
			String deleter = intent.getStringExtra("deleter");
			RoutineData temp = r;
			if(temp.getName().equals(deleter))
			{
				if(temp.next==null)
				{
					r = null;
				}
				else
				{
					r = temp.next;
				}
			}
			else
			{
			RoutineData head = r; 
			while(!temp.next.getName().equals(deleter))
			{
				temp = temp.next;
			}
			if(temp.next.next!=null)
			temp.next = temp.next.next;
			else
			{
				temp.next = null;
			}
			r = head;
			}
			RoutineData r1 = new RoutineData();
			arrstring = intent.getStringArrayExtra("UPDATE");
			r1.setName(arrstring[0]);
			r1.setDesc(arrstring[1]);
			r1.setTime(arrstring[2]);
			r1.setDate(arrstring[3]);
			r1.setReminder(arrstring[4]);
			if (r == null) {
				r = r1;
				r.next = null;
				length++;
			} else {
				head = r;
				length++;
				while (r.next != null) {
					r = r.next;
					length++;
				}
				length++;
				r.next = r1;
				r1.next = null;
				r = head;
			}
		}
		if (intent.getStringArrayExtra("DATA") != null) {
			RoutineData r1 = new RoutineData();
			arrstring = intent.getStringArrayExtra("DATA");
			r1.setName(arrstring[0]);
			r1.setDesc(arrstring[1]);
			r1.setTime(arrstring[2]);
			r1.setDate(arrstring[3]);
			r1.setReminder(arrstring[4]);
			if (r == null) {
				r = r1;
				r.next = null;
				length++;
			} else {
				head = r;
				length++;
				while (r.next != null) {
					r = r.next;
					length++;
				}
				length++;
				r.next = r1;
				r1.next = null;
				r = head;
			}
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_routine_tasks);
		ListView listView = (ListView) findViewById(R.id.listview);
		int j = 0;
		RoutineData temp = r;
		if (temp != null) {
			titlestring = new String[length];
			while (temp != null) {
				titlestring[j] = temp.getName();
				temp = temp.next;
				j++;
			}
		}
		if (titlestring != null) {
			ArrayAdapter adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, titlestring);
			listView.setAdapter(adapter);
		}

		OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				Intent i = new Intent(RoutineTasks.this, ViewActivity.class);
				String item = (String) parent.getItemAtPosition(position);
				RoutineData head = r;
				while(!r.getName().equals(item))
				{
					r=r.next;
				}
				String[] temp = new String[5];
				temp[0] = r.getName();
				temp[1] = r.getDesc();
				temp[2] = r.getTime();
				temp[3] = r.getDate();
				temp[4] = r.getReminder();
				r = head;
				i.putExtra("Data2",temp );
				startActivity(i);
			}
		};

		listView.setOnItemClickListener(mMessageClickedHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.routine_tasks, menu);
		return true;
	}

	public void sendMessage(View view) {
		Intent intent = new Intent(this, NewTaskActivity.class);
		// EditText editText = (EditText) findViewById(R.id.edit_message);
		// String message = editText.getText().toString();
		startActivity(intent);
	}

}
