package com.example.spotifycloneodev6.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spotifycloneodev6.data.entity.Menu;
import com.example.spotifycloneodev6.data.entity.PlayList;
import com.example.spotifycloneodev6.data.entity.PlayListContiune;
import com.example.spotifycloneodev6.databinding.FragmentHomePageBinding;
import com.example.spotifycloneodev6.ui.adapter.MenuAdapter;
import com.example.spotifycloneodev6.ui.adapter.PlayListAdapter;
import com.example.spotifycloneodev6.ui.adapter.PlayListContiuneAdapter;
import com.example.spotifycloneodev6.ui.adapter.PlayListRecommendAdapter;

import java.util.ArrayList;

public class HomePageFragment extends Fragment {

    private FragmentHomePageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomePageBinding.inflate(inflater, container, false);


        binding.rvPlayList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        ArrayList<PlayList> playLists = new ArrayList<>();
        PlayList pl1 = new PlayList(1,"Beğenilen Şarkılar","best_music");
        PlayList pl2 = new PlayList(2,"Acoustic Rock","acoustic_rock");
        PlayList pl3 = new PlayList(3,"Baldur Gate 3 (Original Game Soundtrack)","best_of_baldurs_gate_3_ost");
        PlayList pl4 = new PlayList(4,"Best Of Rock 1971","best_of_rock_1971");
        PlayList pl5 = new PlayList(5,"Heavy Ballads","heavy_ballads");
        PlayList pl6 = new PlayList(6,"Legendary Guitar Solos","legendary_guitar_solos");
        playLists.add(pl1);
        playLists.add(pl2);
        playLists.add(pl3);
        playLists.add(pl4);
        playLists.add(pl5);
        playLists.add(pl6);

        PlayListAdapter playListAdapter = new PlayListAdapter(playLists,requireContext());
        binding.rvPlayList.setAdapter(playListAdapter);


        binding.rvPlaylistsContiune.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        ArrayList<PlayListContiune> playListContiuneArrayList = new ArrayList<>();
        PlayListContiune plc1 = new PlayListContiune(1,"Heavy Metal","heavy_metal","Judas Priest, Black Sabbath, Ozzy Osbourne");
        PlayListContiune plc2 = new PlayListContiune(2,"Rock Covers","rock_covers","Guns N' Roses,Van Halen,Three Days Grace");
        PlayListContiune plc3 = new PlayListContiune(3,"80s Hard Rock","_80s_hard_rock","Van Halen,Guns N' Roses,Ozzy Osbourne  ");

        playListContiuneArrayList.add(plc1);
        playListContiuneArrayList.add(plc2);
        playListContiuneArrayList.add(plc3);

        PlayListContiuneAdapter playListContiuneAdapter = new PlayListContiuneAdapter(playListContiuneArrayList,requireContext());
        binding.rvPlaylistsContiune.setAdapter(playListContiuneAdapter);


        binding.toolbarRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        ArrayList<Menu> menuArrayList = new ArrayList<>();
        Menu menu1 = new Menu(1,"Tümü");
        Menu menu2 = new Menu(2,"Müzik");
        Menu menu3 = new Menu(3,"Podcast'ler ve Programlar");

        menuArrayList.add(menu1);
        menuArrayList.add(menu2);
        menuArrayList.add(menu3);

        MenuAdapter menuAdapter = new MenuAdapter(menuArrayList,requireContext());
        binding.toolbarRv.setAdapter(menuAdapter);


        binding.rvRecommendPlayList.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        ArrayList<PlayList> playListRecommend = new ArrayList<>();
        PlayList playList1 = new PlayList(1,"Scorpion,Black Sabbath,Rainbow ve daha fazlası","daily_mix_1");
        PlayList playList2 = new PlayList(2,"Bonnie Tyler,Nirvana,Ace of Base ve daha fazlası","daily_mix_2");
        PlayList playList3 = new PlayList(3,"Cem karaca, Hayko Cepkin,manga ve daha fazlası","daily_mix_3");
        PlayList playList4 = new PlayList(4,"KISS, Queen, The Doors ve daha fazlası","daily_mix_4");
        PlayList playList5 = new PlayList(5,"Ghost, Megadeth,Metallica ve daha fazlası","daily_mix_5");
        PlayList playList6 = new PlayList(6,"Marilyn Manson,Saturnus,New Years Day ve daha fazlası","daily_mix_6");
        playListRecommend.add(playList1);
        playListRecommend.add(playList2);
        playListRecommend.add(playList3);
        playListRecommend.add(playList4);
        playListRecommend.add(playList5);
        playListRecommend.add(playList6);

        PlayListRecommendAdapter playListRecommendAdapter = new PlayListRecommendAdapter(playListRecommend,requireContext());
        binding.rvRecommendPlayList.setAdapter(playListRecommendAdapter);











        return binding.getRoot();
    }

}