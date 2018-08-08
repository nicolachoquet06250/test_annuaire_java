package fr.nicolas.choquet.annuaire;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] argv) {
        try {
            new Fenetre();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
