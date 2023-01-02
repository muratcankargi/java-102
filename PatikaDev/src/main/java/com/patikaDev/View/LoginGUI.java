package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.Operator;
import com.patikaDev.Model.User;

import javax.swing.*;
public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField txt_user_usarname;
    private JPasswordField txt_user_pass;
    private JButton btn_login;
    private JButton btn_register;

    public LoginGUI(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        btn_login.addActionListener(e -> {
        if(Helper.isFieldEmpty(txt_user_usarname)||Helper.isFieldEmpty(txt_user_pass)){
            Helper.showMessages("fill");
        }
        else {
            User u= User.getFetch(txt_user_usarname.getText(),txt_user_pass.getText());
            if(u==null){
                Helper.showMessages("Kullanıcı Bulunamadı");
            }
            else {
                switch (u.getType()){
                    case "operator":
                        OperatorGUI opGUI= new OperatorGUI((Operator) u);
                        break;
                    case "educator":
                        EducatorGUI eduGUI= new EducatorGUI();
                        break;
                    case "student":
                        StudentGUI stGUI= new StudentGUI();
                        break;
                }
                dispose();
            }
        }
        });

        btn_register.addActionListener(e -> {
            RegisterGUI registerGUI= new RegisterGUI();
            dispose();
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI loginGUI= new LoginGUI();
    }
}
