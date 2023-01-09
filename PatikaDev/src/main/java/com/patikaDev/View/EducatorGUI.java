package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.Contents;
import com.patikaDev.Model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EducatorGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JButton btn_exit;
    private JTable tbl_education;
    private JTextField txt_search_education;
    private JTable tbl_contents;
    private JTextField txt_content_baslik;
    private JComboBox cmb_egitim_adi;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton içerikEkleButton;
    private JLabel lbl_content_id;
    private JButton içerikSilButton;
    private DefaultTableModel mdl_education_list;
    private Object[] row_education_list;
    private DefaultTableModel mdl_contents_list;
    private Object[] row_contents_list;
    static String userName;

    public EducatorGUI(String username) {
        userName = username;
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        btn_exit.addActionListener(e -> {
            LoginGUI loginGUI = new LoginGUI();
            dispose();
        });

        mdl_education_list = new DefaultTableModel();
        Object[] col_education_list = {"Id", "Eğitim Adı", "Dili"};
        mdl_education_list.setColumnIdentifiers(col_education_list);
        row_education_list = new Object[col_education_list.length];
        loadEducationModel();
        tbl_education.setModel(mdl_education_list);
        tbl_education.getTableHeader().setReorderingAllowed(false);

        txt_search_education.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String query = Helper.searchQuery(txt_search_education.getText());
                loadEducatorModel(Course.searchUserList(query));
            }
        });


        mdl_contents_list = new DefaultTableModel();
        Object[] col_contents_list = {"Id", "Başlık", "Açıklama", "Link", "Patika Adı"};
        mdl_contents_list.setColumnIdentifiers(col_contents_list);
        row_contents_list = new Object[col_contents_list.length];
        loadContensModel();
        tbl_contents.setModel(mdl_contents_list);
        tbl_contents.getTableHeader().setReorderingAllowed(false);
        txt_content_baslik.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String query = Contents.searchQuery(txt_content_baslik.getText(), cmb_egitim_adi.getSelectedItem().toString());
                loadContentsModel(Contents.searchContentsList(query));
            }
        });
        cmb_egitim_adi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = Contents.searchQuery(txt_content_baslik.getText(), cmb_egitim_adi.getSelectedItem().toString());
                loadContentsModel(Contents.searchContentsList(query));
            }
        });
    }

    private void loadEducationModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_education.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Course obj : Course.getList()) {
            i = 0;
            if (obj.getEducator().getUsername().equals(userName)) {
                row_education_list[i++] = obj.getId();
                row_education_list[i++] = obj.getName();
                row_education_list[i++] = obj.getLanguage();
                mdl_education_list.addRow(row_education_list);

                lbl_welcome.setText("Hoşgeldin," + obj.getEducator().getName());
            }
        }
    }

    private void loadEducatorModel(ArrayList<Course> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_education.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Course obj : list) {
            i = 0;
            row_education_list[i++] = obj.getId();
            row_education_list[i++] = obj.getName();
            row_education_list[i] = obj.getLanguage();
            mdl_education_list.addRow(row_education_list);
        }
    }

    private void loadContensModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_contents.getModel();
        clearModel.setRowCount(0);
        int i;

        for (Contents obj : Contents.getList()) {
            i = 0;
            row_contents_list[i++] = obj.getId();
            row_contents_list[i++] = obj.getTitle();
            row_contents_list[i++] = obj.getExplanation();
            row_contents_list[i++] = obj.getLink();
            row_contents_list[i] = Helper.getPatikaName(obj.getPatika_id());
            mdl_contents_list.addRow(row_contents_list);
            int counter=0;
            for(int j=0;j<cmb_egitim_adi.getItemCount();j++) {
                if (cmb_egitim_adi.getItemAt(j).equals(Helper.getPatikaName(obj.getPatika_id()))) {
                    counter++;
                }
            }
            if(counter==0)
                cmb_egitim_adi.addItem(Helper.getPatikaName(obj.getPatika_id()));
        }


    }

    private void loadContentsModel(ArrayList<Contents> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_contents.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Contents obj : list) {
            i = 0;
            row_contents_list[i++] = obj.getId();
            row_contents_list[i++] = obj.getTitle();
            row_contents_list[i++] = obj.getExplanation();
            row_contents_list[i++] = obj.getLink();
            row_contents_list[i] = Helper.getPatikaName(obj.getPatika_id());
            mdl_contents_list.addRow(row_contents_list);
        }
    }
}
