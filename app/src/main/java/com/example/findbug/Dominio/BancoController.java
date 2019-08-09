package com.example.findbug.Dominio;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class BancoController extends SQLiteAssetHelper {

    public static final int VERSAO_BANCO = 1;
    public static final String NOME_BANCO = "insetos.db";

    public BancoController(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }
}
