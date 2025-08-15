package com.tuuser.chandas.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tuuser.chandas.R

class ContactFragment : Fragment() {
    
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSend: Button
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        editTextName = view.findViewById(R.id.editTextName)
        editTextEmail = view.findViewById(R.id.editTextEmail)
        editTextMessage = view.findViewById(R.id.editTextMessage)
        buttonSend = view.findViewById(R.id.buttonSend)
        
        buttonSend.setOnClickListener {
            sendContactEmail()
        }
    }
    
    private fun sendContactEmail() {
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val message = editTextMessage.text.toString()
        
        if (name.isBlank() || email.isBlank() || message.isBlank()) {
            Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        
        val subject = "Comentario de $name"
        val body = """
            Nombre: $name
            Email: $email
            
            Mensaje:
            $message
        """.trimIndent()
        
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("tu-email@ejemplo.com"))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        
        try {
            startActivity(intent)
            clearFields()
        } catch (e: Exception) {
            Toast.makeText(context, "No se encontró una app de email", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun clearFields() {
        editTextName.text.clear()
        editTextEmail.text.clear()
        editTextMessage.text.clear()
    }
}
