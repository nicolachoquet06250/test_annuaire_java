package fr.nicolas.choquet.annuaire;

import fr.nicolas.choquet.annuaire.entities.Person;
import fr.nicolas.choquet.annuaire.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflexion {
    private String classStr;
    private Object object;
    private Class objectClass;
    private String classPackage;

    public TestReflexion(String classPackage, String classStr) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        setClassStr(classStr);
        setClassPackage(classPackage);
        setObjectClass(Class.forName(getClassPackage() + '.' + getClassStr()));
        setObject(getObjectClass().newInstance());
    }

    public TestReflexion(Class objectClass) throws IllegalAccessException, InstantiationException {
        setObjectClass(objectClass);
        setClassStr(getObjectClass().getName());
        setClassPackage(getObjectClass().getPackage().toString());
        setObject(getObjectClass().newInstance());
    }

    public String getString(String champ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if(isString(champ)) {
            Method set=getObjectClass().getMethod("get" + Utils.ucfist(champ));
            Object retour = set.invoke(getObject());
            return retour.toString();
        }
        return null;
    }
    public int getInt(String champ) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if(isInt(champ)) {
            Method set=getObjectClass().getMethod("get" + Utils.ucfist(champ));
            Object retour = set.invoke(getObject());
            return Integer.parseInt(retour.toString());
        }
        return 0;
    }

    public void set(String champ, String value) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if(isString(champ)) {
            Method set=getObjectClass().getMethod("set" + Utils.ucfist(champ), String.class);
            set.invoke(getObject(), value);
        }
    }
    public void set(String champ, int value) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if(isInt(champ)) {
            Method set=getObjectClass().getMethod("set" + Utils.ucfist(champ), int.class);
            set.invoke(getObject(), value);
        }
    }

    public boolean hasIntField(String field) {
        try {
            getObjectClass().getMethod("get" + Utils.ucfist(field));
            getObjectClass().getMethod("set" + Utils.ucfist(field), int.class);
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasStringField(String field) {
        try {
            getObjectClass().getMethod("get" + Utils.ucfist(field));
            getObjectClass().getMethod("set" + Utils.ucfist(field), String.class);
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isString(String champ) {
        return hasStringField(champ);
    }

    public boolean isInt(String champ) {
        return hasIntField(champ);
    }

    public boolean hasMethod(String name) {
        Method[] methods = getObjectClass().getMethods();

        for (Method method : methods) {
            if(method.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    private String getClassStr() {
        return classStr;
    }
    private void setClassStr(String classStr) {
        this.classStr = classStr;
    }

    private Object getObject() {
        return object;
    }
    private void setObject(Object object) {
        this.object = object;
    }

    private String getClassPackage() {
        return classPackage;
    }
    private void setClassPackage(String classPackage) {
        this.classPackage = classPackage;
    }

    private Class getObjectClass() {
        return objectClass;
    }
    private void setObjectClass(Class objectClass) {
        this.objectClass = objectClass;
    }

    public static void main(String[] argv) {
        try {
            TestReflexion person = new TestReflexion("fr.nicolas.choquet.annuaire.entities", "Person");
            person.set("id", 1);
            person.set("nom", "Choquet");
            person.set("prenom", "Nicolas");
            person.set("telephone", "0763207630");

            TestReflexion person2 = new TestReflexion(Person.class);
            person2.set("id", 2);
            person2.set("nom", "Choquet");
            person2.set("prenom", "Yann");
            person2.set("telephone", "0763207631");

            System.out.println(person.getInt("id"));
            System.out.println(person.getString("nom"));
            System.out.println(person.getString("prenom"));
            System.out.println(person.getString("telephone"));

            System.out.println("\n");

            System.out.println(person2.getInt("id"));
            System.out.println(person2.getString("nom"));
            System.out.println(person2.getString("prenom"));
            System.out.println(person2.getString("telephone"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
