//Kinan Saad
public class App{
    public static void main(String [] args){

        Graph G = new Graph();

        G.ajouterSommet('A');
        G.ajouterSommet('B');
        G.ajouterSommet('C');
        G.ajouterSommet('D');
        G.ajouterSommet('E');
        G.ajouterSommet('F');
        G.ajouterSommet('G');
        G.ajouterSommet('H');
        G.ajouterSommet('I');
        G.ajouterSommet('J');
        G.ajouterSommet('K');

        G.lier('A', 'B');
        G.lier('A', 'C');
        G.lier('A', 'D');
        G.lier('A', 'E');
        G.lier('B', 'C');
        G.lier('B', 'K');
        G.lier('C', 'D');
        G.lier('C', 'K');
        G.lier('D', 'E');
        G.lier('D', 'J');
        G.lier('E', 'F');
        G.lier('E', 'I');
        G.lier('F', 'I');
        G.lier('F', 'G');
        G.lier('I', 'G');
        G.lier('I', 'J');
        G.lier('G', 'J');
        G.lier('G', 'H');
        G.lier('J', 'K');
        G.lier('J', 'H');
        G.lier('K', 'H');

        G.WelshPowellResult();
    }
}