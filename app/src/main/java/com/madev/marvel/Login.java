package com.madev.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText edt_usuario, edt_contrasena;
    Button btn_login, btn_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_usuario = findViewById(R.id.edt_usuario);
        edt_contrasena = findViewById(R.id.edt_contrasena);
        btn_login = findViewById(R.id.btn_login);
        btn_password = findViewById(R.id.btn_password);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarUsuario();
            }

            private void validarUsuario() {
                String usuario = edt_usuario.getText().toString().trim();
                String contrasena = edt_contrasena.getText().toString().trim();

                if (usuario.equals("") && contrasena.equals("")) {
                    Toast.makeText(Login.this, "Por favor, ingresa tu usuario y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (usuario.equals("")) {
                    Toast.makeText(Login.this, "Por favor, ingresa tu usuario", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (contrasena.equals("")) {
                    Toast.makeText(Login.this,"Por favor, ingresa tu contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(Login.this, "Ingreso exitoso", Toast.LENGTH_SHORT).show();

            }
        });
        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
                Toast.makeText(Login.this, "Registrate", Toast.LENGTH_SHORT).show();

            }
        });


    }
}