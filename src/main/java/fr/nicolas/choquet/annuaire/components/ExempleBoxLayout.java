package fr.nicolas.choquet.annuaire.components;

import fr.nicolas.choquet.annuaire.entities.JTextFieldFocusListener;
import fr.nicolas.choquet.annuaire.entities.Person;
import fr.nicolas.choquet.annuaire.listeners.JTextFieldActionListener;
import fr.nicolas.choquet.annuaire.listeners.saveNewPersonActionListener;
import fr.nicolas.choquet.annuaire.utils.File;
import org.json.JSONException;

import java.awt.*;
import java.io.UnsupportedEncodingException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ExempleBoxLayout extends JFrame {

    private static String file = "fichier1.json";

    @Override
    protected void frameInit() {
        super.frameInit();
        setTitle("Annuaire");
        setName("Annuaire");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane()
                .setLayout(
                        new BoxLayout(
                                getContentPane(),
                                BoxLayout.PAGE_AXIS
                        )
                );

        File file = new File(ExempleBoxLayout.file);

        try {
            PersonArray personArray = PersonArray.initialize(file.read());

            for (Person person : personArray.get()) {
                addRow( person.getId(),
                        new JTextField[] {
                                new JTextField(new String(person.getNom().getBytes("iso-8859-1"), "utf8")),
                                new JTextField(new String(person.getPrenom().getBytes("iso-8859-1"), "utf8")),
                                new JTextField(person.getTelephone())
                        });
            }

            JTextField nom = new JTextField("Nom");
            nom.addFocusListener(new JTextFieldFocusListener("Nom", nom));
            nom.addActionListener(new JTextFieldActionListener());

            JTextField prenom = new JTextField("Prénom");
            prenom.addFocusListener(new JTextFieldFocusListener("Prénom", prenom));
            prenom.addActionListener(new JTextFieldActionListener());

            JTextField telephone = new JTextField("Téléphone");
            telephone.addFocusListener(new JTextFieldFocusListener("Téléphone", telephone));
            telephone.addActionListener(new JTextFieldActionListener());

            JTextField[] jTextFields = new JTextField[] {
                    nom,
                    prenom,
                    telephone
            };

            addRow( personArray.getLastId()+1, jTextFields);

            JButton okButton = new JButton("Ajouter");
            okButton.addActionListener(new saveNewPersonActionListener(jTextFields));

            addButtons(okButton, new JButton("Annuler"));

        } catch (JSONException e) {
            addErrorRow(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            addErrorRow(e.getMessage());
            e.printStackTrace();
        }
        pack();
        setResizable(true);
    }

    private void addRow(String titre, JComponent... components) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        JLabel label = new JLabel(titre);
        label.setLabelFor(components[0]);
        panel.add(label);

        for (JComponent component : components) {
            panel.add(Box.createHorizontalStrut(10));
            panel.add(component);
        }
        add(panel);
    }

    private void addRow(int titre, JComponent... components) {
        addRow(String.valueOf(titre), components);
    }

    private void addErrorRow(String error) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        JLabel text = new JLabel(error);
        text.setBackground(Color.RED);
        panel.add(text);
        add(panel);
    }

    private void addButtons(JButton...buttons) {
        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
        JPanel panel = new JPanel(flowLayout);
        for (JButton button : buttons) {
            panel.add(button);
        }
        add(panel);
    }

    public static void main(String[] args) {
        JFrame window = new ExempleBoxLayout();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
