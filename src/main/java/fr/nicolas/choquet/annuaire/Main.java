package fr.nicolas.choquet.annuaire;

import fr.nicolas.choquet.annuaire.components.Fenetre;
import org.json.JSONException;

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

    public static void main() {
        try {
            new Fenetre();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
