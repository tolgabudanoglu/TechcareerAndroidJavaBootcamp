package com.example.lezzetkapida.ui.signUp.viewModel;

import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


public class SignUpViewModel extends ViewModel {
    private FirebaseAuth mAuth;

    public SignUpViewModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void createUserWithEmailAndPassword(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener);
    }
}
