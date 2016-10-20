package com.example.kanwalpc.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kanwal PC on 10/19/2016.
 */
public class OwnAdapter extends ArrayAdapter<String> {
//    String[] season_names;
//    int[] season_images;
//    public OwnAdapter(Context context, String[] seasonName, int[] seasonImage) {
//        super(context, R.layout.ownlist, seasonName);
//        this.season_names=seasonName;
//        this.season_images=seasonImage;
//    }

    ArrayList<String> season_names;
    ArrayList<Integer> season_images;
    public OwnAdapter(Context context, ArrayList<String> seasonName, ArrayList<Integer> seasonImage) {
        super(context, R.layout.ownlist, seasonName);
        this.season_names=seasonName;
        this.season_images=seasonImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        LayoutInflater inflater=LayoutInflater.from(getContext());
        rowView=inflater.inflate(R.layout.ownlist,parent, false);

        ImageView display_image=(ImageView)rowView.findViewById(R.id.season_image);
        TextView display_name=(TextView)rowView.findViewById(R.id.season_text);

//        display_name.setText(season_names[position]);
//        display_image.setImageResource(season_images[position]);

        display_name.setText(season_names.get(position));
        display_image.setImageResource(season_images.get(position));

        return rowView;
    }
}
