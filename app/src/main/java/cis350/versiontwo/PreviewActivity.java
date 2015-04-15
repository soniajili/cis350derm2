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
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

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
//        ParseObject.registerSubclass(ImageUpload.class);
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(getApplicationContext(), "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");

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


        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {

                final ParseObject imageSubmission = new ParseObject
                    ("ImageUpload");
                final ParseFile imageFile = new ParseFile("img",
                convertImageToBytes(Uri.parse(uriString)));
//                imageFile.saveInBackground();
                imageFile.saveInBackground(new SaveCallback() {

                @Override
                public void done(ParseException e) {
                    if (e == null) {
                         imageSubmission.put("file", imageFile);
                         imageSubmission.put("objectType", "image");
                         imageSubmission.put("diagnosis", diagnosis);
//                imageSubmission.put("file", imageFile);
                         imageSubmission.put("tags", tags);
                         imageSubmission.put("location", location);

                         imageSubmission.saveInBackground();

                         Intent intent = new Intent(PreviewActivity.this,
                                CollectionActivity.class);
                        startActivity(intent);

                     } else {
                         Toast.makeText(getApplicationContext(), "Failed to save image",Toast.LENGTH_SHORT).show();

                     }
                  }
                 } );


//                  Intent intent = new Intent(PreviewActivity.this,
//                          CollectionActivity.class);

                                                //                Intent intent = new Intent(getApplicationContext(),
//                        CollectionActivity.class);
//                  startActivity(intent);
                                            }
                                        }

        );


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

