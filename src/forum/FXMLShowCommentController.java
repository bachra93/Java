/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum;

import forum.Classes.PostDao;
import forum.Entity.Post;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import forum.Entity.Commentaire;
import forum.Classes.CommentaireDao;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author user
 */
public class FXMLShowCommentController implements Initializable {

    @FXML
    private Label l1;
       @FXML
    private Label l4;
     @FXML
    private ListView l3;
         @FXML
    private TextField t1;
   @FXML
    private String name ;
   private Integer id ;
   @FXML
   
    public void setName(String name) {
   
        System.out.println(name);
        this.name = name;
    }

    public void setId(Integer id) {
        System.out.println(id);
        this.id = id;
    }
    

      public void co(ActionEvent a ){
       
        System.out.println("hello there");
        Post Post = new Post();
        PostDao PostDao = new PostDao();
        System.out.println(name);
        Post = PostDao.findPostByTitre(name);
        l1.setText(name);

        

        Commentaire Commentaire = new Commentaire();
        CommentaireDao CommentaireDao = new CommentaireDao();
        
     
       
       ObservableList<Integer> panes = FXCollections.observableArrayList();
        ObservableList<String> panes2 = FXCollections.observableArrayList();
        ArrayList<Commentaire> listPost = new ArrayList<Commentaire>();
        listPost = CommentaireDao.findPostById(id);
        for (int i = 0; i < listPost.size(); i++) {
            int idhotel = listPost.get(i).getId();
            String nom = listPost.get(i).getContenu();

            // if (listHotel.get(i).getIdHotel()!= idhotel) {
            //panes.add(loadPays(listPays.get(i)));
            System.out.println(idhotel);
            System.out.println(nom);

            panes.add(listPost.get(i).getId());
            panes2.add(listPost.get(i).getContenu());

            l3.setItems(panes2);

        
        

    }
    

  


      }
      
       public void add(ActionEvent ab ){
               Commentaire commentaire = new Commentaire();
        CommentaireDao commentaireDao = new CommentaireDao();
   
        commentaire.setId(id);
         
           commentaire.setContenu(t1.getText());

        commentaireDao.addPost(commentaire);
       
       
    }
          public void del(ActionEvent ab) {
        String titre = (String) l3.getSelectionModel().getSelectedItem();
       CommentaireDao commentaireDao = new CommentaireDao();
              System.out.println("test");
              System.out.println(titre);
       
        commentaireDao.removeCommentById(titre);

    }
             @FXML
    private void ShowI(ActionEvent e) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        app_stage.setScene(home_page_scene);
        app_stage.show();

    }
    
              public void up(ActionEvent abc) {
        String titre = (String) l3.getSelectionModel().getSelectedItem();
       CommentaireDao commentaireDao = new CommentaireDao();
       Commentaire commentaire = new Commentaire();
           commentaire.setContenu(t1.getText());
       commentaireDao.updateDepot(commentaire, titre);
              System.out.println("test");
              System.out.println(titre);
       
    

    }

          
 

      
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
  
        // TODO
    }

}
