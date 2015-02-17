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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Blesk extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blesk);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blesk, menu);
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
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {

        View rootView;
        Button button;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_blesk, container, false);

            button = (Button) rootView.findViewById(R.id.button1);
            button.setOnClickListener(this);
            return rootView;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button1:
                    final EditText smerove_text = (EditText) rootView.findViewById(R.id.smerovecislo);
                    final EditText iso_text = (EditText) rootView.findViewById(R.id.iso);
                    final EditText clona_text = (EditText) rootView.findViewById(R.id.clona);
                    final TextView dosah_text = (TextView) rootView.findViewById(R.id.dosah);
                    boolean kontrola = true;

                    if (smerove_text.getText().toString().matches("") || Integer.parseInt(smerove_text.getText().toString()) == 0) kontrola = false;
                    else if (iso_text.getText().toString().matches("") || Integer.parseInt(iso_text.getText().toString()) == 0) kontrola = false;
                    else if (clona_text.getText().toString().matches("") || Double.parseDouble(clona_text.getText().toString()) == 0.0) kontrola = false;

                    if (kontrola) {
                        Double clona = Double.parseDouble(clona_text.getText().toString());
                        Integer smerove = Integer.parseInt(smerove_text.getText().toString());
                        Integer iso = Integer.parseInt(iso_text.getText().toString());

                        Double dosah = smerove/clona*(Math.sqrt(iso/100));
                        dosah = (double) Math.round(dosah * 100)/100;

                        dosah_text.setText("Dosah blesku: " + dosah.toString() + " m");
                    }

                    break;
            }
        }
    }
}
