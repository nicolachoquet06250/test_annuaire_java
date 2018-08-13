package fr.nicolas.choquet.annuaire.components.mysql;

public class Field {
    private String alias = null;
    private String name = null;

    public Field() {}
    public Field(String name) {
        this.name = name;
    }
    public Field(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean hasAlias() {
        return alias != null;
    }
}
