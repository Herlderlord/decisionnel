/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.gui.controller;

import carsense.FunctionsPreference.FunctionPreferenceStrategy;
import carsense.FunctionsPreference.VoieBasiqueStrategy;
import carsense.FunctionsPreference.VoieNormalStrategy;
import carsense.Methods.Borda;
import carsense.Methods.MethodStrategy;
import carsense.Methods.PrometheeOne;
import carsense.Methods.PrometheeTwo;
import carsense.Modele.Problem;
import carsense.Output.OutputGenerator;
import carsense.Output.StringGenerator;
import carsense.Process.Builder;
import carsense.gui.FXMLCarSense;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dem_l
 */
public class MainFrameController implements Initializable {

    private Stage stage;
    public void setStage (Stage stage) { this.stage = stage; }
    public Stage getStage() { return this.stage; }
    
    /**
     * FXML LINKS FOR CONTROLS TO WORK
     */

    @FXML
    public TextField donneesVoitures_field, donneesCriteres_field;
    @FXML
    public Button selectionVoitures_button, selectionCriteres_button, 
            prometheeOne_button, prometheeTwo_button, borda_button,
            generer_button;
    @FXML
    public Label methodeSelectionnee_label;
    @FXML
    public TextArea output_area;
    
    private String fichierVoitures = "", fichierCriteres = "";
    private String methode = "";
    
    private Problem problem;
    private FunctionPreferenceStrategy function;
    private MethodStrategy strategy;
    private OutputGenerator generator;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        generator = new StringGenerator();
        function = new VoieBasiqueStrategy ();
    }    
    
    public void updateProblem () throws IOException {
        if (!fichierVoitures.isEmpty() && !fichierCriteres.isEmpty()) {
            problem = Builder.createProblemVoiture(fichierVoitures, fichierCriteres);
        }
    }
    
    @FXML
    public void selectionVoitures_evt(ActionEvent evt) throws IOException {
        if (evt.getSource().equals(selectionVoitures_button)) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(Paths.get("").toAbsolutePath().toString()+File.separator+"res"));
            fileChooser.setTitle("Choix fichier voitures");
            File file = fileChooser.showOpenDialog(getStage());
            if (file != null) {
                fichierVoitures = file.getCanonicalPath();
                donneesVoitures_field.setText(file.getName());
                updateProblem();
            }
        }
    }
    
    @FXML
    public void selectionCriteres_evt(ActionEvent evt) throws IOException {
        if (evt.getSource().equals(selectionCriteres_button)) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(Paths.get("").toAbsolutePath().toString()+File.separator+"res"));
            fileChooser.setTitle("Choix fichier critères");
            File file = fileChooser.showOpenDialog(getStage());
            if (file != null) {
                fichierCriteres = file.getCanonicalPath();
                donneesCriteres_field.setText(file.getName());
                updateProblem();
            }
        }
    }
    
    @FXML
    public void prometheeOne_evt(ActionEvent evt) {
        if (evt.getSource().equals(prometheeOne_button)) {
            methode = "prometheeOne";
            methodeSelectionnee_label.setText("Méthode sélectionnée : Prométhée I");
        }
    }
    
    @FXML
    public void prometheeTwo_evt(ActionEvent evt) {
        if (evt.getSource().equals(prometheeTwo_button)) {
            methode = "prometheeTwo";
            methodeSelectionnee_label.setText("Méthode sélectionnée : Prométhée II");
        }
    }
    
    @FXML
    public void borda_evt (ActionEvent evt) {
        if (evt.getSource().equals(borda_button)) {
            methode = "borda";
            methodeSelectionnee_label.setText("Méthode sélectionnée : Borda");
        }
    
    }
    
    @FXML
    public void generer_evt (ActionEvent evt) throws IOException {
        if (evt.getSource().equals(generer_button)) {
            Alert alert = new Alert(null);
            alert.setTitle("Générer");
            if (fichierVoitures.isEmpty() || fichierCriteres.isEmpty() || methode.isEmpty()) {
                alert.setAlertType(AlertType.ERROR);
                alert.setHeaderText(null);
                String contentText = "";
                if (fichierVoitures.isEmpty()) {
                    contentText += "Veuilez choisir un fichier de chargement de voitures.\n";
                }
                if (fichierCriteres.isEmpty()) {
                    contentText += "Veuilez choisir un fichier de chargement de critères.\n";
                }
                if (methode.isEmpty()) {
                    contentText += "Veuillez sélectionner une méthode de calcul.";
                }
                alert.setContentText(contentText);
                alert.showAndWait();
            }
            else {
                /*alert.setAlertType(AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Fichiers de configuration :\nFichier voitures : "+fichierVoitures+"\nFichier critères : "+fichierCriteres);
                alert.showAndWait();*/
                
                
                String text = "";
                if (methode.equals("prometheeOne")) {
                    strategy = new PrometheeOne();
                    strategy.calcul(problem);
                    text = generator.generate((PrometheeOne)strategy);
                }
                else if(methode.equals("prometheeTwo")) {
                    strategy = new PrometheeTwo();
                    strategy.calcul(problem);
                    text = generator.generate((PrometheeTwo)strategy);
                }
                else if(methode.equals("borda")) {
                    strategy = new Borda(problem.voitures);
                    ((Borda)strategy).calcul(problem);
                    text = generator.generate((Borda)strategy); 
                }
                output_area.setText(text);
                //changer pour StringGenerator
            }
            
        }
    }
}
