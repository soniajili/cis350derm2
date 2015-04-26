package cis350.versiontwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sonia on 4/25/15.
 */
public class EnlargedImageActivity extends ActionBarActivity {

    TextView diagnosisText;
    TextView tagText;
    TextView locationText;
    ImageView image;

    /** Display page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlarged_image);

        Intent intent = getIntent();
        String diagnosis = intent.getStringExtra("diagnosis");
        String tags = intent.getStringExtra("tags");
        String location = intent.getStringExtra("location");

        image = (ImageView) findViewById(R.id.image);
        diagnosisText = (TextView) findViewById(R.id.diagnosisText);
        tagText = (TextView) findViewById(R.id.tagText);
        locationText = (TextView) findViewById(R.id.locationText);

        diagnosisText.setText(diagnosis);
        tagText.setText(tags);
        locationText.setText(location);



    }

    /** Inflate the menu; this adds items to the action bar if it is present */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
        return true;
    }

}
