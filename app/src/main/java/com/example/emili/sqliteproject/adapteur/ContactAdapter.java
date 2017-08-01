package com.example.emili.sqliteproject.adapteur;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emili.sqliteproject.R;
import com.example.emili.sqliteproject.donnee.Contact;

import java.util.List;

/**
 * Created by emili on 31/07/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Contact> data;
    LayoutInflater layoutInflater;
    int taille;
    private RecyclerItemClickListener clickListener;


    public ContactAdapter(Context context, List<Contact> data ,RecyclerItemClickListener clickListener ){

        this.context = context;
        this.data = data;
        this.clickListener = clickListener;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.contact, parent, false);

        //this below
        //MyHolder holder = new MyHolder(view);
        // or this below
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder = (MyHolder) holder;
        Contact contact = data.get(position);

        myHolder.prenom.setText(contact.getPrenom());
        myHolder.nom.setText(contact.getNom());
        myHolder.genre.setText(contact.getGenre());
        String ageP1 = String.valueOf(contact.getAge());
        String agefinal =  ageP1 +" ans";
        myHolder.age.setText(agefinal);
        ((MyHolder) holder).bind(contact, clickListener);
    }

    @Override
    public int getItemCount() {

        taille = data.size();
        return taille;
    }


    public class MyHolder extends RecyclerView.ViewHolder{

        TextView prenom, nom, genre, age;
        View trait;

        public MyHolder(View itemView) {
            super(itemView);

            prenom = (TextView) itemView.findViewById(R.id.list_prenom);
            nom = (TextView) itemView.findViewById(R.id.list_nom);
            genre = (TextView) itemView.findViewById(R.id.list_genre);
            age = (TextView) itemView.findViewById(R.id.list_age);
            trait = (View) itemView.findViewById(R.id.trait);
        }

        public void bind(final Contact contact, final RecyclerItemClickListener listener){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.OnClickListener(contact, getLayoutPosition());
                }
            });
        }
    }

    public interface RecyclerItemClickListener{

        void OnClickListener(Contact contact , int position);
    }


}
