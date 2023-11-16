package com.example.spotifycloneodev6.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifycloneodev6.data.entity.PlayList;
import com.example.spotifycloneodev6.databinding.PlaylistCardDesignBinding;

import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlaylistCardDesignTutucu>{

    private List<PlayList> playlistListesi;
    private Context mContext;

    public PlayListAdapter(List<PlayList> playlistListesi, Context mContext) {
        this.playlistListesi = playlistListesi;
        this.mContext = mContext;
    }



    public class PlaylistCardDesignTutucu extends RecyclerView.ViewHolder{
        private PlaylistCardDesignBinding design;

        public PlaylistCardDesignTutucu(PlaylistCardDesignBinding binding) {
            super(binding.getRoot());
            this.design = binding;
        }
    }

    @NonNull
    @Override
    public PlaylistCardDesignTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlaylistCardDesignBinding binding = PlaylistCardDesignBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new PlaylistCardDesignTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistCardDesignTutucu holder, int position) {

        PlayList playList = playlistListesi.get(position);
        PlaylistCardDesignBinding binding = holder.design;

        binding.ivPlayList.setImageResource(mContext.getResources().getIdentifier(playList.getPlayListImage(),"drawable",mContext.getPackageName()));
        binding.textViewList.setText(playList.getPlayListName());



    }

    @Override
    public int getItemCount() {
        return playlistListesi.size();
    }
}