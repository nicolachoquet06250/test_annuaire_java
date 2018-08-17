package fr.nicolas.choquet.annuaire.listeners;

import fr.nicolas.choquet.annuaire.components.custom.swing.MyJTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonActionLitener implements ActionListener {
    private int id;
    private MyJTextField[] myJTextFields = null;
    public JButtonActionLitener(int id, MyJTextField[] myJTextFields) {
        setId(id);
        setMyJTextFields(myJTextFields);
    }

    public JButtonActionLitener(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }
    private void setId(int id) {
        this.id = id;
    }

    public MyJTextField[] getMyJTextFields() {
        return myJTextFields;
    }
    private void setMyJTextFields(MyJTextField[] myJTextFields) {
        this.myJTextFields = myJTextFields;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        System.out.println(getId());
        if(getMyJTextFields() != null) {
            System.out.println(getMyJTextFields());
        }
    }
}
