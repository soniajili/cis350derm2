package cis350.versiontwo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Sonia on 4/25/15.
 */
public class EnlargedImageActivity extends ActionBarActivity {

    TextView diagnosisText;
    TextView tagText;
    TextView locationText;
    ImageView image;
    ImageButton upVoteButton;
    ImageButton downVoteButton;

    /** Display page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlarged_image);

        Intent intent = getIntent();
        String diagnosis = intent.getStringExtra("diagnosis");
        String tags = intent.getStringExtra("tags");
        String location = intent.getStringExtra("location");
        String url = intent.getStringExtra("url");
        final String id = intent.getStringExtra("id");
        String upvotesString = intent.getStringExtra("upvotes");
        String downvotesString = intent.getStringExtra("downvotes");
        final Integer upInt = Integer.parseInt(upvotesString);
        Integer downInt = Integer.parseInt(downvotesString);

        diagnosisText = (TextView) findViewById(R.id.diagnosisText);
        tagText = (TextView) findViewById(R.id.tagText);
        locationText = (TextView) findViewById(R.id.locationText);
        image = (ImageView) findViewById(R.id.image);

        diagnosisText.setText(diagnosis);
        tagText.setText(tags);
        locationText.setText(location);

        try {
            InputStream in = new URL(url).openStream();
            Bitmap bmp = BitmapFactory.decodeStream(in);
            image.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        upvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // upload image and data when submit button is clicked
            public void onClick(View v) {

                final ParseObject imageSubmission = new ParseObject
                        ("ImageUpload");
                int up = upInt.intValue() + 1;


                final ParseFile imageFile = new ParseFile("img",
                        convertImageToBytes(Uri.parse(uriString)));
                imageFile.saveInBackground(new SaveCallback() {

                    @Override
                    public void done(ParseException e) {
                        if (e == null) {

                            imageSubmission.put("file", imageFile);
                            imageSubmission.put("objectType", "image");
                            imageSubmission.put("diagnosis", diagnosis);
                            imageSubmission.put("tags", tags);
                            imageSubmission.put("location", location);

                            imageSubmission.saveInBackground();

                            Intent intent = new Intent(PreviewActivity.this,
                                    CollectionActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Failed to save image",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }

    /** Inflate the menu; this adds items to the action bar if it is present */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
        return true;
    }

}
