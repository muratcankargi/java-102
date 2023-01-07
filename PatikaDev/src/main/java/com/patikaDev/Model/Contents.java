package com.patikaDev.Model;

import com.patikaDev.Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Contents {
    private int id;
    private String title;
    private String explanation;
    private String link;
    private int patika_id;

    public Contents(int id, String title, String explanation, String link, int patika_id) {
        this.id = id;
        this.title = title;
        this.explanation = explanation;
        this.link = link;
        this.patika_id = patika_id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public static ArrayList<Contents> getList() {
        ArrayList<Contents> contentsList = new ArrayList<>();
        Contents contents;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM contents");
            while (rs.next()) {
                contents = new Contents(rs.getInt("id"), rs.getString("title"), rs.getString("explanation"), rs.getString("link"), rs.getInt("patika_id"));
                contentsList.add(contents);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contentsList;
    }
}
