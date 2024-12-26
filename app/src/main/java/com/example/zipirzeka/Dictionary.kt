package com.example.zipirzeka

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import android.provider.MediaStore
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class Dictionary : Fragment() {
    private val PERMISSION_CODE = 1000
    private val IMAGE_CAPTURE_CODE = 1001

    var vFilename: String = ""
    private lateinit var photoLayout: GridLayout // Fotoğrafları ekleyeceğimiz layout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dictionary, container, false)

        // Fotoğraf göstermek için LinearLayout
        photoLayout = view.findViewById(R.id.photoLayout)

        val btnTakePhoto: View = view.findViewById(R.id.btn_takephoto)
        btnTakePhoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkPermissions()) {
                    openCamera()
                } else {
                    requestPermissions()
                }
            } else {
                Toast.makeText(requireContext(), "Your version of Android is not supported, min Android 6.0", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        vFilename = "FOTO_$timeStamp.jpg"

        val file = File(requireContext().getExternalFilesDir(null), vFilename)
        val imageUri = FileProvider.getUriForFile(requireContext(), requireContext().packageName + ".provider", file)

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    private fun checkPermissions(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.CAMERA
        )
        return cameraPermission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(), Manifest.permission.CAMERA
            )) {
            Toast.makeText(requireContext(), "Camera permission is required to take photos", Toast.LENGTH_LONG).show()
        }
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.CAMERA),
            PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            val file = File(requireContext().getExternalFilesDir(null), vFilename)
            val uri = FileProvider.getUriForFile(requireContext(), requireContext().packageName + ".provider", file)

            // Yeni bir ImageView oluştur ve URI ile görüntüle
            val imageView = ImageView(requireContext())
            val layoutParams = GridLayout.LayoutParams()
            layoutParams.width = 0
            layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT
            layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f) // Her sütun eşit genişlikte
            layoutParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED)
            layoutParams.setMargins(8, 8, 8, 8)

            imageView.layoutParams = layoutParams
            imageView.adjustViewBounds = true
            imageView.setImageURI(uri)

            // Fotoğrafı 'photoLayout' içerisine ekle
            photoLayout.addView(imageView)

            Toast.makeText(requireContext(), "Image saved to: ${file.absolutePath}", Toast.LENGTH_LONG).show()
        }
    }


}
