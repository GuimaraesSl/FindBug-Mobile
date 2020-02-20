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
    private List<Inseto> listaInseto;

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

        listaInseto = new ArrayList<>();
        adapterFav = new Favoritos(this, listaInseto);
        listaInseto.clear();

        listaInseto = db.getInsetos();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listFav.setLayoutManager(mLayoutManager);
        listFav.setItemAnimator(new DefaultItemAnimator());
        listFav.setHasFixedSize(true);
        listFav.setAdapter(adapterFav);

        adapter = new Favoritos(this, db.getInsetos());
        listFav.setAdapter(adapter);

    }
}