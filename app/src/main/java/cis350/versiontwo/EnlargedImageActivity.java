package cis350.versiontwo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
import com.parse.ParseQuery;
import com.parse.GetCallback;

import java.io.InputStream;
import java.net.URL;

/**
 * EnglargedImageActivity allows the user to view a larger version of the image with details
 * about the diagnosis. Also allows users to upvote or downvote an image.
 */
public class EnlargedImageActivity extends ActionBarActivity {

    TextView diagnosisText;
    TextView tagText;
    TextView locationText;
    TextView upvoteText;
    TextView downvoteText;
    ImageView image;
    ImageButton upvoteButton;
    ImageButton downvoteButton;

    /** Display page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlarged_image);

        // Receive data from previous page's click
        final Intent intent = getIntent();
        String diagnosis = intent.getStringExtra("diagnosis");
        String tags = intent.getStringExtra("tags");
        String location = intent.getStringExtra("location");
        String url = intent.getStringExtra("url");
        String upvotesString = intent.getStringExtra("upvotes");
        String downvotesString = intent.getStringExtra("downvotes");
        upvoteText = (TextView) findViewById(R.id.upvoteNumber);
        downvoteText = (TextView) findViewById(R.id.downvoteNumber);
        upvoteText.setText(upvotesString);
        downvoteText.setText(downvotesString);
        final Integer oUpInt = Integer.parseInt(upvotesString);
        final Integer oDownInt = Integer.parseInt(downvotesString);

        diagnosisText = (TextView) findViewById(R.id.diagnosisText);
        tagText = (TextView) findViewById(R.id.tagText);
        locationText = (TextView) findViewById(R.id.locationText);

        upvoteButton = (ImageButton) findViewById(R.id.upvoteButton);
        downvoteButton = (ImageButton) findViewById(R.id.downvoteButton);

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

        // set functionality for the upvote button
        upvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // upload image and data when submit button is clicked
            public void onClick(View v) {

                final ParseObject imageSubmission = new ParseObject("ImageUpload");

                String upvotesString = intent.getStringExtra("upvotes");
                String id = intent.getStringExtra("id");
                final int upInt = Integer.parseInt(upvotesString) + 1;

                upvoteText.setText(String.valueOf(upInt));

                ParseQuery query = ParseQuery.getQuery("ImageUpload");
                query.getInBackground(id, new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {

                            if (oUpInt == object.getInt("upvotes")) {
                                object.increment("upvotes");
                                object.saveInBackground();
                            }

                        } else {
                            //failed
                        }
                    }
                });
            }
        });

        // set functionality for the downvoteButton
        downvoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // upload image and data when submit button is clicked
            public void onClick(View v) {

                final ParseObject imageSubmission = new ParseObject("ImageUpload");

                String downvotesString = intent.getStringExtra("downvotes");
                String id = intent.getStringExtra("id");
                final int downInt = Integer.parseInt(downvotesString) + 1;

                downvoteText.setText(String.valueOf(downInt));

                ParseQuery query = ParseQuery.getQuery("ImageUpload");
                Log.d("the id is: ", String.valueOf(id));
                query.getInBackground(id, new GetCallback<ParseObject>() {
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {

                            if (oDownInt == object.getInt("downvotes")) {
                                object.increment("downvotes");
                                object.saveInBackground();
                            }

                        } else {
                            //failed
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
