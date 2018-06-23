package com.example.a98max1.milpaus.modelo;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.TextView;

import com.example.a98max1.milpaus.MainActivity;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechtoText implements RecognitionListener{
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private MainActivity mainActivity;
    private String text = "";
    private boolean isListening = false;
    private int MINIMUM_LENGTH_FOR_EXTRA_SPEECH_IN_MILLIS = 3000;
    public final BackgroundVoiceListener  backgroundVoiceListener ; // o erro lançado por esta linha será corrigido mais tarde com o desenvolvimento da classe.
    public TextView subtitle;

    public SpeechtoText() {
        mainActivity = new MainActivity();

        backgroundVoiceListener  = new BackgroundVoiceListener();

        speech = SpeechRecognizer.createSpeechRecognizer(MainActivity.getContext()); // este erro também será resolvido no decorrer do tutorial.

        speech.setRecognitionListener(this);

        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,

        Locale.getDefault()); // leia logo abaixo a explicação desta linha.

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, mainActivity.getPackageName());

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, MINIMUM_LENGTH_FOR_EXTRA_SPEECH_IN_MILLIS);

    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {
        setListening(false);
    }

    @Override
    public void onBeginningOfSpeech() {
        setListening(true);
    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {
        setListening(false);
    }

    @Override
    public void onError(int i) {
        mainActivity.setSubtitle(text);
        Log.i("Text","text: " + text);
    }

    @Override
    public void onResults(Bundle bundle) {

    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        ArrayList<String> matches = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        text = "";
        if(matches!=null)
        for (String result : matches)
            text += result + "\n";

        MainActivity.setSubtitle(text);
        setListening(false);
    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }

    public boolean isListening() {
        return isListening;
    }

    public void setListening (boolean listening){
        isListening=listening;
    }

    public class BackgroundVoiceListener extends Thread{
        public void run(){
            try {
                this.sleep(2000);
                if(!isListening()){
                    setListening(true);
                    speech.startListening(recognizerIntent);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
