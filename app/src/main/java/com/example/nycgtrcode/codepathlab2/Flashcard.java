package com.example.nycgtrcode.codepathlab2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

@Entity
public class Flashcard {

    @Ignore
    Flashcard(String question, String answer) {
        this.uuid = UUID.randomUUID().toString();
        this.question = question;
        this.answer = answer;
    }

    Flashcard(String question, String answer, String a, String a1) {
        this.uuid = UUID.randomUUID().toString();
        this.question = question;
        this.answer = answer;
        this.a = a;
        this.a1 = a1;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private String uuid;

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @NonNull
    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "answer")
    private String answer;

    @Nullable
    @ColumnInfo(name = "a")
    private String a;

    @Nullable
    @ColumnInfo(name = "a1")
    private String a1;

    @Nullable
    @ColumnInfo(name = "a2")
    private String a2;

    @Nullable
    @ColumnInfo(name = "a3")
    private String a3;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Nullable
    public String a() {
        return a;
    }

    public void a(String a) {
        this.a = a;
    }

    @Nullable
    public String a1() {
        return a1;
    }

    public void a1(String a1) {
        this.a1 = a1;
    }

    @Nullable
    public String a2() {
        return a2;
    }

    public void a2(String a2) {
        this.a2 = a2;
    }

    @Nullable
    public String a3() {
        return a3;
    }

    public void a3(String a3) {
        this.a3 = a3;
    }
}
