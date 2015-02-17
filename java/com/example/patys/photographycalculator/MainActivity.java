package com.example.patys.photographycalculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener{

        View rootView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);

            Button btn_hloubka = (Button)rootView.findViewById(R.id.btn_hloubka);
            btn_hloubka.setOnClickListener(this);

            Button btn_uhel = (Button)rootView.findViewById(R.id.btn_uhel);
            btn_uhel.setOnClickListener(this);

            Button btn_mezikrouzky = (Button)rootView.findViewById(R.id.btn_mezikrouzky);
            btn_mezikrouzky.setOnClickListener(this);

            Button btn_blesk = (Button)rootView.findViewById(R.id.btn_blesk);
            btn_blesk.setOnClickListener(this);

            return rootView;
        }

        @Override
        public void onClick(View v) {
            Intent intent;

            switch (v.getId())
            {
                case R.id.btn_hloubka:
                    intent = new Intent(v.getContext(), Hloubka_ostrosti.class);
                    startActivity(intent);
                    break;
                case R.id.btn_uhel:
                    intent = new Intent(v.getContext(), Zorny_uhel.class);
                    startActivity(intent);
                    break;
                case R.id.btn_mezikrouzky:
                    intent = new Intent(v.getContext(), Mezikrouzky.class);
                    startActivity(intent);
                    break;
                case R.id.btn_blesk:
                    intent = new Intent(v.getContext(), Blesk.class);
                    startActivity(intent);
                    break;
            }
        }

    }
}
