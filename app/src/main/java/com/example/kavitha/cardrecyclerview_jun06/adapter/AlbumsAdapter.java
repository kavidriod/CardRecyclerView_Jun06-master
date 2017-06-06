package com.example.kavitha.cardrecyclerview_jun06.adapter;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.kavitha.cardrecyclerview_jun06.R;
import com.example.kavitha.cardrecyclerview_jun06.model.Album;

import java.util.List;

/**
 * Created by Kavitha on 6/6/2017.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder>   {


    Context context;
    List<Album> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public AlbumsAdapter(Context context ,List<Album> albumList){
        this.albumList = albumList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
            Album eachAlbum = albumList.get(position);

        holder.title.setText(eachAlbum.getName());
        holder.count.setText(eachAlbum.getNumOfSongs() + " Songs");

        Glide.with(context).load(eachAlbum.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(holder.overflow);
            }
        });


    }


    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void  showPopUpMenu(View view){
        // inflate menu
        PopupMenu popupMenu = new PopupMenu(context,view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.menu_album,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new MysetOnMenuItemClickListener());
        popupMenu.show();
    }


    class MysetOnMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{

        public  MysetOnMenuItemClickListener()
        {

        }


        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_add_favourite:
                    Toast.makeText(context,"Add to Favourite",Toast.LENGTH_LONG).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(context,"Play Next",Toast.LENGTH_LONG).show();
                    return true;
                default:
            }
            return false;
        }
    }
}
