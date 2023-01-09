package com.patikaDev.Helper;

import com.patikaDev.Model.Patika;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Helper {
    public static int screenCenterPoint(String position, Dimension size) {
        int point = switch (position) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
        return point;
    }

    public static void setLayout() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static void showMessages(String str){
        optionPageTR();
        String msg;
        String title;
        switch (str){
            case "fill":
                msg="Tüm alanları doldurunuz!";
                title="Hata";
                break;
            case "done":
                msg="İşlem başarılı!";
                title="İşlem";
                break;
            case "error":
                msg="Bir hata oluştu!";
                title="Hata";
            default:
                msg=str;
                title="Mesaj";
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean confirm(String str){
        optionPageTR();
        String msg;
        if ("sure".equals(str)) {
            msg = "Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
        } else {
            msg = str;
        }
         return  JOptionPane.showConfirmDialog(null,msg,"Karar",JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPageTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");
    }

    public static boolean isAdded(ArrayList<String> patikalarim,String str) {
        for (String ptk : patikalarim) {
            if (str.equals(ptk)) {
                return true;
            }
        }
        return false;
    }

    public static Integer getPatikaId(String patikaName){
        for(Patika p:Patika.getList())
            if(p.getName().equals(patikaName)){
                return p.getId();
        }
        return -1;
    }
    public static String getPatikaName(Integer patikaId){
        for(Patika p:Patika.getList())
            if(p.getId()==patikaId){
                return p.getName();
            }
        return "";
    }
    public static String searchQuery(String name) {
        String query = "SELECT * FROM course WHERE name ILIKE '%{{name}}%'";
        query = query.replace("{{name}}", name);
        return query;
    }
}
