package com.madev.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button btn_registrarme, btn_ingresar;
    EditText edt_nombre,edt_correo,edt_contraseña,edt_nacimiento;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        edt_nombre = findViewById(R.id.edt_nombre);
        edt_correo = findViewById(R.id.edt_correo);
        edt_contraseña = findViewById(R.id.edt_contraseña);
        edt_nacimiento = findViewById(R.id.edt_nacimiento);

        btn_ingresar = findViewById(R.id.btn_ingresar);
        btn_registrarme = findViewById(R.id.btn_registrarme);

        btn_registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edt_nombre.getText().toString();
                String correo = edt_correo.getText().toString();
                String contraseña = edt_contraseña.getText().toString();
                String nacimiento = edt_nacimiento.getText().toString();

                if (nombre.isEmpty() && correo.isEmpty() && contraseña.isEmpty() && nacimiento.isEmpty()){
                    Toast.makeText(Register.this, "Complete todos los datos", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(nombre,correo,contraseña,nacimiento);
                    Toast.makeText(Register.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
    }

    private void registerUser(String nombre, String correo, String contraseña, String nacimiento) {
        mAuth.createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String,Object> map = new HashMap<>();
                map.put("id",id);
                map.put("nombre",nombre);
                map.put("correo",correo);
                map.put("contraseña",contraseña);
                map.put("nacimiento",nacimiento);

                mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(Register.this,Login.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}