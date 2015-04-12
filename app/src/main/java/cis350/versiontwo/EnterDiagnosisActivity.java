package cis350.versiontwo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.parse.Parse;


public class EnterDiagnosisActivity extends ActionBarActivity {
    Spinner locationSelection;
    Button preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_diagnosis);


        Intent intent = getIntent();
        Uri uri = intent.getParcelableExtra("URI");

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageURI(uri);

        // Add location information
        locationSelection = (Spinner) findViewById(R.id.location_spinner);
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter
                .createFromResource(this,
                        R.array.location_array, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSelection.setAdapter(locationAdapter);

        preview = (Button) findViewById(R.id.registerButton);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");

        // Get user data
        final EditText diagnosis = (EditText) findViewById(R.id.diagnosis);
        final EditText tags = (EditText) findViewById(R.id.tags);
        String location= locationSelection.getSelectedItem().toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_diagnosis, menu);
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
