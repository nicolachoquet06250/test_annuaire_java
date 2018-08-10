package fr.nicolas.choquet.annuaire.components;

import fr.nicolas.choquet.annuaire.listeners.MenuItemListener;
import fr.nicolas.choquet.annuaire.listeners.TableColChangeListener;
import fr.nicolas.choquet.annuaire.utils.File;
import org.json.JSONException;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class Fenetre extends JFrame {
    private static String file = "fichier1.json";

    public Fenetre() throws IOException, JSONException {
        this.setTitle("Annuaire");
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        this.setSize(width-5, height-5);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFichier = new JMenu("Fichiers");

        JMenuItem item1 = new JMenuItem("Ouvrir");
        item1.addActionListener(new MenuItemListener());
        JMenuItem item2 = new JMenuItem("Fermer");
        item2.addActionListener(new MenuItemListener(this));
        JMenuItem item3 = new JMenuItem("Redémarrer");
        item3.addActionListener(new MenuItemListener(this));

        menuFichier.add(item1);
        menuFichier.add(item2);
        menuFichier.add(item3);

        menuBar.add(menuFichier);

        File file = new File(Fenetre.file);

        PersonArray personArray = PersonArray.initialize(file.read());

        JPanel personsPanel = new JPanel();

        JsonTable jtableau = new JsonTable(personArray);
        jtableau.getCellEditor(0,1).addCellEditorListener(new TableColChangeListener(this, jtableau, Fenetre.file));
        personsPanel.add(jtableau);

        container.add(menuBar, BorderLayout.NORTH);
        container.add(personsPanel, BorderLayout.CENTER);

        this.setContentPane(container);
        this.setVisible(true);
    }
}