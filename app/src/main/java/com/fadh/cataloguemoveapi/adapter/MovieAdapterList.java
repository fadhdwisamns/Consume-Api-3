package com.fadh.cataloguemoveapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fadh.cataloguemoveapi.R;
import com.fadh.cataloguemoveapi.model.ModelOmdb;
import com.fadh.cataloguemoveapi.model.Search;
import com.fadh.cataloguemoveapi.model.SearchMdb;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapterList extends RecyclerView.Adapter<MovieAdapterList.MovieViewHolder> {
    List<Search> modelOmdbs;
    Context context;

    public MovieAdapterList(Context context , List<Search> modelOmdbs){
        this.modelOmdbs = modelOmdbs;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent , false);
        MovieViewHolder myViewHolder = new MovieViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.judul.setText(modelOmdbs.get(position).getTitle());
//        holder.ratting.setText(modelOmdbs.get(position).getRatings().get(0).getValue());
        holder.ratting.setText(String.valueOf(modelOmdbs.get(position).getYear()));
        holder.genre.setText(modelOmdbs.get(position).getType());
        holder.description.setText(modelOmdbs.get(position).getImdbID());

        String image = modelOmdbs.get(position).getPoster();
        Picasso.get().load(image).into(holder.gambar);
    }

    @Override
    public int getItemCount() {

        return modelOmdbs.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar;
        TextView judul , genre , description , ratting;
        public MovieViewHolder(@NonNull View i) {
            super(i);
            gambar = i.findViewById(R.id.gambar);
            judul = i.findViewById(R.id.judulFilm);
            genre = i.findViewById(R.id.Genre);
            description = i.findViewById(R.id.description);
            ratting = i.findViewById(R.id.ratting);
        }
    }
}
