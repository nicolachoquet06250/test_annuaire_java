package fr.nicolas.choquet.annuaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class Fenetre extends JFrame implements ItemListener, ActionListener {

    private JComboBox combo;

    public Fenetre(){
        this.setTitle("Animation");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        String[] options_tab = {"Option 1", "Option 2", "Option 3", "Option 4"};
        JComboBox combo = new JComboBox(options_tab);
        this.combo = combo;
        combo.addItemListener(this);
        combo.addActionListener(this);
        combo.setPreferredSize(new Dimension(100, 20));

        JPanel top = new JPanel();
        JLabel label = new JLabel("Une ComboBox");
        top.add(label);
        top.add(combo);
        container.add(top, BorderLayout.NORTH);
        this.setContentPane(container);
        this.setVisible(true);
    }

    public void itemStateChanged(ItemEvent e) {
        System.out.println("Évenement déclenché sur " + e.getItem());
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Action déclenché sur " + this.combo.getSelectedItem());
    }
}