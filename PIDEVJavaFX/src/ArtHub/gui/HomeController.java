package ArtHub.gui;


import ArtHub.entities.Categorie;
import ArtHub.entities.Evenement;

import ArtHub.entities.Participant;


import ArtHub.entities.User;

import ArtHub.services.EvenementCRUD;


import ArtHub.services.ParticipantCRUD;

import ArtHub.services.UserCRUD;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.twilio.rest.api.v2010.account.call.Feedback;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class HomeController implements Initializable {

    @FXML
    private Button btnUsers;
    @FXML
    private Button btnJobs;
    @FXML
    private Button BtnLabels;
    @FXML
    private Button btnFeedbacks;
    @FXML
    private Button btnEvents;
    @FXML
    private Button btnCategories;
    @FXML
    private Button btnSignout;
    @FXML
    private ImageView btnSignal;
    @FXML
    private Pane pnlEvents;
    @FXML
    private TextField inputEvents;
    @FXML
    private HBox event_stats;
    @FXML
    private Label Events_thisweek;
    @FXML
    private Label Eventsthisweek;
    @FXML
    private ImageView showHistory;

    @FXML
    private Pane pnlUsers;
    @FXML
    private TextField inputUsers;
    @FXML
    private HBox Users_stats;
    @FXML
    private ImageView showHistory1;
    @FXML
    private Pane pnlJobs;
    @FXML
    private TextField inputJobs;
    @FXML
    private HBox Jobs_stats;
    @FXML
    private ImageView showHistory11;
    @FXML
    private Pane pnlLabels;
    @FXML
    private HBox Labels_stats;
    @FXML
    private ImageView showHistory111;
    @FXML
    private Pane pnlFeedback;
    @FXML
    private HBox Feedback_stats;
    @FXML
    private ImageView showHistory1111;
    @FXML
    private Pane pnlCategorie;
    @FXML
    private HBox Categorie_stats;
    @FXML
    private ImageView showHistory11111;

    @FXML
    private ImageView showHistory111111;
    @FXML
    private VBox itemsLabels;
    @FXML
    private VBox itemsEvents;
    @FXML
    private VBox itemsUsers;
    @FXML
    private VBox itemsJobs;
    @FXML
    private VBox itemsFeedback;
    @FXML
    private VBox ItemsCategorie;

    @FXML
    private ImageView addEvent;
    @FXML
    private Label events_this_month;
    @FXML
    private Label Total_events;
    @FXML
    private Label Total_participants;
    @FXML
    private ImageView archive;
    @FXML
    private ImageView SwitchFront;
    @FXML
    private Button btnPosts;
    @FXML
    private Pane pnlPosts;
    @FXML
    private HBox Posts_stats;
    @FXML
    private VBox ItemsPosts;
    @FXML
    private TextField inputFeedback;

    @FXML
    private Button statsback;

    private VBox itemsSignalisation;
    private ImageView SignalShow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) {
       
        
        

        
        
        if (actionEvent.getSource() == btnSignout) {
            disconnect();
        }
      

        if (actionEvent.getSource() == btnEvents) {
            ShowEvents();
            pnlEvents.setStyle("-fx-background-color : #02030A");
            pnlEvents.toFront();
        }
    }

    public void ShowUsers() {
        itemsUsers.getChildren().clear();
        Users_stats.setVisible(true);
        UserCRUD ps = new UserCRUD();
        // id table view
        JFXTreeTableColumn<User, String> id_user = new JFXTreeTableColumn<>("id_user");
        id_user.setPrefWidth(150);
        id_user.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().getId_user()));
            }
        });

        // nom table view
        JFXTreeTableColumn<User, String> nom = new JFXTreeTableColumn<>("nom");
        nom.setPrefWidth(150);
        nom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty((param.getValue().getValue().getNom()));
            }
        });
        //making the cell editable
        nom.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable nom text field
        nom.setOnEditCommit((TreeTableColumn.CellEditEvent<User, String> u) -> {
            int idd = u.getTreeTableView().getTreeItem(u.getTreeTablePosition().getRow()).getValue().getId_user();
            String newValue = u.getNewValue();

            u.getTreeTableView()
                    .getTreeItem(u.getTreeTablePosition()
                            .getRow())
                    .getValue().setNom((u.getNewValue()));
            ps.modifierUser(idd, "nom", newValue);
        });

   
        // nom_event table view
        JFXTreeTableColumn<User, String> prenom = new JFXTreeTableColumn<>("prenom");
        prenom.setPrefWidth(150);
        prenom.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getPrenom());
            }
        });
        prenom.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable nom_event text field
        prenom.setOnEditCommit((TreeTableColumn.CellEditEvent<User, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId_user();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setPrenom(t.getNewValue());
            ps.modifierUser(idd, "prenom", newValue);
        });

        // username table view
        JFXTreeTableColumn<User, String> username = new JFXTreeTableColumn<>("username");
        username.setPrefWidth(150);
        username.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty((param.getValue().getValue().getUsername()));
            }
        });
        username.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable username text field
        username.setOnEditCommit((TreeTableColumn.CellEditEvent<User, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId_user();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setUsername(t.getNewValue());
            ps.modifierUser(idd, "username", newValue);
        });

        // mail table view
        JFXTreeTableColumn<User, String> mail = new JFXTreeTableColumn<>("mail");
        mail.setPrefWidth(150);
        mail.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getMail());
            }

        });

        mail.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable mail text field
        mail.setOnEditCommit((TreeTableColumn.CellEditEvent<User, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId_user();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setMail(t.getNewValue());
            ps.modifierUser(idd, "mail", newValue);
        });
        // pwd_user table view
        JFXTreeTableColumn<User, String> pwd_user = new JFXTreeTableColumn<>("pwd_user");
        pwd_user.setPrefWidth(150);
        pwd_user.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getPwd_user());
            }
        });

        pwd_user.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable pwd_user text field
        pwd_user.setOnEditCommit((TreeTableColumn.CellEditEvent<User, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId_user();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setPwd_user(t.getNewValue());
            ps.modifierUser(idd, "pwd_user", newValue);
        });

        // ref_admin table view
        JFXTreeTableColumn<User, String> ref_admin = new JFXTreeTableColumn<>("ref_admin");
        ref_admin.setPrefWidth(150);
        ref_admin.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty((param.getValue().getValue().getRef_admin()));
            }
        });
        ref_admin.setCellFactory((TreeTableColumn<User, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable ref_admin text field
        ref_admin.setOnEditCommit((TreeTableColumn.CellEditEvent<User, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId_user();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setRef_admin(t.getNewValue());
            ps.modifierUser(idd, "ref_admin", newValue);
        });

        List<User> myLst;
        myLst = ps.afficherUser();
        ObservableList<User> Users = FXCollections.observableArrayList();

        myLst.forEach(p -> Users.add(p));
        JFXTreeTableView<User> treeview = new JFXTreeTableView<>();
        final TreeItem<User> root = new RecursiveTreeItem<User>(Users, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(id_user, nom, prenom, username, mail, pwd_user, ref_admin);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.setEditable(true);

        //declarer la button supprimer
        JFXButton DltBtn = new JFXButton("Remove");
        DltBtn.setLayoutY(410D);
        DltBtn.setOnAction(new EventHandler<ActionEvent>() {

            //eventHandler de la button supprimer
            @Override
            public void handle(ActionEvent event) {
                Dialog confirmation = new Dialog();
                GridPane grid2 = new GridPane();
                Label l1 = new Label("Delete User?");
                grid2.add(l1, 2, 2);
                confirmation.setTitle("Confirmation de suppression!");
                confirmation.getDialogPane().setContent(grid2);
                ButtonType Confi = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType Ann = new ButtonType("No", ButtonBar.ButtonData.OK_DONE);
                confirmation.getDialogPane().getButtonTypes().add(Confi);
                confirmation.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                confirmation.setResultConverter(new Callback<ButtonType, User>() {
                    @Override
                    public User call(ButtonType param) {
                        if (param == Confi) {
                            User p = treeview.getSelectionModel().getSelectedItem().getValue();
                            ps.supprimerUser((User) p);
                            Button cancelButton = (Button) confirmation.getDialogPane().lookupButton(ButtonType.CLOSE);
                            cancelButton.fire();
                            ShowUsers();
                        }

                        return null;
                    }
                });
                treeview.getStylesheets().add(getClass().getResource("treetableview.css").toExternalForm());
                treeview.setStyle("-fx-background-color:rgba(0,255,255,0.2);");
                itemsUsers.getChildren().addAll(treeview, DltBtn);
                confirmation.showAndWait();
            }
        });

        itemsUsers.getChildren().addAll(treeview, DltBtn);

    }

    public void ShowEvents() {
        SwitchFront.setVisible(true);

        itemsEvents.getChildren().clear();
        EvenementCRUD ps = new EvenementCRUD();
        UserCRUD us = new UserCRUD();

        // id_org table view
        JFXTreeTableColumn<Evenement, String> id_org = new JFXTreeTableColumn<>("Orgranisateur");
        id_org.setPrefWidth(150);
        id_org.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                int x = param.getValue().getValue().getId_org().getId_user();
                User user = us.FindUser(x);
                return new SimpleStringProperty(user.getUsername());
            }
        });

        // date table view
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        JFXTreeTableColumn<Evenement, String> date = new JFXTreeTableColumn<>("date");
        date.setPrefWidth(150);
        date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getDate_event().format(formatters));
            }
        });
        date.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable date text field
        date.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setDate_event(LocalDate.parse(t.getNewValue(), formatters));
            ps.modifierEvenement(idd, "date", newValue);
        });

        // nom_event table view
        JFXTreeTableColumn<Evenement, String> nom_event = new JFXTreeTableColumn<>("nom_event");
        nom_event.setPrefWidth(150);
        nom_event.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getNom_event());
            }
        });
        nom_event.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable nom_event text field
        nom_event.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setNom_event(t.getNewValue());
            ps.modifierEvenement(idd, "nom_event", newValue);
        });

        // type_event table view
        JFXTreeTableColumn<Evenement, String> type_event = new JFXTreeTableColumn<>("type_event");
        type_event.setPrefWidth(150);
        type_event.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getType_event());
            }
        });
        type_event.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable type_event text field
        type_event.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setType_event(t.getNewValue());
            ps.modifierEvenement(idd, "type_event", newValue);
        });

        // categorie table view
        JFXTreeTableColumn<Evenement, String> categorie = new JFXTreeTableColumn<>("categorie");
        categorie.setPrefWidth(150);
        categorie.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getCategorie().getCategorie_name());
            }

        });

        categorie.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable cat+1egorie text field
        categorie.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();
            Categorie C = new Categorie();
            C.setCategorie_name(t.getNewValue());
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setCategorie(C);
            ps.modifierEvenement(idd, "categorie", newValue);
        });
        // description table view
        JFXTreeTableColumn<Evenement, String> description = new JFXTreeTableColumn<>("description");
        description.setPrefWidth(150);
        description.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getDescription());
            }
        });

        description.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable description text field
        description.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setDescription(t.getNewValue());
            ps.modifierEvenement(idd, "description", newValue);
        });
        JFXTreeTableColumn<Evenement, String> capacite_event = new JFXTreeTableColumn<>("capacite_event");
        capacite_event.setPrefWidth(150);
        capacite_event.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().getCapacite_event()));
            }

        });

        capacite_event.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable capacite_event text field
        capacite_event.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setCapacite_event(Integer.parseInt(t.getNewValue()));
            ps.modifierEvenement(idd, "capacite_event", newValue);
        });

        List<Evenement> myLst;
        myLst = ps.consulterEvenement();
        ObservableList<Evenement> Evenements = FXCollections.observableArrayList();

        myLst.forEach(p -> Evenements.add(p));
        int i = 0;
        int x = 0;
        int z = myLst.size();
        for (int j = 0; j < myLst.size(); j++) {
            if (DAYS.between(LocalDate.now(), myLst.get(j).getDate_event()) <= 7) {
                i++;
            }
            if (DAYS.between(LocalDate.now(), myLst.get(j).getDate_event()) <= 30) {
                x++;
            }
        }
        JFXTreeTableView<Evenement> treeview = new JFXTreeTableView<>();
        final TreeItem<Evenement> root = new RecursiveTreeItem<Evenement>(Evenements, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(id_org, date, nom_event, type_event, categorie, description, capacite_event);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.setEditable(true);
        treeview.getStylesheets().add(getClass().getResource("treetableview.css").toExternalForm());

        //declarer la button supprimer
        JFXButton DltBtn = new JFXButton("Remove");
        DltBtn.setLayoutY(410D);
        DltBtn.setOnAction(new EventHandler<ActionEvent>() {
            //eventHandler de la button supprimer
            @Override
            public void handle(ActionEvent event) {
                Dialog confirmation = new Dialog();
                GridPane grid2 = new GridPane();
                Label l1 = new Label("Delete Evenement?");
                grid2.add(l1, 2, 2);
                confirmation.setTitle("Confirmation de suppression!");
                confirmation.getDialogPane().setContent(grid2);
                ButtonType Confi = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType Ann = new ButtonType("No", ButtonBar.ButtonData.OK_DONE);
                confirmation.getDialogPane().getButtonTypes().add(Confi);
                confirmation.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                confirmation.setResultConverter(new Callback<ButtonType, Evenement>() {
                    @Override
                    public Evenement call(ButtonType param) {
                        if (param == Confi) {

                            String name = treeview.getSelectionModel().getSelectedItem().getValue().getNom_event();

                            Evenement p = ps.FindEvenementByName(name);
                            ps.supprimerEvenement((Evenement) p);
                            Button cancelButton = (Button) confirmation.getDialogPane().lookupButton(ButtonType.CLOSE);
                            cancelButton.fire();
                            ShowEvents();
                        }

                        return null;
                    }
                });
                confirmation.showAndWait();
            }
        });

        inputEvents.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<Evenement>>() {
                    @Override
                    public boolean test(TreeItem<Evenement> t) {

                        boolean flag = t.getValue().getNom_event().toLowerCase().contains(newValue.toLowerCase());
                        return flag;
                    }
                });
            }
        });
        event_stats.setVisible(true);
        Events_thisweek.setText(Integer.toString(i));
        events_this_month.setText(Integer.toString(x));
        Total_events.setText(Integer.toString(z));
        Eventsthisweek.setText("Events this week");
        treeview.setStyle("-fx-background-color:rgba(0,255,255,0.2);");
        itemsEvents.getChildren().addAll(treeview, DltBtn);

        ParticipantCRUD crud = new ParticipantCRUD();
        Participant inst = new Participant();
        List<Participant> myList;
        myList = crud.consulterParticipant();
        Total_participants.setText(Integer.toString(myList.size()));

    }

    @FXML
    private void addEvent(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ADD_Event.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Host an event");

            stage.setScene(new Scene(root1));

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FRONT_EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void refresh(MouseEvent event) {
        ShowEvents();
    }

    @FXML
    private void archive(MouseEvent event) {

        itemsEvents.getChildren().clear();
        EvenementCRUD ps = new EvenementCRUD();
        UserCRUD us = new UserCRUD();

        // id_org table view
        JFXTreeTableColumn<Evenement, String> id_org = new JFXTreeTableColumn<>("Orgranisateur");
        id_org.setPrefWidth(50);
        id_org.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                int x = param.getValue().getValue().getId_org().getId_user();
                User user = us.FindUser(x);
                return new SimpleStringProperty(user.getUsername());
            }
        });

        // date table view
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        JFXTreeTableColumn<Evenement, String> date = new JFXTreeTableColumn<>("date");
        date.setPrefWidth(150);
        date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getDate_event().format(formatters));
            }
        });
        date.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable date text field
        date.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setDate_event(LocalDate.parse(t.getNewValue(), formatters));
            ps.modifierEvenement(idd, "date", newValue);
        });

        // nom_event table view
        JFXTreeTableColumn<Evenement, String> nom_event = new JFXTreeTableColumn<>("nom_event");
        nom_event.setPrefWidth(150);
        nom_event.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getNom_event());
            }
        });
        nom_event.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable nom_event text field
        nom_event.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setNom_event(t.getNewValue());
            ps.modifierEvenement(idd, "nom_event", newValue);
        });

        // type_event table view
        JFXTreeTableColumn<Evenement, String> type_event = new JFXTreeTableColumn<>("type_event");
        type_event.setPrefWidth(150);
        type_event.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getType_event());
            }
        });
        type_event.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable type_event text field
        type_event.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setType_event(t.getNewValue());
            ps.modifierEvenement(idd, "type_event", newValue);
        });

        // categorie table view
        JFXTreeTableColumn<Evenement, String> categorie = new JFXTreeTableColumn<>("categorie");
        categorie.setPrefWidth(150);
        categorie.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getCategorie().getCategorie_name());
            }

        });

        categorie.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable cat+1egorie text field
        categorie.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();
            Categorie C = new Categorie();
            C.setCategorie_name(t.getNewValue());
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setCategorie(C);
            ps.modifierEvenement(idd, "categorie", newValue);
        });
        // description table view
        JFXTreeTableColumn<Evenement, String> description = new JFXTreeTableColumn<>("description");
        description.setPrefWidth(150);
        description.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getDescription());
            }
        });

        description.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable description text field
        description.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setDescription(t.getNewValue());
            ps.modifierEvenement(idd, "description", newValue);
        });
        JFXTreeTableColumn<Evenement, String> capacite_event = new JFXTreeTableColumn<>("capacite_event");
        capacite_event.setPrefWidth(150);
        capacite_event.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().getCapacite_event()));
            }

        });

        capacite_event.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        //setting the new value for editable capacite_event text field
        capacite_event.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int idd = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();
            String newValue = t.getNewValue();

            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setCapacite_event(Integer.parseInt(t.getNewValue()));
            ps.modifierEvenement(idd, "capacite_event", newValue);
        });

        List<Evenement> myLst;
        myLst = ps.ArchiveEvenement();
        ObservableList<Evenement> Evenements = FXCollections.observableArrayList();

        myLst.forEach(p -> Evenements.add(p));

        JFXTreeTableView<Evenement> treeview = new JFXTreeTableView<>();
        final TreeItem<Evenement> root = new RecursiveTreeItem<Evenement>(Evenements, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(id_org, date, nom_event, type_event, categorie, description, capacite_event);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.setEditable(true);
        treeview.getStylesheets().add(getClass().getResource("treetableview.css").toExternalForm());

        //declarer la button supprimer
        JFXButton DltBtn = new JFXButton("Remove");
        DltBtn.setLayoutY(410D);
        //DltBtn.getStylesheets().add(getClass(button3).getResource("feed gui.css").toExternalForm());
        DltBtn.setOnAction(new EventHandler<ActionEvent>() {
            //eventHandler de la button supprimer
            @Override
            public void handle(ActionEvent event) {
                Dialog confirmation = new Dialog();
                GridPane grid2 = new GridPane();
                Label l1 = new Label("Delete Evenement?");
                grid2.add(l1, 2, 2);
                confirmation.setTitle("Confirmation de suppression!");
                confirmation.getDialogPane().setContent(grid2);
                ButtonType Confi = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType Ann = new ButtonType("No", ButtonBar.ButtonData.OK_DONE);
                confirmation.getDialogPane().getButtonTypes().add(Confi);
                confirmation.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                confirmation.setResultConverter(new Callback<ButtonType, Evenement>() {
                    @Override
                    public Evenement call(ButtonType param) {
                        if (param == Confi) {
                            String name = treeview.getSelectionModel().getSelectedItem().getValue().getNom_event();
                            Evenement p = ps.FindEvenementByName(name);
                            ps.supprimerEvenement((Evenement) p);
                            Button cancelButton = (Button) confirmation.getDialogPane().lookupButton(ButtonType.CLOSE);
                            cancelButton.fire();
                            ShowEvents();
                        }

                        return null;
                    }
                });
                confirmation.showAndWait();
            }
        });

        inputEvents.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<Evenement>>() {
                    @Override
                    public boolean test(TreeItem<Evenement> t) {

                        boolean flag = t.getValue().getNom_event().toLowerCase().contains(newValue.toLowerCase());
                        return flag;
                    }
                });
            }
        });
        event_stats.setVisible(true);

        treeview.setStyle("-fx-background-color:rgba(0,255,255,0.2);");
        itemsEvents.getChildren().addAll(treeview, DltBtn);

    }

    @FXML
    private void showParticipants(MouseEvent event) {
        ParticipantCRUD ps = new ParticipantCRUD();
        itemsEvents.getChildren().clear();
        // id_participation table view
        JFXTreeTableColumn<Participant, String> id_participation = new JFXTreeTableColumn<>("id_participation");
        id_participation.setPrefWidth(300);
        id_participation.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Participant, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Participant, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().getId_participation()));
            }
        });

        // id_user table view
        JFXTreeTableColumn<Participant, String> id_user = new JFXTreeTableColumn<>("id_user");
        id_user.setPrefWidth(300);
        id_user.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Participant, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Participant, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().getId_user().getId_user()));
            }
        });
        // id_event table view
        JFXTreeTableColumn<Participant, String> id_event = new JFXTreeTableColumn<>("id_event");
        id_event.setPrefWidth(300);
        id_event.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Participant, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Participant, String> param) {
                return new SimpleStringProperty(Integer.toString(param.getValue().getValue().getId_event().getId()));
            }

        });

        List<Participant> myLst;
        myLst = ps.consulterParticipant();
        ObservableList<Participant> Participants = FXCollections.observableArrayList();

        myLst.forEach(p -> Participants.add(p));
        JFXTreeTableView<Participant> treeview = new JFXTreeTableView<>();
        final TreeItem<Participant> root = new RecursiveTreeItem<Participant>(Participants, RecursiveTreeObject::getChildren);
        treeview.getColumns().setAll(id_participation, id_user, id_event);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.setEditable(true);
        treeview.getStylesheets().add(getClass().getResource("treetableview.css").toExternalForm());

        //declarer la button supprimer
        JFXButton DltBtn = new JFXButton("Remove");
        DltBtn.setLayoutY(410D);
        DltBtn.setOnAction(new EventHandler<ActionEvent>() {

            //eventHandler de la button supprimer
            @Override
            public void handle(ActionEvent event) {
                Dialog confirmation = new Dialog();
                GridPane grid2 = new GridPane();
                Label l1 = new Label("Delete Participant?");
                grid2.add(l1, 2, 2);
                confirmation.setTitle("Confirmation de suppression!");
                confirmation.getDialogPane().setContent(grid2);
                ButtonType Confi = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType Ann = new ButtonType("No", ButtonBar.ButtonData.OK_DONE);
                confirmation.getDialogPane().getButtonTypes().add(Confi);
                confirmation.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                confirmation.setResultConverter(new Callback<ButtonType, Participant>() {
                    @Override
                    public Participant call(ButtonType param) {
                        if (param == Confi) {
                            Participant p = treeview.getSelectionModel().getSelectedItem().getValue();
                            ps.supprimerParticipant((Participant) p);
                            Button cancelButton = (Button) confirmation.getDialogPane().lookupButton(ButtonType.CLOSE);
                            cancelButton.fire();

                        }

                        return null;
                    }
                });
                confirmation.showAndWait();
            }
        });

        inputEvents.setPromptText("Rechercher ..");
        inputEvents.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<Participant>>() {
                    @Override
                    public boolean test(TreeItem<Participant> t) {

                        boolean flag = String.valueOf(t.getValue().getId_participation()).contains(newValue);
                        return flag;
                    }
                });
            }
        });

        itemsEvents.getChildren().addAll(treeview, DltBtn);
    }

    @FXML
    private void SwitchFront(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FRONT_Event.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Host an event");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FRONT_EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {

            Stage stage1 = (Stage) btnSignout.getScene().getWindow();
            stage1.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Host an event");

            stage.setScene(new Scene(root1));

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FRONT_EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoToStat(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StatFeedback.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Statistiques");

            stage.setScene(new Scene(root1));

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FRONT_EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



   

    public void ShowCategorie() {
        Categorie_stats.setVisible(true);
    }

}
