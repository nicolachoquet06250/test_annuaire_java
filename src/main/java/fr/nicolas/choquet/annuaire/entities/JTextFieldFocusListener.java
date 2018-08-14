package fr.nicolas.choquet.annuaire.entities;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JTextFieldFocusListener implements FocusListener {
    private JTextField field;
    private String baseText;
    public JTextFieldFocusListener(String baseText, JTextField field) {
        setField(field);
        setBaseText(baseText);
    }

    private JTextField getField() {
        return field;
    }

    private void setField(JTextField field) {
        this.field = field;
    }

    private String getBaseText() {
        return baseText;
    }

    private void setBaseText(String baseText) {
        this.baseText = baseText;
    }

    public void focusGained(FocusEvent e) {
        if(field.getText().equals(getBaseText())) {
            field.setText("");
        }
        System.out.println(field.getText());
    }

    public void focusLost(FocusEvent e) {
        if(field.getText().equals("")) {
            field.setText(getBaseText());
        }
    }
}
