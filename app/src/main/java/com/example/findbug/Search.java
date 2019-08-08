package com.example.findbug;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.findbug.Dominio.BancoController;
import com.example.findbug.Dominio.DatabaseAcess;
import com.example.findbug.Dominio.Inseto;

import java.lang.reflect.Array;
import java.util.List;

public class Search extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner SpnTipo;
    Spinner SpnLavoura;
    public String Lavoura;
    public String TIPO;
    DatabaseAcess db;
    ListView LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseAcess(this);
        final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this);
        databaseAcess.open();

        LIST = (ListView)findViewById(R.id.LIST);
        SpnTipo     = (Spinner)findViewById(R.id.SpnTipo);
        SpnLavoura  = (Spinner)findViewById(R.id.SpnLavoura);

        //TEM QUE DÁ UM JEITO NISSO AQUI
        final String[][] resultado = {new String[10]};

        List<String> quotes = databaseAcess.getQuotes();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, quotes);
        this.LIST.setAdapter(adapter);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.TIPO, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinner_item);
        SpnTipo.setAdapter(adapter1);
        SpnTipo.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.LAVOURA, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_item);
        SpnLavoura.setAdapter(adapter2);
        SpnLavoura.setOnItemSelectedListener(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.SearchInseto(TIPO,Lavoura);
                Intent it = new Intent(Search.this, MenuBar.class);
                startActivity(it);
                //Chamar o activity MenuBar

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spin = (Spinner)parent;

        if(spin.getId() == R.id.SpnTipo){
            TIPO = SpnTipo.getItemAtPosition(position).toString();
        }

        if(spin.getId() == R.id.SpnLavoura){
            Lavoura = SpnLavoura.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
