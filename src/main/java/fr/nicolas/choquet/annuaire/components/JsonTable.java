package fr.nicolas.choquet.annuaire.components;

import org.json.JSONArray;
import org.json.JSONException;

import javax.swing.*;
import java.io.UnsupportedEncodingException;

class JsonTable extends JTable {
    private JSONArray jsonArray;
    private PersonArray personArray;
    private Object[] header;

    JsonTable(PersonArray personArray) throws JSONException, UnsupportedEncodingException {
        super(personArray.toObjectArray(), personArray.get(0).getHeader());

        setJsonArray(personArray.toJsonArray());
        setHeader(personArray.get(0).getHeader());
        setPersonArray(personArray);
    }

    private void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    private void setPersonArray(PersonArray personArray) {
        this.personArray = personArray;
    }

    private void setHeader(Object[] header) {
        this.header = header;
    }

    JSONArray getJsonArray() {
        return jsonArray;
    }

    PersonArray getPersonArray() {
        return personArray;
    }

    Object[] getHeader() {
        return header;
    }
}
