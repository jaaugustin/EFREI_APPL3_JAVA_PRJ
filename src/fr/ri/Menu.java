/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ri;

import java.util.Scanner;

/**
 *
 * @author Jacques
 */
public class Menu {

    private ActionsBDDImpl dt = new ActionsBDDImpl();
    private Scanner input;
    private String choix = "";
    private int id = 0;
    private String nom = "";
    private String prenom = "";
    private String adresse = "";
    private String pseudo = "";
    private String responsable = "";
    private String hobby = "";
    private int annaissance = 0;
    private float salaire = 0;
    private float prime = 0;

    public void traiterChoixMenu() {
        input = new Scanner(System.in);

        System.out.println("§§§§§§§§§§§§  MENU §§§§§§§§§§§§  \n");
        System.out.println("1. Afficher tous les programmeurs \n");
        System.out.println("2. Afficher un programmeur \n");
        System.out.println("3. Supprimer un programmeur \n");
        System.out.println("4. Ajouter un programmeur \n");
        System.out.println("5. Modifier le salaire \n");
        System.out.println("6. Quitter le programme \n");
        System.out.print("Quel est votre choix ? :  ");

        choix = input.nextLine();

        if ("1".equals(choix)) {
            dt.afficherProgrammeurs();
            traiterChoixMenu();
        } else if ("2".equals(choix)) {

            System.out.print("Id du programmeur à afficher  :   ");
            id = input.nextInt();
            while (dt.getProgrammeur(id) == null) {
                System.out.print("Recherche KO. Saisissez à nouveau l'id :   ");
                id = input.nextInt();
            }
            dt.afficherUnSeulProgrammeur();
            traiterChoixMenu();

        } else if ("3".equals(choix)) {
            System.out.print("Id du programmeur à supprimer  :   ");
            id = input.nextInt();
            while (dt.supprimerProgrammeur(id) == 0) {
                System.out.print("Suppression KO. Saisissez à nouveau l'id :   ");
                id = input.nextInt();
            }
            System.out.print("SUPPRESSION REUSSIE ! \n \n");
            traiterChoixMenu();

        } else if ("4".equals(choix)) {
            System.out.print("Nom du programmeur : ");
            nom = input.nextLine();
            System.out.print("Prénom du programmeur : ");
            prenom = input.nextLine();
            System.out.print("Adresse du programmeur : ");
            adresse = input.nextLine();
            System.out.print("Pseudo du programmeur : ");
            pseudo = input.nextLine();
            System.out.print("Responsable du programmeur : ");
            responsable = input.nextLine();
            System.out.print("Hobby du programmeur : ");
            hobby = input.nextLine();
            System.out.print("Année de naissance du programmeur : ");
            annaissance = new Integer(input.nextLine());
            System.out.print("Salaire du programmeur : ");
            salaire = new Float(input.nextLine());
            System.out.print("Prime du programmeur : ");
            prime = new Float(input.nextLine());

            ProgrammeurBean nouveauProgr = new ProgrammeurBean(nom, prenom, adresse,
                    pseudo, responsable, hobby,
                    annaissance, salaire, prime);
            if (dt.ajouterProgrammeur(nouveauProgr) != 0) {
                System.out.print("AJOUT REUSSI ! \n \n");
            }

            traiterChoixMenu();

        } else if ("5".equals(choix)) {
            System.out.print("Id du programmeur : ");
            id = new Integer(input.nextLine());
            
            while (dt.getProgrammeur(id) == null) {
                System.out.print("Programmeur introuvable. Saisissez à nouveau l'id :   ");
                id = new Integer(input.nextLine());
            }
            
            System.out.print("Nouveau salaire de ce programmeur : ");
            salaire = new Float(input.nextLine());
            while (dt.modifierSalaireProgrammeur(salaire, id) == 0) {
                System.out.print("MODIFICATION KO. Saisissez à nouveau l'id :  ");
                 id = new Integer(input.nextLine());
            }
            System.out.print("MODIFICATION REUSSIE ! \n \n");
            traiterChoixMenu();

        } else if ("6".equals(choix)) {

            System.exit(0);

        } else {
            System.out.println("ERREUR! Veuillez saisir un nombre entre 1 et 6.");
            traiterChoixMenu();
        }

    }

}
