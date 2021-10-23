package com.example.conncurso_de_tartas.view;



import static com.example.conncurso_de_tartas.core.constants.Constants.ANIMATION_LENGTH_TIME;
import static com.example.conncurso_de_tartas.core.ex.ActivityExtensionsKt.showLongToast;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.conncurso_de_tartas.R;
import com.example.conncurso_de_tartas.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    //Declaramos las variables
    private ActivityMainBinding binding; //Utilizamos una vinculacion directa al xml que es comodo y eficiente


    @Override
    protected void onCreate(Bundle savedInstanceState) {  // Funcion oncreate desde aqui se crea la actividad
        super.onCreate(savedInstanceState);
        setUpLayout();
        setUpListeners();
    }

    private void setUpLayout() {  // Inflamos la vista desde la clase autogenerada activitimain
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /*
    Clase para el escuchador del boton SUBMIT y la animacion del EditText una vez el chech box se
    selecione y deselecione
     */

    private void setUpListeners() {
        binding.btnSubmit.setOnClickListener(view -> initSubmitLogic());//view binding de boton submit con funcion lamda llamando a la clase

        binding.cbHasParticipated.setOnCheckedChangeListener((compoundButton, b) -> { //Logica de la animacion del Checkbox
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

    /*
    Logica en la cual se leen todos los datos nombre,apellido , edad,  sexo etc..
    -comprueba si todos los campos estan rellenados con los valores que necesitan.
    -Comprueba si es mayor de 17 años
    -Comprueba si el EditText ha puesto otro concurso de tartas
    -Si los datos son incoorectos se printea unos Toast especificos por cada error
     */

    private void initSubmitLogic() {
        if (isNameOrSurnameValid(Objects.requireNonNull(binding.tilName.getEditText()).getText().toString()) &&
                isNameOrSurnameValid(Objects.requireNonNull(binding.tilSurname.getEditText()).getText().toString()) &&
                binding.radioGroup.getCheckedRadioButtonId() != -1) {
            if (isAgeValid(Objects.requireNonNull(binding.tilAge.getEditText()).getText().toString())) {
                if (binding.cbHasParticipated.isChecked()) {
                    if (Objects.requireNonNull(binding.tilHasParticipated.getEditText()).getText().toString().isEmpty()) {
                        showLongToast(this, getString(R.string.old_contest_name));
                    } else {
                        showLongToast(this, getString(R.string.registration_completed));
                    }

                } else {
                    showLongToast(this, getString(R.string.registration_completed));
                }

            } else {
                showLongToast(this, getString(R.string.make_sure_you_are_older_18));
            }

        } else {
            showLongToast(this, getString(R.string.make_sure_your_data_is_not_empty));
            /*
            He extraido en values/strings todos los texxtos printeados y los exporto con R.string para que no este hardcodeado
             */
        }
    }




    //Validators : estos comprueban quer no esten vacios los EditText y que la edad sea la correcta


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
