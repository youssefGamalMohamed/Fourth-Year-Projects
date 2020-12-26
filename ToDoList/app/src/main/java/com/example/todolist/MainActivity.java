package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView mylist = (ListView)findViewById(R.id.mylistview1);
        final ArrayAdapter<String> array_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        mylist.setAdapter(array_adapter);
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed = (EditText)findViewById(R.id.ed1);
                String value = ed.getText().toString();
                if(!value.equals("")) {
                    array_adapter.add(value);
                    ed.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(),"No Item Founded",Toast.LENGTH_LONG).show();
                }
            }
        });

        mylist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtv = (TextView)view;
                array_adapter.remove(txtv.getText().toString());
                array_adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}