package com.example.findbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findbug.Dominio.DatabaseAcess;
import com.example.findbug.Dominio.Inseto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class inf_Adicionais extends AppCompatActivity {

    TextView textInfTipo, textInfLavoura, textNome, textInfAd;
    public static int ID;
    DatabaseAcess db;
    Inseto inseto;
    MenuBar Menu;
    ImageView imageView;
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf__adicionais);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseAcess(this);
        Menu = new MenuBar();
        inseto = new Inseto();

        final DatabaseAcess databaseAcess = DatabaseAcess.getInstance(this);
        databaseAcess.open();

        textInfTipo = findViewById(R.id.txtInfTipo);
        textInfLavoura = findViewById(R.id.txtInfLavoura);
        textNome = findViewById(R.id.txtInfNome);
        textInfAd = findViewById(R.id.txtInfAd);
        imageView = findViewById(R.id.imageView2);

        //PROCESSO PARA SETAR IMAGEM NO IMAGEVIEW DO INFORMAÇÕES ADICIONAIS=====
        byte[] imagem = db.PegarImagensByID(String.valueOf(ID));
        Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
        imageView.setImageBitmap(bt);
        //======================================================================


        //PROCESSO PARA CHAMAR A FUNÇÃO QUE RECOLHERÁ AS INFORMAÇÕES NO INSETO ESCOLHIDO
        try {
            inseto = db.selecionarInseto(ID);
            //chama a função selecionar insetos do DA

            textNome.setText("" + inseto.getNome());
            textInfTipo.setText(""+inseto.getTipo());
            textInfLavoura.setText(""+inseto.getLavoura());
            textInfAd.setText(""+inseto.getInf_adicionais());

        }catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }


        //Setando o ID do inseto para adicionar na lista de favoritos
        final FloatingActionButton fav = findViewById(R.id.favButton);
        //Verifica se o ID tratado já está na lista e determina o icone do botão
        if(!db.getIdFav().contains(String.valueOf(ID))){
            fav.setImageResource(R.drawable.ic_favorite_border);
        } else {
            fav.setImageResource(R.drawable.ic_fav);
        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Se o ID não estiver contido na lista de IDs dos favoritos determina o icone do botão e chama a função de adicionar
                if(!db.getIdFav().contains(String.valueOf(ID))){
                    fav.setImageResource(R.drawable.ic_fav);
                    db.inserirIDFav(ID);
                    Toast.makeText(inf_Adicionais.this, db.getIdFav()+"", Toast.LENGTH_SHORT).show();
                //Se não, determina o icone do botão e chama a função de excluir o ID da lista
                } else {
                    fav.setImageResource(R.drawable.ic_favorite_border);
                    db.deletarIdFav(ID);
                    Toast.makeText(inf_Adicionais.this, db.getIdFav()+"", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
