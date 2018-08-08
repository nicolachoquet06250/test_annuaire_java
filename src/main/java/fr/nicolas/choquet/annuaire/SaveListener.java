package fr.nicolas.choquet.annuaire;

import fr.nicolas.choquet.annuaire.entities.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SaveListener implements ActionListener {
    private JPanel personsPanel;
    private ArrayList<Person> persons;
    public SaveListener() {}
    public SaveListener(JPanel personsPanel, ArrayList<Person> persons) {
        this.personsPanel = personsPanel;
        this.persons = persons;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(personsPanel);
        System.out.println(persons);

        JPanel personPanel = personsPanel;
    }
}
