package fr.nicolas.choquet.annuaire.components;

import org.json.JSONArray;
import org.json.JSONException;

import javax.swing.*;
import java.io.UnsupportedEncodingException;

public class JsonTable extends JTable {
    private JSONArray jsonArray;
    private PersonArray personArray;
    private Object[] header;
    public JsonTable(PersonArray personArray) throws JSONException, UnsupportedEncodingException {
        super(personArray.toObjectArray(), personArray.get(0).getHeader());

        setJsonArray(personArray.toJsonArray());
        setHeader(personArray.get(0).getHeader());
        setPersonArray(personArray);
        initialize();
    }

    public void initialize() {

    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public void setPersonArray(PersonArray personArray) {
        this.personArray = personArray;
    }

    public void setHeader(Object[] header) {
        this.header = header;
    }
}
