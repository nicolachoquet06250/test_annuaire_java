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

        JPanel comboPanel = new JPanel();
        JLabel label = new JLabel("Une ComboBox");
        comboPanel.add(label);
        comboPanel.add(combo);

        JPanel checkboxPanel = new JPanel();
        JCheckBox checkbox1 = new JCheckBox("CheckBox 1");
        JCheckBox checkbox2 = new JCheckBox("CheckBox 2");

        checkbox1.addActionListener(new CheckBoxListener());
        checkbox2.addActionListener(new CheckBoxListener());

        checkboxPanel.add(checkbox1);
        checkboxPanel.add(checkbox2);

        JPanel radioPanel = new JPanel();
        ButtonGroup radioButtonGroup = new ButtonGroup();
        JRadioButton radio1 = new JRadioButton("Radio 1");
        JRadioButton radio2 = new JRadioButton("Radio 2");

        radio1.addActionListener(new RadioButtonListener());
        radio2.addActionListener(new RadioButtonListener());

        radioButtonGroup.add(radio1);
        radioButtonGroup.add(radio2);

        radioPanel.add(radio1);
        radioPanel.add(radio2);

        container.add(comboPanel, BorderLayout.NORTH);
        container.add(checkboxPanel, BorderLayout.CENTER);
        container.add(radioPanel, BorderLayout.SOUTH);

        this.setContentPane(container);

        this.setVisible(true);
    }
}