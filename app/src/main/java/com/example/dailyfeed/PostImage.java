package com.example.dailyfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dailyfeed.Database.DailyFeedModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class PostImage extends AppCompatActivity {


    private static final String IMAGE_DIRECTORY = "/DailyFeed";
    ImageView imageViewPost;
    EditText editTextPost;
    Button buttonPost;
    String selectedImagePath= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_image);

        DailyFeedModel.getInstance(getApplicationContext());
        imageViewPost = findViewById(R.id.imageViewPost);
        editTextPost = findViewById(R.id.textViewCaption);
        buttonPost = findViewById(R.id.buttonPost);

        imageViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,100);

            }
        });
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String caption = editTextPost.getText().toString().trim();
                DailyFeedModel.open();
                DailyFeedModel.insertPost(caption,selectedImagePath);
                DailyFeedModel.close();

                imageViewPost.setImageResource(0);
                editTextPost.setText("");

            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();


                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                imageViewPost.setImageBitmap(bitmap);

                File photo = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
                if (!photo.exists()) {
                    photo.mkdirs();
                }
                try {
                    File f = new File(photo, Calendar.getInstance().getTimeInMillis() + ".jpg");
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(stream.toByteArray());
                    MediaScannerConnection.scanFile(getApplicationContext(), new String[]{f.getPath()},
                            new String[]{"image/jpeg"}, null);
                    fo.close();
                    selectedImagePath = f.getAbsolutePath();
                    Log.i("YAY",""+selectedImagePath);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }
        }
    }


}
