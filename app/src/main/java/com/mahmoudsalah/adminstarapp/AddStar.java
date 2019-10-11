package com.mahmoudsalah.adminstarapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ablanco.imageprovider.ImageProvider;
import com.ablanco.imageprovider.ImageSource;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.backendless.geo.Units;

import java.io.IOException;

public class AddStar extends AppCompatActivity implements View.OnKeyListener {
EditText starNameText,starAgeText,lifeText;
Button addButton;
ImageView imageView;
ConstraintLayout backGroundLayOut;
String urlImage;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_star);
    starNameText = findViewById(R.id.starNameText);
    starAgeText = findViewById(R.id.starAgeText);
    lifeText = findViewById(R.id.lifeText);
    addButton = findViewById(R.id.addButton);
    imageView = findViewById(R.id.imageView);
    imageView.setImageDrawable(getResources().getDrawable(R.drawable.dummy));
    backGroundLayOut =findViewById(R.id.backGroundLayOut);
    backGroundLayOut.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            InputMethodManager input = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            input.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);


        }
    });
    }

    public void Add(View view) {

        star actor = new star();
        actor.setName(starNameText.getText().toString());
        actor.setAge(starAgeText.getText().toString());
        actor.setImage(urlImage);



actor.saveAsync(new AsyncCallback<star>() {
    @Override
    public void handleResponse(star response) {
        Toast.makeText(AddStar.this, "Done", Toast.LENGTH_SHORT).show();
        starNameText.setText("");
        starAgeText.setText("");
        lifeText.setText("");
        imageView.setImageDrawable(null);
    }

    @Override
    public void handleFault(BackendlessFault fault) {
        Toast.makeText(AddStar.this, ""+fault.getMessage(), Toast.LENGTH_SHORT).show();
    }
});
    }

    public void Image(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK&&data!=null){
            try {
                Uri selectImage = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectImage);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_ENTER && event.getAction()==event.ACTION_DOWN){
            Add(v);
        }

        return false;
    }

    public void upload(View view) {

        Backendless.Files.Android.upload(bitmap, Bitmap.CompressFormat.PNG, 20,
                starNameText.getText().toString(), "images", new AsyncCallback<BackendlessFile>() {
                    @Override
                    public void handleResponse(BackendlessFile response) {
                        urlImage = response.getFileURL();
                        Toast.makeText(AddStar.this, "Uploaded", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(AddStar.this, "failed "+fault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
