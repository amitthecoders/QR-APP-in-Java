package net.illusionuniverse.qrcodescanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    EditText qrvalue;
    Button generateBtn,scanBtn;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrvalue = findViewById(R.id.qrInput);
        generateBtn = findViewById(R.id.generateBtn);
        scanBtn = findViewById(R.id.scanBtn);
        qrImage = findViewById(R.id.qrPlaceHolder);

        generateBtn.setOnClickListener(v -> {
            String data = qrvalue.getText().toString();
            if(data.isEmpty()){
                qrvalue.setError("Value Required.");
            }else {
                QRGEncoder qrgEncoder = new QRGEncoder(data,null, QRGContents.Type.TEXT,500);
                try {
                    Bitmap qrBits = qrgEncoder.encodeAsBitmap();
                    qrImage.setImageBitmap(qrBits);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        scanBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Scanner.class)));



    }
}
