package com.example.findbug.Dominio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAcess {

    //==============CONEXÃO==========================
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAcess instance;
    public static final String COLUNA_NOME = "Nome";
    List<Inseto> insetos;
    DatabaseAcess databaseAcess;

    public DatabaseAcess(Context context){
        this.openHelper = new BancoController(context);
    }

    public static DatabaseAcess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAcess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database != null){
            this.database.close();
        }

    }
    //==================================================

    public static final String TABELA_INSETO = "tb_insetos";
    public static final String COLUNA_COD = "_id";
    public static final String COLUNA_TIPO = "tipo";
    public static final String COLUNA_LAVOURA = "lavoura";
    public static final String COLUNA_INF_ADICIONAIS = "inf_adicionais";
    public static final String COLUNA_IMAGENS = "imagem";

    //Get all Insetos
    public List<Inseto> getInsetos()
    {
        SQLiteDatabase db = this.openHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"_id", "Nome", "tipo", "lavoura", "inf_adicionais", "imagem"};
        String tableName = "tb_insetos";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect, null,null,null,null, null);
        List<Inseto> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Inseto inseto = new Inseto();
                inseto.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                inseto.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
                inseto.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                inseto.setLavoura(cursor.getString(cursor.getColumnIndex("lavoura")));
                inseto.setInf_adicionais(cursor.getString(cursor.getColumnIndex("inf_adicionais")));

                result.add(inseto);
            } while(cursor.moveToNext());
        }
        return result;
    }

    //SELECIONA E RECOLHE TODAS AS INFOMAÇÕES DE UM INSETO ESPECÍFICO, DE ACORDO COM O ID;
    public Inseto selecionarInseto(int codigo){

        SQLiteDatabase db = this.openHelper.getReadableDatabase();

        Cursor cursor = db.query(TABELA_INSETO, new String[]{COLUNA_COD, COLUNA_NOME,
                        COLUNA_TIPO, COLUNA_LAVOURA, COLUNA_INF_ADICIONAIS, COLUNA_IMAGENS}, COLUNA_COD + " = ?",
                new String[]{String.valueOf(codigo)}, null, null, null, null);

        if(cursor!=null) {
            cursor.moveToFirst();
        }

        Inseto inseto = new Inseto(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));


        return inseto;
    }

    //Search dos insetos, salva os ID's Resultado em uma List<Srtring>
    public List<String> SearchInseto(String Tipo, String Lavoura) {
        List<String> result = new ArrayList<String>();
        SQLiteDatabase db = this.openHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        qb.setTables(TABELA_INSETO);

        if (Tipo != null & Lavoura != null) {
            Cursor c1 = db.query(TABELA_INSETO, new String[]{COLUNA_COD, COLUNA_NOME,
                            COLUNA_TIPO, COLUNA_LAVOURA, COLUNA_INF_ADICIONAIS, COLUNA_IMAGENS}, COLUNA_TIPO + "=? and " + COLUNA_LAVOURA + "=?",
                    new String[]{Tipo, Lavoura}, null, null, null, null);

            if (c1.moveToFirst()) {
                do {
                    result.add(c1.getString(0));
                } while (c1.moveToNext());
            }

            return result;
        }

        if (Tipo == null & Lavoura != null) {

            Cursor c1 = db.query(TABELA_INSETO, new String[]{COLUNA_COD, COLUNA_NOME,
                            COLUNA_TIPO, COLUNA_LAVOURA, COLUNA_INF_ADICIONAIS, COLUNA_IMAGENS}, COLUNA_LAVOURA + "=?",
                    new String[]{Lavoura}, null, null, null, null);

            if (c1.moveToFirst()) {
                do {
                    result.add(c1.getString(0));
                } while (c1.moveToNext());
            }

            return result;

        }

        if (Lavoura == null & Tipo != null) {

            Cursor c1 = db.query(TABELA_INSETO, new String[]{COLUNA_COD, COLUNA_NOME,
                            COLUNA_TIPO, COLUNA_LAVOURA, COLUNA_INF_ADICIONAIS, COLUNA_IMAGENS}, COLUNA_TIPO + "=?",
                    new String[]{Tipo}, null, null, null, null);

            if (c1.moveToFirst()) {
                do {
                    result.add(c1.getString(0));
                } while (c1.moveToNext());
            }

            return result;

        }

        if (Lavoura == null & Tipo == null) {

            String query = "SELECT * FROM " + TABELA_INSETO;

            SQLiteDatabase bd = this.openHelper.getReadableDatabase();

            Cursor c = bd.rawQuery(query, null);

            if (c.moveToFirst()) {
                do {
                    Inseto inseto = new Inseto();
                    inseto.setCodigo(Integer.parseInt(c.getString(0)));

                    result.add(String.valueOf(inseto.getCodigo()));
                } while (c.moveToNext());
            }

            return result;

        }

        result = null;
        return result;

    }
    //===============================================================================================================

    //Pega o BLOB referente aos ID's dos resultados e guarda em um byte[] para ser posteriormente convertido.
    public byte[] PegarImagensByID(String ID) {
        SQLiteDatabase database = this.openHelper.getWritableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] select = {"_id", "Nome", "tipo", "lavoura", "inf_adicionais", "imagem"};

        qb.setTables("tb_insetos");

        Cursor c = qb.query(database, select, "_id = ?", new String[]{String.valueOf(ID)}, null, null, null);
        byte[] resultado = null;
        if (c.moveToFirst()) {
            do {
                resultado = c.getBlob(5);
            } while (c.moveToNext());
        }
        return resultado;
    }

    //==================================================================================================

}
