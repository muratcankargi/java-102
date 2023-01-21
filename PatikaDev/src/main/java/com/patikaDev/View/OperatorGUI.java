package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Helper.Item;
import com.patikaDev.Model.Course;
import com.patikaDev.Model.Operator;
import com.patikaDev.Model.Patika;
import com.patikaDev.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField txt_user_name;
    private JTextField txt_user_username;
    private JTextField txt_user_password;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField txt_user_id;
    private JButton btn_user_delete;
    private JTextField txt_search_user_name;
    private JTextField txt_seach_user_username;
    private JComboBox cmb_seach_user_type;
    private JButton btn_user_search;
    private JPanel pnl_patika_list;
    private JScrollPane scrl_patika_list;
    private JTable tbl_patika_list;
    private JTextField txt_patika_name;
    private JButton btn_patika_add;
    private JPanel pnl_couse_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_right;
    private JTextField txt_course_name;
    private JTextField txt_course_lang;
    private JComboBox cmd_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_course_add;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private JPopupMenu patikaMenu;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private final Operator operator;


    public OperatorGUI(Operator operator) {
        this.operator = operator;

        add(wrapper);

        setSize(1000, 500);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setTitle(Config.PROJECT_TITLE);

        setVisible(true);

        lbl_welcome.setText("Hoşgeldin, " + operator.getName());

        //ModelUserList
        mdl_user_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_user_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];

        loadUserModel();

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            String selected_user_id = tbl_user_list.getSelectedRow() != -1 ? tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString() : null;
            txt_user_id.setText(selected_user_id);
           /* hocanın yolu
           try {
                String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                txt_user_id.setText(select_user_id);
            } catch (Exception ex) {

            }*/
        });
        tbl_user_list.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString());
                String user_name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 1).toString();
                String user_username = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 2).toString();
                String user_password = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 3).toString();
                String user_type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 4).toString();

                if (User.update(user_id, user_name, user_username, user_password, user_type)) {
                    Helper.showMessages("done");
                }
                loadUserModel();
                loadEducatorCombo();
                loadCourseModel();
            }
        });


        //PatikaList
        patikaMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Güncelle");
        JMenuItem deleteMenu = new JMenuItem("Sil");
        patikaMenu.add(updateMenu);
        patikaMenu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
            UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patika.getFetch(select_id));
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                }
            });
        });

        deleteMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
                if (Patika.delete(select_id)) {
                    Helper.showMessages("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                } else {
                    Helper.showMessages("error");
                }
            }
        });

        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"ID", "Patika Adı"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();
        loadPatikaCombo();

        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.setComponentPopupMenu(patikaMenu);
        tbl_patika_list.getTableHeader().setReorderingAllowed(false); //başlıkları yerinden oynatmayı kaldırıyor
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(100); //başlığın boyutunu ayarlıyor

        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int select_row = tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(select_row, select_row);
            }
        });


        //CourseList
        mdl_course_list = new DefaultTableModel();
        Object[] col_courseList = {"ID", "Ders Adı", "Programlama Dili", "Patika", "Eğitmen"};
        mdl_course_list.setColumnIdentifiers(col_courseList);
        row_course_list = new Object[col_courseList.length];
        loadCourseModel();
        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        loadPatikaCombo();
        loadEducatorCombo();



        btn_user_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_user_name) || Helper.isFieldEmpty(txt_user_username) || Helper.isFieldEmpty(txt_user_password)) {
                Helper.showMessages("fill");
            } else {
                String name = txt_user_name.getText();
                String username = txt_user_username.getText();
                String password = txt_user_password.getText();
                String type = cmb_user_type.getSelectedItem().toString();
                if (User.add(name, username, password, type)) {
                    Helper.showMessages("done");
                    loadUserModel();
                    loadEducatorCombo();
                    txt_user_name.setText(null);
                    txt_user_username.setText(null);
                    txt_user_password.setText(null);
                }
            }
        });
        btn_user_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_user_id)) {
                Helper.showMessages("fill");
            } else {
                if (Helper.confirm("sure")) {
                    int user_id = Integer.parseInt(txt_user_id.getText());
                    if (User.delete(user_id)) {
                        Helper.showMessages("done");
                        loadUserModel();
                        loadEducatorCombo();
                        loadCourseModel();
                        txt_user_id.setText(null);
                    } else {
                        Helper.showMessages("error");
                    }
                }
            }
        });
        btn_user_search.addActionListener(e -> {
            String name = txt_search_user_name.getText();
            String username = txt_seach_user_username.getText();
            String type = cmb_seach_user_type.getSelectedItem().toString();

            String query = User.searchQuery(name, username, type);
            loadUserModel(User.searchUserList(query));

        });
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI lgGUI=new LoginGUI();
        });
        btn_patika_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(txt_patika_name)) {
                Helper.showMessages("fill");
            } else {
                if (Patika.add(txt_patika_name.getText())) {
                    Helper.showMessages("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    txt_patika_name.setText(null);
                } else {
                    Helper.showMessages("error");
                }
            }
        });
        btn_course_add.addActionListener(e -> {
            Item patikaItem = (Item) cmd_course_patika.getSelectedItem();
            Item userItem = (Item) cmb_course_user.getSelectedItem();
            if (Helper.isFieldEmpty(txt_course_name) || Helper.isFieldEmpty(txt_course_lang)) {
                Helper.showMessages("fill");
            } else {
                if (Course.add(userItem.getKey(), patikaItem.getKey(), txt_course_name.getText(), txt_course_lang.getText())) {
                    Helper.showMessages("done");
                    loadCourseModel();
                    txt_course_lang.setText(null);
                    txt_course_name.setText(null);
                } else {
                    Helper.showMessages("error");
                }
            }
        });
    }

    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;

        for (Course obj : Course.getList()) {
            i=0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            row_course_list[i++] = obj.getLanguage();
            row_course_list[i++] = obj.getPatika().getName();
            row_course_list[i++] = obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list);

        }
    }

    private void loadPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Patika obj : Patika.getList()) {
            i = 0;
            row_patika_list[i++] = obj.getId();
            row_patika_list[i++] = obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    public void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (User user : User.getList()) {
            i = 0;
            row_user_list[i++] = user.getId();
            row_user_list[i++] = user.getName();
            row_user_list[i++] = user.getUsername();
            row_user_list[i++] = user.getPassword();
            row_user_list[i++] = user.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadUserModel(ArrayList<User> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        for (User user : list) {
            int i = 0;
            row_user_list[i++] = user.getId();
            row_user_list[i++] = user.getName();
            row_user_list[i++] = user.getUsername();
            row_user_list[i++] = user.getPassword();
            row_user_list[i++] = user.getType();

            mdl_user_list.addRow(row_user_list);
        }
    }

    private void loadPatikaCombo() {
        cmd_course_patika.removeAllItems();
        for (Patika obj : Patika.getList()) {
            cmd_course_patika.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    private void loadEducatorCombo() {
        cmb_course_user.removeAllItems();
        for (User obj : User.getList()) {
            if (obj.getType().equals("educator")) {
                cmb_course_user.addItem(new Item(obj.getId(), obj.getName()));
            }
        }
    }


}
