package com.example.findbug.Dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoController extends SQLiteAssetHelper {

    public static final int VERSAO_BANCO = 1;
    public static final String NOME_BANCO = "insetos.db";

    public BancoController(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }
}
