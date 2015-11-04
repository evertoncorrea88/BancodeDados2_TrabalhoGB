package com.everton.bancodedados2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Everton on 11/2/15.
 */
public class AdapterListView extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Contact> contactList;

    public AdapterListView(Context context, List<Contact> contactList) {
        //Itens do listview
        this.contactList = contactList;
        //Objeto responsável por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    public void updateResults(List<Contact> contactList) {
        this.contactList = contactList;
        //Triggers the list update
        notifyDataSetChanged();
    }

    public int getCount() {
        return contactList.size();
    }

    public Contact getItem(int position) {
        return contactList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ItemSuporte itemHolder;

        //se a view estiver nula, inflamos o layout nela.
        if (view == null) {
            //infla o layout para podermos pegar as views
            view = mInflater.inflate(R.layout.item_list, null);
            //cria um item de suporte para não precisarmos sempre inflar as mesmas informacoes
            itemHolder = new ItemSuporte();
            itemHolder.name = ((TextView) view.findViewById(R.id.name));
            itemHolder.number = ((TextView) view.findViewById(R.id.number));
            itemHolder.email = ((TextView) view.findViewById(R.id.email));
            //define os itens na view;
            view.setTag(itemHolder);
        } else {
            //se a view já existe pega os itens.
            itemHolder = (ItemSuporte) view.getTag();
        }
        //pega os dados da lista e define os valores nos itens.
        Contact item = contactList.get(position);
        itemHolder.name.setText(item.getName());
        itemHolder.number.setText(Integer.toString(item.getNumber()));
        itemHolder.email.setText(item.getEmail());

        //retorna a view com as informações
        return view;
    }

    /**
     * Classe de suporte para os itens do layout.
     */
    private class ItemSuporte {
        TextView name;
        TextView number;
        TextView email;
    }


}
