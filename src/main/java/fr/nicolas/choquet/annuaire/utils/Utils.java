package fr.nicolas.choquet.annuaire.utils;

public class Utils {
    private Utils(){}
    public static String ucfist(String text) {
        return text.substring(0, 1).toUpperCase()+ text.substring(1).toLowerCase();
    }
}
