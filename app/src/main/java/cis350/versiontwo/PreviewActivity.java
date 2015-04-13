package cis350.versiontwo;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class PreviewActivity extends ActionBarActivity {

    ImageView image;
    TextView diagnosisText;
    TextView tagText;
    TextView locationText;
    Button editButton;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        image = (ImageView) findViewById(R.id.image);
        diagnosisText = (TextView) findViewById(R.id.diagnosisText);
        tagText = (TextView) findViewById(R.id.tagText);
        locationText = (TextView) findViewById(R.id.locationText);

        // Set the image
        Intent intent = getIntent();
        final Uri uri = intent.getParcelableExtra("image");
        final String uriString = uri.toString();
        image = (ImageView) findViewById(R.id.image);
        image.setImageURI(uri);

        // Set the text
        final String diagnosis = intent.getStringExtra("diagnosis");
        diagnosisText.setText(diagnosis);

        final String tags = intent.getStringExtra("tags");
        tagText.setText(tags);

        final String location = intent.getStringExtra("location");
        locationText.setText(location);

        editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject imageSubmission = new ParseObject(diagnosis);
                ParseFile imageFile = new ParseFile("img",
                        convertImageToBytes(Uri.parse
                                (uriString)));
                imageFile.saveInBackground();
                imageSubmission.put("objectType", "image");
                imageSubmission.put("file", imageFile);
                imageSubmission.put("tags", tags);
                imageSubmission.put("location", location);
                imageSubmission.saveInBackground();

                Intent intent = new Intent(getApplicationContext(),
                        CollectionActivity.class);
                startActivity(intent);

            }
        });


    }

    private byte[] convertImageToBytes(Uri uri) {
        byte[] data = null;
        try {
            ContentResolver cr = getBaseContext().getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
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
