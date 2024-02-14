package com.example.picture_match;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Gridview_Activity extends AppCompatActivity {

    Toolbar toolbar;
    GridView gridview;
    TextView timer;
    List<String> imgList = new ArrayList<>();
    List<String> list = new ArrayList<>();
    ProgressBar progressBar;
    int levelNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_activity);

        getImageFromAssets();

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progressBar);
        gridview = findViewById(R.id.gridview);
        timer = findViewById(R.id.timer);

        levelNo=getIntent().getIntExtra("levelNo",0);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Gridview_Activity.this);
        builder.setTitle("No Time Limit");
        builder.setMessage("YOU HAVE A 5 SECONDS TO MEMORIZEAL IMAGES");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GridView_Adapter adapter = new GridView_Adapter(Gridview_Activity.this, imgList, timer, levelNo, progressBar);

                gridview.setAdapter(adapter);
            }
        }).show();
    }

    private void getImageFromAssets() {

        ArrayList<String> pathList = new ArrayList<>();
        try {
            String[] images = getAssets().list("");

            list = Arrays.asList(images);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgList.addAll((list.subList(0, 6)));
        imgList.addAll(imgList);
        Collections.shuffle(imgList);

        Log.d("UUU", "getImageFromAssets: imgList=" + imgList);
        Log.d("UUU", "getImageFromAssets: imgList Length=" + imgList.size());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_Share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage = "https://play.google.com/store/apps/details?id=com.alcamasoft.memorymatch\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.alcamasoft.memorymatch";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }
        return super.onOptionsItemSelected(item);
    }

}