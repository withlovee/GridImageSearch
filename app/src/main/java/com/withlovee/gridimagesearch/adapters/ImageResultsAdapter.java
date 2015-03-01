package com.withlovee.gridimagesearch.adapters;

import android.content.Context;
import android.text.Html;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.withlovee.gridimagesearch.R;
import com.withlovee.gridimagesearch.models.ImageResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vee on 2/24/15.
 */
public class ImageResultsAdapter extends ArrayAdapter<ImageResult> {

    public ImageResultsAdapter(Context context, ArrayList<ImageResult> objects) {
        super(context, R.layout.item_image_result, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageResult imageInfo = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
        }
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

        // Clear out image
        ivImage.setImageResource(0);

        // Set text
        tvTitle.setText(Html.fromHtml(imageInfo.title));

        // Set image
        Picasso.with(getContext()).load(imageInfo.thumbUrl).into(ivImage);

        return convertView;
    }
}
