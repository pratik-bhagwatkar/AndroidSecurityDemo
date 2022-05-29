package com.example.encryptdsharedpref

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val et_name=findViewById<EditText>(R.id.et_PersonName)
        val tv_value=findViewById<TextView>(R.id.tv_value)
        val button=findViewById<Button>(R.id.button)
        val button2=findViewById<Button>(R.id.button2)

        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        val sharedPreferences = EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        button.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putString("Name", et_name.text.toString()).apply()
        }

        button2.setOnClickListener {
            val storedValue=sharedPreferences.getString("Name", "")
            println("Value = $storedValue")
            tv_value.text="Value = $storedValue"
        }




    }
}