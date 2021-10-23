package com.example.conncurso_de_tartas;

import static com.example.conncurso_de_tartas.Constants.ANIMATION_LENGTH_TIME;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.conncurso_de_tartas.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    //Declaramos las variables
    private ActivityMainBinding binding; //Utilizamos una vinculacion directa al xml qeu sera mas comodo y mas eficiente


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpListeners();
    }

    private void setUpListeners() {
        binding.btnSubmit.setOnClickListener(view -> initSubmitLogic());

        binding.cbHasParticipated.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                YoYo.with(Techniques.SlideInDown).duration(ANIMATION_LENGTH_TIME).playOn(binding.tilHasParticipated);
                binding.tilHasParticipated.setVisibility(View.VISIBLE);
            } else {
                YoYo.with(Techniques.SlideOutUp).duration(ANIMATION_LENGTH_TIME).playOn(binding.tilHasParticipated);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(() -> binding.tilHasParticipated.setVisibility(View.GONE), ANIMATION_LENGTH_TIME);
            }

        });

    }

    private void initSubmitLogic() {
        if (isNameOrSurnameValid(Objects.requireNonNull(binding.tilName.getEditText()).getText().toString()) &&
                isNameOrSurnameValid(Objects.requireNonNull(binding.tilSurname.getEditText()).getText().toString()) &&
                binding.radioGroup.getCheckedRadioButtonId() != -1) {
            if (isAgeValid(Objects.requireNonNull(binding.tilAge.getEditText()).getText().toString())) {
                if (binding.cbHasParticipated.isChecked()) {
                    if (Objects.requireNonNull(binding.tilHasParticipated.getEditText()).getText().toString().isEmpty()) {
                        showToast(getString(R.string.old_contest_name));
                    } else {
                        showToast(getString(R.string.registration_completed));
                    }

                } else {
                    showToast(getString(R.string.registration_completed));
                }

            } else {
                showToast(getString(R.string.make_sure_you_are_older_18));
            }

        } else {
            showToast(getString(R.string.make_sure_your_data_is_not_empty));
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    //Validators
    private Boolean isNameOrSurnameValid(String name) {
        try {
            if (Integer.parseInt(name) >= 0) {
                return false;
            } else return !name.isEmpty();
        } catch (Exception e) {
            return !name.isEmpty();
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
