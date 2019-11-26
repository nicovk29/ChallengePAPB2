package com.example.challengepapb2;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment{

    private Thread Thread1;
    private Thread Thread2;
    private Thread Thread3;
    private boolean condition = true;
    private boolean condition2 = true;
    private static int flag = 0;


    protected int DELAY;
    private final int DEFAULT_INTERVAL = 500;
    private final int DEFAULT_NUMBER = 7;
    private final String INTERVAL = "INTERVAL";
    private final String NUMBER = "NUMBER";
    private int JACKPOT_NUMBER = 0;

    private SharedPreferences mySharedPreferences;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_fragment1, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        final Button btnStart = getActivity().findViewById(R.id.btn_start);
        final Button btnStop = getActivity().findViewById(R.id.btn_stop);
        final TextView tvNum1 = getActivity().findViewById(R.id.tv_number1);
        final TextView tvNum2 = getActivity().findViewById(R.id.tv_number2);
        final TextView tvNum3 = getActivity().findViewById(R.id.tv_number3);

        mySharedPreferences = getActivity().getSharedPreferences(getActivity().getApplication().toString(), MODE_PRIVATE);
        DELAY = mySharedPreferences.getInt(INTERVAL, DEFAULT_INTERVAL);
        JACKPOT_NUMBER = mySharedPreferences.getInt(NUMBER, DEFAULT_NUMBER);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                condition=true;
                if(Thread1 == null || Thread1.getState() == Thread.State.TERMINATED){
                    final Runnable runnable1 = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while(condition){
                                    final int min = 0;
                                    final int max = 9;
                                    final int random = new Random().nextInt((max - min) + 1) + min;
                                    if(!condition){
                                        return;
                                    }
                                    Thread.sleep(DELAY);
                                    tvNum1.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            tvNum1.setText(""+random);
                                        }
                                    });
                                }
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    Thread1 = new Thread(runnable1);
                    Thread1.start();
                }
                if(Thread2 == null || Thread2.getState() == Thread.State.TERMINATED){
                    Runnable runnable2 = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while(condition){
                                    final int min = 0;
                                    final int max = 9;
                                    final int random = new Random().nextInt((max - min) + 1) + min;
                                    Thread.sleep(DELAY);
                                    if(!condition2){
                                        return;
                                    }
                                    tvNum2.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            tvNum2.setText(""+random);
                                        }
                                    });
                                }
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    Thread2 = new Thread(runnable2);
                    Thread2.start();
                }
                if(Thread3 == null || Thread3.getState() == Thread.State.TERMINATED){
                    Runnable runnable3 = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while(condition){
                                    final int min = 0;
                                    final int max = 9;
                                    final int random = new Random().nextInt((max - min) + 1) + min;
                                    Thread.sleep(DELAY);
                                    tvNum3.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            tvNum3.setText(""+random);
                                        }
                                    });
                                }
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    Thread3 = new Thread(runnable3);
                    Thread3.start();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(flag==0){
                    Thread1.interrupt();
                    flag++;
                }
                else if(flag==1){
                    Thread2.interrupt();
                    flag++;
                }else if(flag==2){
                    Thread3.interrupt();
                    int teks1 = Integer.parseInt(tvNum1.getText().toString());
                    int teks2 = Integer.parseInt(tvNum2.getText().toString());
                    int teks3 = Integer.parseInt(tvNum3.getText().toString());
                    flag=0;

                    String title = "Notif boi";
                    String body = "Jackpot";

                    if(teks1 == JACKPOT_NUMBER && teks2 == JACKPOT_NUMBER && teks3 == JACKPOT_NUMBER){
                        showNotification(getContext(), title, body, new Intent());
                    }
                }


            }
        });

    }

    public final void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }
}
