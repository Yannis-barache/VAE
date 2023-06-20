public class Valide {

    private Valide(){

    }

    public static boolean memeMDP(String mdp1,String mdp2){

        return mdp1.equals(mdp2);

    }
    

    public static boolean emailValide(String email) {
        int indexSymbole = email.indexOf('@');
        int indexPoint = email.indexOf('.', indexSymbole);

        if (indexSymbole < 0 || indexPoint < 0) {
            return false; // L'adresse e-mail doit contenir à la fois le symbole @ et le symbole .
        }

        // Vérifier qu'il n'y a pas de symboles supplémentaires avant ou après le @
        if (indexSymbole != email.lastIndexOf('@') || indexSymbole == 0 || indexSymbole == email.length() - 1) {
            return false;
        }

        // Vérifier qu'il n'y a pas de symboles supplémentaires avant ou après le .
        if (indexPoint != email.lastIndexOf('.') || indexPoint == indexSymbole + 1 || indexPoint == email.length() - 1) {
            return false;
        }

        return true;
    }


}