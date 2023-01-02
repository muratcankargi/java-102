package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.User;

import javax.swing.*;

public class RegisterGUI extends JFrame {
    private JPanel wrapper;
    private JTextField txt_user_name;
    private JTextField txt_user_username;
    private JButton btn_register;
    private JButton btn_login;
    private JTextField txt_user_password;

    public RegisterGUI() {
        add(wrapper);
        setSize(400, 400);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_register.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_user_name) || Helper.isFieldEmpty(txt_user_username) || Helper.isFieldEmpty(txt_user_password)) {
                Helper.showMessages("fill");
            } else {
                String name = txt_user_name.getText();
                String username = txt_user_username.getText();
                String password = txt_user_password.getText();
                if (User.add(name, username, password)) {
                    Helper.showMessages("done");
                    txt_user_name.setText(null);
                    txt_user_username.setText(null);
                    txt_user_username.setText(null);
                }
            }
        });
        btn_login.addActionListener(e -> {
            LoginGUI loginGUI= new LoginGUI();
            dispose();
        });
    }
}
