package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.Questions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddQuestionGUI extends JFrame {
    private JPanel wrapper;
    private JTextArea txt_question;
    private JTextField txt_true_answer;
    private JButton btn_question_add;
    private JTextField txt_false_answer_1;
    private JTextField txt_false_answer_2;
    private JTextField txt_false_answer_3;
    private JButton btn_exit;
    private JTextPane textPane1;

    public AddQuestionGUI(String contents_title) {
        add(wrapper);
        setSize(500, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        btn_question_add.addActionListener(e -> {

        });
        btn_question_add.addActionListener(e -> {
            if (txt_question.getText() == null || Helper.isFieldEmpty(txt_true_answer) || Helper.isFieldEmpty(txt_false_answer_1) || Helper.isFieldEmpty(txt_false_answer_2) || Helper.isFieldEmpty(txt_false_answer_3)) {
                Helper.showMessages("fill");
            } else {
                if (Questions.add(txt_question.getText(), txt_true_answer.getText(), txt_false_answer_1.getText(), txt_false_answer_2.getText(), txt_false_answer_3.getText(), contents_title)) {
                    Helper.showMessages("done");
                    txt_question.setText(null);
                    txt_true_answer.setText(null);
                    txt_false_answer_1.setText(null);
                    txt_false_answer_2.setText(null);
                    txt_false_answer_3.setText(null);

                } else {
                    Helper.showMessages("error");
                }
            }
        });
        btn_exit.addActionListener(e -> {
            dispose();
        });
    }

}
