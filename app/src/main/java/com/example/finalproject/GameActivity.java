package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.finalproject.Model.ApiResponse;
import com.example.finalproject.Model.Category;
import com.example.finalproject.Model.Question;
import com.example.finalproject.Network.NetworkService;
import com.example.finalproject.UI.MyViewPager;
import com.example.finalproject.UI.ViewPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {

    private MyViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<Question> questions;
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;
    private String category;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        Intent intent = getIntent();
        this.category = intent.getStringExtra("CATEGORY");
        toolbar.setTitle(category);
        final FrameLayout progressBar = findViewById(R.id.progressBarHolder);

        viewPager = findViewById(R.id.viewPager);




        NetworkService.getInstance()
                    .getJSONApi()
                    .getQuestions(10, Category.valueOf(category).id)
                    .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<ApiResponse> call, @NotNull Response<ApiResponse> response) {
                        questions  = response.body().getResults();
                        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), questions);
                        viewPager.setAdapter(viewPagerAdapter);
                        viewPager.setOffscreenPageLimit (questions.size());
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(@NotNull Call<ApiResponse> call, @NotNull Throwable t) {
                        Toast.makeText(GameActivity.this, "Error retrieving data", Toast.LENGTH_LONG).show();
                        t.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    public void onAnswer(View v){
       MyViewPager myViewPager = findViewById(R.id.viewPager);
       myViewPager.setCurrentItem(myViewPager.getCurrentItem()+1,true);


       if( v.getTag() != null && v.getTag().toString().equals("correct")){
           Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show();
           correctAnswers++;
       } else {
           Toast.makeText(this,"Wrong :(",Toast.LENGTH_SHORT).show();
           incorrectAnswers++;
       }

        if((correctAnswers+incorrectAnswers) == questions.size() ) {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("incorrectAnswers",incorrectAnswers);
            intent.putExtra("category",category);
            intent.putExtra("correctAnswers",correctAnswers);
            intent.putExtra("questions",questions.size());

            startActivity(intent);
        }


    }
}
