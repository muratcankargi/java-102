package com.patikaDev.Model;

import com.patikaDev.Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Questions {
    private int id;
    private String question;
    private String true_answer;
    private String wrong_answer_1;
    private String wrong_answer_2;
    private String wrong_answer_3;
    private String contents_title;

    public Questions(int id, String question, String true_answer, String wrong_answer_1, String wrong_answer_2, String wrong_answer_3, String contents_title) {
        this.id = id;
        this.question = question;
        this.true_answer = true_answer;
        this.wrong_answer_1 = wrong_answer_1;
        this.wrong_answer_2 = wrong_answer_2;
        this.wrong_answer_3 = wrong_answer_3;
        this.contents_title = contents_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTrue_answer() {
        return true_answer;
    }

    public void setTrue_answer(String true_answer) {
        this.true_answer = true_answer;
    }

    public String getWrong_answer_1() {
        return wrong_answer_1;
    }

    public void setWrong_answer_1(String wrong_answer_1) {
        this.wrong_answer_1 = wrong_answer_1;
    }

    public String getWrong_answer_2() {
        return wrong_answer_2;
    }

    public void setWrong_answer_2(String wrong_answer_2) {
        this.wrong_answer_2 = wrong_answer_2;
    }

    public String getWrong_answer_3() {
        return wrong_answer_3;
    }

    public void setWrong_answer_3(String wrong_answer_3) {
        this.wrong_answer_3 = wrong_answer_3;
    }

    public String getContents_title() {
        return contents_title;
    }

    public void setContents_title(String contents_title) {
        this.contents_title = contents_title;
    }
    public static ArrayList<Questions> getList() {
        ArrayList<Questions> questionsList = new ArrayList<>();
        Questions obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM questions");
            while (rs.next()) {
                int id = rs.getInt("id");
                String questions = rs.getString("question");
                String true_answer = rs.getString("true_answer");
                String wrong_answer_1 = rs.getString("wrong_answer_1");
                String wrong_answer_2 = rs.getString("wrong_answer_2");
                String wrong_answer_3 = rs.getString("wrong_answer_3");
                String contents_title = rs.getString("contents_title");
                obj = new Questions(id,questions, true_answer,wrong_answer_1,wrong_answer_2,wrong_answer_3,contents_title);
                questionsList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return questionsList;
    }
}
