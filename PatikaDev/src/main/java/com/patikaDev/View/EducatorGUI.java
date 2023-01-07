package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EducatorGUI extends  JFrame{
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JButton btn_exit;
    private JTable tbl_education;
    private JTextField txt_search_education;
    private DefaultTableModel mdl_education_list;
    private Object[] row_education_list;
    static String userName;
    public EducatorGUI(String username){
        userName=username;
        add(wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        btn_exit.addActionListener(e -> {
            LoginGUI loginGUI= new LoginGUI();
            dispose();
        });

        mdl_education_list= new DefaultTableModel();
        Object[] col_education_list={"Id","Eğitim Adı","Dili"};
        mdl_education_list.setColumnIdentifiers(col_education_list);
        row_education_list=new Object[col_education_list.length];
        loadEducationModel();
        tbl_education.setModel(mdl_education_list);
        tbl_education.getTableHeader().setReorderingAllowed(false);
        txt_search_education.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            String query=Helper.searchQuery(txt_search_education.getText());

            }
        });
    }
    private void loadEducationModel(){
        DefaultTableModel clearModel=(DefaultTableModel) tbl_education.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Course obj: Course.getList()){
            i=0;
            if(obj.getEducator().getUsername().equals(userName)){
            row_education_list[i++]=obj.getId();
            row_education_list[i++]=obj.getName();
            row_education_list[i++]=obj.getLanguage();
            mdl_education_list.addRow(row_education_list);
        }}
    }
}
