package com.fadh.cataloguemoveapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.fadh.cataloguemoveapi.adapter.MovieAdapterList;
import com.fadh.cataloguemoveapi.model.ModelOmdb;
import com.fadh.cataloguemoveapi.model.Search;
import com.fadh.cataloguemoveapi.model.SearchMdb;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
//    private static final String TAG = MainActivity.class.getSimpleName();
//
//    private final static String API_KEY = "aac2f6b6";
    ImageView image;
    Button cari;
    EditText txtCari;
    TextView judul , genre , description , ratting;
    RecyclerView movieRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.gambar);
        judul = findViewById(R.id.judulFilm);
        genre = findViewById(R.id.Genre);
        description = findViewById(R.id.description);
        ratting = findViewById(R.id.ratting);
        movieRecycler = findViewById(R.id.movie_recycler);
        txtCari = findViewById(R.id.txtCari);



        cari = findViewById(R.id.btnCari);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDetailMovie(txtCari.getText().toString());
            }
        });


    }
    APIInterface apiInterface;
    ProgressDialog progressDialog;
    private void callDetailMovie(String tittle) {
        apiInterface = ApiClient.getClient().create(APIInterface.class);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        Call<SearchMdb> call = apiInterface.getDetails(tittle, "7d141b4a" );
        call.enqueue(new Callback<SearchMdb>() {
            @Override
            public void onResponse(Call<SearchMdb> call, Response<SearchMdb> response) {
                progressDialog.dismiss();
                SearchMdb dataMovie = response.body();

                if (dataMovie !=null){


                    MovieAdapterList adapterList = new MovieAdapterList(MainActivity.this , dataMovie.getSearch());

                    movieRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    movieRecycler.setItemAnimator(new DefaultItemAnimator());
                    movieRecycler.setAdapter(adapterList);




//                   MovieAdapter adapter = new MovieAdapter(MainActivity.this,lstMovieOMDB);
//
//                    lstMovieOMDB.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                    lstMovieOMDB.setItemAnimator(new DefaultItemAnimator());
//                    lstMovieOMDB.setAdapter(adapter);

                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchMdb> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
