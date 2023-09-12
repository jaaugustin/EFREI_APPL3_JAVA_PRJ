/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ri;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Jacques
 * Interface contenant l'ensemble des services mis en oeuvres dans la classe ActionsBDDImpl
 * 
 */
public interface ActionsBDD {

    public ResultSet getResultSet(String req);

    public ArrayList getProgrammeurs();

    public ProgrammeurBean getProgrammeur(int id);

    public void afficherProgrammeurs();

    public void afficherUnSeulProgrammeur();

    public int supprimerProgrammeur(int id);

    public int modifierSalaireProgrammeur(Float salaire, int id);

    public int ajouterProgrammeur(ProgrammeurBean prog);

//    public void libererRessources();
}
