package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.Contents;
import com.patikaDev.Model.Course;
import com.patikaDev.Model.Patika;
import com.patikaDev.Model.Questions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
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
    private JComboBox cmb_egitim_adi_2;
    private JTextField txt_content_title;
    private JTextField txt_content_explanation;
    private JButton btn_contents_add;
    private JTextField txt_content_link;
    private DefaultTableModel mdl_education_list;
    private Object[] row_education_list;
    private DefaultTableModel mdl_contents_list;
    private Object[] row_contents_list;
    private DefaultTableModel mdl_questions_list;
    private Object[] row_questions_list;
    private JLabel lbl_content_id;
    private JButton btn_content_delete;
    private JPopupMenu patikaMenu; //düzeltilecek
    private JTable tbl_questions;

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

        patikaMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Soru Ekle");
        patikaMenu.add(updateMenu);

        updateMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_contents.getValueAt(tbl_contents.getSelectedRow(), 0).toString());
            UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patika.getFetch(select_id));
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadContensModel();
                    loadEducationModel();
                    loadQuestionsModel();
                }
            });
        });

        tbl_contents.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int select_row = tbl_contents.rowAtPoint(point);
                tbl_contents.setRowSelectionInterval(select_row, select_row);
            }
        });

        mdl_contents_list = new DefaultTableModel();
        Object[] col_contents_list = {"Id", "Başlık", "Açıklama","Link","Patika Adı"};
        mdl_contents_list.setColumnIdentifiers(col_contents_list);
        row_contents_list = new Object[col_contents_list.length];
        loadContensModel();
        tbl_contents.setModel(mdl_contents_list);
        tbl_contents.setComponentPopupMenu(patikaMenu);
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

        btn_contents_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_content_title) || Helper.isFieldEmpty(txt_content_explanation) || Helper.isFieldEmpty(txt_content_link)) {
                Helper.showMessages("fill");
            } else {

                String title = txt_content_title.getText();
                String explanation = txt_content_explanation.getText();
                String link = txt_content_link.getText();
                int patika_id = Helper.getPatikaId(cmb_egitim_adi_2.getSelectedItem().toString());
                if (Contents.add(title, explanation, link, patika_id)) {
                    Helper.showMessages("done");
                    loadContensModel();
                    loadEducationModel();
                    txt_content_title.setText(null);
                    txt_content_explanation.setText(null);
                    txt_content_link.setText(null);
                    txt_content_baslik.setText(null);
                    cmb_egitim_adi.setSelectedIndex(0);
                }
            }
        });
        tbl_contents.getSelectionModel().addListSelectionListener(e -> {
            String selected_user_id = tbl_contents.getSelectedRow() != -1 ? tbl_contents.getValueAt(tbl_contents.getSelectedRow(), 0).toString() : null;
            lbl_content_id.setText(selected_user_id);
        });
        btn_content_delete.addActionListener(e -> {
            if (lbl_content_id.getText().equals("")) {
                Helper.showMessages("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int user_id = Integer.parseInt(lbl_content_id.getText());
                    if (Contents.delete(user_id)) {
                        Helper.showMessages("done");
                        loadEducationModel();
                        loadContensModel();
                        lbl_content_id.setText("");
                        txt_content_baslik.setText(null);
                        cmb_egitim_adi.setSelectedIndex(0);
                    } else {
                        Helper.showMessages("error");
                    }
                }
            }
        });

        mdl_questions_list = new DefaultTableModel();
        Object[] col_questions_list = {"Id", "Soru", "Doğru Cevap", "Yanlış Cevap", "Yanlış Cevap", "Yanlış Cevap", "İçerik Adı"};
        mdl_questions_list.setColumnIdentifiers(col_questions_list);
        row_questions_list = new Object[col_questions_list.length];
        loadQuestionsModel();
        tbl_questions.setModel(mdl_questions_list);
        tbl_questions.getTableHeader().setReorderingAllowed(false);




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
                lbl_content_id.setText(null);
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
            int counter = 0;
            for (int j = 0; j < cmb_egitim_adi.getItemCount(); j++) {
                if (cmb_egitim_adi.getItemAt(j).equals(Helper.getPatikaName(obj.getPatika_id()))) {
                    counter++;
                }
            }
            if (counter == 0) {
                cmb_egitim_adi.addItem(Helper.getPatikaName(obj.getPatika_id()));
                cmb_egitim_adi_2.addItem(Helper.getPatikaName(obj.getPatika_id()));
            }
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

    private void loadQuestionsModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_questions.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Questions obj : Questions.getList()) {
            i = 0;
            row_questions_list[i++] = obj.getId();
            row_questions_list[i++] = obj.getQuestion();
            row_questions_list[i++] = obj.getTrue_answer();
            row_questions_list[i++] = obj.getWrong_answer_1();
            row_questions_list[i++] = obj.getWrong_answer_2();
            row_questions_list[i++] = obj.getWrong_answer_3();
            row_questions_list[i] = obj.getContents_title();
            mdl_questions_list.addRow(row_questions_list);
        }
    }
}
