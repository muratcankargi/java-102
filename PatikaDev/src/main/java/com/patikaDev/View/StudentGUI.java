package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.Contents;
import com.patikaDev.Model.Patika;

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
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private DefaultTableModel mdl_contents_list;
    private Object[] row_contents_list;
    private ArrayList<String> patikalarim = new ArrayList<>();

    public StudentGUI() {
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

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
            if(!Helper.isAdded(patikalarim,txt_patikalar.getText())){
                patikalarim.add(txt_patikalar.getText());
                txtarea_katildiginiz_patikalar.setText(txtarea_katildiginiz_patikalar.getText()+txt_patikalar.getText()+"\n");
                cmb_patikalarim.addItem(txt_patikalar.getText());
            }

        });
        btn_contents.addActionListener(e -> {
            mdl_contents_list = new DefaultTableModel();
            Object[] col_contents_list = {"ID", "Başlık","Açıklama","Link"};
            mdl_contents_list.setColumnIdentifiers(col_contents_list);
            row_contents_list = new Object[col_contents_list.length];
            loadContensModel();

            tbl_contents.setModel(mdl_contents_list);
            tbl_contents.getTableHeader().setReorderingAllowed(false); //başlıkları yerinden oynatmayı kaldırıyor
            tbl_contents.getColumnModel().getColumn(0).setMaxWidth(10);
        });


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
    private void loadContensModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_contents.getModel();
        clearModel.setRowCount(0);
        int i;

        for (Contents obj : Contents.getList()) {
            i = 0;
            if(obj.getPatika_id()==Helper.getPatikaId(cmb_patikalarim.getSelectedItem().toString())){
            row_contents_list[i++] = obj.getId();
            row_contents_list[i++] = obj.getTitle();
            row_contents_list[i++]=obj.getExplanation();
            row_contents_list[i]=obj.getLink();
            mdl_contents_list.addRow(row_contents_list);
        }}
    }
}