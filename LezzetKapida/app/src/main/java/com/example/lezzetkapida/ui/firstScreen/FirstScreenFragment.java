package com.example.lezzetkapida.ui.firstScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.lezzetkapida.databinding.FragmentFirstScreenBinding;
import com.example.lezzetkapida.utils.Listeners;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class FirstScreenFragment extends Fragment {

    private FragmentFirstScreenBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstScreenBinding.inflate(inflater, container, false);




        binding.btnGoogle.setOnClickListener(v -> {
            googleSignIn();
        });

        binding.btnSignInPage.setOnClickListener(v -> Listeners.firstScreenToSignIn(v));
        binding.btnSignUpPage.setOnClickListener(v -> Listeners.firstScreenToSignUp(v));
        return binding.getRoot();
    }

    private void googleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("709041619409-4nqp0pjg3tgoub02frfijeu1s4vhlnqd.apps.googleusercontent.com")
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);
        Intent signInIntent = googleSignInClient.getSignInIntent();
        signInLauncher.launch(signInIntent);
    }
    ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    handleSignInResult(task);
                } else {

                }
            }
    );
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);


            firebaseAuthWithGoogle(account.getIdToken());
        } catch (ApiException e) {

            Log.w("TAG", "Google sign in failed", e);

        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(requireContext(),"Giriş başarılı", Toast.LENGTH_SHORT).show();
                        Listeners.firstScreenToHome(requireView());
                    } else {
                        Toast.makeText(requireContext(),"Giriş başarısız", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}