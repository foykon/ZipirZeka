package com.example.zipirzeka.PopUp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.example.zipirzeka.R
import android.app.AlarmManager
import android.app.PendingIntent
import android.media.Image
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

fun showOverlay(context: Context) {
    // UI thread'inde çalıştırmak için Handler
    Handler(Looper.getMainLooper()).post {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        // LayoutInflater ile custom layout'u inflate et
        val inflater = LayoutInflater.from(context)
        val overlayLayout = inflater.inflate(R.layout.overlay_layout, null)

        // Ekranın üst kısmına yerleştirilecek ayarlar
        val layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
        )

        // WindowManager'a overlay'yi ekle
        windowManager.addView(overlayLayout, layoutParams)
        val questionImage = overlayLayout.findViewById<ImageView>(R.id.overlay_question)
        questionImage.setImageResource(R.drawable.cat)


        val option1 = overlayLayout.findViewById<Button>(R.id.option1_button)
        option1.setOnClickListener {
            windowManager.removeView(overlayLayout)
        }
        val option2 = overlayLayout.findViewById<Button>(R.id.option2_button)
        option2.setOnClickListener {
            windowManager.removeView(overlayLayout)
        }
        val option3 = overlayLayout.findViewById<Button>(R.id.option3_button)
        option3.setOnClickListener {
            windowManager.removeView(overlayLayout)
        }
        val option4 = overlayLayout.findViewById<Button>(R.id.option4_button)
        option4.setOnClickListener {
            windowManager.removeView(overlayLayout)
        }


    }
}
fun scheduleOverlayUsingWorkManager(context: Context) {
    // Worker'ı her 30 saniyede bir çalışacak şekilde planla
    val workRequest = PeriodicWorkRequestBuilder<OverlayWorker>(5, TimeUnit.SECONDS)
        .setInitialDelay(5, TimeUnit.SECONDS) // İlk alarmın 30 saniye sonra tetiklenmesini sağla
        .build()

    // WorkManager ile işi kuyruğa al
    WorkManager.getInstance(context).enqueue(workRequest)
}


fun checkOverlayPermission(context: Context) {
    if (!Settings.canDrawOverlays(context)) {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
        context.startActivity(intent)
    }
}



