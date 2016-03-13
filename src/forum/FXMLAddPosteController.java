/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forum;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import forum.Classes.PostDao;
import forum.Entity.Post;

/**
 *
 * @author user
 */
public class FXMLAddPosteController implements Initializable {
    
    @FXML
    private TextField t1;
        @FXML
    private TextField t2;
         @FXML
    private HTMLEditor t3;
         
         private Integer a ;
         
        
        @FXML
 private void handleButtonAction(ActionEvent event) {
       
        Post Post = new Post();
        PostDao PostDao = new PostDao();
   
        Post.setTitre(t2.getText());
         
           Post.setContenu(t3.getHtmlText());

        PostDao.addPost(Post);

     
 

    }
     @FXML
    private void ShowI(ActionEvent e) throws IOException {
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLShowPost.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        app_stage.setScene(home_page_scene);
        app_stage.show();

    }

    public void setA(Integer a) {
        this.a = a;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
