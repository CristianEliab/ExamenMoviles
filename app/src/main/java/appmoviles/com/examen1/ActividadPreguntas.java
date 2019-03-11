package appmoviles.com.examen1;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ActividadPreguntas extends AppCompatActivity implements View.OnClickListener {

    private static final char[] operaciones = {'*', '/', '-', '+'};
    private static final String[] preguntas = {};
    private static final int MAX_OP = 3;
    private static final int MIN_OP = 0;
    private static final int MAX_NUM = 100;
    private static final int MIN_NUM = 10;
    private static final int[] posiblesPuntos = {5, 10, 15, 20, 25};


    private Random random;
    private RadioButton pregunta1;
    private RadioButton pregunta2;
    private RadioButton pregunta3;
    private RadioButton pregunta4;
    private RadioButton pregunta5;
    private RadioGroup grupoPreguntas;
    private Button eleccion;
    private TextView pregunta_texto;
    private TextView puntos;
    private int respuesta_correcta;
    private String pregunta;
    private ImageView imagen_respuesta;
    private RelativeLayout relativeLayout;
    private int puntos_totales;
    private int correcto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_preguntas);

        init();
    }

    private void init() {

        random = new Random();

        pregunta1 = findViewById(R.id.pregunta1);
        pregunta2 = findViewById(R.id.pregunta2);
        pregunta3 = findViewById(R.id.pregunta3);
        pregunta4 = findViewById(R.id.pregunta4);
        pregunta5 = findViewById(R.id.pregunta5);
        grupoPreguntas = findViewById(R.id.respuestas_preguntas);

        eleccion = findViewById(R.id.boton_eleccion_preguntas);
        imagen_respuesta = findViewById(R.id.imagen_preguntas);

        pregunta_texto = findViewById(R.id.pregunta);
        puntos = findViewById(R.id.puntos);

        relativeLayout = findViewById(R.id.background);

        Intent datos = getIntent();
        String dificultad = datos.getExtras().getString("dificultad");
        if (dificultad.equals("100")) {
            generarPreguntasDificiles();
        } else {
            generarPreguntasFaciles();
        }


        eleccion.setOnClickListener(this);
    }

    private void generarPreguntasDificiles() {

        char operador1 = operaciones[random.nextInt(((MAX_OP - MIN_OP) + 1) + MIN_OP)];
        char operador2 = operaciones[random.nextInt(((MAX_OP - MIN_OP) + 1) + MIN_OP)];

        int numero1 = random.nextInt(((MAX_NUM - MIN_NUM) + 1) + MIN_NUM);
        int numero2 = random.nextInt(((MAX_NUM - MIN_NUM) + 1) + MIN_NUM);
        int numero3 = random.nextInt(((MAX_NUM - MIN_NUM) + 1) + MIN_NUM);

        switch (operador1) {
            case '*':
                respuesta_correcta += numero1 * numero2;
                break;
            case '/':
                respuesta_correcta += numero1 / numero2;
                break;
            case '-':
                respuesta_correcta += numero1 - numero2;
                break;
            case '+':
                respuesta_correcta += numero1 + numero2;
                break;
        }

        switch (operador2) {
            case '*':
                respuesta_correcta += numero3 * respuesta_correcta;
                break;
            case '/':
                respuesta_correcta += numero3 / respuesta_correcta;
                break;
            case '-':
                respuesta_correcta += numero3 - respuesta_correcta;
                break;
            case '+':
                respuesta_correcta += numero3 + respuesta_correcta;
                break;
        }

        correcto = respuesta_correcta;

        pregunta = "" + numero1 + operador1 + numero2 + operador2 + numero3;
        pregunta_texto.setText("Pregunta: " + "\n" + pregunta);

        List<String> listaRandom = new ArrayList<String>();


        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(1);
        String pregunta1_text = format.format(respuesta_correcta);
        String pregunta2_text = format.format((respuesta_correcta-numero1));
        String pregunta3_text = format.format((respuesta_correcta+numero2));
        String pregunta4_text = format.format((respuesta_correcta-numero3));
        String pregunta5_text = format.format((respuesta_correcta+numero2));

        listaRandom.add(pregunta1_text);
        listaRandom.add(pregunta2_text);
        listaRandom.add(pregunta3_text);
        listaRandom.add(pregunta4_text);
        listaRandom.add(pregunta5_text);

        Collections.shuffle(listaRandom);

        pregunta1.setText(listaRandom.get(0));
        pregunta2.setText(listaRandom.get(1));
        pregunta3.setText(listaRandom.get(2));
        pregunta4.setText(listaRandom.get(3));
        pregunta5.setText(listaRandom.get(4));

    }

    private void generarPreguntasFaciles() {
        char operador1 = operaciones[random.nextInt(((MAX_OP - MIN_OP) + 1) + MIN_OP)];

        int numero1 = random.nextInt(((MAX_NUM - MIN_NUM) + 1) + MIN_NUM);
        int numero2 = random.nextInt(((MAX_NUM - MIN_NUM) + 1) + MIN_NUM);

        switch (operador1) {
            case '*':
                respuesta_correcta = numero1 * numero2;
                break;
            case '/':
                respuesta_correcta = numero1 / numero2;
                break;
            case '-':
                respuesta_correcta = numero1 - numero2;
                break;
            case '+':
                respuesta_correcta = numero1 + numero2;
                break;
        }

        pregunta = "" + numero1 + operador1 + numero2;
        pregunta_texto.setText("Pregunta: " + "\n" + pregunta);

        correcto = respuesta_correcta;

        List<String> listaRandom = new ArrayList<String>();

        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(1);
        String pregunta1_text = format.format(respuesta_correcta);
        String pregunta2_text = format.format((respuesta_correcta-numero1));
        String pregunta3_text = format.format((respuesta_correcta+numero2));
        String pregunta4_text = format.format((respuesta_correcta-numero2));
        String pregunta5_text = format.format((respuesta_correcta+numero1));

        listaRandom.add(pregunta1_text);
        listaRandom.add(pregunta2_text);
        listaRandom.add(pregunta3_text);
        listaRandom.add(pregunta4_text);
        listaRandom.add(pregunta5_text);

        Collections.shuffle(listaRandom);

        pregunta1.setText(listaRandom.get(0));
        pregunta2.setText(listaRandom.get(1));
        pregunta3.setText(listaRandom.get(2));
        pregunta4.setText(listaRandom.get(3));
        pregunta5.setText(listaRandom.get(4));
    }

    @Override
    public void onClick(View v) {
        int radioButtonID = grupoPreguntas.getCheckedRadioButtonId();
        RadioButton radioButton = grupoPreguntas.findViewById(radioButtonID);
        String respuesta_cliente = radioButton.getText().toString();

        if(respuesta_cliente.equals(correcto+"")){
            imagen_respuesta.setImageResource(R.mipmap.ic_correct);
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorcorrect));
            eleccion.setBackgroundColor(getResources().getColor(R.color.colorback));
        }else{
            imagen_respuesta.setImageResource(R.mipmap.ic_incorrect);
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorincorrect));
            eleccion.setBackgroundColor(getResources().getColor(R.color.colorback));
        }
    }
}
