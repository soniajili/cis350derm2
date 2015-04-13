package cis350.versiontwo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class GetImageActivity extends Activity {

    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    private ImageView img;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_image_activity);
        Toast.makeText(getApplicationContext(), "In onCreate line 25",
                Toast.LENGTH_SHORT).show();
        img = (ImageView)findViewById(R.id.ImageView01);

        ((Button) findViewById(R.id.Button01))
                .setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        Toast.makeText(getApplicationContext(), "Line 34",
                                Toast.LENGTH_SHORT).show();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                img.setImageURI(selectedImageUri);
                Toast.makeText(getApplicationContext(), "line 49 set image uri",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Toast.makeText(getApplicationContext(), "line 55",
                Toast.LENGTH_SHORT).show();
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}