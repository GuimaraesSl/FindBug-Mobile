package com.example.findbug;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findbug.Adapter.Favoritos;
import com.example.findbug.Dominio.DatabaseAcess;
import com.example.findbug.Dominio.Inseto;

import java.util.ArrayList;
import java.util.List;

public class Fav extends AppCompatActivity {

    DatabaseAcess db;
    RecyclerView listFav;
    RecyclerView.LayoutManager layoutManager;
    Favoritos adapterFav;
    Favoritos  adapter;
    public List<Inseto> listaInseto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Favoritos ");
        setSupportActionBar(toolbar);

        db = new DatabaseAcess(this);
        listFav = (RecyclerView)findViewById(R.id.listFav);
        layoutManager = new LinearLayoutManager(this);
        int[] n = new int[db.getIdFav().size()];

        listaInseto = new ArrayList<>();
        adapterFav = new Favoritos(this, listaInseto);
        listaInseto.clear();

        for(int i = 0; i<db.getIdFav().size(); i++) {
            n[i] = Integer.parseInt(db.getIdFav().get(i));
            listaInseto.add(db.selecionarInseto(n[i]));
        }
        System.out.println(listaInseto);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listFav.setLayoutManager(mLayoutManager);
        listFav.setItemAnimator(new DefaultItemAnimator());
        listFav.setHasFixedSize(true);
        listFav.setAdapter(adapterFav);

        adapter = new Favoritos(this, listaInseto);
        listFav.setAdapter(adapter);

    }
}