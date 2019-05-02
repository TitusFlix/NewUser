package com.example.newuser;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText fname,surname, id, phone,email;
    String str_name,str_surname,str_id,str_phone;
    private ProgressDialog progress;
    Button btn_reg;

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
        /*btn_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progress=new ProgressDialog(Register.this);
                progress.setMax(100);
                progress.setMessage("Please Wait..");
                progress.setTitle("Register");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progress.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (progress.getProgress()<=progress.getMax()){
                                Thread.sleep(200);
                                handle.sendMessage(handle.obtainMessage());
                                if(progress.getProgress()==progress.getMax())
                                {
                                    progress.dismiss();
                                }
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
            Handler handle= new Handler(){
                @Override
                public void handleMessage(Message msg){
                    super.handleMessage(msg);
                    progress.incrementProgressBy(1);

                }
            };
        });*/

    }
    public void OnReg(View view){
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
        }else if(TextUtils.isEmpty(phone.getText())){
            phone.setError( "Cell is Required" );
        }
        else{
            String str_name = fname.getText().toString().trim();
            String str_surname = surname.getText().toString().trim();
            String str_id = id.getText().toString().trim();
            String str_phone = phone.getText().toString().trim();
            String str_email = email.getText().toString().trim();
            String type="register";

            BackgroungWorker backgroungWorker= new BackgroungWorker(this);
            backgroungWorker.execute(type,str_name,str_surname,str_id,str_phone,str_email);
            Toast.makeText(this,"Process Initiated",Toast.LENGTH_SHORT).show();
            fname.getText().clear();
            surname.getText().clear();
            id.getText().clear();
            phone.getText().clear();
            email.getText().clear();
        }

    }
}