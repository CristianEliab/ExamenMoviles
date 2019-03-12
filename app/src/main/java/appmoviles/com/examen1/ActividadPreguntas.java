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
    private static final int[] posiblesPuntos_dificiles = {20, 30, 40, 50};
    private static final int[] posiblesPuntos_faciles = {5, 10, 15, 20};

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
    private int puntos_correcto_dificil;
    private int puntos_correcto_faciles;
    private ImageView refresh;
    private ImageView return_;
    private int variable_puntos;
    boolean dificil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_preguntas);

        init();
    }

    private void init() {
        variable_puntos = 0;
        random = new Random();

        pregunta1 = findViewById(R.id.pregunta1);
        pregunta2 = findViewById(R.id.pregunta2);
        pregunta3 = findViewById(R.id.pregunta3);
        pregunta4 = findViewById(R.id.pregunta4);
        pregunta5 = findViewById(R.id.pregunta5);
        grupoPreguntas = findViewById(R.id.respuestas_preguntas);

        eleccion = findViewById(R.id.boton_eleccion_preguntas);
        eleccion.setOnClickListener(this);

        pregunta_texto = findViewById(R.id.pregunta);
        puntos = findViewById(R.id.puntos);

        relativeLayout = findViewById(R.id.background);

        puntos_correcto_dificil = posiblesPuntos_dificiles[random.nextInt(((MAX_OP - MIN_OP) + 1) + MIN_OP)];
        puntos_correcto_faciles = posiblesPuntos_faciles[random.nextInt(((MAX_OP - MIN_OP) + 1) + MIN_OP)];

        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(this);

        return_ = findViewById(R.id.return_);
        return_.setOnClickListener(this);

        generarActividad();
    }

    private void generarActividad() {
        int temp = variable_puntos;
        Intent datos = getIntent();
        // Inicializar los puntos
        String dificultad = datos.getExtras().getString("dificultad");
        variable_puntos = Integer.parseInt(datos.getExtras().getString("PUNTAJE"));
        temp = temp - variable_puntos ;
        variable_puntos = variable_puntos + temp;
        puntos.setText("Puntos: " + variable_puntos);

        imagen_respuesta = findViewById(R.id.imagen_preguntas);

        if (dificultad.equals("100")) {
            generarPreguntasDificiles();
            dificil = true;
        } else {
            generarPreguntasFaciles();
            dificil = false;
        }

        if(dificil){
            imagen_respuesta.setImageResource(R.mipmap.ic_problem);
        }else{
            imagen_respuesta.setImageResource(R.mipmap.ic_easy);
        }

    }


    private void generarPreguntasDificiles() {

        char operador1 = operaciones[random.nextInt(((MAX_OP - MIN_OP) + 1) + MIN_OP)];
        char operador2 = operaciones[random.nextInt(((MAX_OP - MIN_OP) + 1) + MIN_OP)];

        int numero1 = random.nextInt(((MAX_NUM - MIN_NUM) + 1) + MIN_NUM);
        int numero2 = random.nextInt(((MAX_NUM - MIN_NUM) + 1) + MIN_NUM);
        int numero3 = random.nextInt(((MAX_NUM - MIN_NUM) + 1) + MIN_NUM);

        switch (operador1) {
            case '*':
                respuesta_correcta = numero1 * numero2;
                break;
            case '/':
                if (numero2 != 0) {
                    respuesta_correcta = numero1 / numero2;
                }
                break;
            case '-':
                respuesta_correcta = numero1 - numero2;
                break;
            case '+':
                respuesta_correcta = numero1 + numero2;
                break;
        }

        switch (operador2) {
            case '*':
                respuesta_correcta = respuesta_correcta * numero3;
                break;
            case '/':
                if (numero3 != 0) {
                    respuesta_correcta = respuesta_correcta / numero3;
                }
                break;
            case '-':
                respuesta_correcta = respuesta_correcta - numero3;
                break;
            case '+':
                respuesta_correcta = respuesta_correcta + numero3;
                break;
        }

        pregunta = "(" + numero1 + operador1 + numero2 + ")" + operador2 + numero3;
        pregunta_texto.setText("Pregunta: " + "\n" + pregunta);

        randomRespuestas(numero1, numero2, 0);
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
                if (numero2 != 0) {
                    respuesta_correcta = numero1 / numero2;
                }
                break;
            case '-':
                respuesta_correcta = numero1 - numero2;
                break;
            case '+':
                respuesta_correcta = numero1 + numero2;
                break;
        }

        pregunta = "(" + numero1 + operador1 + numero2 + ")";
        pregunta_texto.setText("Pregunta: " + "\n" + pregunta);

        randomRespuestas(numero1, numero2, 0);
    }


    private void randomRespuestas(int numero1, int numero2, int numero3) {

        List<String> listaRandom = new ArrayList<String>();

        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(1);
        String pregunta1_text = format.format(respuesta_correcta);
        String pregunta2_text = format.format((respuesta_correcta - numero1));
        String pregunta3_text = format.format((respuesta_correcta + numero2));
        String pregunta4_text = format.format((respuesta_correcta - numero2));
        String pregunta5_text = format.format((respuesta_correcta + numero1));

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
        if (v.equals(refresh)) {
            generarActividad();
            desbloquearRespuesta();
            configuracionNormal();
        } else if (v.equals(return_)) {
            Intent intent = new Intent();
            intent.putExtra("PUNTAJE_GLOBAL", "" + variable_puntos);
            setResult(2, intent);
            finish();//finishing activity
        } else {
            int radioButtonID = grupoPreguntas.getCheckedRadioButtonId();
            RadioButton radioButton = grupoPreguntas.findViewById(radioButtonID);
            String respuesta_cliente = radioButton.getText().toString();

            if (respuesta_cliente.equals(respuesta_correcta + "")) {
                configuracionesAcerto();
                calcularPuntaje();
            } else {
                configuracionesError();
            }
            bloquearRespuesta();


        }
    }

    private void configuracionNormal() {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorback));
        eleccion.setBackgroundColor(getResources().getColor(R.color.colorcorrect));
    }

    private void desbloquearRespuesta() {
        eleccion.setEnabled(true);
    }

    private void bloquearRespuesta() {
        eleccion.setEnabled(false);
    }

    private void calcularPuntaje() {
        if (dificil) {
            variable_puntos = variable_puntos + puntos_correcto_dificil;
            puntos.setText("");
            puntos.setText("Puntos: "+variable_puntos + "");
        } else {
            variable_puntos = variable_puntos + puntos_correcto_faciles;
            puntos.setText("");
            puntos.setText("Puntos: "+variable_puntos + "");
        }
    }

    private void configuracionesError() {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorincorrect));
        eleccion.setBackgroundColor(getResources().getColor(R.color.color_gris));
    }

    private void configuracionesAcerto() {
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorcorrect));
        eleccion.setBackgroundColor(getResources().getColor(R.color.color_gris));
        pregunta1.setTextColor(getResources().getColor(R.color.colorblack));
        pregunta2.setTextColor(getResources().getColor(R.color.colorblack));
        pregunta3.setTextColor(getResources().getColor(R.color.colorblack));
        pregunta4.setTextColor(getResources().getColor(R.color.colorblack));
        pregunta5.setTextColor(getResources().getColor(R.color.colorblack));

        TextView titulo = findViewById(R.id.titulo_preguntas);
        titulo.setTextColor(getResources().getColor(R.color.colorblack));
        TextView pregunta = findViewById(R.id.pregunta);
        pregunta.setTextColor(getResources().getColor(R.color.colorblack));
        TextView puntos = findViewById(R.id.puntos);
        puntos.setTextColor(getResources().getColor(R.color.colorblack));
    }
}
