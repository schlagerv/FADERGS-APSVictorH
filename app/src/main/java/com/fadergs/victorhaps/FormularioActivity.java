package com.fadergs.victorhaps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNomeTime, etPontos;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_time);

        etNomeTime = (EditText) findViewById(R.id.etNomeTime);
        etPontos = (EditText) findViewById(R.id.etPontos);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private void salvar() {
        String nome = etNomeTime.getText().toString();
        String pontos = etPontos.getText().toString();

        if (nome.isEmpty()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon(android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve informar o nome do Time.");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        } else {
            Time time = new Time();
            time.setNome(nome);
            if (pontos.isEmpty()) {
                time.setPontos(0);
            } else {
                time.setPontos(Integer.valueOf(pontos));
            }
            TimeDAO.inserirTime(this, time);
        }
        this.finish();
    }
}


