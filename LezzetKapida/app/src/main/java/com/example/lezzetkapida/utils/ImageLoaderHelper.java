package com.example.lezzetkapida.utils;

import android.content.Context;
import android.widget.ImageView;

public class ImageLoaderHelper {
    public static void loadImage(Context context,ImageView imageView,String imgName) {
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+imgName;
        com.bumptech.glide.Glide.with(context).load(url).into(imageView);
    }

}
