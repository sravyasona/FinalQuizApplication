package com.example.finalproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.Model.User;
import com.example.finalproject.Network.NetworkService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {


    Button login;

    // user information is private mode
    // It intents to the start activity to the main activity
    @Override
    protected void onStart() {
        SharedPreferences settings = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        try {
            if(settings.getString("UserId",null)!=null){
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }catch (Exception e){

        }
        super.onStart();
    }

    // login button to enter into the main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        final FrameLayout progressBar = findViewById(R.id.progressBarHolder);
//    TODO    if not userid in prefs else starrt activity; finish()
        final ProgressDialog dialog = new ProgressDialog(StartActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
        NetworkService.getInstance()
                .getJSONApi()
                .getUserByName( ((EditText)findViewById(R.id.username)).getText().toString())

        //Our APIService instance will return a Call instance which has a method called enqueue(Callback<T> callback). [16]
                .enqueue(new Callback<User>() {

                    // after giving user id it store in the data storage.
                    @Override
                    public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
                        SharedPreferences settings = getSharedPreferences("UserInfo", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("UserId",response.body().getId());
                        editor.apply();
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                        finish();
                    }

                    @Override
                    public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {

                        t.printStackTrace();
                        dialog.dismiss();
                    }
                });
    }
}
