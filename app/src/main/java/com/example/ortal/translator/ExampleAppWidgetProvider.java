package com.example.ortal.translator;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Locale;

public class ExampleAppWidgetProvider extends AppWidgetProvider implements
        TextToSpeech.OnInitListener {
    Context CONTEXT;

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        CONTEXT = context;
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch ExampleActivity
            Intent intent = new Intent(context, MyActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_layout);
            views.setOnClickPendingIntent(R.id.button, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
    }


    public void ExceptSpeechInput(View view) {

        // Starts an Activity that will convert speech to text
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Use a language model based on free-form speech recognition
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        // Recognize speech based on the default speech of device
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        // Prompt the user to speak
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
             "what to do here?"  );// getString(R.string.speech_input_phrase)
        PendingIntent pendingIntent = PendingIntent.getActivity(CONTEXT, 0, intent, 0);
        try{

            RemoteViews views = new RemoteViews(CONTEXT.getPackageName(), R.layout.app_widget_layout);
            views.setOnClickPendingIntent(R.id.button, pendingIntent);

        } catch (ActivityNotFoundException e){

            Toast.makeText(CONTEXT,"some error" , Toast.LENGTH_LONG).show();


        }

    }


    @Override
    public void onInit(int status) {

    }
}