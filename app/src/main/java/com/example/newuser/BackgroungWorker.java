package com.example.newuser;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroungWorker extends AsyncTask<String, Void, String> {
    //context
    Context context;
    AlertDialog alertDialog;
    BackgroungWorker(Context ctx){
        context=ctx;
    }
    static String  msg="";
    public static String getMessage(){
        return msg;
    }
    public static void setMessage(String ms){
        msg=ms;
    }


    @Override
    protected void onPreExecute()
    {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Message");
    }

    @Override
    protected void onPostExecute(String msg) {
        /*if(result.equals("Success")){

            Intent intent = new Intent(context, Register.class);
            context.startActivity(intent);
        }else{*/

            alertDialog.setMessage(msg);
            alertDialog.show();


        }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        //urls
        String reg_url="http://192.168.0.108/userReg/register.php";
        String login_url="http://192.168.0.108/userReg/login.php";
        if(type.equals("register"))
        {
            try {
                String fname =params[1];
                String surname =params[2];
                String id =params[3];
                String phone =params[4];
                String email =params[5];


                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS =httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data= URLEncoder.encode("fname","UTF-8")+ "="+URLEncoder.encode(fname,"UTF-8")+"&"+
                        URLEncoder.encode("surname","UTF-8")+ "="+URLEncoder.encode(surname,"UTF-8")+"&"+
                        URLEncoder.encode("id","UTF-8")+ "="+URLEncoder.encode(id,"UTF-8")+"&"+
                        URLEncoder.encode("phone","UTF-8")+ "="+URLEncoder.encode(phone,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+ "="+URLEncoder.encode(email,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS= httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;

                }

                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return result;
            }catch (MalformedURLException e){
                e.printStackTrace();

            }
            catch (IOException e){
                e.printStackTrace();
            }

        }else if (type.equals("login")){
            try {
                String user_name =params[1];
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS =httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data= URLEncoder.encode("user_name","UTF-8")+ "="+URLEncoder.encode(user_name,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS= httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                msg=result;
                return result;
            }catch (MalformedURLException e){
                e.printStackTrace();

            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
        return null;
    }
}
