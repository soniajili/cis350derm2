package cis350.versiontwo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.parse.ParseObject;
import com.parse.ParseQuery;


public class CollectionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        ParseQuery<ParseObject> query = ParseQuery.getQuery
                ("collectionImages");
        query.whereEqualTo("objectType", "image");
        /*
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    ImageView image = (ImageView) findViewById(R.id.image);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "error retrieving images",
                            Toast.LENGTH_SHORT).show();
                } */

                /*
                for(int i =0 ; i < objects.size(); i++){

                    ParseObject object = objects.get(i);
                    ParseFile fileObject = (ParseFile) object.get("imageFile");
                    fileObject.getDataInBackground(new GetDataCallback() {
                        public void done(byte[] data,ParseException e) {
                            if (e == null) {

                                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                imgs[i].setImageBitmap(bmp);

                            } else {
                                Log.d("test",
                                        "There was a problem downloading the data.");
                            }
                        }
                    });
                }
                progressDialog.dismiss();
            }
        });

        ParseQuery query = new ParseQuery("imageCollection");
        query.getInBackground("objectType", new GetCallback() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    Log.d("test", "The object was not found...");
                    ParseFile fileObject = (ParseFile)object.get("images");
                    ParseImageView imageView = (ParseImageView) findViewById(android.R.id.icon);
                    // The placeholder will be used before and during the fetch, to be replaced by the fetched image
                    // data.
                    imageView.setPlaceholder(getResources().getDrawable(R.drawable.placeholder));
                    imageView.setParseFile(file);
                    imageView.loadInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            Log.i("ParseImageView",
                                    "Fetched! Data length: " + data.length + ", or exception: " + e.getMessage());
                        }
                    });
                    /*
                    fileObject.getDataInBackground(new GetDataCallback() {
                        public void done(byte[] data, ParseException e) {
                            if (e == null) {
                                Log.d("test", "We've got data in data.");
                                // use data for something

                            } else {
                                Log.d("test", "There was a problem downloading the data.");
                            }
                        }
                    });

                } else {
                    Log.d("error", "Error retrieving images");

                }
            }
        });
        /*link: https://www.parse.com/questions/retrieve-image-from-parse */

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collection, menu);
        return true;
    }

    /*
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
    */
}
