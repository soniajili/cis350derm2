package cis350.versiontwo;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

    /** Display the page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        new RemoteDataTask().execute();
    }
    /**  perform background operations and publish results on the UI thread */
    class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /** get image data */
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

        /** sets up grid */
        @Override
        protected void onPostExecute(Void result) {
            gridView = (GridView) findViewById(R.id.grid_view);
            adapter = new ImageAdapter(CollectionActivity.this, imageArrayList);
            gridView.setAdapter(adapter);
        }
    }

    /** Handle action bar item clicks here */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.homePage) {
            goHome();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Go to home page */
    private void goHome() {
        Intent intent = new Intent(getApplicationContext(), cis350.versiontwo.HomeActivity.class);
        startActivity(intent);
    }

    /** set up ImageAdapter which helps display the image */
    class ImageAdapter extends BaseAdapter {
        private Context context;
        ArrayList<String> imageArrayList;

        public ImageAdapter(Context c, ArrayList<String> imageArray) {
            context = c;
            imageArrayList = imageArray;
        }

        /** get the number of images to be displayed */
        public int getCount() {
            return imageArrayList.size();
        }

        /** get the item to be displayed */
        public Object getItem(int position) {
            return imageArrayList.get(position);
        }

        /** get item's position */
        public long getItemId(int position) {
            return position;
        }

        /** create a new ImageView for each item referenced by the Adapter */
        public View getView(int position, View view, ViewGroup parent) {
            Log.d("ArrayList size: ", Integer.toString(imageArrayList.size()));
            View row = view;
            ViewHolder holder;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                        .LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.gridview_item, parent, false);
                holder = new ViewHolder(row);
                row.setTag(holder);

            } else {
                holder = (ViewHolder) row.getTag();


            }
            try {
                InputStream in = new URL(imageArrayList.get(position)).openStream();
                Bitmap bmp = BitmapFactory.decodeStream(in);
                holder.image.setImageBitmap(bmp);
            } catch (Exception e) {
                e.printStackTrace();

            }

            return row;
        }
    }

    /** place image */
    class ViewHolder {
        ImageView image;
        ViewHolder(View v) {
            image = (ImageView) v.findViewById(R.id.image_view);
        }
    }
}