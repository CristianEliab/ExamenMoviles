package appmoviles.com.examen1;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Dialogo  extends DialogFragment{

    //widgets
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    public String inputvalue;
    public int puntos;
    private ImageView qrCode;
    private ImageView saveQr;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmap;

    public Dialogo() {
    }

    //vars

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add, container, false);

        TextView textView = view.findViewById(R.id.text);
        textView.setText("With this code you can make exchanges in the icesi shop" + "\n" + inputvalue);

        qrCode = view.findViewById(R.id.qrCode);

        generateCode(inputvalue, puntos);

        return view;
    }

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static Dialogo newInstance(int num) {
        Dialogo f = new Dialogo();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);
        return f;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
        }catch (ClassCastException e){
        }
    }

    private void generateCode(String inputValue, int puntos){
        qrgEncoder = new QRGEncoder(
                inputValue, null,
                QRGContents.Type.TEXT,
                puntos);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            //
        }
    }
}
