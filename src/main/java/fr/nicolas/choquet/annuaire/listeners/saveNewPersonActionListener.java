package fr.nicolas.choquet.annuaire.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class saveNewPersonActionListener implements ActionListener {
    private JTextField[] jTextFields;
    private int nextId;
    public saveNewPersonActionListener(JTextField[] jTextFields, int nextId) {
        setjTextFields(jTextFields);
        setNextId(nextId);
    }

    public JTextField[] getjTextFields() {
        return jTextFields;
    }
    private void setjTextFields(JTextField[] jTextFields) {
        this.jTextFields = jTextFields;
    }

    public int getNextId() {
        return nextId;
    }
    private void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(getNextId());
        System.out.println(getjTextFields()[0].getText());
        System.out.println(getjTextFields()[1].getText());
        System.out.println(getjTextFields()[2].getText());
    }
}
