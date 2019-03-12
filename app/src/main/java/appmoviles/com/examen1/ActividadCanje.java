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


public class ActividadCanje extends AppCompatActivity implements View.OnClickListener {

    private static final String NOMBRE_ARTICULO_1 = "Lapicero Icesi";
    private static final String NOMBRE_ARTICULO_2 = "Cuaderno";
    private static final String NOMBRE_ARTICULO_3 = "Libreta Icesi";
    private static final String NOMBRE_ARTICULO_4 = "Camiseta Icesi";
    private static final String NOMBRE_ARTICULO_5 = "Saco Icesi";

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

        puntos.setText("Puntos: " + variable_puntos);
    }

    private void init() {

        puntos = findViewById(R.id.puntaje);

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
        if(v.equals(return_)){
            Intent intent = new Intent();
            intent.putExtra("PUNTAJE_GLOBAL", "" + variable_puntos);
            setResult(2, intent);
            finish();//finishing activity
        }else{
            // Dialogo del Código
            DialogFragment dialog = new Dialogo();
            String lugar = "";
            ((Dialogo) dialog).lugarCercano = lugar;
            dialog.show(getSupportFragmentManager(), "MyCustomDialog");
        }
    }

}
