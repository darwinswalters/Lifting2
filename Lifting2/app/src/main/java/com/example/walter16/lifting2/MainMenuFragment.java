package com.example.walter16.lifting2;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.example.walter16.lifting2.R.id.list_item_main_menu_textview;


public class MainMenuFragment extends Fragment {

    private ListView mainMenuListView;



    private String[] mainMenuListItemText = {
      "Cycle Calculator"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }



    private void createMainMenuList() {

        ArrayAdapter<String> mainMenuAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_main_menu, list_item_main_menu_textview);

        for(String currentMainMenuItem : mainMenuListItemText)
            mainMenuAdapter.add(currentMainMenuItem);


        ListView mainMenuListView = (ListView) getActivity().findViewById(R.id.main_menu_list_view);
        mainMenuListView.setAdapter(mainMenuAdapter);

        mainMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent cycleCalcIntent = new Intent(getActivity(), CalculateCycleActivity.class);
                startActivity(cycleCalcIntent);

            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LoadMainMenu loadMainMenu = new LoadMainMenu();
        loadMainMenu.execute();

        mainMenuListView = (ListView) getActivity().findViewById(R.id.main_menu_list_view);

        // Listen for when user clicks on cycleCalculator text view

        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    private class LoadMainMenu extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void...params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            createMainMenuList();

        }
    }



}
