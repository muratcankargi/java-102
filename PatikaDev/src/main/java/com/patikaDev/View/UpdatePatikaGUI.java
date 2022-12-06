package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.Patika;

import javax.swing.*;
public class UpdatePatikaGUI extends JFrame{
    private JPanel wrapper;
    private JTextField txt_patika_name;
    private JButton btn_update;
    private Patika patika;
    public UpdatePatikaGUI(Patika patika){
    this.patika=patika;
    add(wrapper);
    setSize(300,150);
    setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle(Config.PROJECT_TITLE);
    setVisible(true);
    txt_patika_name.setText(patika.getName());

    btn_update.addActionListener(e -> {
        if(Helper.isFieldEmpty(txt_patika_name)){
            Helper.showMessages("fill");
        }
        else{
            if(Patika.update(patika.getId(),txt_patika_name.getText())){
        Helper.showMessages("done");
            }
            dispose();
        }
        });
    }
}

