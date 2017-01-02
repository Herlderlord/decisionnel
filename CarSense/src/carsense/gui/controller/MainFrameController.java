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
import carsense.Output.HtmlGenerator;
import carsense.Output.OutputGenerator;
import carsense.Output.StringGenerator;
import carsense.Process.Builder;
import carsense.gui.FXMLCarSense;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
            generer_button, genererHtml_button;
    @FXML
    public Label methodeSelectionnee_label, config_status;
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
            generator = new StringGenerator();
            Alert alert = new Alert(null);
            alert.setTitle("Générer");
            if (problem==null || methode.isEmpty()) {
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
                switch (methode) {
                    case "prometheeOne":
                        strategy = new PrometheeOne();
                        strategy.calcul(problem);
                        text = generator.generate((PrometheeOne)strategy);
                        break;
                    case "prometheeTwo":
                        strategy = new PrometheeTwo();
                        strategy.calcul(problem);
                        text = generator.generate((PrometheeTwo)strategy);
                        break;
                    case "borda":
                        strategy = new Borda();
                        ((Borda)strategy).calcul(problem); 
                        text = generator.generate((Borda)strategy);
                        break;
                    default:
                        break;
                }
                output_area.setText(text);
                //changer pour StringGenerator
            }
            
        }
    }
    
    @FXML
    public void genererHtml_evt (ActionEvent evt) throws IOException, URISyntaxException {
        if (evt.getSource().equals(genererHtml_button)) {
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
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Nom du fichier HTML");
                dialog.setHeaderText("Exportation des résultats en HTML");
                dialog.setContentText("Entrez un nom pour le fichier HTML : ");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    String fileName = result.get();
                    if (!fileName.contains(".html")) {
                        fileName += ".html";
                    }
                    fileName = URLEncoder.encode(fileName,"UTF-8");
                    File file = new File(fileName);
                    FileOutputStream fos = new FileOutputStream (file);
                    OutputStreamWriter osw = new OutputStreamWriter (fos);
                    String text = "";
                    generator = new HtmlGenerator();
                    switch (methode) {
                        case "prometheeOne":
                            strategy = new PrometheeOne();
                            strategy.calcul(problem);
                            text = generator.generate((PrometheeOne)strategy);
                            break;
                        case "prometheeTwo":
                            strategy = new PrometheeTwo();
                            strategy.calcul(problem);
                            text = generator.generate((PrometheeTwo)strategy);
                            break;
                        case "borda":
                            strategy = new Borda();
                            ((Borda)strategy).calcul(problem);
                            text = generator.generate((Borda)strategy);
                            break;
                        default:
                            break;
                    }
                    osw.write(text);
                    osw.close();
                    fos.close();
                    Desktop.getDesktop().browse(new URI(fileName));
                }
            }
        }
    }
}
