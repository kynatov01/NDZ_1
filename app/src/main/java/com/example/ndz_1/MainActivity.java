package com.example.ndz_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btn_whatsapp, btn_Google;
    ImageView imageView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.iv_main_activity);
        btn_whatsapp = findViewById(R.id.btn_whatsApp);
        editText = findViewById(R.id.et_activity);
        btn_Google =findViewById(R.id.btn_Google);

        click();
    }

    private void click() {
        btn_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://wa.me/" + text));
                startActivity(intent);

            }
        });
        btn_Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchUrl = editText.getText().toString();
                if(searchUrl.isEmpty()){
                    Toast.makeText(MainActivity.this, "NOT TEXT" , Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com/search?q=" + searchUrl));
                startActivity(intent);
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                imageView.setImageURI(data.getData());
            }
        }
    }
}