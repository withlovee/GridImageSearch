package com.withlovee.gridimagesearch.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.withlovee.gridimagesearch.R;
import com.withlovee.gridimagesearch.adapters.ImageResultsAdapter;
import com.withlovee.gridimagesearch.listeners.EndlessScrollListener;
import com.withlovee.gridimagesearch.models.ImageResult;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchActivity extends ActionBarActivity {

    private ArrayList<ImageResult> imageResults;
    private GridView gvResults;
    private ImageResultsAdapter aImageResults;
    private Context context;
    private String keyword = "Golden Retriever Puppy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_search);
        setupViews();
        imageResults = new ArrayList<ImageResult>();
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvResults.setAdapter(aImageResults);
        // searchPhotos(keyword);
    }

    private void setupViews(){

        gvResults = (GridView) findViewById(R.id.gvResults);
        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Log.i("DEBUG", "LOADMORE " + page + " " + totalItemsCount);
                loadMorePhotos(page);
            }
        });
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launch the image display activity
                // Create an intent
                Intent i = new Intent(SearchActivity.this, ImageDisplayActivity.class);

                // Get the image result to display
                ImageResult result = imageResults.get(position);

                // Pass image result into the intent
                i.putExtra("image", result);

                // Launch the new activity
                startActivity(i);
            }
        });
    }

    private void loadMorePhotos(int page){
        String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + keyword + "&start=" + ((page-1) * 4);
        // Log.i("DEBUG-APPEND-"+page, url);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray imagesJSON = null;
                try {
                    imagesJSON = response.getJSONObject("responseData").getJSONArray("results");
                    // Log.i("DEBUG-APPEND", imagesJSON.toString());
                    imageResults.addAll(ImageResult.fromJSONArray(imagesJSON));
                    aImageResults.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void searchPhotos(String searchKeyword){
        keyword = searchKeyword;
        String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + searchKeyword;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray imagesJSON = null;
                try {
                    imageResults.clear();
                    imagesJSON = response.getJSONObject("responseData").getJSONArray("results");
                    Log.i("DEBUG", imagesJSON.toString());
                    imageResults.addAll(ImageResult.fromJSONArray(imagesJSON));
                    aImageResults.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(context, "Searching for " + s + "...", Toast.LENGTH_SHORT).show();
                searchPhotos(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
