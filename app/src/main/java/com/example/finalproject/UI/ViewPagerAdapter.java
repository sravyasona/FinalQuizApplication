package com.example.finalproject.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.finalproject.Model.Question;
import com.example.finalproject.UI.Fragments.QuestionFragment;

import java.util.ArrayList;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<Question> questions;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Question> questions){
        super (fm);
        this.questions = questions;

    }

    @Override
    public Fragment getItem(int position) {
        QuestionFragment questionFragment = new QuestionFragment();
        Bundle bundle = new Bundle();
        Question question = questions.get(position);
        bundle.putParcelable("question",question);
        questionFragment.setArguments(bundle);
        return questionFragment;
    }

    @Override
    public int getCount() {
        return questions.size();
    }
}
