package com.madev.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class info_heroes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_heroes);

        Intent intent = getIntent();
        String id = intent.getStringExtra("Id");
        String nombre = intent.getStringExtra("Nombre");
        String Descri = intent.getStringExtra("Descripción");
        String Modi = intent.getStringExtra("Modificación");
        String Mini = intent.getStringExtra("Miniatura");

        TextView txtNombre = findViewById(R.id.txt_nombre1);
        TextView txtId = findViewById(R.id.txt_id1);
        TextView txtDescri = findViewById(R.id.txt_descri);
        TextView txtModi = findViewById(R.id.txt_modi);
        ImageView imgMini = findViewById(R.id.img_img);

        txtNombre.setText(nombre);
        txtId.setText(id);
        txtModi.setText(Modi);
        txtDescri.setText(Descri);

        Picasso.get().load(Mini).into(imgMini);
    }
}
