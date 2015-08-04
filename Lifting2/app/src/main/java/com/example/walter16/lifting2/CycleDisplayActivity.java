package com.example.walter16.lifting2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class CycleDisplayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // removed for current iteration of project
        setContentView(R.layout.activity_cycle_display);

        Intent oneRMIntent = getIntent();
        Double oneRM = oneRMIntent.getDoubleExtra(MainActivity.ONERM_DOUBLE, 0.0);

//        LiftCalculations liftCalculations = new LiftCalculations();
//        Double[] liftArray = new Double[12];
//        liftCalculations.cycleSets(oneRM, liftArray);

//        ArrayList<String> cycleList = new ArrayList<>();
//        cycleList.add("A");
//        cycleList.add("B");
//        cycleList.add("Fuck this");
//        populateList(cycleList, liftArray);

        String[] forecastMockList = { "Today - Sunny - 88/63",
                "Tomorrow - Sunny - 88/63",
                "7/27 - Sunny - 88/63",
                "7/28 - Sunny - 88/63",
                "7/29 - Sunny - 88/63",
                "7/30 - Sunny - 88/63",
                "7/31 - Sunny - 88/63",};

        ArrayAdapter<String> cycleAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_cycle_display, R.id.cycleListView, forecastMockList);


        ListView cycleListView = (ListView) this.findViewById(R.id.cycleListView);
        //cycleListView.setAdapter(cycleAdapter);
        //String oneRMString = oneRM.toString();

//        TextView cycleTextView = new TextView(this);
//        cycleTextView.setTextSize(20);
//        cycleTextView.setText((CharSequence) cycleList);

        setContentView(cycleListView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cycle_display, menu);
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


}
