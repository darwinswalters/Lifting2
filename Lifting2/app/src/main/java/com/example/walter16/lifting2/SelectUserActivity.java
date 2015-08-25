package com.example.walter16.lifting2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class SelectUserActivity extends AppCompatActivity {

    private ArrayList<String> userStringList;

    private ArrayAdapter<String> userListViewAdapter;

    private Context selectUserActivityContext;

    private EditText newUserEditText;

    private InputMethodManager imm;

    private  SharedPreferences userListSharedPreference;

    private static final String SHARED_PREFERENCES_FILE_NAME = "userSharedPreferences";

    private ListView userListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        // initialize the list
        userStringList = new ArrayList<String>();
        selectUserActivityContext = SelectUserActivity.this;
        newUserEditText = (EditText) findViewById(R.id.new_user_edit_text);

        imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

        userListView = (ListView) findViewById(R.id.user_listview);


        // initialize SharedPreferences
        userListSharedPreference = getSharedPreferences(SHARED_PREFERENCES_FILE_NAME
                ,MODE_PRIVATE);

        loadExistingUsers();


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



        String newUser = newUserEditText.getText().toString();
        // ADD FUNCTIONALITY TO REMIND USER THAT FIELD IS BLANK IF BLANK

        // hide the keyboard
        imm.hideSoftInputFromWindow(newUserEditText.getWindowToken(), 0);

        UpdateUserListTask updateUserListTask = new UpdateUserListTask();
        updateUserListTask.execute(newUser);
    }

    protected class UpdateUserListTask extends AsyncTask<String, Void, String> {

        // add user to the app.  This includes:
//           1. adding name to list
//           2. adding data structure to hold user data
        @Override
        protected String doInBackground(String... newUser) {

            // add user, user key pair entry to userListSharedPreferences

            return newUser[0];
        }

            // update list
//            userStringList.add(newUser[0]);
//            return userStringList;


        @Override
        protected void onPostExecute(String newUser){
//            // update the listview
//            userListViewAdapter = new ArrayAdapter<String>(selectUserActivityContext,
//                    R.layout.list_item_select_user, R.id.user_name_text, result);
//
//            //userListViewAdapter.add(result);
//
//            ListView userListView = (ListView) findViewById(R.id.user_listview);
//            userListView.setAdapter(userListViewAdapter);
//
//            //Clear editText
//            newUserEditText.setText("");
            SharedPreferences.Editor newUserEditor = userListSharedPreference.edit();
            newUserEditor.putString(newUser, newUser);
            newUserEditor.commit();
            loadExistingUsers();

        }
    }

    // start an activity for a specific user if a name on the list is clicked
    // we will have to retrieve the name from sharedpreferences or the text



    private void setUserListView(ListView userListView, ArrayAdapter<String> userListViewAdapter,
                                 int list_item_select_user, int user_name_text, ArrayList<String> userArrayList) {

        userListViewAdapter = new ArrayAdapter<String>(selectUserActivityContext,
                R.layout.list_item_select_user, R.id.user_name_text, userArrayList);

        userListView.setAdapter(userListViewAdapter);

        //Clear editText
        newUserEditText.setText("");

    }

    // Loads the listView with existing user names from sharedPreferences file
    private void loadExistingUsers() {

        Map<String, String> userMap = (Map<String,String>) userListSharedPreference.getAll();

        if (userMap.size() == 0) {
            return;
        }

        ArrayList<String> userArrayList = new ArrayList<String>();
        // iterate through list and populate the fields
        for (Map.Entry<String,String>currentEntry : userMap.entrySet()) {
            userArrayList.add(currentEntry.getValue());
        }

        // update the UserList

        setUserListView(userListView, userListViewAdapter, R.layout.list_item_select_user,
                R.id.user_name_text, userArrayList);

    }
}
