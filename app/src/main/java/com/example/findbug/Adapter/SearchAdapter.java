package com.example.findbug.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findbug.Dominio.DatabaseAcess;
import com.example.findbug.Dominio.Inseto;
import com.example.findbug.R;

import java.util.ArrayList;
import java.util.List;


class SearchViewHolder extends RecyclerView.ViewHolder{

    public TextView name, tipo, lavoura,ID;
    public ImageView imageSearch;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
        tipo = (TextView)itemView.findViewById(R.id.tipo);
        lavoura = (TextView)itemView.findViewById(R.id.lavoura);
        imageSearch = (ImageView)itemView.findViewById(R.id.imageSearch);

    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private List<Inseto> inseto;

    public SearchAdapter(Context context, List<Inseto> inseto) {

        this.context = context;
        this.inseto = inseto;

    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.content_search_item_card,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        DatabaseAcess db;
        db = new DatabaseAcess(context);
        int ID = inseto.get(position).getId();
        holder.name.setText(inseto.get(position).getNome());
        holder.tipo.setText(inseto.get(position).getTipo());
        holder.lavoura.setText(inseto.get(position).getLavoura());
        byte[] imagem = db.PegarImagensByID(String.valueOf(ID));
        Bitmap bt = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
        holder.imageSearch.setImageBitmap(bt);
    }

    @Override
    public int getItemCount() {
        return inseto.size();
    }

    public void setFilter(ArrayList<Inseto> newList){
        inseto = new ArrayList<>();
        inseto.addAll(newList);
        notifyDataSetChanged();
    }

}
