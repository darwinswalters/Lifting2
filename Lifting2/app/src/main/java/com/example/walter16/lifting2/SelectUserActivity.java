package com.example.walter16.lifting2;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectUserActivity extends AppCompatActivity {

    private ArrayList<String> userStringList;

    private ArrayAdapter<String> userListViewAdapter;

    private Context selectUserActivityContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        // initialize the list
        userStringList = new ArrayList<String>();
        selectUserActivityContext = SelectUserActivity.this;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Adds new user to the list
    public void addNewUser(View view){


        EditText newUserEditText = (EditText) findViewById(R.id.new_user_edit_text);
        String newUser = newUserEditText.getText().toString();
        // ADD FUNCTIONALITY TO REMIND USER THAT FIELD IS BLANK IF BLANK
        UpdateUserListTask updateUserListTask = new UpdateUserListTask();
        updateUserListTask.execute(newUser);
    }

    protected class UpdateUserListTask extends AsyncTask<String, Void, ArrayList<String>> {

        // add user to the app.  This includes:
//           1. adding name to list
//           2. adding data structure to hold user data
        @Override
        protected ArrayList<String> doInBackground(String...newUser) {

            // update list
            userStringList.add(newUser[0]);
            return userStringList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result){
            // update the listview
            userListViewAdapter = new ArrayAdapter<String>(selectUserActivityContext,
                    R.layout.list_item_select_user, R.id.user_name_text, result);

            //userListViewAdapter.add(result);

            ListView userListView = (ListView) findViewById(R.id.user_listview);
            userListView.setAdapter(userListViewAdapter);

        }
    }
}
