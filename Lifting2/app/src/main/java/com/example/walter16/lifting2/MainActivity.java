package com.example.walter16.lifting2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.walter16.lifting2.R.id.list_item_cycle_textview;


public class MainActivity extends ActionBarActivity {

    Context context = MainActivity.this;

    public final static String ONERM_DOUBLE = "com.example.walter16.lifting2.double";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] forecastMockList = { "Today - Sunny - 88/63",
//                "Tomorrow - Sunny - 88/63",
//                "7/27 - Sunny - 88/63",
//                "7/28 - Sunny - 88/63",
//                "7/29 - Sunny - 88/63",
//                "7/30 - Sunny - 88/63",
//                "7/31 - Sunny - 88/63",};
//
//        ArrayAdapter<String> cycleAdapter = new ArrayAdapter<String>(this,
//                R.layout.list_item_cycle, R.id.list_item_cycle_textview, forecastMockList);
//
//
//        ListView cycleListView = (ListView) this.findViewById(R.id.mainCycleListView);
//        cycleListView.setAdapter(cycleAdapter);
        //cycleListView.setAdapter(cycleAdapter);
        //String oneRMString = oneRM.toString();

//        TextView cycleTextView = new TextView(this);
//        cycleTextView.setTextSize(20);
//        cycleTextView.setText((CharSequence) cycleList);

        //setContentView(cycleListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void calculateCycle(View view){

        EditText editText = (EditText) findViewById(R.id.editText);
        Double oneRM = Double.parseDouble(editText.getText().toString());
//
//        // Create intent to start new screen
//        Intent cycleIntent = new Intent(this, CycleDisplayActivity.class);
//        cycleIntent.putExtra(ONERM_DOUBLE, oneRM);
//
//        startActivity(cycleIntent);
/*
        String[] forecastMockList = { "Today - Sunny - 88/63",
                "Tomorrow - Sunny - 88/63",
                "7/27 - Sunny - 88/63",
                "7/28 - Sunny - 88/63",
                "7/29 - Sunny - 88/63",
                "7/30 - Sunny - 88/63",
                "7/31 - Sunny - 88/63",};

        ArrayAdapter<String> cycleAdapter = new ArrayAdapter<String>(this,
                R.layout.list_item_cycle, R.id.list_item_cycle_textview, forecastMockList);


        ListView cycleListView = (ListView) this.findViewById(R.id.mainCycleListView);
        //cycleListView.setAdapter(cycleAdapter);
        //String oneRMString = oneRM.toString();

//        TextView cycleTextView = new TextView(this);
//        cycleTextView.setTextSize(20);
//        cycleTextView.setText((CharSequence) cycleList);

        setContentView(cycleListView);*/

        ForecastAsyncTask forecstAsyncTask = new ForecastAsyncTask();
        forecstAsyncTask.execute(oneRM);
    }

    protected class ForecastAsyncTask extends AsyncTask<Double,Void,String[]> {

        @Override
        protected String[] doInBackground(Double...oneRM) {

        LiftCalculations liftCalculations = new LiftCalculations();
        Double[] liftArray = new Double[12];
        liftCalculations.cycleSets(oneRM[0], liftArray);
        String[] cycleStringList = new String[12];

                populateList(cycleStringList, liftArray);
           return cycleStringList;
        }

        @Override
        protected void onPostExecute(String[] result) {

            ArrayAdapter<String> cycleAdapter = new ArrayAdapter<String>(context,
                    R.layout.list_item_cycle, list_item_cycle_textview, result);


            ListView cycleListView = (ListView) findViewById(R.id.mainCycleListView);
            cycleListView.setAdapter(cycleAdapter);
            //cycleListView.setAdapter(cycleAdapter);
            //String oneRMString = oneRM.toString();

//        TextView cycleTextView = new TextView(this);
//        cycleTextView.setTextSize(20);
//        cycleTextView.setText((CharSequence) cycleList);

            //setContentView(cycleListView);

        }

    }

    public void populateList(String[] cycleList, Double[] liftArray) {
        cycleList[0] = ("1 x " + liftArray[0] + " x 5");
        cycleList[1] =("1 x " + liftArray[1] + " x 5");
        cycleList[2] =("1 x " + liftArray[2] + " x 5+");

        cycleList[3] =("1 x " + liftArray[3] + " x 3");
        cycleList[4] =("1 x " + liftArray[4] + " x 3");
        cycleList[5] =("1 x " + liftArray[5] + " x 3+");

        cycleList[6] =("1 x " + liftArray[6] + " x 5");
        cycleList[7] =("1 x " + liftArray[7] + " x 3");
        cycleList[8] =("1 x " + liftArray[8] + " x 1+");

        cycleList[9] =("1 x " + liftArray[9] + " x 5");
        cycleList[10] =("1 x " + liftArray[10] + " x 5");
        cycleList[11] =("1 x " + liftArray[11] + " x 5");
    }
}
