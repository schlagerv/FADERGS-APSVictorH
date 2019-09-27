package com.fadergs.victorhaps;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterJogador extends BaseAdapter {
    private List<Jogador> listaJogadores;
    private Context context;
    private LayoutInflater inflater;

    public AdapterJogador(Context context, List<Jogador> listaJogadores) {
        this.context = context;
        this.listaJogadores = listaJogadores;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaJogadores.size();
    }

    @Override
    public Object getItem(int i) {
        return listaJogadores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaJogadores.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemSuporte item;

        if (view == null) {
            view = inflater.inflate(R.layout.layout_lista_jogador, null);
            item = new ItemSuporte();
            item.tvNomeJogador = (TextView) view.findViewById(R.id.tvListaNomeJogador);
            item.tvListaNumeroCamisa = (TextView) view.findViewById(R.id.tvListaNumeroCamisa);
            item.layoutJogador = (LinearLayout) view.findViewById(R.id.layoutJogador);
            view.setTag(item);
        } else {
            item = (ItemSuporte) view.getTag();
        }

        Jogador jogador = listaJogadores.get(i);
        item.tvNomeJogador.setText(jogador.getNome());
        item.tvListaNumeroCamisa.setText(String.valueOf(jogador.getCamisa()));

        if (jogador.getNome().equals(context.getString(R.string.txtListaVazia))) {
            item.tvListaNumeroCamisa.setText(" ");
        }

        if (i % 2 == 0) {
            item.layoutJogador.setBackgroundColor(Color.WHITE);
        } else {
            item.layoutJogador.setBackgroundColor(Color.rgb(230, 230, 230));
        }

        return view;
    }

    private class ItemSuporte {
        TextView tvNomeJogador, tvListaNumeroCamisa;
        LinearLayout layoutJogador;
    }
}
