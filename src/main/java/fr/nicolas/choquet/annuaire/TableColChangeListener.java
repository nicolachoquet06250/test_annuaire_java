package fr.nicolas.choquet.annuaire;

import fr.nicolas.choquet.annuaire.entities.Person;
import fr.nicolas.choquet.annuaire.utils.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import java.util.ArrayList;

public class TableColChangeListener implements CellEditorListener {

    private JTable table;
    private String file;

    public TableColChangeListener() {}
    public TableColChangeListener(JTable table, String file) {
        this.table = table;
        this.file = file;
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
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        System.out.println(value);
        System.out.println(x);
        System.out.println(y);
        System.out.println(fileContent);
    }

    public void editingCanceled(ChangeEvent e) {}
}
