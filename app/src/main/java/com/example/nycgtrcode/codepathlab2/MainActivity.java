package com.example.nycgtrcode.codepathlab2;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView addBtn, editBtn, nextBtn;
    TextView output, A, A1, A2, A3;
    boolean showQuestion=true, showAnswer=false;
    String q, aCorrect ,a, a1, a2, a3;


    int index = 0;

    List<Flashcard> allCards;

    //DB
    FlashcardDatabase flashcardDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = findViewById(R.id.add_btn);
        editBtn = findViewById(R.id.edit_btn);
        nextBtn = findViewById(R.id.btn_next);
        output = findViewById(R.id.output);
        A = findViewById(R.id.a);
        A1 = findViewById(R.id.a1);
        A2 = findViewById(R.id.a2);
        A3 = findViewById(R.id.a3);
        //DB
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());

        allCards = flashcardDatabase.getAllCards();

        //checkIntentData();

        q = "Who is the 44th president of the United States?";
        aCorrect = "Barack Obama";
        a = "Barack Obama";
        a1 = "George W. Bush";
        a2 = "Bill Gates";
        a3 = "None";

        editBtnClick();
        addBtnClick();
        nextBtnClick();
        output.setText(q);
        setOutputTextViewClick();
        setChoices();
        setOnClickChoices();

    }
    private void checkIntentData() {
        Intent i = getIntent();
        if(i.getStringExtra("q")==null||i.getStringExtra("aCorrect")==null||i.getStringExtra("a")==null||i.getStringExtra("a1")==null||i.getStringExtra("a2")==null||i.getStringExtra("a3")==null)
        {
            q = "Who is the 44th president of the United States?";
            aCorrect = "Barack Obama";
            a = "Barack Obama";
            a1 = "George W. Bush";
            a2 = "Bill Gates";
            a3 = "None";

        }
        else
        {
            q= i.getStringExtra("q");
            aCorrect = i.getStringExtra("aCorrect");
            a=i.getStringExtra("a");
            a1 = i.getStringExtra("a1");
            a2 = i.getStringExtra("a2");
            a3 = i.getStringExtra("a3");
            Snackbar.make(findViewById(R.id.activityMain), "New Card Created", Snackbar.LENGTH_LONG).show();

        }
    }

    private void editBtnClick() {
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editCard = new Intent(MainActivity.this, AddCardActivity.class);
                editCard.putExtra("q", q);
                editCard.putExtra("aCorrect", aCorrect);
                editCard.putExtra("a", a);
                editCard.putExtra("a1", a1);
                editCard.putExtra("a2", a2);
                editCard.putExtra("a3", a3);
                startActivity(editCard);
            }
        });

    }

    private void addBtnClick() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCardActivity = new Intent(MainActivity.this, AddCardActivity.class);
                startActivityForResult(addCardActivity, 100);
            }
        });
    }

    private void nextBtnClick()
    {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q = allCards.get(index).getQuestion();
                aCorrect = allCards.get(index).getAnswer();
                a = allCards.get(index).getChoiceA();
                a1 = allCards.get(index).getChoiceA1();
                a2 = allCards.get(index).getChoiceA2();
                a3 = allCards.get(index).getChoiceA3();
                output.setText(q);
                setChoices();
                setOnClickChoices();
                index++;
                if (index > allCards.size()-1)
                    index=0;

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100)
            if (resultCode == Activity.RESULT_OK)
            {
                q= data.getStringExtra("q");
                aCorrect = data.getStringExtra("aCorrect");
                a=data.getStringExtra("a");
                a1 = data.getStringExtra("a1");
                a2 = data.getStringExtra("a2");
                a3 = data.getStringExtra("a3");
                Snackbar.make(findViewById(R.id.activityMain), "New Card Created", Snackbar.LENGTH_LONG).show();
                output.setText(q);
                setOutputTextViewClick();
                setChoices();
                setOnClickChoices();

                flashcardDatabase.insertCard(new Flashcard(q, aCorrect, a, a1, a2, a3));
            }

    }

    void setOutputTextViewClick() {
        output.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showQuestion)
                {
                    showQuestion = false;
                    showAnswer = true;
                    output.setText(aCorrect);
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

    private void setChoices() {
        A.setText(a);
        A1.setText(a1);
        A2.setText(a2);
        A3.setText(a3);
    }


    private void setOnClickChoices() {
        A.setOnClickListener(this);
        A1.setOnClickListener(this);
        A2.setOnClickListener(this);
        A3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       Button val = (Button) v;
       String valText = val.getText().toString();

       if(!valText.equals(aCorrect))
           val.setBackground(getResources().getDrawable(R.color.colorRed));

       else
           val.setBackground(getResources().getDrawable(R.color.colorGreen));




    }
}

