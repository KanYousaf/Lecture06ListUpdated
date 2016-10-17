package com.example.kanwalpc.lists;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView display_details_season_tv,details_season_tv;
    int position_to_show;
//    public static final String[] Season_Details={"Silicon Valley is about Richard Henricks and his company named pied piper",
//            "Game of Thrones is a fantasy drama",
//            "Big Bang Theory is scientific sci-fi drama",
//            "Prison Break is about the story of Micheal Scofield and his brother",
//            "Citizen Khan: Mr. Khan , a pakistani citizen living abroad in UK",
//            "Divinci Demons: Story about Leonardo Divinci",
//            "Mr. Robot is about hacker's story and how he wants to take revenge on society",
//            "House of Cards is about underwood and his compaign to become president of USA",
//            "Sherlock Holmes: Detective to solve crimes"
//    };

    public static final String[] Season_Details={"Silicon Valley","Silicon Valley is about Richard Henricks and his company named pied piper",
            "Game of Thrones","Game of Thrones is a fantasy drama",
            "Big Bang Theory","Big Bang Theory is scientific sci-fi drama",
            "Prison Break","Prison Break is about the story of Micheal Scofield and his brother",
            "Citizen Khan","Citizen Khan: Mr. Khan , a pakistani citizen living abroad in UK",
            "Divinci Demons","Divinci Demons: Story about Leonardo Divinci",
            "Mr. Robot","Mr. Robot is about hacker's story and how he wants to take revenge on society",
            "House of Cards","House of Cards is about underwood and his compaign to become president of USA",
            "Sherlock Holmes","Sherlock Holmes: Detective to solve crimes"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        display_details_season_tv = (TextView) this.findViewById(R.id.details_season);
        details_season_tv=(TextView)this.findViewById(R.id.display_info);

        Intent receive_intent=getIntent();
        int data=receive_intent.getExtras().getInt("season_id");
        String name=receive_intent.getStringExtra("season_name");
        details_season_tv.setText("SeasonID : " + data + "\n" + "Details: " + "\n" + name);

//        display_details_season_tv.setText(Season_Details[data]);

        for(int i=0; i< Season_Details.length; i+=2){
            if(name.equals(Season_Details[i])){
                display_details_season_tv.setText(Season_Details[i+1]);
            }
        }
    }
}
