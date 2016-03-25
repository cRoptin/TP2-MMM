package fr.istic.mmmtp1;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AddClient extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleAdapter moAdapter;
    private ArrayList<HashMap<String, String>> loItems;

    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displayListView();

        /*loItems = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> map;
        map = new HashMap<String, String>();
        map.put("name", "Marco Polo");
        map.put("date", "15/09/1254");
        map.put("city", "Venise");
        loItems.add(map);

        map = new HashMap<String, String>();
        map.put("name", "Christophe Colomb");
        map.put("date", "01/01/1451");
        map.put("city", "Genes");
        loItems.add(map);

        Intent addClient = getIntent();
        moAdapter = new SimpleAdapter(this.getBaseContext(), loItems, R.layout.item,
                new String[] {"name", "date", "city"}, new int[] {R.id.lblName, R.id.lblDate, R.id.lblCity});

        final ListView moClients = (ListView) findViewById(R.id.listView);
        moClients.setAdapter(moAdapter);
        moClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/

        final Button oBtnAdd = (Button) findViewById(R.id.btnAdd);
        oBtnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View poView) {
                Intent display = new Intent(AddClient.this, MainActivity.class);
                startActivity(display);
            }
        });

        final EditText oTxtSearch = (EditText) findViewById(R.id.txtSearch);
        oTxtSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View poView) {
                oTxtSearch.setText("");
            }
        });

        TextWatcher fieldValidatorTextWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Uri uri = Uri.parse(DBContentProvider.CONTENT_URI.toString());
                String sQuery = "";
                String rqName = ((EditText) findViewById(R.id.txtSearch)).getText().toString();
                if (rqName != "") {
                    sQuery += "UPPER(name) LIKE '%" + rqName +"%'";
                }
                Cursor cursor = getContentResolver().query(uri, null, sQuery, null, null);
                dataAdapter.changeCursor(cursor);
            }

        };
        oTxtSearch.addTextChangedListener(fieldValidatorTextWatcher);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }

    private void displayListView() {

        String[] columns = new String[] {
                ClientDB.KEY_NAME,
                ClientDB.KEY_DATE,
                ClientDB.KEY_CITY
        };

        int[] to = new int[] {
                R.id.lblName,
                R.id.lblDate,
                R.id.lblCity,
        };

        dataAdapter = new SimpleCursorAdapter(
                this,
                R.layout.item,
                null,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);
        getLoaderManager().initLoader(0, null, this);

    }

    // This is called when a new Loader needs to be created.
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ClientDB.KEY_ROWID,
                ClientDB.KEY_NAME,
                ClientDB.KEY_DATE,
                ClientDB.KEY_CITY};
        CursorLoader cursorLoader = new CursorLoader(this, DBContentProvider.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        dataAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        dataAdapter.swapCursor(null);
    }


}
