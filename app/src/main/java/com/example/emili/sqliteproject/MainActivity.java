package com.example.emili.sqliteproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.emili.sqliteproject.adapteur.ContactAdapter;
import com.example.emili.sqliteproject.donnee.Contact;
import com.example.emili.sqliteproject.donnee.ContactDbHelper;
import com.example.emili.sqliteproject.donnee.StoreContact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ContactDbHelper contactDbHelper;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Contact> contactList;
    ContactAdapter contactAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactDbHelper = new ContactDbHelper(this);
        SQLiteDatabase sqLiteDatabase = contactDbHelper.getReadableDatabase();


    /*
    private  void displayData(){

        contactDbHelper = new ContactDbHelper(this);
        SQLiteDatabase sqLiteDatabase = contactDbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ StoreContact.ContactEntry.DB_NAME, null);

        try{
            count.setText("Le nombre de contact est : " + cursor.getCount());

        }finally {
            cursor.close();
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_ajouter:
                goToAddActivity();
                return true;
            case R.id.action_supprimer:
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void goToAddActivity() {

        Intent intent = new Intent(MainActivity.this, AjouterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart(){

        super.onStart();
        //displayData();
        displayData2();
    }

    private void displayData2() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewContact);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        contactList = new ArrayList<Contact>();
        contactDbHelper = new ContactDbHelper(this);
        SQLiteDatabase sqLiteDatabase = contactDbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ StoreContact.ContactEntry.DB_NAME, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(StoreContact.ContactEntry.COLUMN_ID));
                String prenom = cursor.getString(cursor.getColumnIndex(StoreContact.ContactEntry.COLUMN_PRENOM));
                String nom = cursor.getString(cursor.getColumnIndex(StoreContact.ContactEntry.COLUMN_NOM));
                String genre = cursor.getString(cursor.getColumnIndex(StoreContact.ContactEntry.COLUMN_GENRE));
                int age = cursor.getInt(cursor.getColumnIndex(StoreContact.ContactEntry.COLUMN_AGE));

                Contact contact = new Contact(id, prenom, nom, age, genre);

                contactList.add(contact);
                cursor.moveToNext();
            }
        }
        contactAdapter = new ContactAdapter(MainActivity.this, contactList, new ContactAdapter.RecyclerItemClickListener() {
            @Override
            public void OnClickListener(Contact contact, int position) {

                Intent intent = new Intent(MainActivity.this, ModifierActivity.class);
                intent.putExtra("id", contact.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(contactAdapter);
    }
}
