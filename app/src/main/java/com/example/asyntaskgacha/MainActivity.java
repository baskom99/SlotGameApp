package com.example.asyntaskgacha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView slot1, slot2, slot3, imagetropy;
    TextView texts;
    Button btstart;
    boolean isPlay = false;
    private static int[] img = {R.drawable.slot1, R.drawable.slot1, R.drawable.slot2, R.drawable.slot3,
            R.drawable.slot4, R.drawable.slot5, R.drawable.slotbar};

    SlotAsyncTask SlotAsyn1, SlotAsyn2, SlotAsyn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slot1 = findViewById(R.id.lyslot1);
        slot2 = findViewById(R.id.lyslot2);
        slot3 = findViewById(R.id.lyslot3);
        imagetropy = findViewById(R.id.imageView6);

        slot1.setImageResource(R.drawable.slotbar);
        slot2.setImageResource(R.drawable.slotbar);
        slot3.setImageResource(R.drawable.slotbar);

        texts = findViewById(R.id.textselamat);

        btstart = findViewById(R.id.button);
        btstart.setOnClickListener(this);
        imagetropy.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {

            texts.setText("");
            imagetropy.setVisibility(View.INVISIBLE);

            if (v.getId() ==  btstart.getId()){
            btstart.setVisibility(View.INVISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btstart.setVisibility(View.VISIBLE);
                }
            }, 2000); // where 1000 is equal to 1 sec (1 * 1000)

            if (!isPlay){


                SlotAsyn1 = new SlotAsyncTask();
                SlotAsyn2 = new SlotAsyncTask();
                SlotAsyn3 = new SlotAsyncTask();

                SlotAsyn1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, slot1);
                SlotAsyn2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, slot2);
                SlotAsyn3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, slot3);

                btstart.setText("STOP");
                isPlay = !isPlay;
            }
            else{
                SlotAsyn1.play = false;
                SlotAsyn2.play = false;
                SlotAsyn3.play = false;
                btstart.setText("PLAY");
                isPlay = !isPlay;

              if (slot1.getDrawable().getConstantState() == slot2.getDrawable().getConstantState()
                      && slot2.getDrawable().getConstantState() == slot3.getDrawable().getConstantState()) {
                  imagetropy.setVisibility(View.VISIBLE);
                  texts.setText(c);
                    }
              else{
                  texts.setText("Sorry, want to try again?");
              }
            }
        }
    }



    private class SlotAsyncTask extends AsyncTask<ImageView, Integer, Boolean>{
        ImageView slotimg;
        Random random = new Random();
        public  boolean play = true;


        public SlotAsyncTask(){
            play = true;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);
        }


        @Override
        protected Boolean doInBackground(ImageView... imgs) {
            slotimg = imgs[0];
            int a=0;
                while (play){
                int i = random.nextInt(6);
                    publishProgress(i);

                try {
                    Thread.sleep(random.nextInt(300));
                    }
                catch(InterruptedException e){
                    e.printStackTrace();
                    }
                }
               return !play;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            slotimg.setImageResource(img[values[0]]);
        }
    }

}