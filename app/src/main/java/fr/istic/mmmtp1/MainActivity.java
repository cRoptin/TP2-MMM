package fr.istic.mmmtp1;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button oBtnValid = (Button) findViewById(R.id.btnValid);
        oBtnValid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View poView) {
                EditText sName = (EditText) findViewById(R.id.txtName);
                EditText dDate = (EditText) findViewById(R.id.txtDate);
                EditText sCity = (EditText) findViewById(R.id.txtCity);
                String txtToast = sName.getText() + " né(e) le " + dDate.getText() + " à " + sCity.getText();
                Toast.makeText(getApplicationContext(), txtToast, Toast.LENGTH_SHORT).show();

                Intent display = new Intent(MainActivity.this, AddClient.class);

                ContentValues values = new ContentValues();
                values.put(ClientDB.KEY_NAME, sName.getText().toString());
                values.put(ClientDB.KEY_DATE, dDate.getText().toString());
                values.put(ClientDB.KEY_CITY, sCity.getText().toString());
                getContentResolver().insert(DBContentProvider.CONTENT_URI, values);

                startActivity(display);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_options, menu);
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
        } else if (id == R.id.init_form) {
            EditText oNameVal = (EditText) findViewById(R.id.txtName);
            oNameVal.setText("");
            EditText oDateVal = (EditText) findViewById(R.id.txtDate);
            oDateVal.setText("");
            EditText oCityVal = (EditText) findViewById(R.id.txtCity);
            oCityVal.setText("");
        } else if (id == R.id.add_number) {
            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
            EditText txtNumber = new EditText(getApplicationContext());
            txtNumber.setText("00-00-00-00-00");
            mainLayout.addView(txtNumber);
            item.setEnabled(false);
        } else if (id == R.id.wikigo) {
            EditText oCityVal = (EditText) findViewById(R.id.txtCity);
            Intent oWiki = new Intent(Intent.ACTION_VIEW, Uri.parse("http://fr.wikipedia.org/wiki/" + oCityVal.getText()));
            startActivity(oWiki);
        }

        return super.onOptionsItemSelected(item);
    }

}
