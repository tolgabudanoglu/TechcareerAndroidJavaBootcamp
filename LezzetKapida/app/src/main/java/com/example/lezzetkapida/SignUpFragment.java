package com.example.lezzetkapida;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lezzetkapida.databinding.FragmentSignUpBinding;
import com.example.lezzetkapida.ui.viewModel.SignUpViewModel;
import com.example.lezzetkapida.utils.Listeners;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;
    private SignUpViewModel viewModel;
    private FirebaseAuth mAuth;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);



        binding.btnSign.setOnClickListener(v -> {
            String mail = binding.etMail.getText().toString();
            String password = binding.etPassword.getText().toString();
            String passwordAgain = binding.etPasswordAgain.getText().toString();

            Log.e("mail",mail);

            if (mail.isEmpty() || password.isEmpty() || passwordAgain.isEmpty()) {
                Log.e("mail",mail);
                Toast.makeText(getContext(), "Lütfen boş alanları doldurun.", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(passwordAgain)) {
                Toast.makeText(getContext(), "Şifreler eşleşmiyor.", Toast.LENGTH_SHORT).show();
            } else {
                SignUp(v);
            }
        });

        return binding.getRoot();
    }
    public void SignUp(View v){

        viewModel.createUserWithEmailAndPassword(binding.etMail.getText().toString(), binding.etPassword.getText().toString(), task -> {
            if (task.isSuccessful()){
                Toast.makeText(getContext(), "başarılı", Toast.LENGTH_SHORT).show();
                Listeners.SignUpToSignIn(v);
            }else {
                // Başarısızsa, kullanıcıya bir mesaj gösterin
                String errorMessage = task.getException().getMessage();
                Toast.makeText(getContext(), "Authentication failed." + errorMessage,
                        Toast.LENGTH_SHORT).show();
                // updateUI(null);
            }
        });

    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
    }
}