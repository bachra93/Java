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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author user
 */
public class FXMLShowPostController implements Initializable {

    @FXML
    private ListView l1;
    @FXML
    private TextArea t4;
    @FXML
    private TextArea t5;
    @FXML
    private WebView w1;
    @FXML
    private WebEngine e;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        Post Post = new Post();
        PostDao PostDao = new PostDao();

        ObservableList<Integer> panes = FXCollections.observableArrayList();
        ObservableList<String> panes2 = FXCollections.observableArrayList();
        ArrayList<Post> listPost = new ArrayList<Post>();
        listPost = PostDao.displayAllPosts();
        for (int i = 0; i < listPost.size(); i++) {
            int idhotel = listPost.get(i).getId();
            String nom = listPost.get(i).getTitre();

            // if (listHotel.get(i).getIdHotel()!= idhotel) {
            //panes.add(loadPays(listPays.get(i)));
            System.out.println(idhotel);
            System.out.println(nom);

            panes.add(listPost.get(i).getId());
            panes2.add(listPost.get(i).getTitre());

            l1.setItems(panes2);
        }

    }

    @FXML
    private void ShowI(ActionEvent e) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLShowPost.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        app_stage.setScene(home_page_scene);
        app_stage.show();

    }

    @FXML
    public void handleMouseClick(MouseEvent arg0) {
        String titre = (String) l1.getSelectionModel().getSelectedItem();
        Post Post = new Post();
        PostDao PostDao = new PostDao();
        System.out.println(titre);
        Post = PostDao.findPostByTitre(titre);
        t4.setEditable(false);
        System.out.println(Post.getTitre());
        t4.setText(Post.getTitre());

        e = w1.getEngine();

        e.loadContent(Post.getContenu());

    }

    public void del(ActionEvent a) {
        String titre = (String) l1.getSelectionModel().getSelectedItem();
        PostDao PostDao = new PostDao();
        PostDao.removePostById(titre);

    }

    public void comment(ActionEvent b) throws IOException {
        Post post = new Post();
        PostDao postDao = new PostDao();
        FXMLLoader l = new FXMLLoader();
        Parent home_page_parent = l.load(getClass().getResource("FXMLComment.fxml").openStream());
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) b.getSource()).getScene().getWindow();

        app_stage.setScene(home_page_scene);
        FXMLShowCommentController fXMLShowCommentController = l.getController();
        fXMLShowCommentController.setName((String) l1.getSelectionModel().getSelectedItem());
        post = postDao.findPostByTitre((String) l1.getSelectionModel().getSelectedItem());
      
        fXMLShowCommentController.setId(post.getId());
        app_stage.show();

    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
