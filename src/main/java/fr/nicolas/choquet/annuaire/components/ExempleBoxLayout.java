package fr.nicolas.choquet.annuaire.components;

import fr.nicolas.choquet.annuaire.components.custom.swing.MyJTextField;
import fr.nicolas.choquet.annuaire.listeners.JTextFieldFocusListener;
import fr.nicolas.choquet.annuaire.entities.Person;
import fr.nicolas.choquet.annuaire.listeners.JButtonActionLitener;
import fr.nicolas.choquet.annuaire.listeners.saveNewPersonActionListener;
import fr.nicolas.choquet.annuaire.utils.File;
import org.json.JSONException;

import java.awt.*;
import java.io.UnsupportedEncodingException;

import javax.swing.*;

public class ExempleBoxLayout extends JFrame {

    private static String file = "fichier1.json";
    private JPanel panel = null;

    @Override
    protected void frameInit() {
        super.frameInit();
        setTitle("Annuaire");
        setName("Annuaire");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(550, 200));
        getContentPane()
                .setLayout(
                        new BoxLayout(
                                getContentPane(),
                                BoxLayout.PAGE_AXIS
                        )
                );

        setScrollPane();

        File file = new File(ExempleBoxLayout.file);

        try {
            PersonArray personArray = PersonArray.initialize(file.read());

            for (Person person : personArray.get()) {
                MyJTextField[] localMyJTextFields = new MyJTextField[] {
                        new MyJTextField(new String(person.getNom().getBytes("iso-8859-1"), "utf8")),
                        new MyJTextField(new String(person.getPrenom().getBytes("iso-8859-1"), "utf8")),
                        new MyJTextField(person.getTelephone())
                };

                JButton update = new JButton("Modifier");
                update.addActionListener(new JButtonActionLitener(person.getId(), localMyJTextFields));

                JButton delete = new JButton("Supprimer");
                delete.addActionListener(new JButtonActionLitener(person.getId()));

                JButton[] localJButtons = new JButton[] {update, delete};
                addRow(
                        person.getId(),
                        localMyJTextFields,
                        localJButtons
                );
            }

            int nextId = personArray.getLastId()+1;

            JTextField nom = new MyJTextField("Nom");
            nom.addFocusListener(new JTextFieldFocusListener("Nom", nom));

            JTextField prenom = new MyJTextField("Prénom");
            prenom.addFocusListener(new JTextFieldFocusListener("Prénom", prenom));

            JTextField telephone = new MyJTextField("Téléphone");
            telephone.addFocusListener(new JTextFieldFocusListener("Téléphone", telephone));

            JTextField[] jTextFields = new JTextField[] {
                    nom,
                    prenom,
                    telephone
            };


            JButton okButton = new JButton("Créer");
            okButton.addActionListener(new saveNewPersonActionListener(jTextFields, nextId));
            JButton[] jButtonFields = new JButton[] {
                    okButton
            };

            addRow( nextId, jTextFields, jButtonFields);

        } catch (JSONException e) {
            addErrorRow(e.getMessage());
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            addErrorRow(e.getMessage());
            e.printStackTrace();
        }
        pack();
        ImageIcon icon = new ImageIcon("src/main/resources/images/annuaire.png");
        setIconImage(icon.getImage());
        setResizable(true);
    }

    private void addRow(String titre, JComponent[] components, JButton[] buttons) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        MyJTextField titleTextField = new MyJTextField(titre);
        titleTextField.setEnabled(false);

//        JLabel label = new JLabel(titre);
//        label.setLabelFor(components[0]);
//        panel.add(label);
        panel.add(titleTextField);

        for (JComponent component : components) {
            panel.add(Box.createHorizontalStrut(10));
            panel.add(component);
        }
        for(JButton button : buttons) {
            panel.add(Box.createHorizontalStrut(10));
            panel.add(button);
        }
        if(hasScrollPane()) {
            this.panel.add(panel);
        }
        else {
            add(panel);
        }
    }
    private void addRow(int titre, JComponent[] components, JButton[] buttons) {
        addRow(String.valueOf(titre), components, buttons);
    }

    private void addErrorRow(String error) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        JLabel text = new JLabel(error);
        text.setBackground(Color.RED);

        if(hasScrollPane()) {
            this.panel.add(text);
        }
        else {
            add(text);
        }
        add(panel);
    }

    private void setScrollPane() {
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.panel = panel;

        this.panel.add(Box.createVerticalGlue());
        getContentPane().add(scrollPane);
    }

    private boolean hasScrollPane() {
        return panel != null;
    }

    public static void main(String[] args) {
        JFrame window = new ExempleBoxLayout();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main() {
        JFrame window = new ExempleBoxLayout();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
