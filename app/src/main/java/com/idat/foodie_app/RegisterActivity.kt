package com.idat.foodie_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val btnReg = findViewById<Button>(R.id.btnRegister)
        val btnRegG = findViewById<Button>(R.id.btnRegGoogle)
        val btnCancelarR = findViewById<Button>(R.id.btnCancelarRegi)
        val txtEmailR = findViewById<TextInputEditText>(R.id.txtEmailRegis)
        val txtpasswR = findViewById<TextInputEditText>(R.id.txtPasswordRegis)

        btnReg.setOnClickListener {
            val email = txtEmailR.text.toString()
            val password = txtpasswR.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "FOODIE: Los campos deben estar llenos para el registro", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showMenuPrincipal(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        Toast.makeText(this, "FOODIE: Se ha producido un error autenticando al usuario", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnCancelarR.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnRegG.setOnClickListener{
            val mensaje = "FOODIE: Esta funcionalidad estara disponible pronto!"
            val duracion = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, mensaje, duracion)
            toast.show()
        }
    }

    private fun showMenuPrincipal(email: String, provider: ProviderType) {
        val menuPrincipalintent = Intent(this, MenuPrincipal::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(menuPrincipalintent)
    }
}