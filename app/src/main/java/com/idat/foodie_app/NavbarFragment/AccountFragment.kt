package com.idat.foodie_app.NavbarFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.content.Intent
import android.content.DialogInterface
import android.app.Activity.RESULT_OK
import android.provider.MediaStore
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.idat.foodie_app.R
import android.app.Activity
import android.graphics.Bitmap

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private val PICK_IMAGE_REQUEST = 1
    private val TAKE_PHOTO_REQUEST = 2
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var imageView2: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        floatingActionButton = view.findViewById(R.id.floatingActionButton)
        imageView2 = view.findViewById(R.id.imageView2)

        floatingActionButton.setOnClickListener {
            showImageSourceDialog()
        }

        return view
    }

    private fun showImageSourceDialog() {
        val options = arrayOf("Cámara", "Galería")

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Seleccionar Fuente de Imagen")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> takePhoto()
                    1 -> pickImageFromGallery()
                }
                dialog.dismiss()
            }
            .show()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun takePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(takePictureIntent, TAKE_PHOTO_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageView2 = view?.findViewById(R.id.imageView2)!!

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    // Resultado de la galería
                    if (data != null && data.data != null) {
                        val imageUri = data.data
                        imageView2.setImageURI(imageUri)
                    }
                }
                TAKE_PHOTO_REQUEST -> {
                    // Resultado de la cámara
                    val imageBitmap = data?.extras?.get("data") as Bitmap?
                    if (imageBitmap != null) {
                        imageView2.setImageBitmap(imageBitmap)
                    }
                }
            }
        }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}