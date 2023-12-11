package com.example.lezzetkapida.ui.splashScreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lezzetkapida.databinding.FragmentSplashScreenBinding;
import com.example.lezzetkapida.utils.Listeners;


public class SplashScreenFragment extends Fragment {

    private FragmentSplashScreenBinding binding;
    private static final long FRAGMENT_DELAY = 4000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                loadNextFragment();
            }
        }, FRAGMENT_DELAY);





        return binding.getRoot();
    }
    private void loadNextFragment() {
        Listeners.splashScreenToFirstScreen(getView());
    }
}
