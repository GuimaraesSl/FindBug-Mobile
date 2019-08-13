package com.example.findbug;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.findbug.Dominio.DatabaseAcess;
import com.example.findbug.Dominio.Inseto;

import java.util.ArrayList;
import java.util.List;

public class MenuBar extends AppCompatActivity {

    public int cont = 0;
    ImageView imagens;
    public int Id;
    ImageButton mais, seta_direita, seta_esquerda, compartilhar;
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

        inseto = new Inseto();
        db = new DatabaseAcess(this);
        inf = new inf_Adicionais();
        resultado = new ArrayList<>();

        seta_direita = findViewById(R.id.seta_direita);
        seta_esquerda = findViewById(R.id.seta_esquerda);
        imagens = findViewById(R.id.imageInseto);
        mais = findViewById(R.id.mais);
        compartilhar = findViewById(R.id.compartilhar);

        final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this);
        databaseAcess.open();

        resultado = Search.getResult();
        final String[] result = new String[resultado.size()];
        resultado.toArray(result);

        //FUNÇÃO BOTÃO DE INFORMAÇÕES ADICIONAIS
        //REGISTRA O ID DO INSETO ESCOLHIDO E INICIA O ACTIVITY DAS INFORMAÇÕES ADICIONAIS
        mais.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                inf_Adicionais.ID = Id;

                Intent it = new Intent(MenuBar.this, inf_Adicionais.class);
                startActivity(it);
            }
        });

        compartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MenuBar.this, Compartilhar.class);
                startActivity(it);

            }
        });


        //===== PROCESSO DE MANIPULAÇÃOS DOS RESULTADOS DA PESQUISA (ID E IMAGENS)====

        if(cont==0){
            //BLOCO SetImage
            //Blob da imagem é puxada da função do DatabaseAcess(PegarImagemByID)
            //Blob é convertido em Bitmap e setado no ImageView
            byte[] imagem = db.PegarImagensByID(String.valueOf(result[cont]));
            Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
            imagens.setImageBitmap(bt);
            Id = (Integer.parseInt(result[cont]));
        }

        seta_direita.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (result.length == (cont + 1)) {
                    byte[] imagem = db.PegarImagensByID(String.valueOf(result[cont]));
                    Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                    imagens.setImageBitmap(bt);
                } else {
                    cont++;
                    byte[] imagem = db.PegarImagensByID(String.valueOf(result[cont]));
                    Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                    imagens.setImageBitmap(bt);
                }
                Id = Integer.parseInt(result[cont]);
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
            }
        });
        //>>===================================================================
    }
}

