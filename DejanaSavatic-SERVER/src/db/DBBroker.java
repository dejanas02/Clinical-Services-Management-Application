/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domain.ApstraktniDomenskiObjekat;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author korisnk
 */
public class DBBroker {
    private static DBBroker instance;
    private Connection connection;

    private DBBroker() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public ArrayList<ApstraktniDomenskiObjekat> select(ApstraktniDomenskiObjekat ado) throws SQLException {
        String upit = "SELECT * FROM " + ado.tableName()+ " " + ado.alijas()
                + " " + ado.join() + " " + ado.requirementForSelect(ado);
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.getList(rs);
    }

    public PreparedStatement insert(ApstraktniDomenskiObjekat ado) throws SQLException {
        String naredba = "INSERT INTO " + ado.tableName()+ " "
                + ado.columnsForInsert()+ " VALUES(" + ado.valuesForInsert()+ ")";
        System.out.println(naredba);
        PreparedStatement ps = connection.prepareStatement(naredba, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    public void update(ApstraktniDomenskiObjekat ado) throws SQLException {
        String naredba = "UPDATE " + ado.tableName()+ " SET "
                + ado.valuesForUpdate()+ " WHERE " + ado.requirement();
        System.out.println(naredba);
        Statement s = connection.createStatement();
        s.executeUpdate(naredba);
    }

    public void delete(ApstraktniDomenskiObjekat ado) throws SQLException {
        String naredba = "DELETE FROM " + ado.tableName()+ " WHERE " + ado.requirement();
        System.out.println(naredba);
        Statement s = connection.createStatement();
        s.executeUpdate(naredba);
    }
    
}
