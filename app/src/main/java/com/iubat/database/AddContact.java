package com.iubat.database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddContact extends AppCompatActivity {

    EditText edtName, edtEmail,edtPhone;
    Button btnSave;
    int conId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);
        btnSave = findViewById(R.id.btn_save);

        Intent i = getIntent();
        conId = i.getIntExtra("con_id", -1);
        if (conId != -1){
            ContactTable ct = new ContactTable(AddContact.this);
            ContactModel cm = ct.readContact(conId);

            edtName.setText(cm.getName());
            edtEmail.setText(cm.getEmail());
            edtPhone.setText(cm.getPhone());
            btnSave.setText("Update");
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String buttonText = btnSave.getText().toString();
                DBHelper dbHelper = new DBHelper(AddContact.this);
                ContactTable ct = new ContactTable(AddContact.this);
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                if (buttonText.equals("Update")){
                    ContactModel cm = new ContactModel( conId, name, email, phone);
                    Log.e("Shuvo", "during update: "+ cm.getId());
                    ct.updateContact(cm);

                }else{
                    ContactModel cm = new ContactModel(name, email, phone);
                    ct.insertContact(cm);

                }


                edtName.setText("");
                edtEmail.setText("");
                edtPhone.setText("");

                startActivity(new Intent(AddContact.this, MainActivity.class));
            }
        });

    }
}