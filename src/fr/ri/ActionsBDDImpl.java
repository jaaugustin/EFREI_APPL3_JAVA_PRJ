/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jacques
 */
public class ActionsBDDImpl implements ActionsBDD {
    public static final String OCCUPATION = "Programmeur";
    public static final String URL = "jdbc:derby://localhost:1527/APTN61_BD";
    public static final String USER = "adm";
    public static final String MDP = "adm";
    public static final String REQ_SEL_TOUS = "SELECT * from PROGRAMMEUR";
    public static final String REQ_SEL_UN = "SELECT * from PROGRAMMEUR WHERE id = ?";
    public static final String REQ_MODIF_SALAIRE = "UPDATE PROGRAMMEUR "
            + "SET salaire =?"
            + "WHERE id = ?";
    public static final String REQ_SUPPR = "DELETE from PROGRAMMEUR WHERE id = ?";
    public static final String REQ_AJOUT = "INSERT INTO PROGRAMMEUR(NOM,PRENOM,"
            + "ADRESSE,PSEUDO,RESPONSABLE,HOBBY,"
            + "ANNAISSANCE,SALAIRE,PRIME) VALUES\n"
            + "(?,?,?,?,?,?,?,?,?)";

    private Connection dbConn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private ArrayList<ProgrammeurBean> listeProgrammeurs;
    private ProgrammeurBean prog;

    public ActionsBDDImpl() {
        try {
            dbConn = DriverManager.getConnection(URL, USER, MDP);
        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, sqle);
        }

    }

    @Override
    public ResultSet getResultSet(String req) {
        try {
            stmt = dbConn.createStatement();
            rs = stmt.executeQuery(req);
        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.SEVERE, null, sqle);
        }
        return rs;
    }

    @Override
    public ArrayList getProgrammeurs() {
        rs = this.getResultSet(REQ_SEL_TOUS);
        listeProgrammeurs = new ArrayList<>();

        try {
            while (rs.next()) {
                prog = new ProgrammeurBean();
                prog.setId(rs.getInt("ID"));
                prog.setPrenom(rs.getString("PRENOM"));
                prog.setNom(rs.getString("NOM"));
                prog.setAdresse(rs.getString("ADRESSE"));
                prog.setPseudo(rs.getString("PSEUDO"));
                prog.setResponsable(rs.getString("RESPONSABLE"));
                prog.setHobby(rs.getString("HOBBY"));
                prog.setAnNaissance(rs.getInt("ANNAISSANCE"));
                prog.setSalaire(rs.getFloat("SALAIRE"));
                prog.setPrime(rs.getFloat("PRIME"));
                
                listeProgrammeurs.add(prog);
            }
        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, sqle);
        }
        return listeProgrammeurs;
    }

    @Override
    public ProgrammeurBean getProgrammeur(int id) {
        prog = null;
        try {
            pstmt = dbConn.prepareStatement(REQ_SEL_UN);
            pstmt.setString(1, Integer.toString(id));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                prog = new ProgrammeurBean();
                prog.setId(rs.getInt("ID"));
                prog.setPrenom(rs.getString("PRENOM"));
                prog.setNom(rs.getString("NOM"));
                prog.setAdresse(rs.getString("ADRESSE"));
                prog.setPseudo(rs.getString("PSEUDO"));
                prog.setResponsable(rs.getString("RESPONSABLE"));
                prog.setHobby(rs.getString("HOBBY"));
                prog.setAnNaissance(rs.getInt("ANNAISSANCE"));
                prog.setSalaire(rs.getFloat("SALAIRE"));
                prog.setPrime(rs.getFloat("PRIME"));
            }
        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, sqle);
        }
        return prog;
    }

    @Override
    public int supprimerProgrammeur(int id) {
        int resultatSuppr = 0;
        try {
            pstmt = dbConn.prepareStatement(REQ_SUPPR);
            pstmt.setString(1, Integer.toString(id));
            resultatSuppr = pstmt.executeUpdate();

        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, sqle);
        }

        return resultatSuppr;
    }

    @Override
    public int modifierSalaireProgrammeur(Float salaire, int id) {
        int resultatModif = 0;

        try {
            pstmt = dbConn.prepareStatement(REQ_MODIF_SALAIRE);
            pstmt.setFloat(1, salaire);
            pstmt.setString(2, Integer.toString(id));

            resultatModif = pstmt.executeUpdate();

        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, sqle);
        }

        return resultatModif;
    }

    @Override
    public int ajouterProgrammeur(ProgrammeurBean prog) {
        int resultatAjout = 0;

        try {
            pstmt = dbConn.prepareStatement(REQ_AJOUT);
            pstmt.setString(1, prog.getNom());
            pstmt.setString(2, prog.getPrenom());
            pstmt.setString(3, prog.getAdresse());
            pstmt.setString(4, prog.getPseudo());
            pstmt.setString(5, prog.getResponsable());
            pstmt.setString(6, prog.getHobby());
            pstmt.setString(7, Integer.toString(prog.getAnNaissance()));
            pstmt.setString(8, Float.toString(prog.getSalaire()));
            pstmt.setString(9, Float.toString(prog.getPrime()));

            resultatAjout = pstmt.executeUpdate();

        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, sqle);
        }

        return resultatAjout;

    }

    @Override
    public void afficherProgrammeurs() {
        listeProgrammeurs = this.getProgrammeurs();
        for (ProgrammeurBean programmeur : listeProgrammeurs) {
            System.out.println(programmeur);
        }
    }

    @Override
    public void afficherUnSeulProgrammeur() {
        System.out.println(prog.toString());
    }

//    @Override
//    public void libererRessources() {
//        try {
//            if (dbConn != null) {
//                dbConn.close();
//
//                if (stmt != null) {
//                    stmt.close();
//                }
//
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//
//                if (rs != null) {
//                    rs.close();
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, ex);
//        }
//
//    }
}
