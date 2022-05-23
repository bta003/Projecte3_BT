package Projecte3;

public class Cursos {
    private static int idCursos;
    private int ID;
    private String nom;
    private int preu_hora;
    
    public Cursos(){
        idCursos++;
}

    public Cursos(int ID, String nom, int preu_hora) {
        idCursos++;
        this.ID = ID;
        this.nom = nom;
        this.preu_hora = preu_hora;
    }

    public static int getIdCursos() {
        return idCursos;
    }

    public int getID() {
        return ID;
    }

    public String getNom() {
        return nom;
    }

  
    public int getPreu_hora() {
        return preu_hora;
    }
    
    

    
    
}
