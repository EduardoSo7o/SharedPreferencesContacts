package com.example.sharedpreferencescontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sharedpreferencescontacts.exceptions.*;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etData = findViewById(R.id.met_data);
    }

    public void save(View view){
        try {
            String name = etName.getText().toString();
            String contactInfo = etData.getText().toString();

            if (name.equals("")){
                throw new NullNameException();
            }else if (contactInfo.equals("")){
                throw new NullContactInfoException();
            }

            SharedPreferences sharedPreferences = getSharedPreferences("contacts", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("name", contactInfo);
            editor.commit();

            Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT).show();
        } catch (NullNameException e) {
            Toast.makeText(this, "Type contact name", Toast.LENGTH_SHORT).show();
        } catch (NullContactInfoException e) {
            Toast.makeText(this, "Type contact info", Toast.LENGTH_SHORT).show();
        }
    }

    public void search(View view){
        try {
            String name = etName.getText().toString();

            if (name.equals(""))
                throw new NullNameException();

            SharedPreferences sharedPreferences = getSharedPreferences("contacts", Context.MODE_PRIVATE);

            String contactInfo = sharedPreferences.getString("name", "");

            if (!contactInfo.equals("")) {
                etData.setText(contactInfo);
            } else {
                Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show();
            }
        } catch (NullNameException e) {
            Toast.makeText(this, "Type contact name", Toast.LENGTH_SHORT).show();
        }
    }
}