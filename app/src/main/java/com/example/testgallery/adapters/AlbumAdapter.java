package com.example.testgallery.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testgallery.activities.mainActivities.ItemAlbumActivity;
import com.example.testgallery.R;
import com.example.testgallery.models.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Album> mListAlbums;
    private Context context;

    public AlbumAdapter(List<Album> mListAlbums, Context context) {
        this.mListAlbums = mListAlbums;
        this.context = context;
    }

    public void setData(List<Album> mListAlbums) {
        this.mListAlbums = mListAlbums;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlbumAdapter.AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);


        return new AlbumAdapter.AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.AlbumViewHolder holder, int position) {
        holder.onBind(mListAlbums.get(position));
    }

    @Override
    public int getItemCount() {
        if (mListAlbums != null) {
            return mListAlbums.size();
        }
        return 0;
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img_album;
        private final TextView txtName_album;
        private final TextView txtCount_item_album;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            img_album = itemView.findViewById(R.id.img_album);
            txtName_album = itemView.findViewById(R.id.txtName_album);
            txtCount_item_album = itemView.findViewById(R.id.txtCount_item_album);
        }

        public void onBind(Album ref) {
            txtName_album.setText(ref.getName());
            txtCount_item_album.setText(String.valueOf(ref.getList().size()) + " items");
            Glide.with(context).load(ref.getImg().getThumb()).into(img_album);

            img_album.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ItemAlbumActivity.class);
                    ArrayList<String> list = new ArrayList<>();
                    for(int i=0;i<ref.getList().size();i++) {
                        list.add(ref.getList().get(i).getThumb());
                    }

                    intent.putStringArrayListExtra("data", list);
                    intent.putExtra("name", ref.getName());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
