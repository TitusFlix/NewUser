package com.example.newuser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText fname,surname, id, phone,email;
    String str_name,str_surname,str_id,str_phone;
    private ProgressDialog progress;
    Button btn_reg;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname=(EditText) findViewById(R.id.et_name);
        surname=(EditText) findViewById(R.id.et_surname);
        id=(EditText) findViewById(R.id.et_id);
        phone=(EditText) findViewById(R.id.et_phone );
        email=(EditText) findViewById(R.id.et_email );
        btn_reg=(Button) findViewById(R.id.btn_reg);
        spinner=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(Register.this,
        android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
    public void OnReg(View view){
        String name = null;
        if (TextUtils.isEmpty(fname.getText())){
            fname.setError( "First name is Required" );
        }
        else if(TextUtils.isEmpty(surname.getText())){
            surname.setError( "Surname is Required" );
        }
        else if(TextUtils.isEmpty(id.getText())){
            id.setError( "ID is Required" );
        }
        else if(TextUtils.isEmpty(phone.getText())){
            phone.setError( "Cell is Required" );
        }else if(TextUtils.isEmpty(email.getText())){
            email.setError( "Cell is Required" );
        }else if(spinner.getSelectedItem().equals("Select")){
            Toast.makeText(Register.this,"Please Select the gender type !!", Toast.LENGTH_LONG) .show();
            return;

        }
        else{
            String str_name = fname.getText().toString().trim();
            String str_surname = surname.getText().toString().trim();
            String str_id = id.getText().toString().trim();
            String str_phone = phone.getText().toString().trim();
            String str_email = email.getText().toString().trim();
            String str_gender = spinner.getSelectedItem().toString().trim();
            String type="register";

            BackgroungWorker backgroungWorker= new BackgroungWorker(this);
            backgroungWorker.execute(type,str_name,str_surname,str_id,str_phone,str_email,str_gender);
            Toast.makeText(this,"Process Initiated",Toast.LENGTH_SHORT).show();
            fname.getText().clear();
            surname.getText().clear();
            id.getText().clear();
            phone.getText().clear();
            email.getText().clear();
            spinner.setSelection(0);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:
                startActivity(new Intent(Register.this,MainActivity.class));
                //finish();
                System.exit(0);
                break;
            case R.id.menuSettings:
                Toast.makeText(this,"You Just Clicked Settings", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
