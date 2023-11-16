package com.example.spotifycloneodev6.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifycloneodev6.data.entity.PlayList;
import com.example.spotifycloneodev6.databinding.PlaylistRecommendDesignBinding;

import java.util.List;

public class PlayListRecommendAdapter extends RecyclerView.Adapter<PlayListRecommendAdapter.PlayListRecommendHolder> {
    private List<PlayList> playlistListesi;
    private Context mContext;

    public PlayListRecommendAdapter(List<PlayList> playlistListesi, Context mContext) {
        this.playlistListesi = playlistListesi;
        this.mContext = mContext;
    }



    public class PlayListRecommendHolder extends RecyclerView.ViewHolder{
        private PlaylistRecommendDesignBinding binding;

        public PlayListRecommendHolder(PlaylistRecommendDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public PlayListRecommendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlaylistRecommendDesignBinding binding = PlaylistRecommendDesignBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new PlayListRecommendHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull PlayListRecommendHolder holder, int position) {

        PlayList playList = playlistListesi.get(position);
        PlaylistRecommendDesignBinding binding = holder.binding;

        binding.ivPlayList.setImageResource(mContext.getResources().getIdentifier(playList.getPlayListImage(),"drawable",mContext.getPackageName()));
        binding.tvRecommend.setText(playList.getPlayListName());

    }

    @Override
    public int getItemCount() {
        return playlistListesi.size();
    }
}
