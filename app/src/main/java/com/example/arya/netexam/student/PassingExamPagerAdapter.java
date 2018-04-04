package com.example.arya.netexam.student;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.arya.netexam.model.Questions;


class PassingExamPagerAdapter extends FragmentStatePagerAdapter {
    private Questions questions;
    private String examName;

    PassingExamPagerAdapter(FragmentManager fm, Questions questions, String examName) {
        super(fm);
        this.questions = questions;
        this.examName = examName;
    }

    @Override
    public Fragment getItem(int i) {

        Fragment fragment = new PassingExamFragment();

        Bundle args = new Bundle();
        args.putSerializable(PassingExamFragment.ARG_QUESTION, questions.getQuestions().get(i));
        args.putInt(PassingExamFragment.ARG_POSITION, i);
        args.putInt(PassingExamFragment.ARG_COUNT, getCount());
        args.putString(PassingExamFragment.ARG_EXAM_NAME, examName);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return questions.getQuestions().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Question " + (position + 1);
    }
}
