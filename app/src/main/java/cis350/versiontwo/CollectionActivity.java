package cis350.versiontwo;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class CollectionActivity extends ActionBarActivity {
    GridView gridView;
    List<ParseObject> obj;
    ArrayList<String> imageArrayList;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
//        Parse.enableLocalDatastore(getApplicationContext());
//        Parse.initialize(getApplicationContext(), "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
//                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        new RemoteDataTask().execute();
    }

    class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            imageArrayList = new ArrayList<String>();
            try {

                ParseQuery query = ParseQuery.getQuery("ImageUpload");

                obj = query.find();
                for (ParseObject o : obj) {
                    ParseFile image = (ParseFile) o.get("file");
                    if (image != null) {
                        imageArrayList.add(image.getUrl());
                        Log.d("URL: ", image.getUrl());
                    }
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            gridView = (GridView) findViewById(R.id.grid_view);
            adapter = new ImageAdapter(CollectionActivity.this, imageArrayList);
            gridView.setAdapter(adapter);
        }
    }
        /*
        //Enable Parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");

        ParseQuery query = ParseQuery.getQuery("steph");
      //  query.whereEqualTo("objectType", "image");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        ParseObject object = objects.get(i);
                        ParseFile fileObject = (ParseFile) object.get("file");
                        Toast.makeText(getApplicationContext(),
                                "getting fileObjects",
                                Toast.LENGTH_SHORT).show();
                        fileObject.getDataInBackground(new GetDataCallback() {
                            public void done(byte[] data, ParseException e) {
                                if (e == null) {
                                    ImageView imageView = (ImageView) findViewById(R.id.image);
                                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    imageView.setImageBitmap(bmp);

                                } else {
                                    Log.d("test",
                                            "There was a problem downloading the data.");
                                }
                            }
                        });
                    }
                }

                else{
                    Toast.makeText(getApplicationContext(),
                            "error retrieving images",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });
=======
//
//        //Enable Parse
//        Parse.enableLocalDatastore(getApplicationContext());
//        Parse.initialize(getApplicationContext(),
//                "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
//                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");
//
//        ParseQuery query = ParseQuery.getQuery
//                ("steph");
//        query.whereEqualTo("objectType", "image");
//
//        query.findInBackground(new FindCallback<ParseObject>() {
//
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//
//                if (e == null) {
//                    for (int i = 0; i < objects.size(); i++) {
//                        ParseObject object = objects.get(i);
//                        ParseFile fileObject = (ParseFile) object.get
//                                ("file");
//                        Toast.makeText(getApplicationContext(),
//                                "getting fileObjects",
//                                Toast.LENGTH_SHORT).show();
//                        fileObject.getDataInBackground(new GetDataCallback() {
//                            public void done(byte[] data, ParseException e) {
//                                if (e == null) {
//                                    ImageView imageView = (ImageView) findViewById(R.id.image);
//                                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//                                    imageView.setImageBitmap(bmp);
//
//                                } else {
//                                    Log.d("test",
//                                            "There was a problem downloading the data.");
//                                }
//                            }
//                        });
//                    }
//                }
//
//                else{
//                    Toast.makeText(getApplicationContext(),
//                            "error retrieving images",
//                            Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });
>>>>>>> origin/master


        GridView gridview = (GridView) findViewById(R.id.grid_view);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(CollectionActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
*/

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

    class ImageAdapter extends BaseAdapter {
        private Context context;
        ArrayList<String> imageArrayList;

        public ImageAdapter(Context c, ArrayList<String> imageArray) {
            context = c;
            imageArrayList = imageArray;
        }

        public int getCount() {
            return imageArrayList.size();
        }

        public Object getItem(int position) {
            return imageArrayList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View view, ViewGroup parent) {
            Log.d("ArrayList size: ", Integer.toString(imageArrayList.size()));
            View row = view;
            ViewHolder holder = null;

            if (row == null) {
                Log.d("Made it to: ", "row is null (a)");
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                Log.d("Made it to: ", "we did the inflater crap (b)");
                row = inflater.inflate(R.layout.gridview_item, parent, false);
                Log.d("Made it to: ", "we did more inflater crap (c)");
                holder = new ViewHolder(row);
                Log.d("Made it to: ", "we did something with the holder (d)");
                row.setTag(holder);

            } else {
                Log.d("Made it to: ", "row wasn't null????");
                holder = (ViewHolder) row.getTag();


            }
            try {
               /* URL url = new URL(imageArrayList.get(position));
                Log.d("Made it to: ", "got the URL (1)");
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                Log.d("Made it to: ", "got the bitmap (2)");*/
                InputStream in = new URL(imageArrayList.get(position)).openStream();
                Bitmap bmp = BitmapFactory.decodeStream(in);
                holder.image.setImageBitmap(bmp);
                Log.d("Made it to: ", "SET THE DAMN IMAGE (3)");
            } catch (Exception e) {
                Log.d("Made it to: ", "EXCEPTIONNNN");
                e.printStackTrace();
            }
            /*try {
                Log.d("Made it to: ", "entered Try statement (1)");
                InputStream in = (InputStream) new URL(imageArrayList.get(position)).getContent();
                Log.d("Made it to: ", "input stream bullshit (2)");
                Drawable d = Drawable.createFromStream(in, "");
                Log.d("Made it to: ", "Drawable.create... (3)");
                holder.image.setImageDrawable(d);
            } catch (Exception e) {
                Log.d("Made it to: ", "EXCEPTION (4)");
                return null;
            }*/
            return row;
        }
    }

    class ViewHolder {
        ImageView image;
        ViewHolder(View v) {
            image = (ImageView) v.findViewById(R.id.image_view);
        }
    }
}