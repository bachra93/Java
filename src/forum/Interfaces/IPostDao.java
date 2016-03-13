/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum.Interfaces;

import forum.Entity.Post;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public interface IPostDao {
        void addPost(Post depot);
   Post findPostByTitre(String titre);
   ArrayList<Post> displayAllPosts();
   void removePostById(String titre);
    
}
