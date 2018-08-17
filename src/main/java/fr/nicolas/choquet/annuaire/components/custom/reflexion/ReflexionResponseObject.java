package fr.nicolas.choquet.annuaire.components.custom.reflexion;

import fr.nicolas.choquet.annuaire.utils.Utils;

import java.lang.reflect.Type;

public class ReflexionResponseObject {
    private Object valeur;
    private Class objectClass;
    private String field;
    public ReflexionResponseObject(Class objectClass, String field, Object valeur) {
        setObjectClass(objectClass);
        setField(field);
        setValeur(valeur);
    }

    private Object getValeur() {
        return valeur;
    }
    private void setValeur(Object valeur) {
        this.valeur = valeur;
    }

    private Class getObjectClass() {
        return objectClass;
    }
    private void setObjectClass(Class objectClass) {
        this.objectClass = objectClass;
    }

    private String getField() {
        return field;
    }
    private void setField(String field) {
        this.field = field;
    }

    public Type getType() {
        return isInt() ? int.class : String.class;
    }

    public int toInt() {
        return Integer.valueOf(getValeur().toString());
    }

    @Override
    public String toString() {
        return getValeur().toString();
    }

    public boolean isInt() {
        try {
            getObjectClass().getMethod("set" + Utils.ucfist(getField()), int.class);
            return true;
        }
        catch (NoSuchMethodException e) {
            return false;
        }
    }

    public boolean isString() {
        try {
            getObjectClass().getMethod("set" + Utils.ucfist(getField()), String.class);
            return true;
        }
        catch (NoSuchMethodException e) {
            return false;
        }
    }
}
