package com.example.nycgtrcode.codepathlab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddCardActivity extends AppCompatActivity {

    ImageView closeBtn, saveBtn;
    EditText question, aCorrect,  a, a1, a2, a3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        closeBtn = findViewById(R.id.btn_close);
        saveBtn = findViewById(R.id.save_btn);
        question = findViewById(R.id.question_prompt);
        aCorrect = findViewById(R.id.answer_prompt_correct);
        a = findViewById(R.id.answer_prompt);
        a1 = findViewById(R.id.answer_prompt1);
        a2 = findViewById(R.id.answer_prompt2);
        a3 = findViewById(R.id.answer_prompt3);
        checkIntentData();
        saveBtnClick();
        closeBtnClick();
    }

    private void checkIntentData() {
        Intent i = getIntent();
        if (i.getStringExtra("q")!=null&&i.getStringExtra("aCorrect")!=null&&i.getStringExtra("a")!=null&&i.getStringExtra("a1")!=null&&i.getStringExtra("a2")!=null&&i.getStringExtra("a3")!=null)
        {
            question.setText(i.getStringExtra("q"));
            aCorrect.setText(i.getStringExtra("aCorrect"));
            a.setText(i.getStringExtra("a"));
            a1.setText(i.getStringExtra("a1"));
            a2.setText(i.getStringExtra("a2"));
            a3.setText(i.getStringExtra("a3"));
        }
    }

    private void saveBtnClick() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q = question.getText().toString();
                String aC = aCorrect.getText().toString();
                String A = a.getText().toString();
                String A1 = a1.getText().toString();
                String A2 = a2.getText().toString();
                String A3 = a3.getText().toString();
                if (verifyInputs(q, aC, A ,A1, A2, A3))
                    passUserInput(q , aC, A, A1, A2, A3);
            }
        });
    }

    private boolean verifyInputs(String q, String aC, String A, String A1, String A2, String A3) {
        if (q.length()<1)
            question.setError("Field can't be left empty");

        else if(aC.length()<1)
            aCorrect.setError("Field can't be left empty");

        else if(A.length()<1)
            a.setError("Field can't be left empty");
        else if(A1.length()<1)
            a1.setError("Field can't be left empty");
        else if(A2.length()<1)
            a2.setError("Field can't be left empty");
        else if(A3.length()<1)
            a3.setError("Field can't be left empty");
        else if(!A.equals(aC)||!A1.equals(aC)||!A2.equals(aC)||!A3.equals(aC))
            Snackbar.make(findViewById(R.id.addCardView), "Please enter a correct answer for the choices", Snackbar.LENGTH_LONG).show();


        if (q.length()>=1&&aC.length()>=1&&A.length()>=1&&A1.length()>=1&&A2.length()>=1&&A3.length()>=1&&(A.equals(aC)||A1.equals(aC)||A2.equals(aC)||A3.equals(aC)))
            return true;

        return false;

    }

    private void passUserInput(String q, String aC, String A, String A1, String A2, String A3) {

        System.out.println(aC + "\t:aC Data");

        Intent mainActivity = new Intent(AddCardActivity.this, MainActivity.class);
        mainActivity.putExtra("q", q);
        mainActivity.putExtra("aCorrect", aC);
        mainActivity.putExtra("a", A);
        mainActivity.putExtra("a1", A1);
        mainActivity.putExtra("a2", A2);
        mainActivity.putExtra("a3", A3);
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
