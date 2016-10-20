package com.example.kanwalpc.lists;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ListView myFavSeasonList;
    OwnAdapter myOwnAdapter;
    ArrayList<String> seasonNamesAL;
    ArrayList<Integer> seasonImagesAL;
    int pos;
    private static final String[] myFavSeasonArray={
            "Silicon Valley",
            "Game of Thrones",
            "Big Bang Theory",
            "Prison Break",
            "Citizen Khan",
            "Divinci Demons",
            "Mr. Robot",
            "House of Cards",
            "Sherlock Holmes"};

    private static final int[] myFavSeasonImageArray={
            R.drawable.siliconvalley,
            R.drawable.gameofthrones,
            R.drawable.bigbangtheory,
            R.drawable.prisonbreak,
            R.drawable.citizenkhan,
            R.drawable.divincidemons,
            R.drawable.mrrobot,
            R.drawable.houseofcards,
            R.drawable.sherlockholmes};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFavSeasonList=(ListView)this.findViewById(R.id.list_of_seasons);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,
//                android.R.layout.simple_list_item_1,myFavSeasonArray);
//
        if(savedInstanceState==null) {
            seasonNamesAL = new ArrayList<>(Arrays.asList(myFavSeasonArray));
            seasonImagesAL = new ArrayList<>();
            for (int i = 0; i < myFavSeasonImageArray.length; i++) {
                seasonImagesAL.add(myFavSeasonImageArray[i]);
            }
        }
        else {
            loadData();
        }
        myOwnAdapter=new OwnAdapter(MainActivity.this, seasonNamesAL,
                                        seasonImagesAL);
        myFavSeasonList.setAdapter(myOwnAdapter);
        myFavSeasonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent send_intent=new Intent(MainActivity.this,DetailActivity.class);
                String name=myFavSeasonList.getItemAtPosition(position).toString();
                send_intent.putExtra("season_id",position);
                send_intent.putExtra("season_name",name);
                startActivity(send_intent);
            }
        });
        myFavSeasonList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                String season_name=myFavSeasonList.getItemAtPosition(position).toString();
//                seasonNamesAL.get(position);

                AlertDialog.Builder alertBox=new AlertDialog.Builder(MainActivity.this);
                //set Title and Dialog Message
                alertBox.setTitle("Do You Want to Delete it? "+season_name);
                alertBox.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        seasonNamesAL.remove(position);
                        seasonImagesAL.remove(position);
                        myOwnAdapter.notifyDataSetChanged();
                    }
                });

                alertBox.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                // create alert dialog
                AlertDialog alertDialog=alertBox.create();
                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    protected void onPause() {
        saveData();
        super.onPause();
    }

    @Override
    protected void onStop() {
        saveData();
        super.onStop();
    }

    public void saveData(){
        SharedPreferences pref=getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefEditor=pref.edit();
        prefEditor.putInt("season_size", seasonNamesAL.size());
        prefEditor.putInt("images_size", seasonImagesAL.size());

        for(int i=0; i< seasonNamesAL.size(); i++){
            prefEditor.putString("season_names", seasonNamesAL.get(i));
        }

        for(int i=0; i< seasonImagesAL.size(); i++){
            prefEditor.putInt("season_images", seasonImagesAL.get(i));
        }
        prefEditor.commit();
    }

    public void loadData(){
        SharedPreferences pref=getPreferences(MODE_PRIVATE);
        int season_size_value=pref.getInt("season_size",0);
        int image_size_value=pref.getInt("images_size",0);

        for(int i=0; i< season_size_value; i++){
            seasonNamesAL.add(pref.getString("season_names"+i,null));
        }

        for(int i=0; i< image_size_value; i++){
            seasonImagesAL.add(pref.getInt("season_images"+i,0));
        }
    }
}
