package com.madev.marvel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.madev.marvel.adaptadores.HeroeAdaptador;
import com.madev.marvel.clases.Heroe;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HeroeAdaptador.OnItemClickListener {

    List<Heroe> ListaHeroe = new ArrayList<>();
    RecyclerView rcv_heroes;
    HeroeAdaptador adaptador;
    BottomNavigationView nav_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcv_heroes = findViewById(R.id.rcv_heroes);
        rcv_heroes.setLayoutManager(new LinearLayoutManager(this));

        nav_btn = findViewById(R.id.btn_navegation);

        adaptador = new HeroeAdaptador(ListaHeroe, this);
        rcv_heroes.setAdapter(adaptador);

        nav_btn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

                int itemId = menuItem.getItemId();
                if (itemId == R.id.nav_home) {
                } else if (itemId == R.id.nav_comics) {
                    // Cargar el fragmento de solicitar cómics (si lo tienes)
                    selectedFragment = new ComicsActivity();
                } else if (itemId == R.id.nav_configuracion) {
                    selectedFragment = new ConfiguracionActivity();
                }

                if (selectedFragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frm_home, selectedFragment);
                    transaction.commit();
                }

                return true;
            }
        });

        cargarInformacion();
    }

    private void cargarInformacion() {
        String url = "https://gateway.marvel.com:443/v1/public/characters?ts=1717727149&apikey=41bd81efbc6c643e24fcb3f38becdaed&hash=7e4f3cde6cf87fa1df8131dcb1d5df7c";
        StringRequest myRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    recibirRespuesta(new JSONObject(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error en el servidor", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en el servidor", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        rq.add(myRequest);
    }

    private void recibirRespuesta(JSONObject respuesta) {
        try {
            JSONObject data = respuesta.getJSONObject("data");
            for (int i = 0; i < data.getJSONArray("results").length(); i++) {
                String id = data.getJSONArray("results").getJSONObject(i).getString("id");
                String nombre = data.getJSONArray("results").getJSONObject(i).getString("name");
                String descripcion = data.getJSONArray("results").getJSONObject(i).getString("description");
                String modificacion = data.getJSONArray("results").getJSONObject(i).getString("modified");
                String miniatura = data.getJSONArray("results").getJSONObject(i).getJSONObject("thumbnail").getString("path") + "." + data.getJSONArray("results").getJSONObject(i).getJSONObject("thumbnail").getString("extension");

                Heroe p = new Heroe(id, nombre, descripcion, modificacion, miniatura);

                ListaHeroe.add(p);
            }

            adaptador.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error en el servidor", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(Heroe heroe) {
        Intent intent = new Intent(MainActivity.this, info_heroes.class);
        intent.putExtra("Nombre", heroe.getNombre());
        intent.putExtra("Id", heroe.getId());
        intent.putExtra("Descripción", heroe.getDescripcion());
        intent.putExtra("Modificación", heroe.getModificacion());
        intent.putExtra("Miniatura", heroe.getMiniatura());
        startActivity(intent);
    }
}
