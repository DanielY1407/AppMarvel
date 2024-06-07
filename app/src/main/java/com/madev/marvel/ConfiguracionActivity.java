package com.madev.marvel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ConfiguracionActivity extends Fragment {


    private TextView textViewNombre, textViewCorreo;
    private Button btnCerrarSesion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_configuracion, container, false);

        // Inicializar vistas
        textViewNombre = view.findViewById(R.id.textViewNombre);
        textViewCorreo = view.findViewById(R.id.textViewCorreo);
        btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion);

        // Obtener información personal del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String nombre = sharedPreferences.getString("nombre", "");
        String correo = sharedPreferences.getString("correo", "");

        // Mostrar información personal del usuario en las vistas
        textViewNombre.setText("Nombre: " + nombre);
        textViewCorreo.setText("Correo: " + correo);

        // Configurar el clic del botón "Cerrar Sesión"
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });

        return view;
    }

    // Método para cerrar sesión
    private void cerrarSesion() {
        // Eliminar los datos guardados en SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Elimina todos los datos guardados
        editor.apply();

        // Redirigir al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(requireActivity(), Login.class);
        startActivity(intent);
        requireActivity().finish();
    }
}