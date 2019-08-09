package com.example.findbug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findbug.Dominio.DatabaseAcess;
import com.example.findbug.Dominio.Inseto;

public class inf_Adicionais extends AppCompatActivity {

    TextView textInfTipo, textInfLavoura, textCod, textInfAd;
    public static int ID;
    DatabaseAcess db;
    Inseto inseto;
    MenuBar Menu;
    ImageView imageView;

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
        textCod = findViewById(R.id.txtInfCod);
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
            //chama a função selecionar insetos do BC

            textCod.setText(""+inseto.getCodigo());
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




    }


}
