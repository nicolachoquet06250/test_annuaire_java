package fr.nicolas.choquet.annuaire.components;


import java.awt.Rectangle;

import javax.swing.JTextField;

/**
 * @author Jonathan Guéhenneux
 */
public class MyJTextField extends JTextField {

    static final int HEIGHT = 20;

    MyJTextField(String text) {
        super(text);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, HEIGHT);
    }

    @Override
    public void setBounds(Rectangle bounds) {
        bounds.height = HEIGHT;
        super.setBounds(bounds);
    }
}