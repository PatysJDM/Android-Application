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


public class Zorny_uhel extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zorny_uhel);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zorny_uhel, menu);
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
        private Double sensorWidth = null;
        private Double sensorHeight = null;
        private Double sensorDiagonal = null;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_zorny_uhel, container, false);

            Spinner spinner = (Spinner)rootView.findViewById(R.id.spinner_senzory);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.senzory, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);

            button = (Button) rootView.findViewById(R.id.button1);
            button.setOnClickListener(this);

            return rootView;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Spinner spinner = (Spinner) parent;

            switch (spinner.getId()) {
                case R.id.spinner_senzory:
                    switch (pos){
                        case 0:
                            sensorWidth = 23.0;
                            sensorHeight = 15.0;
                            sensorDiagonal = 27.46;
                            break;
                        case 1:
                            sensorWidth = 17.3;
                            sensorHeight = 13.0;
                            sensorDiagonal = 21.64;
                            break;
                        case 2:
                            sensorWidth = 36.0;
                            sensorHeight = 24.0;
                            sensorDiagonal = 43.27;
                            break;
                    }
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button1:
                    final EditText ohnisko_text = (EditText) rootView.findViewById(R.id.ohnisko_uhel);
                    final TextView horizontal = (TextView) rootView.findViewById(R.id.horizontal);
                    final TextView vertical = (TextView) rootView.findViewById(R.id.vertical);
                    final TextView diagonal = (TextView) rootView.findViewById(R.id.diagonal);

                    if (!ohnisko_text.getText().toString().matches("") && Integer.parseInt(ohnisko_text.getText().toString()) != 0){
                        Integer ohnisko = Integer.parseInt(ohnisko_text.getText().toString());
                        /* Výpočet horizontálního zorného úhlu */
                        Double uhel_h = 2 * Math.atan(sensorWidth / (2 * ohnisko));
                        uhel_h = Math.toDegrees(uhel_h);
                        uhel_h = (double) Math.round(uhel_h * 100.0) / 100.0;

                        /* Výpočet vertikálního zorného úhlu */
                        Double uhel_v = 2 * Math.atan(sensorHeight / (2 * ohnisko));
                        uhel_v = Math.toDegrees(uhel_v);
                        uhel_v = (double) Math.round(uhel_v * 100.0) / 100.0;

                        /* Výpočet diagonálního zorného úhlu */
                        Double uhel_d = 2 * Math.atan(sensorDiagonal / (2 * ohnisko));
                        uhel_d = Math.toDegrees(uhel_d);
                        uhel_d = (double) Math.round(uhel_d * 100.0) / 100.0;

                        horizontal.setText("Horizontální zorný úhel: " + uhel_h.toString() + "°");
                        vertical.setText("Vertikální zorný úhel: " + uhel_v.toString() + "°");
                        diagonal.setText("Diagonální zorný úhel: " + uhel_d.toString() + "°");
                    }

                    break;
            }
        }
    }
}
