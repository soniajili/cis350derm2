package cis350.versiontwo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class CollectionActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ParseQuery<ParseObject> query = ParseQuery.getQuery
                ("collectionImages");
        query.whereEqualTo("objectType", "image");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                } else {
                    Toast.makeText(getApplicationContext(),
                            "error retrieving images",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //starts here
        /*
        ParseQuery query = new ParseQuery("imageCollection");
        query.getInBackground("objectType",new GetCallback() {
            @Override
            public void done(ParseObject* object, ParseException e) {
                if (object == null) {
                    Log.d("test", "The object was not found...");
                } else {
                    Log.d("test", "Retrieved the object.");
                    ParseFile fileObject = (ParseFile)object.get("images");
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
                }
            }
        });
        */

        /*link: https://www.parse.com/questions/retrieve-image-from-parse */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collection, menu);
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
