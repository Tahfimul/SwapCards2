package com.example.nycgtrcode.codepathlab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddCardActivity extends AppCompatActivity {

    ImageView closeBtn, saveBtn;
    EditText question;
    EditText answer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        closeBtn = findViewById(R.id.btn_close);
        saveBtn = findViewById(R.id.save_btn);
        question = findViewById(R.id.question_prompt);
        answer = findViewById(R.id.answer_prompt);
        saveBtnClick();
        closeBtnClick();
    }

    private void saveBtnClick() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q = question.getText().toString();
                String a = answer.getText().toString();
                if (verifyInputs(q, a))
                    passUserInput(q , a);
            }
        });
    }

    private boolean verifyInputs(String q, String a) {
        if (q.length()<1)
            question.setError("Field can't be left empty");

        else if(a.length()<1)
            answer.setError("Field can't be left empty");

        if (q.length()>=1&&a.length()>=1)
            return true;

        return false;

    }

    private void passUserInput(String q, String a) {

        Intent mainActivity = new Intent(AddCardActivity.this, MainActivity.class);
        mainActivity.putExtra("q", q);
        mainActivity.putExtra("a", a);
        startActivity(mainActivity);
    }

    private void closeBtnClick() {
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
    }
}
