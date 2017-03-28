
public class DifferentOrderTrees extends Exception {

    public DifferentOrderTrees() {
        super("Erreur : on ne peut pas fusionner deux arbres d'ordres différents");
    }

    public DifferentOrderTrees(String s) {
        super(s);
    }
}
