package fr.nicolas.choquet.annuaire;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.nicolas.choquet.annuaire.entities.Person;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.CellEditorListener;

public class Fenetre extends JFrame {

    public Fenetre() throws IOException, JSONException {
        this.setTitle("Animation");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFichier = new JMenu("Fichiers");
        JMenu menuRun = new JMenu("Fonctionnement");

        JMenuItem item1 = new JMenuItem("Ouvrir");
        JMenuItem item2 = new JMenuItem("Fermer");
        JMenuItem item3 = new JMenuItem("Lancer");
        JMenuItem item4 = new JMenuItem("Arrêter");

        menuFichier.add(item1);
        menuFichier.add(item2);
        menuRun.add(item3);
        menuRun.add(item4);

        menuBar.add(menuFichier);
        menuBar.add(menuRun);
//
//        String[] options_tab = {"Option 1", "Option 2", "Option 3", "Option 4"};
//        JComboBox combo = new JComboBox(options_tab);
//        combo.addActionListener(new ComboListener(combo));
//        combo.setPreferredSize(new Dimension(100, 20));
//
//        JPanel comboPanel = new JPanel();
//        JLabel label = new JLabel("Une ComboBox");
//        comboPanel.add(label);
//        comboPanel.add(combo);
//
//        JPanel checkboxPanel = new JPanel();
//        JCheckBox checkbox1 = new JCheckBox("CheckBox 1");
//        JCheckBox checkbox2 = new JCheckBox("CheckBox 2");
//
//        checkbox1.addActionListener(new CheckBoxListener());
//        checkbox2.addActionListener(new CheckBoxListener());
//
//        checkboxPanel.add(checkbox1);
//        checkboxPanel.add(checkbox2);
//
//        JPanel radioPanel = new JPanel();
//        ButtonGroup radioButtonGroup = new ButtonGroup();
//        JRadioButton radio1 = new JRadioButton("Radio 1");
//        JRadioButton radio2 = new JRadioButton("Radio 2");
//
//        radio1.addActionListener(new RadioButtonListener());
//        radio2.addActionListener(new RadioButtonListener());
//
//        radioButtonGroup.add(radio1);
//        radioButtonGroup.add(radio2);
//
//        radioPanel.add(radio1);
//        radioPanel.add(radio2);
//
        String path = "src/main/resources/";
        FileWriter fw1 = new FileWriter(path + "fichier1.json");
        BufferedWriter bw1 = new BufferedWriter(fw1);
        bw1.write("[{\"id\": \"0\", \"nom\": \"Choquet\", \"prenom\": \"Nicolas\", \"telephone\": \"0763207630\"}, {\"id\": \"1\", \"nom\": \"Choquet\", \"prenom\": \"Yann\", \"telephone\": \"0763207631\"}, {\"id\": \"2\", \"nom\": \"Loubet\", \"prenom\": \"Karine\", \"telephone\": \"0763207632\"}]");
        bw1.close();

        File jsonFile = new File(path + "fichier1.json");
        int car;
        StringBuilder json = new StringBuilder();
        FileInputStream ftemp = new FileInputStream(jsonFile);
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
                            personObject.getInt("id"),
                            personObject.getString("nom"),
                            personObject.getString("prenom"),
                            personObject.getString("telephone")
                    )
            );
        }

        JPanel personsPanel = new JPanel();

        String[] entetes = {"Id", "Nom", "Prenom", "Téléphone"};
        Object[][] tmp_persons = new Object[persons.size()+1][4];
        int i = 0;
        for(Person person : persons) {
            Object[] tmp_person = {person.getId(), person.getNom(), person.getPrenom(), person.getTelephone()};
            tmp_persons[i] = tmp_person;
            i++;
        }

        JTable tableau = new JTable(tmp_persons, entetes);
        tableau.getCellEditor(0,1).addCellEditorListener(new TableColChangeListener());
        personsPanel.add(tableau);

//        container.add(comboPanel, BorderLayout.NORTH);
//        container.add(checkboxPanel, BorderLayout.CENTER);
//        container.add(radioPanel, BorderLayout.SOUTH);

        container.add(menuBar, BorderLayout.NORTH);
        container.add(personsPanel, BorderLayout.CENTER);

        this.setContentPane(container);
        this.setVisible(true);
    }
}