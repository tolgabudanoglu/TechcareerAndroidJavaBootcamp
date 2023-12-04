package com.example.lezzetkapida;

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
import com.example.lezzetkapida.ui.viewModel.SignInViewModel;
import com.example.lezzetkapida.ui.viewModel.SignUpViewModel;
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





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentSignInBinding.inflate(inflater, container, false);





        binding.btnLogin.setOnClickListener(v -> {
                SignIn();
        });











        return binding.getRoot();


    }

    public void SignIn(){
        viewModel.signInWithEmailAndPassword(binding.etMailLogin.getText().toString(), binding.etPasswordLogin.getText().toString(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Authentication succes.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Giriş başarısız ise, kullanıcıya bir mesaj gösterin
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(getContext(), "Authentication failed." + errorMessage,
                            Toast.LENGTH_SHORT).show();
                    // updateUI(null);
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