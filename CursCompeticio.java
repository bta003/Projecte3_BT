package Projecte3;

public class CursCompeticio extends Cursos{
    private String nivell;

    public CursCompeticio() {
    }

    public CursCompeticio(int ID, String nom, int preu_hora, String nivell) {
        super(ID, nom, preu_hora);
        this.nivell = nivell;
    }

    public String getNivell() {
        return nivell;
    }
    
    
    
}
