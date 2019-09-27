package com.fadergs.victorhaps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FormularioJogadorActivity extends AppCompatActivity {

    private EditText etNumeroCamisa, etNomeJogador;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_jogador);

        etNumeroCamisa = (EditText) findViewById(R.id.etNumeroCamisa);
        etNomeJogador = (EditText) findViewById(R.id.etNomeJogador);

        btnSalvar = (Button) findViewById(R.id.btnSalvarJogador);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private void salvar() {
        String nome = etNomeJogador.getText().toString();
        String camisa = etNumeroCamisa.getText().toString();

        if (nome.isEmpty()||camisa.isEmpty()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon(android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve informar o nome do Jogador e o Número da Camisa.");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        } else {
            Jogador jogador = new Jogador();
            jogador.setNome(nome);
            jogador.setCamisa(Integer.valueOf(camisa));
            jogador.setIdTime(getIntent().getExtras().getInt("idTime"));

            JogadorDAO.inserirJogador(this, jogador);
        }
        this.finish();
    }
}

