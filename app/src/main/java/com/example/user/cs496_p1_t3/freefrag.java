package com.example.user.cs496_p1_t3;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by user on 2017-12-27.
 */

public class freefrag extends Fragment {

    public freefrag(){

    }
    MediaRecorder myAudioRecorder = null;
    ImageButton play,stop,record;
    boolean starting = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedlnstanceState){

        View view = inflater.inflate(R.layout.freefrag, container, false);
        play = (ImageButton)view.findViewById(R.id.play);
        stop = (ImageButton)view.findViewById(R.id.stop);
        record = (ImageButton)view.findViewById(R.id.record);

        play.setEnabled(false);
        stop.setEnabled(false);
        record.setEnabled(true);


        record.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(starting==false){
                    if(myAudioRecorder == null) myAudioRecorder = new MediaRecorder();
                    else myAudioRecorder.reset();
                }


                myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                myAudioRecorder.setOutputFile(getContext().getFilesDir()+"/audio.3gp");
               // if(myAudioRecorder!=null) myAudioRecorder.reset();
                try {
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (IllegalStateException ise) {
                } catch (IOException ioe) {

                }
                record.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(getContext(), "Start recording", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                myAudioRecorder.stop();
                myAudioRecorder.release();
                myAudioRecorder = null;
                stop.setEnabled(false);
                record.setEnabled(true);
                play.setEnabled(true);
                Toast.makeText(getContext(), "Finished recording", Toast.LENGTH_LONG).show();
            }

        });

        play.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                MediaPlayer mediaplayer = new MediaPlayer();

                try {
                    mediaplayer.setDataSource(getContext().getFilesDir()+"/audio.3gp");
                    mediaplayer.prepare();
                    mediaplayer.start();

                    Toast.makeText(getContext(), "Playing Audio", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                }


            }
        });





        return view;
    }
}
