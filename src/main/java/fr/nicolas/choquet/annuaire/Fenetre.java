package fr.nicolas.choquet.annuaire;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.nicolas.choquet.annuaire.entities.Person;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class Fenetre extends JFrame {

    private static String path = "src/main/resources/";

    public Fenetre() throws IOException, JSONException {
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

        FileWriter fw1 = new FileWriter(path + "fichier1.json");
        BufferedWriter bw1 = new BufferedWriter(fw1);
        bw1.write("[{\"nom\": \"Choquet\", \"prenom\": \"Nicolas\", \"telephone\": \"0763207630\"}]");
        bw1.close();

        File jsonFile = new File(path + "fichier1.json");
        int car;
        StringBuilder json = new StringBuilder("");
        FileInputStream ftemp = null;
        ftemp = new FileInputStream(jsonFile);
        while( (car = ftemp.read()) != -1)
            json.append((char)car);
        ftemp.close();
        String jsonText = json.toString();
        ArrayList<Person> persons = new ArrayList<Person>();

        JSONArray jsonArray = new JSONArray(jsonText);
        for(int i=0, max=jsonArray.length(); i<max; i++) {
            JSONObject personObject = jsonArray.getJSONObject(i);
            persons.add(
                    new Person(
                            personObject.getString("nom"),
                            personObject.getString("prenom"),
                            personObject.getString("telephone")
                    )
            );
        }

        JPanel personsPanel = new JPanel();
        for(int i=0, max=persons.size(); i<max; i++) {
            Person person = persons.get(i);
            JTextField nom = new JTextField(person.getNom());
            JTextField prenom = new JTextField(person.getPrenom());
            JTextField telephone = new JTextField(person.getTelephone());

            personsPanel.add(nom);
            personsPanel.add(prenom);
            personsPanel.add(telephone);
        }

        container.add(comboPanel, BorderLayout.NORTH);
        container.add(checkboxPanel, BorderLayout.CENTER);
        container.add(radioPanel, BorderLayout.SOUTH);
        container.add(personsPanel, BorderLayout.AFTER_LAST_LINE);

        this.setContentPane(container);

        this.setVisible(true);
    }
}