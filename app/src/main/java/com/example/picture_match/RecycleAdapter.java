package com.example.picture_match;

import static com.example.picture_match.MainActivity.preferences;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.LevelHolder> {
    Horizontal_RecyclerView_Activity horizontal_recyclerView_activity;
    String[] levelArr;
    String[] list_hading;
    int pos = 0;
    int levelNo;

    int time;


    public RecycleAdapter(Horizontal_RecyclerView_Activity horizontal_recyclerView_activity, String[] levelArr, String[] list_hading, int levelNo) {
        this.horizontal_recyclerView_activity = horizontal_recyclerView_activity;
        this.levelArr = levelArr;
        this.list_hading = list_hading;
        this.levelNo = levelNo;
        this.time = time;
    }

    @NonNull
    @Override
    public LevelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(horizontal_recyclerView_activity).inflate(R.layout.horizontal_recyclerview_activity_item, parent, false);
        LevelHolder holder = new LevelHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LevelHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText("" + list_hading[position]);
        pos = position;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class LevelHolder extends RecyclerView.ViewHolder {
        ListView listview;
        TextView textView;
        ListView_Adapter adapter;

        public LevelHolder(@NonNull View itemView) {
            super(itemView);
            listview = itemView.findViewById(R.id.horizontal_recyclerview_activity_item_listView);
            adapter = new ListView_Adapter(horizontal_recyclerView_activity, levelArr);
            listview.setAdapter(adapter);

            textView = itemView.findViewById(R.id.matchtext);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    String levelStatus = preferences.getString("levelStatus" + i, "pending");
                    Log.d("UUU", "onItemClick: pos=" + i + " LevelStatus=" + levelStatus);

                    if (i == 0) {

                        Intent intent = new Intent(horizontal_recyclerView_activity, Gridview_Activity.class);
                        intent.putExtra("levelNo", i);
                        horizontal_recyclerView_activity.startActivity(intent);
                        horizontal_recyclerView_activity.finish();

                    }
                    if (levelStatus.equals("win") || levelNo == i) {

                        Intent intent = new Intent(horizontal_recyclerView_activity, Gridview_Activity.class);
                        intent.putExtra("levelNo", i);
                        horizontal_recyclerView_activity.startActivity(intent);
                        horizontal_recyclerView_activity.finish();

                    }
                }
            });

        }
    }
}
