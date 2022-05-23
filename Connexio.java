package Projecte3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Connexio {

    // in real life, use a connection pool....
    private Connection connection;

    //String driverClassName = "com.mysql.jdbc.Driver";
    //String dbURL = "jdbc:mysql://localhost:3306/esqui";
    //String user = "root";
    //String password = "Fat/3232";
    public Connexio(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<Client> getPersonList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from Usuaris");) {
            List<Client> personList = new ArrayList<>();
            while (rs.next()) {
                String dni = rs.getString("DNI");
                String nom = rs.getString("Nom");
                String cognoms = rs.getString("Cognoms");
                Client person = new Client(dni, nom, cognoms);
                personList.add(person);
            }
            return personList;
        }
    }

    public List<CursColectiu> getCursColList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from Cursos C, Colectius CC WHERE C.ID_Curs=CC.ID_Curs");) {
            List<CursColectiu> curscolList = new ArrayList<>();
            while (rs.next()) {
                int idcurs = rs.getInt("ID_curs");
                String nom = rs.getString("Nom");
                int preuhora = rs.getInt("Preu_hora");
                //String preuhora = Integer.toString(preuhora1);
                CursColectiu curscol = new CursColectiu(idcurs, nom, preuhora);
                curscolList.add(curscol);
            }
            return curscolList;
        }
    }

    public List<CursCompeticio> getCursComList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from Cursos C, Competicio CCo WHERE C.ID_Curs=CCo.ID_Curs");) {
            List<CursCompeticio> curscomList = new ArrayList<>();
            while (rs.next()) {
                int idcurs = rs.getInt("ID_curs");
                String nom = rs.getString("Nom");
                int preuhora = rs.getInt("Preu_hora");
                String nivellcurs = rs.getString("nivell");
                CursCompeticio curscom = new CursCompeticio(idcurs, nom, preuhora, nivellcurs);
                curscomList.add(curscom);
            }
            return curscomList;
        }
    }

    public List<CursIndividual> getCursIndList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from Cursos C, Individuals CI WHERE C.ID_Curs=CI.ID_Curs");) {
            List<CursIndividual> curscomList = new ArrayList<>();
            while (rs.next()) {
                int idcurs = rs.getInt("ID_curs");
                String nom = rs.getString("Nom");
                int preuhora = rs.getInt("Preu_hora");
                //String preuhora = Integer.toString(preuhora1);
                CursIndividual cursind = new CursIndividual(idcurs, nom, preuhora);
                curscomList.add(cursind);
            }
            return curscomList;
        }
    }

    public static void insertLlogar(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            Connexio con = new Connexio("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/esqui", "root", "Fat/3232");
            //Create Statement
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
        }
    }

}

    
