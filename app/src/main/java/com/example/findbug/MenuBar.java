package com.example.findbug;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.findbug.Dominio.BancoController;
import com.example.findbug.Dominio.DatabaseAcess;
import com.example.findbug.Dominio.Inseto;

import java.util.List;

public class MenuBar extends AppCompatActivity {

    ImageButton mais;
    ImageButton seta_direita, seta_esquerda;
    ImageView imagens;
    public int cont;
    public int Id, codigo;
    public int[] guardar;
    public Inseto inseto;

    DatabaseAcess db;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DatabaseAcess(this);

        final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this);
        databaseAcess.open();

        mais = (ImageButton) findViewById(R.id.mais);

        mais.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent it = new Intent(MenuBar.this , inf_Adicionais.class);
                startActivity(it);
                //Chamar o activity inf_Adicionais
            }
        });

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        seta_direita  = (ImageButton)findViewById(R.id.seta_direita);
        seta_esquerda = (ImageButton)findViewById(R.id.seta_esquerda);
        imagens       = (ImageView)findViewById(R.id.imageInseto);

        guardar = new int[3];

        inseto = new Inseto();

        final List<Inseto> insetos = db.todosInsetos();

        //faz as referências das variáveis

        for(Inseto c : insetos){

            guardar[cont] = c.getCodigo();

            cont++;
        }

        //pega os valores de IDs da tabela e guarda em um vetor

        cont = 0;

        if(cont==0){
            imagens.setImageResource(R.drawable.teste);
        }

        //seta a primeira imagem

        inseto.setId(guardar[cont]);
        //atribui valor de ID = 1(ID do primeiro inseto)

        Toast.makeText(getApplicationContext(), "ID = "+inseto.getId(), Toast.LENGTH_SHORT).show();
        //verificar se o ID = 1

        seta_direita.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                cont++;

                if (cont < 0)
                    cont = 0;

                if (cont == 1) {
                    imagens.setImageResource(R.drawable.teste2);
                }

                //seta a segunda imagem

                if (cont > 1)
                    cont = 1;

                inseto.setId(guardar[cont]);
                //seta o valor do ID do segundo inseto na variavel auxiliar(ID = cont(2))

                //Toast.makeText(getApplicationContext(), "CONT = "+cont, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "ID = " +inseto.getId(), Toast.LENGTH_SHORT).show();
                //teste pra ver se ID = 2

            }
        });


        seta_esquerda.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                cont--;

                if(cont == 0){
                    imagens.setImageResource(R.drawable.teste);
                }
                //seta novamente a primeira imagem se voltar

                if(cont == 1){
                    imagens.setImageResource(R.drawable.teste2);
                }
                //seta a segunda imagem

                if(cont < 0)
                    cont = 0;

                inseto.setId(guardar[cont]);
                //seta o valor do ID do primeiro inseto na variavel auxiliar(ID = cont(1))

                //Toast.makeText(getApplicationContext(), "CONT = "+cont, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "ID = "+inseto.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        }

    //>>===================================================================


}

