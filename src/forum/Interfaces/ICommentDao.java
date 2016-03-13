/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.Interfaces;

import forum.Entity.Commentaire;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public interface ICommentDao {
    ArrayList<Commentaire> findPostById(Integer id);
    void addPost(Commentaire p);
    void removeCommentById(String titre);
    void updateDepot(Commentaire depot,String contenu);
    
}
