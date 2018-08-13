package fr.nicolas.choquet.annuaire;

import fr.nicolas.choquet.annuaire.components.Fenetre;
import fr.nicolas.choquet.annuaire.components.mysql.Field;
import fr.nicolas.choquet.annuaire.components.mysql.Mysql;
import fr.nicolas.choquet.annuaire.components.mysql.Operator;
import fr.nicolas.choquet.annuaire.components.mysql.Where;
import fr.nicolas.choquet.annuaire.entities.Person;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Main {
    public static void main(String[] argv) {
//        try {
//            new Fenetre();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        try {
            Mysql mysql = Mysql.get("mysql-nicolas-choquet.alwaysdata.net", "143175", "2669NICOLAS2107");
            mysql.setDatabase("nicolas-choquet_budgets");
            mysql.connect("nicolas-choquet_budgets");

            mysql.request("SELECT * FROM " + mysql.getDatabase() + ".account a WHERE a.id > 3").query(Person.class);

            mysql.select().from(mysql.getDatabase(".") + "account a")
                    .query(Person.class);
        } catch (SQLException e) {
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
