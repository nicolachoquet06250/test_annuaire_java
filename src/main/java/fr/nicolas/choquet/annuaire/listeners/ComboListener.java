package fr.nicolas.choquet.annuaire.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboListener implements ActionListener {
    private JComboBox combo;

    public ComboListener(JComboBox combo) {
        this.combo = combo;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Action déclenché sur " + this.combo.getSelectedItem());
    }
}
