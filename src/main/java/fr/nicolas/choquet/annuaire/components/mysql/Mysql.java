package fr.nicolas.choquet.annuaire.components.mysql;

import java.sql.*;
import java.util.ArrayList;

public class Mysql {
    private String host;
    private String user;
    private String password;
    private int port;
    private String database = "";
    private String request;
    private Connection connector = null;
    private Mysql(String host, String user, String password, int port) throws SQLException {
        setHost(host);
        setUser(user);
        setPassword(password);
        setPort(port);
    }

    public Mysql connect(String database) throws SQLException {
        setDatabase(database);
        setConnector(DriverManager.getConnection("jdbc:mysql://" + getHost() + "/" + getDatabase("?") + "user=" + getUser() + "&password=" + getPassword()));
        return this;
    }

    public Mysql connect() throws SQLException {
        setConnector(DriverManager.getConnection("jdbc:mysql://" + getHost() + "/" + getDatabase("?") + "user=" + getUser() + "&password=" + getPassword()));
        return this;
    }

    public static Mysql get(String host, String user, String password) throws SQLException {
        return new Mysql(host, user, password, 3306);
    }

    public static Mysql get(String host, String user, String password, int port) throws SQLException {
        return new Mysql(host, user, password, port);
    }

    public String getHost() {
        return host;
    }
    private void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }
    private void setUser(String user) {
        this.user = user;
    }

    public int getPort() {
        return port;
    }
    private void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }
    private void setPassword(String password) {
        this.password = password;
    }

    public String getRequest() {
        return request;
    }
    private void setRequest(String request) {
        this.request = request;
    }

    public String getDatabase() {
        return database;
    }
    public String getDatabase(String separator) {
        return database + separator;
    }
    public void setDatabase(String database) {
        this.database = database;
    }

    public Connection getConnector() {
        return connector;
    }

    private void setConnector(Connection connector) {
        this.connector = connector;
    }

    public Mysql request(String request) {
        this.setRequest(request);
        return this;
    }

    public Mysql select() {
        request = "SELECT *";
        return this;
    }

    public Mysql select(ArrayList<Field> fields) {
        request = "SELECT ";
        int max = fields.size();
        int i = 0;
        for (Field field : fields) {
            if(field.hasAlias()) {
                request += field.getName() + " " + field.getAlias();
            }
            else {
                request += field.getName();
            }
            if(i<max-1) {
                request += ", ";
            }
            i++;
        }
        return this;
    }

    public Mysql insert() {

        return this;
    }

    public Mysql from(String table) {
        request += " FROM " + table;
        return this;
    }

    public Mysql where(ArrayList<Where> where) {
        request += " WHERE ";
        for (Where whereObject : where) {
            if(whereObject.isOperator()) {
                request += whereObject.getOperator() + " ";
            }
            else {
                request += whereObject.getKey() + whereObject.getComparaison() + "\"" + whereObject.getValue() + "\" ";
            }
        }
        return this;
    }

    public Mysql group() {
        request += "GROUP ";
        return this;
    }

    public Mysql order() {
        request += "ORDER ";
        return this;
    }

    public Mysql by(ArrayList<String> fieldsName) {
        request += "BY ";
        int max = fieldsName.size();
        int i = 0;
        for (String fieldName: fieldsName) {
            request += fieldName;
            if(i<max-1) {
                request += ", ";
            }
            i++;
        }
        return this;
    }

    public void query(Class classe) throws SQLException {
        Statement statement = getConnector().createStatement();
        ResultSet resultSet = statement.executeQuery(request);
        System.out.println(request);
    }

    public void show() {

    }

    public void tables() {

    }

    public void databases() {

    }
}
