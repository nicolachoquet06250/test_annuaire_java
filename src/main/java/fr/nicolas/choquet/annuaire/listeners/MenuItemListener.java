package fr.nicolas.choquet.annuaire.listeners;

import com.sun.istack.internal.Nullable;
import fr.nicolas.choquet.annuaire.components.Fenetre;
import fr.nicolas.choquet.annuaire.Main;
import org.json.JSONException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuItemListener implements ActionListener {
    private Fenetre fenetre = null;
    public MenuItemListener() {}
    public MenuItemListener(Fenetre fenetre) {
        setFenetre(fenetre);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Ouvrir")) {
            try {
                new Fenetre();
            } catch (IOException event) {
                event.printStackTrace();
            } catch (JSONException event) {
                event.printStackTrace();
            }
        }
        else if (e.getActionCommand().equals("Fermer")) {
            if(getFenetre() != null) {
                getFenetre().dispose();
            }
        }
        else if (e.getActionCommand().equals("Redémarrer")) {
            fenetre.dispose();
            Main.main();
        }
    }

    @Nullable
    public Fenetre getFenetre() {
        return fenetre;
    }

    public void setFenetre(Fenetre fenetre) {
        this.fenetre = fenetre;
    }
}
