package com.example.newuser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email =(EditText)findViewById(R.id.etUserName);
        //Log.d("CREATION", "Show something");



    }
    public void OpenReg(View view){
        String username=email.getText().toString();
        String type = "login";
        BackgroungWorker backgroungWorker=new BackgroungWorker(this);
        backgroungWorker.execute(type,username);
       //BackgroungWorker msg=new BackgroungWorker(this);
       String ms= BackgroungWorker.getMessage();
       //ms="Success";
       //Log.d("CREATION", "Show something"+ms);

        /*if( TextUtils.isEmpty(email.getText())){

            email.setError( "Email is Required" );

        }
        else{
            startActivity(new Intent(this, Register.class));

        }*/
        if( TextUtils.isEmpty(email.getText())&&email.length()<1){

            email.setError( "Feed requirements" );

        }
        else if(ms.equals("Success")){
            startActivity(new Intent(this, Register.class));
            BackgroungWorker.setMessage("Unsuccessful");
            email.getText().clear();
        }


    }
}
