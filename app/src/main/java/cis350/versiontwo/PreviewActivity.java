package cis350.versiontwo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class PreviewActivity extends ActionBarActivity {

    TextView diagnosisText;
    TextView tagText;
    TextView locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        diagnosisText = (TextView) findViewById(R.id.diagnosisText);
        tagText = (TextView) findViewById(R.id.tagText);
        locationText = (TextView) findViewById(R.id.locationText);

        // Set the text
        Intent intent = getIntent();
        String diagnosis = intent.getStringExtra("diagnosis");
        diagnosisText.setText(diagnosis);

        String tags = intent.getStringExtra("tags");
        tagText.setText(tags);

        String location = intent.getStringExtra("location");
        locationText.setText(location);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preview, menu);
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
