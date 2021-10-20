package com.example.conncurso_de_tartas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.CompoundButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.conncurso_de_tartas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String name;
    private String surname;
    private String age;
    private String gender;
    private String oldContestName;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpListeners();
    }

    private void setUpListeners() {
        binding.btnSubmit.setOnClickListener(view -> initSubmitLogic());

        binding.cbHasParticipated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    YoYo.with(Techniques.SlideInDown).duration(777).playOn(binding.tilHasParticipated);
                    binding.tilHasParticipated.setVisibility(View.VISIBLE);
                } else {
                    YoYo.with(Techniques.SlideOutUp).duration(777).playOn(binding.tilHasParticipated);
                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.tilHasParticipated.setVisibility(View.GONE);
                        }
                    }, 777);

                }

            }
        });

    }

    private void initSubmitLogic() {
        if (isNameOrSurnameValid(binding.tilName.getEditText().getText().toString()) &&
        isNameOrSurnameValid(binding.tilSurname.getEditText().getText().toString()) &&
        isAgeValid(binding.tilAge.getEditText().getText().toString()) &&
        binding.radioGroup.getCheckedRadioButtonId() != -1) {
            if (binding.cbHasParticipated.isChecked()){

            }
        }
    }

    //Validators
    private Boolean isNameOrSurnameValid(String name) {
        try {
           if (Integer.parseInt(name) >= 0){
               return false;
           } else return !name.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    private Boolean isAgeValid(String age) {
        try {
            return Integer.parseInt(age) >= 18;
        } catch (Exception e) {
            return false;
        }
    }
}
