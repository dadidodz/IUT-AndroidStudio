package com.example.p222_tp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class AdapterListeImageTexte extends BaseAdapter {

    private Context context;

    private List<String> valeurs;

    private static LayoutInflater inflater = null;

    public AdapterListeImageTexte (Context context, List<String> valeurs){
        this.context = context;
        this.valeurs = valeurs;

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return this.valeurs.size();
    }

    @Override
    public Object getItem(int position){
        return this.valeurs.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View previous = convertView;
        View retour = previous;

        if (retour == null) {
            retour = inflater.inflate(R.layout.listviewimagetexte_layout, null);
        }

        TextView text = (TextView) retour.findViewById(R.id.textView2);
        text.setText(valeurs.get(position));

        return retour;

    }

}
