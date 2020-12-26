package com.example.employeeassignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        Button btn = (Button)(findViewById(R.id.startactivity_searchbutton));
        EditText ed = (EditText)(findViewById(R.id.startactivity_ed1));
        MyDatabaseClass dbobj = new MyDatabaseClass(getApplicationContext());


        ListView lv = (ListView)(findViewById(R.id.listview1));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        lv.setAdapter(arrayAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed.getText().toString();
                Cursor c = dbobj.GetAllEmployeesContainsThisName(name);
                while(!c.isAfterLast())
                {
                   arrayAdapter.add(c.getString(1));
                    c.moveToNext();
                }
            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String name = ed.getText().toString();
                Cursor c = dbobj.GetAllEmployeesContainsThisName(name);
                int x = 0;
                Employee e = new Employee();
                while(!c.isAfterLast())
                {
                    if(x == position)
                    {
                        e.Name = c.getString(1);
                        e.Title = c.getString(2);
                        e.Phone = c.getString(3);
                        e.Email = c.getString(4);

                    }
                    x++;
                    c.moveToNext();
                }


                ArrayList<Employee> aa = new ArrayList<Employee>();
                aa.add(e);
                Bundle b = new Bundle();
                b.putSerializable("myemployee" , aa);
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtras(b);
                startActivity(i);

                return true;
            }
        });

    }
}