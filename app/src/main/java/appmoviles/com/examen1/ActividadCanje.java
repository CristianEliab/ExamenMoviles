package appmoviles.com.examen1;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ActividadCanje extends AppCompatActivity implements View.OnClickListener {

    private static final String NOMBRE_ARTICULO_1 = "Icesi Pencil";
    private static final String NOMBRE_ARTICULO_2 = "Notebook";
    private static final String NOMBRE_ARTICULO_3 = "Icesi Notepad";
    private static final String NOMBRE_ARTICULO_4 = "Icesi T-shirt";
    private static final String NOMBRE_ARTICULO_5 = "Icesi Coat";

    private static final int VALOR_ARTICULO_1 = 20;
    private static final int VALOR_ARTICULO_2 = 30;
    private static final int VALOR_ARTICULO_3 = 40;
    private static final int VALOR_ARTICULO_4 = 80;
    private static final int VALOR_ARTICULO_5 = 100;

    private RadioButton articulo1;
    private RadioButton articulo2;
    private RadioButton articulo3;
    private RadioButton articulo4;
    private RadioButton articulo5;
    private RadioGroup grupoArticulos;
    private Button boton_eleccion;
    private ImageView return_;
    private TextView puntos;
    private int variable_puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_canje);

        init();
        datos();
    }

    private void datos() {
        Intent datos = getIntent();
        // Inicializar los puntos
        String dificultad = datos.getExtras().getString("dificultad");
        variable_puntos = Integer.parseInt(datos.getExtras().getString("PUNTAJE"));

        puntos.setText("Score: " + variable_puntos);
    }

    private void init() {

        puntos = findViewById(R.id.puntaje);

        grupoArticulos = findViewById(R.id.respuestas);

        articulo1 = findViewById(R.id.articulo1);
        articulo1.setText(NOMBRE_ARTICULO_1);

        articulo2 = findViewById(R.id.articulo2);
        articulo2.setText(NOMBRE_ARTICULO_2);

        articulo3 = findViewById(R.id.articulo3);
        articulo3.setText(NOMBRE_ARTICULO_3);

        articulo4 = findViewById(R.id.articulo4);
        articulo4.setText(NOMBRE_ARTICULO_4);

        articulo5 = findViewById(R.id.articulo5);
        articulo5.setText(NOMBRE_ARTICULO_5);

        boton_eleccion = findViewById(R.id.boton_eleccion);
        boton_eleccion.setOnClickListener(this);

        return_ = findViewById(R.id.return_canje);
        return_.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(return_)) {
            Intent intent = new Intent();
            intent.putExtra("PUNTAJE_GLOBAL", "" + variable_puntos);
            setResult(2, intent);
            finish();//finishing activity
        } else {
            int radioButtonID = grupoArticulos.getCheckedRadioButtonId();
            RadioButton radioButton = grupoArticulos.findViewById(radioButtonID);
            if (radioButton != null) {
                String respuesta_cliente = radioButton.getText().toString();
                //
                obtenerPuntos(respuesta_cliente);
                // Dialogo del Código
                DialogFragment dialog = new Dialogo();
                String lugar = "";
                ((Dialogo) dialog).inputvalue = respuesta_cliente;
                ((Dialogo) dialog).puntos = variable_puntos;
                dialog.show(getSupportFragmentManager(), "MyCustomDialog");
            } else {
                Toast.makeText(this, "Select one product, please!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void obtenerPuntos(String respuesta_cliente) {
        switch (respuesta_cliente) {
            case NOMBRE_ARTICULO_1:
                if (variable_puntos < VALOR_ARTICULO_1) {
                    Toast.makeText(this, "You don't have the necessary points!", Toast.LENGTH_SHORT).show();
                } else {
                    variable_puntos = variable_puntos - VALOR_ARTICULO_1;
                    puntos.setText("Score: " + variable_puntos);
                }
                break;
            case NOMBRE_ARTICULO_2:
                if (variable_puntos < VALOR_ARTICULO_2) {
                    Toast.makeText(this, "You don't have the necessary points!", Toast.LENGTH_SHORT).show();
                } else {
                    variable_puntos = variable_puntos - VALOR_ARTICULO_2;
                    puntos.setText("Score: " + variable_puntos);
                }
                break;
            case NOMBRE_ARTICULO_3:
                if (variable_puntos < VALOR_ARTICULO_3) {
                    Toast.makeText(this, "You don't have the necessary points!", Toast.LENGTH_SHORT).show();
                } else {
                    variable_puntos = variable_puntos - VALOR_ARTICULO_3;
                    puntos.setText("Score: " + variable_puntos);
                }
                break;
            case NOMBRE_ARTICULO_4:
                if (variable_puntos < VALOR_ARTICULO_4) {
                    Toast.makeText(this, "You don't have the necessary points!", Toast.LENGTH_SHORT).show();
                } else {
                    variable_puntos = variable_puntos - VALOR_ARTICULO_4;
                    puntos.setText("Score: " + variable_puntos);
                }
                break;
            case NOMBRE_ARTICULO_5:
                if (variable_puntos < VALOR_ARTICULO_5) {
                    Toast.makeText(this, "You don't have the necessary points!", Toast.LENGTH_SHORT).show();
                } else {
                    variable_puntos = variable_puntos - VALOR_ARTICULO_5;
                    puntos.setText("Score: " + variable_puntos);
                }
                break;
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("PUNTAJE_GLOBAL", "" + variable_puntos);
        setResult(2, intent);
        finish();//finishing activity
    }

}
