package fr.nicolas.choquet.annuaire.components;

import fr.nicolas.choquet.annuaire.entities.Person;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonArray {
    private ArrayList<Person> persons;
    private JSONArray personJson;
    private String jsonInitText;
    private PersonArray(ArrayList<Person> persons) {
        setPersons(persons);
    }

    public static PersonArray initialize() {
        return new PersonArray(new ArrayList<Person>());
    }

    public static PersonArray initialize(String json) throws JSONException {
        PersonArray personArray = new PersonArray(new ArrayList<Person>());
        personArray.setJsonInitText(json);
        personArray.createFromJson();
        personArray.toJsonArray();
        return personArray;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public Person get(int i) {
        return persons.get(i);
    }

    public ArrayList<Person> get() {
        return persons;
    }

    public void add(Person person) {
        persons.add(person);
    }

    public PersonArray update(int i, String prop, String value) {
        Person person = get(i);
        if(prop.equals("nom")) {
            person.setNom(value);
        }
        else if(prop.equals("prenom")) {
            person.setPrenom(value);
        }
        else if(prop.equals("telephone")) {
            person.setTelephone(value);
        }
        return this;
    }

    public PersonArray update(int i, String prop, int value) {
        Person person = get(i);
        if(prop.equals("id")) {
            person.setId(value);
        }
        return this;
    }

    public PersonArray delete(int i) {
        persons.remove(i);
        return this;
    }

    public PersonArray delete(String champ, String value) {
        int i = 0;
        for (Person person: persons) {
            if((champ.equals("nom") && person.getNom().equals(value)) ||
                    (champ.equals("prenom") && person.getPrenom().equals(value)) ||
                    (champ.equals("telephone") && person.getTelephone().equals(value))) {
                persons.remove(i);
            }
            i++;
        }
        return this;
    }

    public PersonArray delete(String champ, int value) {
        int i = 0;
        for (Person person: persons) {
            if((champ.equals("id") && person.getId() == value)) {
                persons.remove(i);
            }
            i++;
        }
        return this;
    }

    public int size() {
        return persons.size();
    }

    public Object[][] toObjectArray() throws UnsupportedEncodingException {
        Object[][] tmp_persons = new Object[this.size()+1][4];
        int i = 0;
        for(Person person : this.get()) {
            Object[] tmp_person = {
                    person.getId(),
                    new String(person.getNom().getBytes("iso-8859-1"), "utf8"),
                    new String(person.getPrenom().getBytes("iso-8859-1"), "utf8"),
                    new String(person.getTelephone().getBytes("iso-8859-1"), "utf8")
            };
            tmp_persons[i] = tmp_person;
            i++;
        }
        return tmp_persons;
    }

    public String toJson() {
        StringBuilder json = new StringBuilder("[");
        for (Person person: persons) {
            json.append(person.toJson() + ", ");
        }
        json.append("]");

        String jsonText = json.toString().replace("}, ]", "}]");
        return jsonText;
    }

    public JSONArray createFromJson() throws JSONException {
        JSONArray jsonArray = new JSONArray(this.jsonInitText);
        for(int i=0, max=jsonArray.length(); i<max; i++) {
            JSONObject personObject = jsonArray.getJSONObject(i);
            this.add(
                    new Person(
                            personObject.getInt("id"),
                            personObject.getString("nom"),
                            personObject.getString("prenom"),
                            personObject.getString("telephone")
                    )
            );
        }
        personJson = jsonArray;
        return jsonArray;
    }

    public JSONArray toJsonArray() throws JSONException {
        personJson = new JSONArray(this.toJson());
        return personJson;
    }

    public void setJsonInitText(String jsonInitText) {
        this.jsonInitText = jsonInitText;
    }
}
