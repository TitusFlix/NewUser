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
    EditText fname,surname,mothername, id, phone,email,address,kinnames,kinidno,kinaddress,telephone,town;
    //String str_name,str_surname,str_id,str_phone;
    private ProgressDialog progress;
    Button btn_reg;
    Spinner spinner;
    Spinner spinner0;
    Spinner spinner1;
    Spinner spinner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname=(EditText) findViewById(R.id.et_name);
        surname=(EditText) findViewById(R.id.et_surname);
        mothername= (EditText) findViewById(R.id.et_mothername);
        id=(EditText) findViewById(R.id.et_id);
        phone=(EditText) findViewById(R.id.et_phone );
        email=(EditText) findViewById(R.id.et_email );
        address=(EditText) findViewById(R.id.et_address);
         //kin
        kinnames=(EditText) findViewById(R.id.et_kinnames);
        kinidno=(EditText) findViewById(R.id.et_kinidno);
        kinaddress=(EditText) findViewById(R.id.et_kinaddress);
        telephone=(EditText) findViewById(R.id.et_telephone);
        town=(EditText) findViewById(R.id.et_town);
        //age=(EditText) findViewById(R.id.et_age);

        btn_reg=(Button) findViewById(R.id.btn_reg);
        spinner=(Spinner) findViewById(R.id.spinner1);
        spinner0=(Spinner) findViewById(R.id.spinner2);
        spinner1=(Spinner) findViewById(R.id.spinner3);
        //kin
        spinner2=(Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(Register.this,
        android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ArrayAdapter<String> adapter0= new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.title));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner0.setAdapter(adapter0);
        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.status));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        //kin
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.relationship));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

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
        }else if(TextUtils.isEmpty(kinnames.getText())){
            kinnames.setError( "Cell is Required" );
        }else if(TextUtils.isEmpty(kinidno.getText())){
            kinidno.setError( "Cell is Required" );
        }else if(TextUtils.isEmpty(telephone.getText())){
            telephone.setError( "Cell is Required" );
        } else if(spinner.getSelectedItem().equals("Select")){
            Toast.makeText(Register.this,"Please Select the gender type !!", Toast.LENGTH_LONG) .show();
            return;

        }
        else if(spinner2.getSelectedItem().equals("SELECT")){
            Toast.makeText(Register.this,"Please Select the relationship type !!", Toast.LENGTH_LONG) .show();
            return;

        }
        else{
            String str_name = fname.getText().toString().trim();
            String str_surname = surname.getText().toString().trim();
            String str_id = id.getText().toString().trim();
            String str_phone = phone.getText().toString().trim();
            String str_email = email.getText().toString().trim();
            String str_gender = spinner.getSelectedItem().toString().trim();
            String str_mothername = mothername.getText().toString().trim();
            String str_address = address.getText().toString().trim();
            String str_title = spinner0.getSelectedItem().toString().trim();
            String str_status = spinner1.getSelectedItem().toString().trim();
            //kin
            String str_kinnames = kinnames.getText().toString().trim();
            String str_kinidno = kinidno.getText().toString().trim();
            String str_kinaddress = kinaddress.getText().toString().trim();
            String str_telephone = telephone.getText().toString().trim();
            String str_town = town.getText().toString().trim();
            //String str_age = age.getText().toString().trim();
            String str_relationship = spinner2.getSelectedItem().toString().trim();
            String type="register";

            BackgroungWorker backgroungWorker= new BackgroungWorker(this);
            backgroungWorker.execute(type,str_name,str_surname,str_id,str_phone,str_email,
                    str_gender,str_mothername,str_address,str_title,str_status,str_kinnames,str_kinidno,str_kinaddress
            ,str_telephone,str_town,str_relationship);
            Toast.makeText(this,"Process Initiated",Toast.LENGTH_SHORT).show();
            fname.getText().clear();
            surname.getText().clear();
            id.getText().clear();
            phone.getText().clear();
            email.getText().clear();
            mothername.getText().clear();
            address.getText().clear();
            kinnames.getText().clear();
            kinidno.getText().clear();
            kinaddress.getText().clear();
            telephone.getText().clear();
            town.getText().clear();
            //age.getText().clear();
            spinner.setSelection(0);
            spinner0.setSelection(0);
            spinner1.setSelection(0);
            spinner2.setSelection(0);

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
