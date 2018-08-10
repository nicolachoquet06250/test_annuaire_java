package fr.nicolas.choquet.annuaire;

import fr.nicolas.choquet.annuaire.entities.Person;
import fr.nicolas.choquet.annuaire.utils.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableColChangeListener implements CellEditorListener {

    private JTable table;
    private String file;
    private Fenetre fenetre;

    TableColChangeListener(Fenetre fenetre, JTable table, String file) {
        setTable(table);
        setFile(file);
        setFenetre(fenetre);
    }

    private void setFile(String file) {
        this.file = file;
    }
    private void setTable(JTable table) {
        this.table = table;
    }
    private void setFenetre(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    public void editingStopped(ChangeEvent e) {
        int x = table.getSelectedColumn();
        int y = table.getSelectedRow();
        String value = table.getValueAt(y, x).toString();
        File fileUtils = new File(file);

        String fileContent = fileUtils.read();
        ArrayList<Person> persons = new ArrayList<Person>();
        try {
            JSONArray jsonArray = new JSONArray(fileContent);
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

            switch (x) {
                case 0:
                    persons.get(y).setId(Integer.parseInt(value));
                    break;
                case 1:
                    persons.get(y).setNom(value);
                    break;
                case 2:
                    persons.get(y).setPrenom(value);
                    break;
                case 3:
                    persons.get(y).setTelephone(value);
                    break;
                default:
                    break;
            }

            StringBuilder jsonContent = new StringBuilder("[");
            for (Person person : persons) {
                jsonContent.append(person.toJson()).append(", ");
            }
            jsonContent.append("]");
            String jsonContentString = jsonContent.toString().replace("}, ]", "}]");

            fileUtils.write(jsonContentString);

        } catch (JSONException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (IndexOutOfBoundsException e1) {
            try {
                int id = Integer.parseInt(table.getValueAt(y, 0).toString());
                String nom = table.getValueAt(y, 1).toString();
                String prenom = table.getValueAt(y, 2).toString();
                String telephone = table.getValueAt(y, 3).toString();

                saveNewLineInTable(id, nom, prenom, telephone, persons, fileUtils);

            } catch (NullPointerException e2) {

            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void saveNewLineInTable(int id, String nom, String prenom, String telephone, ArrayList<Person> persons, File fileUtils) throws IOException {
        if(id != 0 && !nom.equals("") && !prenom.equals("") && !telephone.equals("")) {
            persons.add(Person.create(id, nom, prenom, telephone));
        }

        StringBuilder jsonContent = new StringBuilder("[");
        for (Person person : persons) {
            jsonContent.append(person.toJson()).append(", ");
        }
        jsonContent.append("]");
        String jsonContentString = jsonContent.toString().replace("}, ]", "}]");

        fileUtils.write(jsonContentString);

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
        tableau.getCellEditor(0,1).addCellEditorListener(new TableColChangeListener(fenetre, tableau, file));
        personsPanel.add(tableau);

        fenetre.getContentPane().add(personsPanel, BorderLayout.CENTER);
        fenetre.setVisible(true);
    }

    public void editingCanceled(ChangeEvent e) {}
}
