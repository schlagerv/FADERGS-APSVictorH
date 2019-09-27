package com.fadergs.victorhaps;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainJogadorActivity extends AppCompatActivity {

    private ListView lvJogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores_main);

        lvJogadores = findViewById(R.id.lvJogadores);

        FloatingActionButton fab = findViewById(R.id.fabJogador);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainJogadorActivity.this, FormularioJogadorActivity.class);
                i.putExtra("idTime", getIntent().getExtras().getInt("idTime"));
                startActivity(i);
            }
        });

        lvJogadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Intent intent = new Intent(MainJogadorActivity.this, FormularioJogadorActivity.class);
//                intent.putExtra("idTime", getIntent().getExtras().getInt("idTime"));
//                startActivity(intent);
            }
        });

        lvJogadores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir((Jogador) adapterView.getItemAtPosition(i));
                return true;
            }
        });
    }

    private void excluir(final Jogador jogador) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Jogador");
        alerta.setMessage("Confirma a exclusão do jogador "
                + jogador.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                JogadorDAO.excluirJogador(MainJogadorActivity.this, jogador.getId());
                carregarListaJogadores();
            }
        });
        alerta.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        carregarListaJogadores();
    }

    private void carregarListaJogadores() {
        List<Jogador> listaJogadores = JogadorDAO.getJogadores(this, getIntent().getExtras().getInt("idTime"));

        if (listaJogadores.size() == 0) {
            lvJogadores.setEnabled(false);
            Jogador fake = new Jogador();
            fake.setCamisa(0);
            fake.setNome("Lista Vazia!");
            listaJogadores.add(fake);
        } else {
            lvJogadores.setEnabled(true);
        }
        AdapterJogador adapter = new AdapterJogador(this, listaJogadores);

        lvJogadores.setAdapter(adapter);

    }

}