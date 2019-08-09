package com.example.findbug;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.findbug.Dominio.DatabaseAcess;
import com.example.findbug.Dominio.Inseto;

import java.util.ArrayList;
import java.util.List;

public class MenuBar extends AppCompatActivity {

    public int cont = 0;
    ImageButton mais;
    ImageButton seta_direita, seta_esquerda;
    ImageView imagens;
    public int Id;
    public List<String> resultado;
    Inseto inseto;
    inf_Adicionais inf;

    DatabaseAcess db;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        db = new DatabaseAcess(this);
        final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this);
        databaseAcess.open();
        inf = new inf_Adicionais();
        resultado = new ArrayList<>();
        resultado = Search.getResult();
        final String[] result = new String[resultado.size()];
        resultado.toArray(result);

        seta_direita = findViewById(R.id.seta_direita);
        seta_esquerda = findViewById(R.id.seta_esquerda);
        imagens = findViewById(R.id.imageInseto);
        mais = findViewById(R.id.mais);

        mais.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("MAIS", String.valueOf(Id));
                inf_Adicionais.ID = Id;
                Log.d("MAIS-INF", String.valueOf(inf_Adicionais.ID));

                Intent it = new Intent(MenuBar.this, inf_Adicionais.class);
                startActivity(it);
                //Chamar o activity inf_Adicionais
            }
        });


        inseto = new Inseto();

        if(cont==0){
            byte[] imagem = db.PegarImagensByID(String.valueOf(result[cont]));
            Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
            imagens.setImageBitmap(bt);
            Id = (Integer.parseInt(result[cont]));
        }


        Toast.makeText(getApplicationContext(), "ID = "+inseto.getId(), Toast.LENGTH_SHORT).show();
        //verificar se o ID = 1

        seta_direita.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (result.length == (cont + 1)) {
                    byte[] imagem = db.PegarImagensByID(String.valueOf(result[cont]));
                    Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                    imagens.setImageBitmap(bt);
                } else {
                    cont++;
                    //reuslt[cont] = ID que vai mandar para a função de pegar imagem!!!!
                    byte[] imagem = db.PegarImagensByID(String.valueOf(result[cont]));
                    Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                    imagens.setImageBitmap(bt);
                }

                Id = Integer.parseInt(result[cont]);
                //seta o valor do ID do segundo inseto na variavel auxiliar(ID = cont(2))

                //Toast.makeText(getApplicationContext(), "CONT = "+cont, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "ID = " +inseto.getId(), Toast.LENGTH_SHORT).show();

            }
        });


        seta_esquerda.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if ((cont - 1) < 0) {
                    byte[] imagem = db.PegarImagensByID(String.valueOf(result[cont]));
                    Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                    imagens.setImageBitmap(bt);
                } else {
                    cont--;
                    //result[cont] = ID que vai mandar para a função de pegar imagem!!!!
                    byte[] imagem = db.PegarImagensByID(String.valueOf(result[cont]));
                    Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                    imagens.setImageBitmap(bt);
                }

                Id = Integer.parseInt(result[cont]);
                //seta o valor do ID do primeiro inseto na variavel auxiliar(ID = cont(1))

                //Toast.makeText(getApplicationContext(), "CONT = "+cont, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "ID = "+inseto.getId(), Toast.LENGTH_SHORT).show();
            }
        });


        //>>===================================================================


    }
}

