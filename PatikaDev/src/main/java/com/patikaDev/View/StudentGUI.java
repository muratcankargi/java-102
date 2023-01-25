package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.Contents;
import com.patikaDev.Model.Patika;
import com.patikaDev.Model.Questions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class StudentGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane pnl;
    private JTable tbl_patikalar;
    private JPanel pnl_patikalar;
    private JPanel pnl_icerikler;
    private JPanel pnl_testler;
    private JTextField txt_patikalar;
    private JLabel lbl_hosgeldin;
    private JButton btn_exit;
    private JButton btn_patika_katil;
    private JComboBox cmb_patikalarim;
    private JTable tbl_contents;
    private JButton btn_contents;
    private JTextArea txtarea_katildiginiz_patikalar;
    private JPanel pnl_giris;
    private JComboBox cmb_patika;
    private JComboBox cmb_contents;
    private JButton btn_start;
    private JRadioButton rbtn_answer_1;
    private JRadioButton rbtn_answer_2;
    private JRadioButton rbtn_answer_3;
    private JRadioButton rbtn_answer_4;
    private JRadioButton rbtn_answer_5;
    private JLabel lbl_question;
    private JLabel lbl_question_counter;
    private JPanel pnl_question;
    private JButton btn_next;
    private JButton bitirButton;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private DefaultTableModel mdl_contents_list;
    private Object[] row_contents_list;
    private ButtonGroup answers;
    private ArrayList<String> patikalarim = new ArrayList<>();

    public StudentGUI() {
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        answers = new ButtonGroup();
        answers.add(rbtn_answer_1);
        answers.add(rbtn_answer_2);
        answers.add(rbtn_answer_3);
        answers.add(rbtn_answer_4);
        answers.add(rbtn_answer_5);
        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"ID", "Patika Adı"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();

        tbl_patikalar.setModel(mdl_patika_list);
        tbl_patikalar.getTableHeader().setReorderingAllowed(false); //başlıkları yerinden oynatmayı kaldırıyor
        tbl_patikalar.getColumnModel().getColumn(0).setMaxWidth(100); //başlığın boyutunu ayarlıyor

        tbl_patikalar.getSelectionModel().addListSelectionListener(e -> {
            String selected_patika_name = tbl_patikalar.getSelectedRow() != -1 ? tbl_patikalar.getValueAt(tbl_patikalar.getSelectedRow(), 1).toString() : null;
            txt_patikalar.setText(selected_patika_name);
        });
        btn_exit.addActionListener(e -> {
            LoginGUI loginGUI = new LoginGUI();
            dispose();
        });
        btn_patika_katil.addActionListener(e -> {
            if (!Helper.isAdded(patikalarim, txt_patikalar.getText()) && !txt_patikalar.getText().equals("")) {
                patikalarim.add(txt_patikalar.getText());
                txtarea_katildiginiz_patikalar.setText(txtarea_katildiginiz_patikalar.getText() + txt_patikalar.getText() + "\n");
                cmb_patikalarim.addItem(txt_patikalar.getText());
                cmb_patika.addItem(txt_patikalar.getText());
            }

        });
        btn_contents.addActionListener(e -> {
            mdl_contents_list = new DefaultTableModel();
            Object[] col_contents_list = {"ID", "Başlık", "Açıklama", "Link"};
            mdl_contents_list.setColumnIdentifiers(col_contents_list);
            row_contents_list = new Object[col_contents_list.length];
            loadContensModel();

            tbl_contents.setModel(mdl_contents_list);
            tbl_contents.getTableHeader().setReorderingAllowed(false); //başlıkları yerinden oynatmayı kaldırıyor
            tbl_contents.getColumnModel().getColumn(0).setMaxWidth(50);
        });


        cmb_patika.addActionListener(e -> {
            cmb_contents.removeAllItems();
            cmb_contents.addItem("İçerik");
            if (!cmb_patika.getSelectedItem().equals("Patika")) for (Contents c : Contents.getList()) {
                if (c.getPatika_id() == Helper.getPatikaId(cmb_patika.getSelectedItem().toString())) {
                    cmb_contents.addItem(c.getTitle());
                }
            }

        });

        btn_start.addActionListener(e -> {
            String patika = cmb_patika.getSelectedItem().toString();
            String icerik = cmb_contents.getSelectedItem().toString();
            if (!(icerik.equals("İçerik") || patika.equals("Patika"))) {
                printQuestion(icerik);
                pnl_question.setVisible(true);
                pnl_giris.setVisible(false);
            } else {
                Helper.showMessages("Bir patika ve bir içerik seçiniz!");
            }

        });
        btn_next.addActionListener(e -> {
            counter_2++;
            answers.clearSelection();
            printQuestion(cmb_contents.getSelectedItem().toString());

        });
    }

    int counter_2 = 0;

    private void printQuestion(String contents_title) {
        int counter_1 = 0;
        int counter_3 = 0;
        for (Questions ques : Questions.getList()) {
            if (ques.getContents_title().equals(contents_title)) {
                counter_1++;
            }
        }


        for (Questions ques : Questions.getList()) {
            if (counter_1 == counter_2)
                break;
            if (ques.getContents_title().equals(contents_title)) {
                if (counter_2 == counter_3) {
                    lbl_question.setText(ques.getQuestion());
                    rbtn_answer_1.setText(ques.getTrue_answer());
                    rbtn_answer_2.setText(ques.getWrong_answer_1());
                    rbtn_answer_3.setText(ques.getWrong_answer_2());
                    rbtn_answer_4.setText(ques.getWrong_answer_3());
                    lbl_question_counter.setText((counter_2 + 1) + "/" + counter_1);
                    break;
                }
                counter_3++;
            }

        }
        rbtn_answer_5.setText("Boş bırak");
    }

    private void loadPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patikalar.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Patika obj : Patika.getList()) {
            i = 0;
            row_patika_list[i++] = obj.getId();
            row_patika_list[i++] = obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    private void loadContensModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_contents.getModel();
        clearModel.setRowCount(0);
        int i;

        for (Contents obj : Contents.getList()) {
            i = 0;
            if (obj.getPatika_id() == Helper.getPatikaId(cmb_patikalarim.getSelectedItem().toString())) {
                row_contents_list[i++] = obj.getId();
                row_contents_list[i++] = obj.getTitle();
                row_contents_list[i++] = obj.getExplanation();
                row_contents_list[i] = obj.getLink();
                mdl_contents_list.addRow(row_contents_list);
            }
        }
    }
}