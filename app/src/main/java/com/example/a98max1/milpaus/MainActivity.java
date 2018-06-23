package com.example.a98max1.milpaus;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a98max1.milpaus.modelo.SpeechtoText;

public class MainActivity extends AppCompatActivity {

    private SpeechtoText stt;
    public static Context context;
    private boolean primeiraVez = true;
    public static TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subtitle = (TextView) findViewById(R.id.text);

        subtitle.setText("Asd");

        context = getApplicationContext();

        if (isPrimeiraVez()) {
            setSTT();
        }


    }

    public static Context getContext() {
        return context;
    }

    public boolean isPrimeiraVez() {
        return primeiraVez;
    }

    public void setPrimeiraVez(boolean primeiraVez) {
        this.primeiraVez = primeiraVez;
    }

    public void setSTT() {
        stt = new SpeechtoText();
        setPrimeiraVez(false);
    }

    public void onClickAdicionarProduto(View view) {
        Log.i("Text","clicou");
        stt.backgroundVoiceListener.run();
    }

    public static void setSubtitle(String txt) {
        subtitle.setText(txt);
    }
}
