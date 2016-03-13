/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.Classes;

import forum.Connection.MyConnexion;
import forum.Entity.Post;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import forum.Entity.Commentaire;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import forum.Interfaces.ICommentDao;

/**
 *
 * @author user
 */
public class CommentaireDao implements ICommentDao{
        private Connection cnx;

    public CommentaireDao() {
        cnx = MyConnexion.getInstance();
    }
      public ArrayList<Commentaire> findPostById(Integer id) {
        
         ArrayList<Commentaire> listeCommentaire = new ArrayList<Commentaire>();
        String requete = "select * from commentaire where id=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Commentaire commentaire= new Commentaire();
                commentaire.setId(resultat.getInt(1));
               commentaire.setId_c(resultat.getInt(2));
                 commentaire.setContenu(resultat.getString(3));
                 listeCommentaire.add(commentaire);
            }
            return listeCommentaire;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }
       public void addPost(Commentaire p) {
        String req = "insert into commentaire (id,contenu) values (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
   
            ps.setInt(1, p.getId());
            ps.setString(2, p.getContenu());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
               public void removeCommentById(String titre) {
        String requete = "delete from commentaire where contenu=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, titre);
            ps.executeUpdate();
            System.out.println("Commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
                   public void updateDepot(Commentaire depot,String contenu) {
        String requete = "update commentaire set contenu=? where contenu=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, depot.getContenu());
            ps.setString(2, contenu);
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    
    
}
