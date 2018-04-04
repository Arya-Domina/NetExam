package com.example.arya.netexam.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.arya.netexam.Pref;
import com.example.arya.netexam.R;
import com.example.arya.netexam.model.Questions;


public class PassingExamFragment extends Fragment {

    public static final String ARG_QUESTION = "text_question";
    public static final String ARG_POSITION = "item_position";
    public static final String ARG_COUNT = "item_count";
    public static final String ARG_EXAM_NAME = "exam_name";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_passing_exam, container, false);
        final Bundle args = getArguments();

        final int position = args.getInt(ARG_POSITION);
        ((TextView) rootView.findViewById(R.id.questions_count)).setText(
                getResources().getString(R.string.question) + " " + (position + 1) + " из " + args.getInt(ARG_COUNT));
        Questions.Question question = (Questions.Question) args.getSerializable(ARG_QUESTION);
        assert question != null;
        ((TextView) rootView.findViewById(R.id.text_question)).setText(question.getQuestion());

        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group);
        for (String s : question.getAnswers()) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(s);
            radioGroup.addView(radioButton);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int index = radioGroup.indexOfChild(radioGroup.findViewById(i));
                new Pref(getContext()).saveIndex(args.getString(ARG_EXAM_NAME), position, index);

            }
        });
        int savedRadioIndex = new Pref(getContext()).loadIndex(args.getString(ARG_EXAM_NAME), position);
        if (savedRadioIndex!=-1) {
            RadioButton savedCheckedRadioButton = (RadioButton) radioGroup.getChildAt(savedRadioIndex);
            savedCheckedRadioButton.setChecked(true);
        }
        return rootView;
    }
}
