package com.example.patys.photographycalculator;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class Hloubka_ostrosti extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hloubka_ostrosti);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hloubka_ostrosti, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

        View rootView;
        Button button;
        private Double selectedSensor = null;
        private Integer selectedUnit = null;
        private String unit = null;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_hloubka_ostrosti, container, false);

            Spinner spinner = (Spinner)rootView.findViewById(R.id.spinner1);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.senzory, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
            spinner.setSelection(0);

            Spinner spinner_units = (Spinner)rootView.findViewById(R.id.spinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter_units = ArrayAdapter.createFromResource(rootView.getContext(), R.array.jednotky, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter_units.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner_units.setAdapter(adapter_units);
            spinner_units.setOnItemSelectedListener(this);
            spinner_units.setSelection(0);

            button = (Button) rootView.findViewById(R.id.button1);
            button.setOnClickListener(this);

            return rootView;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button1:
                    final EditText ohnisko_text = (EditText) rootView.findViewById(R.id.ohnisko);
                    final EditText clona_text = (EditText) rootView.findViewById(R.id.clona);
                    final EditText vzdalenost_text = (EditText) rootView.findViewById(R.id.vzdalenost);
                    final TextView predni = (TextView) rootView.findViewById(R.id.predni);
                    final TextView zadni = (TextView) rootView.findViewById(R.id.zadni);
                    final TextView hyper = (TextView) rootView.findViewById(R.id.hyper);
                    boolean kontrola = true;

                    if (ohnisko_text.getText().toString().matches("") || Integer.parseInt(ohnisko_text.getText().toString()) == 0) kontrola = false;
                    else if (clona_text.getText().toString().matches("")|| Double.parseDouble(clona_text.getText().toString()) == 0.0) kontrola = false;
                    else if (vzdalenost_text.getText().toString().matches("")|| Integer.parseInt(vzdalenost_text.getText().toString()) == 0) kontrola = false;

                    if (kontrola){
                        Integer ohnisko = Integer.parseInt(ohnisko_text.getText().toString());
                        Double clona = Double.parseDouble(clona_text.getText().toString());
                        Integer vzdalenost = Integer.parseInt(vzdalenost_text.getText().toString());
                        vzdalenost = vzdalenost * selectedUnit;

                        Double konstanta = selectedSensor * clona * vzdalenost;
                        Double predni_HO = vzdalenost - (konstanta * vzdalenost)/(ohnisko * ohnisko + konstanta);
                        Double zadni_HO = vzdalenost + (konstanta * vzdalenost)/(ohnisko * ohnisko - konstanta);

                        predni_HO = predni_HO/selectedUnit;
                        zadni_HO = zadni_HO/selectedUnit;

                        predni_HO = (double) Math.round(predni_HO * 100.0) / 100.0;
                        zadni_HO = (double) Math.round(zadni_HO * 100.0) / 100.0;

                        predni.setText("Přední hloubka ostrosti: " + predni_HO.toString() + " " + unit);

                        if (zadni_HO < 0.0){
                            zadni.setText("Zadní hloubka ostrosti: nekonečno");
                        }else{
                            zadni.setText("Zadní hloubka ostrosti: " + zadni_HO.toString() + " " + unit);
                        }

                        Double hyperfocal = (ohnisko * ohnisko) / (clona * selectedSensor * 1000); // Dělím 1000, abych dostal hodnotu v metrech
                        hyperfocal = (double) Math.round(hyperfocal * 100.0 ) / 100.0; // Zaokrouhluji na dvě desetinná místa
                        hyper.setText("Hyperfokální vzdálenost: " + hyperfocal.toString() + " m");
                    }
                    break;
            }
        }

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Spinner spinner = (Spinner) parent;

            switch (spinner.getId()) {
                case R.id.spinner1:
                    switch (pos){
                        case 0:
                            selectedSensor = 0.0189;
                            break;
                        case 1:
                            selectedSensor = 0.0149;
                            break;
                        case 2:
                            selectedSensor = 0.0297;
                            break;
                    }
                    break;
                case R.id.spinner:
                    switch (pos){
                        case 0:
                            selectedUnit = 1;
                            unit = "mm";
                            break;
                        case 1:
                            selectedUnit = 10;
                            unit = "cm";
                            break;
                        case 2:
                            selectedUnit = 1000;
                            unit = "m";
                            break;
                    }
                    break;
            }
            /*
            switch (pos){
                case 0:
                    selectedSensor = 0.0189;
                    break;
                case 1:
                    selectedSensor = 0.0149;
                    break;
                case 2:
                    selectedSensor = 0.0297;
                    break;
            }*/
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    }


}
