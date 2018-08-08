package fr.nicolas.choquet.annuaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class Fenetre extends JFrame {

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
        combo.addActionListener(new ComboListener(combo));
        combo.setPreferredSize(new Dimension(100, 20));

        JPanel top = new JPanel();
        JLabel label = new JLabel("Une ComboBox");
        top.add(label);
        top.add(combo);

        JPanel middle = new JPanel();
        JCheckBox checkbox1 = new JCheckBox("CheckBox 1");
        JCheckBox checkbox2 = new JCheckBox("CheckBox 2");

        checkbox1.addActionListener(new CheckBoxListener());
        checkbox2.addActionListener(new CheckBoxListener());

        middle.add(checkbox1);
        middle.add(checkbox2);

        container.add(top, BorderLayout.NORTH);
        container.add(middle, BorderLayout.CENTER);

        this.setContentPane(container);

        this.setVisible(true);
    }
}