package fr.nicolas.choquet.annuaire;

import fr.nicolas.choquet.annuaire.entities.Person;
import fr.nicolas.choquet.annuaire.utils.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class Fenetre extends JFrame {

    public Fenetre() throws IOException, JSONException {
        this.setTitle("Annuaire");
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        this.setSize(width-5, height-5);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFichier = new JMenu("Fichiers");
        JMenu menuRun = new JMenu("Fonctionnement");

        JMenuItem item1 = new JMenuItem("Ouvrir");
        item1.addActionListener(new MenuItemListener());
        JMenuItem item2 = new JMenuItem("Fermer");
        item2.addActionListener(new MenuItemListener(this));
        JMenuItem item3 = new JMenuItem("Lancer");
        JMenuItem item4 = new JMenuItem("Arrêter");

        menuFichier.add(item1);
        menuFichier.add(item2);
        menuRun.add(item3);
        menuRun.add(item4);

        menuBar.add(menuFichier);
        menuBar.add(menuRun);

        File file = new File("fichier1.json");

        file.write("[{\"id\": \"0\", \"nom\": \"Choquet\", \"prenom\": \"Nicolas\", \"telephone\": \"0763207630\"}, {\"id\": \"1\", \"nom\": \"Choquet\", \"prenom\": \"Yann\", \"telephone\": \"0763207631\"}, {\"id\": \"2\", \"nom\": \"Loubet\", \"prenom\": \"Karine\", \"telephone\": \"0763207632\"}]");

        String jsonText = file.read();
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
        tableau.getCellEditor(0,1).addCellEditorListener(new TableColChangeListener(tableau, "fichier1.json"));
        personsPanel.add(tableau);

        container.add(menuBar, BorderLayout.NORTH);
        container.add(personsPanel, BorderLayout.CENTER);

        this.setContentPane(container);
        this.setVisible(true);
    }
}