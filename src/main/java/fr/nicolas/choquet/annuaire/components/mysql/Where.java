package fr.nicolas.choquet.annuaire.components.mysql;

public class Where {
    private String key = null;
    private String comparaison = null;
    private String value = null;
    private String operator = null;

    public Where(String key, String comparaison, String value) {
        setKey(key);
        setComparaison(comparaison);
        setValue(value);
    }

    public Where(String key, String value) {
        setKey(key);
        setComparaison(Operator.EGUAL);
        setValue(value);
    }

    public Where(String operator) {
        setOperator(operator);
    }

    boolean isOperator() {
        return operator != null;
    }

    public String getKey() {
        return key;
    }
    private void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }
    private void setValue(String value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }
    private void setOperator(String operator) {
        this.operator = operator;
    }

    public String getComparaison() {
        return comparaison;
    }
    public void setComparaison(String comparaison) {
        this.comparaison = comparaison;
    }
}
