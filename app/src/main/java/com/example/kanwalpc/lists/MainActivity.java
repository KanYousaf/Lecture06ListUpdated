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
    private ArrayList<String> names_arrayL;
    private ArrayList<Integer> images_arrayL;
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
//        myOwnAdapter=new OwnAdapter(MainActivity.this, myFavSeasonArray,
//                                            myFavSeasonImageArray);
        if(savedInstanceState==null) {
            names_arrayL = new ArrayList<>(Arrays.asList(myFavSeasonArray));
            images_arrayL = new ArrayList<>();
            for (int i = 0; i < myFavSeasonImageArray.length; i++) {
                images_arrayL.add(myFavSeasonImageArray[i]);
            }
        }
        else {
            loadData();
        }

        myOwnAdapter=new OwnAdapter(MainActivity.this, names_arrayL, images_arrayL);

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

                final AlertDialog.Builder alertBox=new AlertDialog.Builder(MainActivity.this);
                //set Title and Dialog Message
                alertBox.setTitle("Do You Want to Delete it? "+season_name);
                alertBox.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertBox.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        names_arrayL.remove(position);
                        images_arrayL.remove(position);
                        myOwnAdapter.notifyDataSetChanged();
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
        prefEditor.putInt("name_array_list_size", names_arrayL.size());
        prefEditor.putInt("image_array_list_size", images_arrayL.size());

        for(int i=0; i< names_arrayL.size(); i++){
            prefEditor.putString("names", names_arrayL.get(i));
        }

        for(int i=0; i< images_arrayL.size(); i++){
            prefEditor.putInt("images", images_arrayL.get(i));
        }
        prefEditor.commit();
    }

    public void loadData(){
        SharedPreferences pref=getPreferences(MODE_PRIVATE);
        int seasons_size=pref.getInt("name_array_list_size",0);
        int image_size=pref.getInt("image_array_list_size",0);

        for(int i=0; i< seasons_size; i++){
            names_arrayL.add(pref.getString("names" +i, null));
        }
        for(int i=0; i< image_size; i++){
            images_arrayL.add(pref.getInt("images" +i, 0));
        }
    }

}
