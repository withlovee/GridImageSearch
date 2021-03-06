package com.withlovee.gridimagesearch.activities;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.withlovee.gridimagesearch.R;
import com.withlovee.gridimagesearch.models.ImageResult;


public class ImageDisplayActivity extends ActionBarActivity {
    private ProgressBar pb;

    public void showProgressBar() {
        pb.setVisibility(ProgressBar.VISIBLE);
    }

    public void hideProgressBar() {
        pb.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        // getActionBar().hide(); // Bug
        pb = (ProgressBar) findViewById(R.id.pbLoading);
        showProgressBar();
        ImageResult image = (ImageResult) getIntent().getSerializableExtra("image");
        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        Picasso.with(this).load(image.fullUrl).into(ivImage, new Callback() {
            @Override
            public void onSuccess() {
                hideProgressBar();
            }

            @Override
            public void onError() {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_display, menu);
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
