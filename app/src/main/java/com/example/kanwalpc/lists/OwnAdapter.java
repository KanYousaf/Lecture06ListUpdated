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
 * Created by Kanwal PC on 10/17/2016.
 */
public class OwnAdapter extends ArrayAdapter<String> {

//    private String[] season_names;
//    private int[] season_images;
//
//    public OwnAdapter(Context context, String[] seasonNames, int[] seasonImages) {
//        super(context, R.layout.ownlist, seasonNames);
//        this.season_names=seasonNames;
//        this.season_images=seasonImages;
//    }

    private ArrayList<String> season_names;
    private ArrayList<Integer> season_images;

    public OwnAdapter(Context context, ArrayList<String> seasonNames, ArrayList<Integer> seasonImages) {
        super(context, R.layout.ownlist, seasonNames);
        this.season_names=seasonNames;
        this.season_images=seasonImages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        LayoutInflater inflater=LayoutInflater.from(getContext());
        rowView=inflater.inflate(R.layout.ownlist,parent,false);

        TextView display_season_name=(TextView)rowView.findViewById(R.id.season_name);
        ImageView display_season_image=(ImageView)rowView.findViewById(R.id.season_image);

//        display_season_name.setText(season_names[position]);
//        display_season_image.setImageResource(season_images[position]);
//
        display_season_name.setText(season_names.get(position));
        display_season_image.setImageResource(season_images.get(position));


        return rowView;
    }
}
