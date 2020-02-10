package com.example.findbug;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findbug.Dominio.DatabaseAcess;
import com.example.findbug.Dominio.Inseto;

import java.util.ArrayList;
import java.util.List;

public class Fav extends AppCompatActivity {

    DatabaseAcess db;
    RecyclerView listFav;
    RecyclerView.LayoutManager layoutManager;
    FavAdapter adapterFav;
    FavAdapter  adapter;
    private List<Inseto> listaInseto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        db = new DatabaseAcess(this);
        listFav = (RecyclerView)findViewById(R.id.listFav);
        layoutManager = new LinearLayoutManager(this);

        listaInseto = new ArrayList<>();
        adapterFav = new FavAdapter(this, listaInseto);
        listaInseto.clear();

        listaInseto = db.getInsetos();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listFav.setLayoutManager(mLayoutManager);
        listFav.setItemAnimator(new DefaultItemAnimator());
        listFav.setHasFixedSize(true);
        listFav.setAdapter(adapterFav);

        adapter = new FavAdapter(this, db.getInsetos());
        listFav.setAdapter(adapter);

    }
}