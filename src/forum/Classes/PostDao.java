/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.Classes;

import forum.Connection.MyConnexion;
import forum.Entity.Post;
import forum.Interfaces.IPostDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PostDao implements IPostDao {

    private Connection cnx;

    public PostDao() {
        cnx = MyConnexion.getInstance();
    }

    @Override
    public void addPost(Post p) {
        String req = "insert into post (titre,contenu) values (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
   
            ps.setString(1, p.getTitre());
            ps.setString(2, p.getContenu());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Post> displayAllPosts() {
        ArrayList<Post> listePost = new ArrayList<Post>();

        String requete = "select * from post";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Post h = new Post();
                h.setId(resultat.getInt(1));
                h.setTitre(resultat.getString(2));
                h.setContenu(resultat.getString(3));

                listePost.add(h);
            }
            return listePost;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des pays " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Post findPostByTitre(String titre) {
        Post post= new Post();
        String requete = "select * from post where titre=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, titre);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                post.setId(resultat.getInt(1));
                post.setTitre(resultat.getString(2));
                 post.setContenu(resultat.getString(3));
            }
            return post;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }
    
        public void removePostById(String titre) {
        String requete = "delete from post where titre=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, titre);
            ps.executeUpdate();
            System.out.println("Depot supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }




}
