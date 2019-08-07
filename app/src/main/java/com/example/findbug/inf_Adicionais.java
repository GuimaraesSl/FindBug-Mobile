package com.example.findbug;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findbug.Dominio.BancoController;
import com.example.findbug.Dominio.DatabaseAcess;
import com.example.findbug.Dominio.Inseto;

public class inf_Adicionais extends AppCompatActivity {

    TextView textInfTipo, textInfLavoura, textCod, textInfAd;
    DatabaseAcess db = new DatabaseAcess(this);
    MenuBar codigo;
    Inseto inseto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf__adicionais);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textInfTipo    = (TextView)findViewById(R.id.txtInfTipo);
        textInfLavoura = (TextView)findViewById(R.id.txtInfLavoura);
        textCod        = (TextView)findViewById(R.id.txtInfCod);
        textInfAd      = (TextView)findViewById(R.id.txtInfAd);

        codigo = new MenuBar();
        inseto = new Inseto();

        //faz as referências das variáveis

        Toast.makeText(getApplicationContext(), "ID = "+inseto.getId(), Toast.LENGTH_LONG).show();
        //teste pra saber se o ID ta vindo com o valor certo{NÃO TÁ}(TÔ MT PUTO COM ISSO)

        try {
            inseto = db.selecionarInseto(1);
            //chama a função selecionar insetos do BC

            textCod.setText(""+inseto.getCodigo());
            textInfTipo.setText(""+inseto.getTipo());
            textInfLavoura.setText(""+inseto.getLavoura());
            textInfAd.setText(""+inseto.getInf_adicionais());

            //seta os valores nos campos da activity

        }catch (Exception ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }




    }


}
