package com.iubat.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    ArrayList<ContactModel> allContacts = new ArrayList<>();
    Context mContext;

    public ContactAdapter(ArrayList<ContactModel> allContacts, Context mContext) {
        this.allContacts = allContacts;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return allContacts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
        }

        TextView txtName = convertView.findViewById(R.id.txt_name);
        TextView txtEmail = convertView.findViewById(R.id.txt_email);
        TextView txtPhone = convertView.findViewById(R.id.txt_phone);
        ImageView imgEdit = convertView.findViewById(R.id.img_edit);
        ImageView imgDelete = convertView.findViewById(R.id.img_delete);

        ContactModel cm = allContacts.get(position);
        txtName.setText(cm.getName());
        txtEmail.setText(cm.getEmail());
        txtPhone.setText(cm.getPhone());
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, AddContact.class);
                i.putExtra("con_id", cm.getId());
                mContext.startActivity(i);
            }
        });

        return convertView;
    }
}
