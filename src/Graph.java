import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Graph {
    private List <Sommet> Sommets;
    private Couleur [] C = {Couleur.Rouge, Couleur.Vert, Couleur.Bleu, Couleur.Orange, Couleur.Violet, Couleur.Orange, Couleur.Jaune};
    private int NombreDeCouleur;
    public Graph(){
        Sommets = new ArrayList<Sommet>();
        NombreDeCouleur = 0;
    }
    public List <Sommet> getGraph(){ return Sommets; }

    public int getNombreDeCouleur(){ return NombreDeCouleur; }

    public void setNombreDeCouleur(int x){ NombreDeCouleur = x; }

    public Sommet getSommet(char name){
        Sommet S;
        try{
            S = (Sommet) Sommets.stream().filter(s-> s.getNom() == name).toArray()[0];
        }
        catch(Exception e){
            System.out.println("Aucun sommet avec le nom " + name + " erreur -> " + e);
            return null;
        }

        return S;
    }
    public int getGraphSize(){ return Sommets.size(); }

    public void ajouterSommet(char name){
        Sommet S = new Sommet(name);
        Sommets.add(S);
    }

    public boolean lier(char n1, char n2){
        if(getSommet(n1).liaisonExists(n2) || getSommet(n2).liaisonExists(n1))
            return false;
        getSommet(n1).lier(getSommet(n2));
        getSommet(n2).lier(getSommet(n1));
        return true;
    }

    public void printAllDeg(){
        Sommets.forEach(s -> System.out.println("deg(" + s.getNom() + ") = " + s.getDeg() + " " + s.getCouleur()));
    }

    public void WelshPowell(){

        //Trie en fonction des degrès des sommets.
        Collections.sort(Sommets, Comparator.comparing(Sommet::getDeg));
        Collections.reverse(Sommets);

        int nombreSommetColored = 0;
        int indiceCouleur = 0;

        for(Sommet s : Sommets){
            //Si tous les sommets sont colorés, on arrête l'algorithme.
            if(nombreSommetColored == this.getGraphSize()){
                this.setNombreDeCouleur(indiceCouleur - 1);
                return;
            }
            //Si le sommet principal n'a pas de couleur, on lui attribue une nouvelle couleur.
            if(s.getCouleur().equals(Couleur.Transparent)){
                s.setCouleur(C[indiceCouleur]);
                nombreSommetColored ++;
                //On vérifie le reste des sommets pour voir si on leur donne la même couleur ou non.
                for(Sommet x : Sommets){
                    //Si 's' ne s'agit pas de 'x'  et que 's' et 'x'ne sont pas adjacents, on colorie x, on continue.
                    if(x.getCouleur().equals(Couleur.Transparent) == true && x.getNom() != s.getNom() && x.liaisonExists(s.getNom()) == false){
                        final Couleur F = C[indiceCouleur];
                        List<Sommet> sommetsAvecCouleurActuelle = Sommets.stream().filter(d->d.getCouleur().equals(F)).collect(Collectors.toList());
                        //Si x n'est pas adjacent à tous les sommets ayant la couleur actuelle, on peut le colorer avec.
                        if(sommetsAvecCouleurActuelle.stream().noneMatch(d->d.liaisonExists(x.getNom()))){
                            x.setCouleur(C[indiceCouleur]);
                            nombreSommetColored ++; 
                        }
                    }
                }
                indiceCouleur ++;
            }
        }
    }

    public void WelshPowellResult(){

        this.WelshPowell();

        for(int i = 0; i <= this.getNombreDeCouleur(); i ++){
            final Couleur F = C[i];
            if(Sommets.stream().anyMatch(s->s.getCouleur().equals(F))){
                System.out.print(F + " (");
                Sommets
                .stream()
                .filter(s->s.getCouleur().equals(F))
                .map(s-> " " + s.getNom())
                .forEach(System.out::print);
                System.out.println(" )");
            }
        }
    }
}



