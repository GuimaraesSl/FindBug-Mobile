package com.example.findbug.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.findbug.Dominio.DatabaseAcess;
import com.example.findbug.Dominio.Inseto;
import com.example.findbug.Fav;
import com.example.findbug.R;

import java.util.ArrayList;
import java.util.List;

class SearchViewHolder extends ViewHolder {

    public TextView name, tipo, lavoura, ID;
    public ImageView imageFav;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.nameFav);
        tipo = (TextView) itemView.findViewById(R.id.tipoFav);
        lavoura = (TextView) itemView.findViewById(R.id.lavouraFav);
        imageFav = (ImageView) itemView.findViewById(R.id.imageFav);
    }
}

public class Favoritos extends RecyclerView.Adapter<SearchViewHolder>{

    private Context context;
    private List<Inseto> inseto;
    public Fav lista;



    public Favoritos(Context context, List<Inseto> inseto){

        this.context = context;
        this.inseto = inseto;

    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.content_adapter_fav, viewGroup, false);

        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int position) {
        DatabaseAcess db;
        db = new DatabaseAcess(context);
        lista = new Fav();
        int[] n = new int[db.getIdFav().size()];
        //Adiciona os insetos da coluna dos IDs dos favoritos
        inseto.clear();
        for(int i = 0; i<db.getIdFav().size(); i++) {
            n[i] = Integer.parseInt(db.getIdFav().get(i));
            inseto.add(db.selecionarInseto(n[i]));
            lista.listaInseto = inseto;
        }

        int ID = inseto.get(position).getCodigo();
        searchViewHolder.name.setText(inseto.get(position).getNome());
        searchViewHolder.tipo.setText(inseto.get(position).getTipo());
        searchViewHolder.lavoura.setText(inseto.get(position).getLavoura());
        byte[] imagem = db.PegarImagensByID(String.valueOf(ID));
        Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
        searchViewHolder.imageFav.setImageBitmap(bt);
    }

    @Override
    public int getItemCount() {
        return inseto.size();
    }

}