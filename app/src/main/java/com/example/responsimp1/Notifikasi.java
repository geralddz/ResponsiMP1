package com.example.responsimp1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Notifikasi extends AppCompatActivity {
    EditText judul, pesan;
    Button logout, kirim;
    String channelnotif = "channelku";
    String channelid = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);

        final SharePrefManager sharePrefManager = new SharePrefManager(this);
        judul = findViewById(R.id.etjudul);
        pesan = findViewById(R.id.etpesan);
        logout = findViewById(R.id.btnlogout);
        kirim = findViewById(R.id.btnkirim);



        logout.setOnClickListener(v -> {
            Intent intent = new Intent(Notifikasi.this, Login.class);
            sharePrefManager.saveIsLogin(false);
            finishAffinity();
            startActivity(intent);
        });

        kirim.setOnClickListener(v -> tampilnotifikasi());
    }
    private void tampilnotifikasi() {
        String Judul = judul.getText().toString();
        String Pesan = pesan.getText().toString();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelid)
                .setLargeIcon(BitmapFactory.decodeResource(Notifikasi.this.getResources(), R.drawable.ic_android))
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle(Judul)
                .setContentText(Pesan)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);

        NotificationManager notificationManager = (NotificationManager) Notifikasi.this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new
                    NotificationChannel(channelnotif, "notifikasi", importance);
            builder.setChannelId(channelnotif);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
        assert notificationManager != null;
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

}