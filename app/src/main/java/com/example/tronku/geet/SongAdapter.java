package com.example.tronku.geet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private Context context;
    private List<Songs> songsList;
    private List<Songs> list;

    public SongAdapter(Context context, List<Songs> list) {
        this.context = context;
        songsList = list;
    }

    public void update(List<Songs> list){
        songsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_item, parent, false);
        final SongViewHolder holder = new SongViewHolder(view);
        holder.song_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent song = new Intent(context, MusicPlayer.class);
                song.putExtra("position", holder.getAdapterPosition());
                song.putParcelableArrayListExtra("songList", (ArrayList<? extends Parcelable>) songsList);
                context.startActivity(song);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        holder.title.setText(songsList.get(position).getSongTitle());
        holder.artist.setText(songsList.get(position).getArtistName());

    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder{

        private TextView title, artist;
        private RelativeLayout song_item;
        private ImageView thumbnail;

        public SongViewHolder(View itemView) {
            super(itemView);
            artist = itemView.findViewById(R.id.artist);
            song_item = itemView.findViewById(R.id.song_item);
            title = itemView.findViewById(R.id.song_title);
            thumbnail = itemView.findViewById(R.id.song_thumbnail);
        }
    }
}
