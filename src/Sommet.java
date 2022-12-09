
import java.util.ArrayList;
import java.util.List;

public class Sommet {
    private char Name; 
    private Couleur C;
    private List <Sommet> Liaisons;

    public Sommet(char l){
        Name = l;
        C = Couleur.Transparent;
        Liaisons = new ArrayList<Sommet>();
    }

    public void setCouleur(Couleur x){C = x; }
    public char getNom(){return Name;}
    public Couleur getCouleur(){return C;}
    public List <Sommet> getLiaisons(){return Liaisons;}
    
    public boolean liaisonExists(char n){
        try{
           Sommet A = (Sommet)Liaisons.stream().filter(s -> s.getNom() == n).toArray()[0];
        }
        catch(Exception e){
            return false;
        }
        return true; 
    }

    @Override
    public String toString(){ return getNom() + " "; }

    public void lier(Sommet S){Liaisons.add(S);}

    public int getDeg(){
        return Liaisons.size();
    }
}
