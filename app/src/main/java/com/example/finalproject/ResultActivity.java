package com.example.finalproject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.Model.Category;
import com.example.finalproject.Model.Result;
import com.example.finalproject.Network.NetworkService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

// Declared private function

    private int total;
    private int correct;
    private String userId;
    private int categoryId;
    private ProgressDialog dialog;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
         this.correct = getIntent().getIntExtra("correctAnswers",0);
         this.total = getIntent().getIntExtra("questions",0);
         this.userId = getSharedPreferences("UserInfo", Context.MODE_PRIVATE).getString("UserId","<undefined>");
         this.categoryId = Category.valueOf(getIntent().getStringExtra("category") ).id;

        TextView  tvScore = findViewById(R.id.score);
        tvScore.setText(String.format("%d/%d", correct, total));

        // menu button where it navigates to the start activity

        Button btnHome = findViewById(R.id.btn_home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        // spinner will be displayed untill the result is displayed
        this.dialog = new ProgressDialog(ResultActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Loading");
        dialog.setMessage("Saving results");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);


        // Here requesting the response from json api to load the result.
        dialog.show();
        NetworkService.getInstance()
                .getJSONApi()
            //executing the post request
                .postResult(new Result(userId,categoryId,correct,total))
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(@NotNull Call<Result> call, @NotNull Response<Result> response) {

                        dialog.dismiss();

                    }

                    @Override
                    public void onFailure(@NotNull Call<Result> call, @NotNull Throwable t) {

                        t.printStackTrace();
                        dialog.dismiss();
                    }
                });
    }
  // menu button where it navigates to the start activity
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
