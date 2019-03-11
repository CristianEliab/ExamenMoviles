package appmoviles.com.examen1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.Random;

public class ActividadPreguntas extends AppCompatActivity {

    private static final String[] operaciones = {"*", "/", "-", "+"};
    private static final String[] preguntas = {};

    private Random random;
    private RadioButton pregunta1;
    private RadioButton pregunta2;
    private RadioButton pregunta3;
    private RadioButton pregunta4;
    private RadioButton pregunta5;
    private Button eleccion;
    private int respuesta_correcta;
    private ImageView imagen_respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_preguntas);

        init();
    }

    private void init() {
        Intent datos = getIntent();
        String dificultad = datos.getExtras().getString("dificultad");

        if(dificultad.equals("100")){
            generarPreguntasDificiles();
        }else{
            generarPreguntasFaciles();
        }
    }

    private void generarPreguntasDificiles() {
    }

    private void generarPreguntasFaciles() {
    }
}
