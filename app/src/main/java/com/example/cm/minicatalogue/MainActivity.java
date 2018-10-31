package com.example.cm.minicatalogue;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST = 0;
    private static final int RESULT_LOAD_IMAGE = 1;

    ArrayList<User> users;
    ListView listUsers;
    CustomAdapter adapter;

    ImageButton add;

    Button load;

    TextView nameV;
    TextView statusV;
    ImageView pictureV;
    //Bitmap bmV;

    String name;
    String status;
    int picture;

    public User input(){
        nameV = (TextView) findViewById(R.id.name_);
        statusV = (TextView) findViewById(R.id.status_);
        pictureV = (ImageView) findViewById(R.id.picture_);

        Bitmap bmV;

        BitmapDrawable btmDr =  ((BitmapDrawable) pictureV.getDrawable());

        if (btmDr == null){
            bmV = Bitmap.createBitmap(800, 600, Bitmap.Config.ARGB_8888);
        }else{
            bmV = btmDr.getBitmap();
        }

        name = nameV.getText().toString();
        status = statusV.getText().toString();
        nameV.setText("Name");
        statusV.setText("Status");

        //return new User(name, status, R.drawable.cm_nasser);
        //return new User(name, status, pictureV);
        return new User(name, status, pictureV, bmV);
        //return new User(name, status, bmV);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            System.out.println("if is True !!!");
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST);
        }else{
            System.out.println("if is False !!!");
            //requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST);
        }

        add = (ImageButton) findViewById(R.id.add_);
        load = (Button) findViewById(R.id.load);
        listUsers = (ListView) findViewById(R.id.listView);




        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        users = new ArrayList<User>();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user_ = input();
                users.add(user_);
                adapter = new CustomAdapter(getApplication(), users);
                listUsers.setAdapter(adapter);
                System.out.println("new user has been added");
            }
        });



        //users.add(new User("Med CHADAD", "Disponible",R.drawable.cm_nasser));

        adapter = new CustomAdapter(getApplication(), users);
        listUsers.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch( requestCode ){
            case PERMISSION_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permissio granted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case RESULT_LOAD_IMAGE :
                if (resultCode == RESULT_OK ){
                    System.out.println("resultCode == RESULT_ok ");
                    Uri selectedImage = data.getData();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    System.out.println("url      : "+picturePath);
                    cursor.close();
                    System.out.println("cursor close");
                    Bitmap bm;
                    bm = BitmapFactory.decodeFile(picturePath);
                    pictureV.setImageBitmap( bm);
                    System.out.println("pictureV.setImageBitmap .... ");

                }
        }
    }
}
