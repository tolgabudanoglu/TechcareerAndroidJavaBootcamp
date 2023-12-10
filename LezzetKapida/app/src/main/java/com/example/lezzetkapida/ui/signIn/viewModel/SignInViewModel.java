package com.example.lezzetkapida.ui.signIn.viewModel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


public class SignInViewModel extends ViewModel {

    private FirebaseAuth mAuth;


    public SignInViewModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void signInWithEmailAndPassword(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener);
    }
}
