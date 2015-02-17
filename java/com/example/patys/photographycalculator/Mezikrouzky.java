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


public class Mezikrouzky extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mezikrouzky);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mezikrouzky, menu);
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
            rootView = inflater.inflate(R.layout.fragment_mezikrouzky, container, false);

            button = (Button) rootView.findViewById(R.id.button1);
            button.setOnClickListener(this);

            return rootView;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button1:
                    final EditText ohnisko_text = (EditText) rootView.findViewById(R.id.ohnisko_mez);
                    final EditText min_vzd_text = (EditText) rootView.findViewById(R.id.min_vzd);
                    final EditText mezikrouzek_text = (EditText) rootView.findViewById(R.id.tl_mezikrouzku);
                    final TextView min_mez_text = (TextView) rootView.findViewById(R.id.min_vzd_mez);
                    final TextView max_mez_text = (TextView) rootView.findViewById(R.id.max_vzd_mez);
                    boolean kontrola = true;

                    if (ohnisko_text.getText().toString().matches("") || Integer.parseInt(ohnisko_text.getText().toString()) == 0) kontrola = false;
                    else if (min_vzd_text.getText().toString().matches("")) kontrola = false;
                    else if (mezikrouzek_text.getText().toString().matches("")) kontrola = false;

                    if (kontrola){
                        Double mezikrouzek = Double.parseDouble(mezikrouzek_text.getText().toString());
                        Integer ohnisko = Integer.parseInt(ohnisko_text.getText().toString());
                        Integer min_vzd = Integer.parseInt(min_vzd_text.getText().toString());

                        if (mezikrouzek > 0.0){
                            Double z = (double) ohnisko * ohnisko / (min_vzd - ohnisko);

                            Double min_vzd_mez = (ohnisko * ohnisko / (mezikrouzek + z)) + ohnisko;
                            min_vzd_mez = (double) Math.round(min_vzd_mez);
                            Double max_vzd_mez = (double) ((ohnisko * ohnisko) / mezikrouzek) + ohnisko;
                            min_vzd_mez = (double) Math.round(min_vzd_mez);

                            Integer min_vzd_int = min_vzd_mez.intValue();
                            Integer max_vzd_int = max_vzd_mez.intValue();

                            min_mez_text.setText("Minimální ostřící vzdálenost: " + min_vzd_int.toString() + " mm");
                            max_mez_text.setText("Maximální ostřící vzdálenost: " + max_vzd_int.toString() + " mm");

                        }else{
                            min_mez_text.setText("Minimální ostřící vzdálenost: " + min_vzd.toString() + " mm");
                            max_mez_text.setText("Maximální ostřící vzdálenost: nekonečno");
                        }

                    }

                    break;
            }
        }
    }
}
