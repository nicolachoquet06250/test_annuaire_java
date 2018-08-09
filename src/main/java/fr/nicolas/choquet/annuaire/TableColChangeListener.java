package fr.nicolas.choquet.annuaire;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

public class TableColChangeListener implements CellEditorListener {

    public void editingStopped(ChangeEvent e) {
        CellEditor editor = (CellEditor) e.getSource();
        System.out.println(editor.getCellEditorValue());
    }

    public void editingCanceled(ChangeEvent e) {}
}
