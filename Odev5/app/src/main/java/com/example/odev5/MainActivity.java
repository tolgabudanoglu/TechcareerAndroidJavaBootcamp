package com.example.odev5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.odev5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //basılan butonları yazdırma
        binding.btn0.setOnClickListener(v -> clickWrite(true, "0"));
        binding.btn1.setOnClickListener(v -> clickWrite(true, "1"));
        binding.btn2.setOnClickListener(v -> clickWrite(true, "2"));
        binding.btn3.setOnClickListener(v -> clickWrite(true, "3"));
        binding.btn4.setOnClickListener(v -> clickWrite(true, "4"));
        binding.btn5.setOnClickListener(v -> clickWrite(true, "5"));
        binding.btn6.setOnClickListener(v -> clickWrite(true, "6"));
        binding.btn7.setOnClickListener(v -> clickWrite(true, "7"));
        binding.btn8.setOnClickListener(v -> clickWrite(true, "8"));
        binding.btn9.setOnClickListener(v -> clickWrite(true, "9"));
        binding.btnDot.setOnClickListener(v -> clickWrite(true, "."));
        binding.btnPlus.setOnClickListener(v -> clickWrite(false, "+"));


        binding.btnBackSpaceClear.setOnClickListener(v -> backClear() );
        binding.btnClear.setOnClickListener(v -> clear() );
        binding.btnEqual.setOnClickListener(v -> calculate() );

    }

    private void backClear() {

        TextView tvInput = binding.tvInput;
        if (tvInput.length() > 0){
            tvInput.setText(tvInput.getText().subSequence(0,tvInput.length()-1));
        }
    }

    private void calculate() {

        TextView tvInput = binding.tvInput;
        TextView tvOutput = binding.tvOutput;


            String inputString = tvInput.getText().toString();
            String[] numbers = inputString.split("\\+");

            double sum = 0;

            for (String newNumbers : numbers){
                try {
                    double splitNumber = Double.parseDouble(newNumbers.trim());
                    sum += splitNumber;
                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Geçersiz sayı formatı: " + newNumbers, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            tvOutput.setText(Double.toString(sum));
        }



    private void clear() {

        binding.tvInput.setText("");
        binding.tvOutput.setText("");
    }

    private void clickWrite(boolean check, String number) {

        TextView tvOutput = binding.tvOutput;
        TextView tvInput = binding.tvInput;

        if (check) {
            tvOutput.setText("");
            tvInput.append(number);
        } else {
            tvInput.append(tvOutput.getText());
            tvInput.append(number);
            tvOutput.setText("");
        }


    }
}