package com.example.zipirzeka.level2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.zipirzeka.R
import java.util.Locale

class Level2 : AppCompatActivity() {
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var btnSpeach: Button
    private lateinit var textListened: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level2) // XML dosyasını bağladık
        btnSpeach = findViewById(R.id.speach)
        textListened = findViewById(R.id.textListened)
        btnSpeach.setOnClickListener(View.OnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Someting...")
            try {
                activityResultLauncher.launch(intent)

            }catch (exp:ActivityNotFoundException){
                Toast.makeText(applicationContext,"Device does not supported",Toast.LENGTH_SHORT).show()
            }
        })
        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result: ActivityResult? ->
            if(result!!.resultCode== RESULT_OK && result!!.data!=null){
                val speactext=result!!.data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<Editable>
                textListened.text = speactext[0]
            }
        }
    }
}