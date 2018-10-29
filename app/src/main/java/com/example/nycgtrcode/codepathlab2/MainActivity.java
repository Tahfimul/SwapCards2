package com.example.nycgtrcode.codepathlab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView addBtn;
    TextView output;
    boolean showQuestion=true, showAnswer=false;
    String q, a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = findViewById(R.id.add_btn);
        output = findViewById(R.id.output);
        Intent i = getIntent();
        if(i.getStringExtra("q")==null||i.getStringExtra("a")==null)
        {
            q = "Who is the 44th president of the United States?";
            a = "Barack Obama";
        }
        else
        {
            q= i.getStringExtra("q");
            a=i.getStringExtra("a");
        }
        addBtnClick();
        output.setText(q);
        setOutputTextViewClick();
    }

    private void addBtnClick() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCardActivity = new Intent(MainActivity.this, AddCardActivity.class);
                startActivity(addCardActivity);
            }
        });
    }

    void setOutputTextViewClick() {
        output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showQuestion)
                {
                    showQuestion = false;
                    showAnswer = true;
                    output.setText(a);
                }
                else
                {
                    showAnswer = false;
                    showQuestion = true;
                    output.setText(q);
                }
            }
        });
    }
}
