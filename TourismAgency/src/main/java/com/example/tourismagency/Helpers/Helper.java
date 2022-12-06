package com.example.tourismagency.Helpers;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static Map<String,Integer> getScreen(Dimension size){
        Map<String,Integer> screen = new HashMap<>();
        screen.put("x", (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2);
        screen.put("y", (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2);
        return screen;
    }

    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if(info.getName().equals("Nimbus")){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isFieldEmpty(JTextField jTextField){
        return jTextField.getText().trim().isEmpty();
    }

    public static boolean isAreaEmpty(JTextArea jTextArea){
        return jTextArea.getText().trim().isEmpty();
    }

    public static boolean isValidFullName(JTextField jTextField){
        return !(jTextField.getText().trim().isEmpty() || jTextField.getText().trim().split(" ").length < 2);
    }

    public static void showMessage(String str){
        optionPaneTr();
        String title = "Bilgilendirme";
        String message = str;

        switch (str){
            case "validate":
                title = "Form Hatası!";
                message = "Lütfen Tüm Alanları Doldurunuz!";
                break;
            case "success":
                title = "Başarılı";
                message = "İşlem Başarıyla Gerçekleştirildi";
                break;
            case "error":
                title = "Başarısız";
                message = "İşlem Gerçekleştirilemedi!";
                break;
            case "duplicate":
                title = "Duplicate";
                message = "Bu Kayıt Daha Önece Kaydedilmiştir!";
                break;
            default:
                break;
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void optionPaneTr(){
        UIManager.put("OptionPane.okButtonText", "Tamam");
    }

    public static boolean confirm(String str){
        String message = str;
        switch (str){
            case "sure":
                message = "Bu işlemi gerçekleştirme istediğinize emin misiniz?";
                break;
            default:
                break;
        }

        return JOptionPane.showConfirmDialog(null, message, "Emin misiniz?", JOptionPane.YES_NO_OPTION) == 0;
    }
}