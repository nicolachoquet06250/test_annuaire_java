package fr.nicolas.choquet.annuaire;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBoxListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("source : " + ((JCheckBox)e.getSource()).getText() + " - état : " + ((JCheckBox)e.getSource()).isSelected());
    }
}
