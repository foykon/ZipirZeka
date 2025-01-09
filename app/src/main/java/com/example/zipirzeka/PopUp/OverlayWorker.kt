package com.example.zipirzeka.PopUp

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class OverlayWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        // Overlay'i UI thread'inde göster
        showOverlay(applicationContext)

        // Yeni işin başlatılması
        scheduleOverlayUsingWorkManager(applicationContext) // İşin yeniden başlatılmasını sağla

        return Result.success()  // İş başarılı bir şekilde tamamlandı
    }
}

