package com.example.lezzetkapida.ui.signIn;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lezzetkapida.databinding.FragmentSignInBinding;
import com.example.lezzetkapida.ui.signIn.viewModel.SignInViewModel;
import com.example.lezzetkapida.utils.Listeners;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignInFragment extends Fragment {

    private FragmentSignInBinding binding;
    private SignInViewModel viewModel;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInOptions googleSignInOptions;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentSignInBinding.inflate(inflater, container, false);


        binding.btnLogin.setOnClickListener(v -> {
                SignIn(v);
        });
        binding.tvKayTOlLogin.setOnClickListener(v -> {
            Listeners.signInToSignUp(v);
        });

        return binding.getRoot();


    }
    public void SignIn(View view){
        viewModel.signInWithEmailAndPassword(binding.etMailLogin.getText().toString(), binding.etPasswordLogin.getText().toString(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Giriş başarılı",
                            Toast.LENGTH_SHORT).show();
                    Listeners.signInToHome(view);
                } else {

                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(getContext(), "Authentication failed." + errorMessage,
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);
    }
}