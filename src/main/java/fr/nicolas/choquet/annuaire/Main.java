package fr.nicolas.choquet.annuaire;

import fr.nicolas.choquet.annuaire.components.FenetreAnnuaire;

import javax.swing.*;

public class Main {
    public static void main(String[] argv) {
        main();
    }

    static FenetreAnnuaire getFenetreAnnuaire() {
        return new FenetreAnnuaire();
    }

    static void main() {
        JFrame window = getFenetreAnnuaire();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
