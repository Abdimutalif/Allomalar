package com.mac.allomalar.utils

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.IBinder
import android.util.Log
import com.mac.allomalar.db.database.AppDatabase
import com.mac.allomalar.models.Alloma
import com.mac.allomalar.models.Image
import com.mac.allomalar.repository.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

private const val TAG = "DownloadingService"

@AndroidEntryPoint
class DownloadingService : Service() {

    @Inject
    lateinit var networkHelper: NetworkHelper

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var repository: Repository

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                for (i in 1..200) {
                    Log.d(TAG, "running: $i")
                    delay(1000)
                }
            } catch (e: Exception) {
                Log.d(TAG, "onStartCommand: ${e.message}")
            }

            readAllomasAndDownloadImages()
        }.start()

        return START_STICKY
    }

    private fun readAllomasAndDownloadImages() = CoroutineScope(Dispatchers.Default).launch {
        val allomas = repository.getAllAllomasFromRoom()
        val fields = repository.getAllAllomaAndSubjectsFromRoom()
        allomas.forEach {
          if (!checkAlloma(it)){
              downloadImageFromPath(it.image_url, database)
          }
        }

        fields.forEach {
            if(!checkImage(it.image_url)){
                downloadImageFromPath(it.image_url, database)
            }
        }
    }

    private suspend fun checkImage(imageUrl: String): Boolean {
        return database.imageDao().getImageById(imageUrl) == null
    }

    private suspend fun checkAlloma(alloma: Alloma): Boolean {
        return database.imageDao().getImageById(alloma.image_url) == null
    }

}


private fun downloadImageFromPath(path: String?, database: AppDatabase) =
    CoroutineScope(Dispatchers.IO).launch {
        var `in`: InputStream? = null
        var bmp: Bitmap? = null
        var responseCode = -1
        try {
            val url = URL(path)
            val con = url.openConnection() as HttpURLConnection
            con.doInput = true
            con.connect()
            responseCode = con.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                `in` = con.inputStream
                bmp = BitmapFactory.decodeStream(`in`)
                `in`.close()

                val dao = database.imageDao()
                val image = Image(path!!, bmp)
                dao.insertImage(image)

                Log.d("TAG", "downloadImageFromPath: saved image to database")
            }
        } catch (ex: Exception) {
            Log.e("Exception", ex.toString())
        }
    }

