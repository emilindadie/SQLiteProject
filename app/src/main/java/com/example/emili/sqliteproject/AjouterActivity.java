package com.example.emili.sqliteproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emili.sqliteproject.donnee.ContactDbHelper;
import com.example.emili.sqliteproject.donnee.StoreContact;

import java.util.ArrayList;
import java.util.List;

public class AjouterActivity extends AppCompatActivity {

    List<String> spinner_item;
    EditText nom, prenom , age;
    String  monGenre;


    ContactDbHelper contactDbHelper;


    Spinner genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        prenom = (EditText) findViewById(R.id.edit_prenom);
        nom = (EditText) findViewById(R.id.edit_nom);
        age = (EditText) findViewById(R.id.edit_age);

        contactDbHelper = new ContactDbHelper(this);

        genre = (Spinner) findViewById(R.id.spinner);
        spinner_item = new ArrayList<String>();
        spinner_item.add("Unconnu");
        spinner_item.add("Homme");
        spinner_item.add("Femme");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, spinner_item);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        genre.setAdapter(dataAdapter);

        genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                monGenre = genre.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                //add contact
                ajouterContact();
                //exist actctivity
                finish();
                return true;
            case R.id.action_delete:
                return true;

            case R.id.action_home:
                goToHome();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void goToHome() {
        Intent intent = new Intent(AjouterActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void ajouterContact(){

        if(prenom.getText().toString().length() != 0  && nom.getText().toString().length() != 0 &&
                age.getText().toString().length() != 0 ){

            String monPrenom = prenom.getText().toString();
            String monNom = nom.getText().toString();
            String ageSaisi = age.getText().toString();
            int monAge = Integer.valueOf(ageSaisi);

            SQLiteDatabase sqLiteDatabase = contactDbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(StoreContact.ContactEntry.COLUMN_PRENOM, monPrenom);
            contentValues.put(StoreContact.ContactEntry.COLUMN_NOM, monNom);
            contentValues.put(StoreContact.ContactEntry.COLUMN_GENRE, monGenre);
            contentValues.put(StoreContact.ContactEntry.COLUMN_AGE, monAge);

            sqLiteDatabase.insert(StoreContact.ContactEntry.DB_NAME, null, contentValues);

            Toast.makeText(getApplicationContext(), "Le contact à été ajouté", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
        }

    }
}
