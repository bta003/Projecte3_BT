package Projecte3;

import java.sql.ResultSet;

public class Client {
    private static int idClient;
    private String dni;
    private String nom;
    private String cognoms;

    
    public Client(){
        idClient++;
    }
    
    public Client (String dni, String nom, String cognoms){
        idClient++;
        
        this.idClient = idClient;
        this.dni = dni;
        this.nom = nom;
        this.cognoms = cognoms;

    }

    public static int getIdClient() {
        return idClient;
    }

    public String getDni() {
        return dni;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }
    
    
}
