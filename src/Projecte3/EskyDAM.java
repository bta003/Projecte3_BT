package Projecte3;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class EskyDAM extends Application {

    TextField txtCliNom;
    TextField txtCliDni;
    TextField txtCliCognoms;
    TextField txtCliCursID;
    TextField txtCliCursNom;
    TextField txtCliCursPreu;
    TextField txtCliCursNivell;
    String numhores = "1";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage escenari) throws Exception {
        escenari.getIcons().add(new Image("Imatges/logo.png"));

        BorderPane bp = new BorderPane();

        bp.setTop(partSuperior());

        Pane inferior = partInferior();
        bp.setBottom(inferior);
        BorderPane.setMargin(inferior, new Insets(20, 20, 20, 20));

        Pane lEsquerra = partEsquerra();
        bp.setLeft(lEsquerra);
        BorderPane.setMargin(lEsquerra, new Insets(20, 20, 20, 20));

        bp.setCenter(formulariCentral());

        Pane lDreta = partDreta();
        bp.setRight(lDreta);
        BorderPane.setMargin(lDreta, new Insets(20, 20, 20, 20));

        Scene escena = new Scene(bp);
        escenari.setScene(escena);
        escenari.setMinHeight(1000);
        escenari.setMinWidth(100);

        escenari.show();

    }

    private GridPane formulariCentral() {
        GridPane gp = new GridPane();

        Label lblDni = new Label("DNI");

        Label lblNom = new Label("Nom");
        lblNom.setTranslateY(10);

        Label lblCognoms = new Label("Cognoms");
        lblCognoms.setTranslateY(20);

        Label lblID = new Label("ID");
        lblID.setTranslateY(30);

        Label lblCursNom = new Label("Nom");
        lblCursNom.setTranslateY(40);

        Label lblPreu = new Label("Preu hora");
        lblPreu.setTranslateY(50);

        txtCliDni = new TextField();

        txtCliNom = new TextField();
        txtCliNom.setTranslateY(10);

        txtCliCognoms = new TextField();
        txtCliCognoms.setTranslateY(20);

        txtCliCursID = new TextField();
        txtCliCursID.setTranslateY(30);

        txtCliCursNom = new TextField();
        txtCliCursNom.setTranslateY(40);

        txtCliCursPreu = new TextField();
        txtCliCursPreu.setTranslateY(50);

        ToggleGroup group = new ToggleGroup();
        final RadioButton rb1 = new RadioButton("1 hora");
        rb1.setSelected(true);
        final RadioButton rb2 = new RadioButton("2 hores");
        final RadioButton rb3 = new RadioButton("3 hores");
        final RadioButton rb4 = new RadioButton("Tot el dia");

        txtCliCursNivell = new TextField();

        gp.setAlignment(Pos.CENTER);

        gp.add(lblDni, 0, 0);
        gp.add(txtCliDni, 1, 0);
        gp.add(lblNom, 0, 1);
        gp.add(txtCliNom, 1, 1);
        gp.add(lblCognoms, 0, 2);
        gp.add(txtCliCognoms, 1, 2);
        gp.add(lblID, 0, 3);
        gp.add(txtCliCursID, 1, 3);
        gp.add(lblCursNom, 0, 4);
        gp.add(txtCliCursNom, 1, 4);
        gp.add(lblPreu, 0, 5);
        gp.add(txtCliCursPreu, 1, 5);

        rb1.setTranslateY(250);
        rb2.setTranslateY(250);
        rb3.setTranslateY(250);
        rb4.setTranslateY(300);
        rb2.setTranslateX(70);
        rb3.setTranslateX(140);

        rb1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    numhores = "1";
                    rb2.setSelected(false);
                    rb3.setSelected(false);
                    rb4.setSelected(false);
                }
            }
        });

        rb2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    numhores = "2";
                    rb1.setSelected(false);
                    rb3.setSelected(false);
                    rb4.setSelected(false);
                }
            }
        });

        rb3.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    numhores = "3";
                    rb1.setSelected(false);
                    rb2.setSelected(false);
                    rb4.setSelected(false);
                }
            }
        });

        rb4.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    numhores = "6";
                    rb1.setSelected(false);
                    rb2.setSelected(false);
                    rb3.setSelected(false);
                }
            }
        });

        gp.getChildren().addAll(rb1, rb2, rb3, rb4);

        return gp;

    }

    private Pane partSuperior() {
        HBox hb = new HBox();
        Label lblEsky = new Label("ESKYDAM");
        lblEsky.setFont(new Font(40));
        lblEsky.setTextFill(Color.web("#0e0edb"));
        hb.setAlignment(Pos.CENTER);
        ImageView logo = new ImageView("Imatges/logo.png");
        logo.setFitHeight(80);
        logo.setFitWidth(80);

        hb.getChildren().addAll(lblEsky, logo);
        return hb;

    }

    private Pane partInferior() {
        Button btn1 = new Button("Eliminar");
        btn1.setTextFill(Color.web("#ffffff"));
        btn1.setStyle("-fx-background-color:#0e0edb;");
        btn1.setOnAction(event -> {
            txtCliDni.clear();
            txtCliNom.clear();
            txtCliCognoms.clear();
            txtCliCursID.clear();
            txtCliCursNom.clear();
            txtCliCursPreu.clear();
        });

        Button btn2 = new Button("Llogar");
        btn2.setTextFill(Color.web("#ffffff"));
        btn2.setStyle("-fx-background-color:#0e0edb;");
        btn2.setTranslateX(20);
        btn2.setOnAction(e -> llogarAlerta());

        Button btn3 = new Button("Tancar");
        btn3.setTextFill(Color.web("#ffffff"));
        btn3.setStyle("-fx-background-color:#0e0edb;");
        btn3.setTranslateX(40);
        btn3.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        HBox hinferior = new HBox();
        hinferior.getChildren().addAll(btn1, btn2, btn3);
        hinferior.setAlignment(Pos.CENTER);

        return hinferior;

    }

    private void llogarAlerta() {
        System.out.println("llogar curs");

        if (txtCliDni.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Has de seleccionar un client");
            alert.showAndWait();
        } else if (txtCliCursID.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Has de seleccionar un curs");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Curs llogat amb exit");
            alert.showAndWait();
        }

        llogarCurs(txtCliDni, txtCliCursID, numhores);
    }

    private void llogarCurs(TextField txtCliDni, TextField txtCliCursID, String numhores) {
        try {
            // create a mysql database connection

            int idcurs = Integer.parseInt(txtCliCursID.getText());
            String dniclient = txtCliDni.getText();
            int nhores = Integer.parseInt(numhores);

            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/esqui";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "Fat/3232");

            Statement st = conn.createStatement();

    
            /*Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM Federats where dni='" + dniclient + "')");
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM Competicio where ID_curs=" + idcurs + ")");
            if (rs.next() && rs2.next()) {
                rs.beforeFirst();

            } else {
                System.out.println("Obtained ResultSet object is empty");
            }
            */
            
            st.executeUpdate("call llogarCurs('" + idcurs + "','" + dniclient + "','" + nhores + "')");


            conn.close();
        } catch (Exception e) {
            System.err.println("ERROR LLOGAR CURS");
            System.err.println(e.getMessage());
        }

    }

    private Pane partDreta() throws Exception {
        VBox vb = new VBox();
        Label lblDreta = new Label("Cursos");
        lblDreta.setFont(new Font(30));
        lblDreta.setTextFill(Color.web("#0e0edb"));

        vb.setAlignment(Pos.CENTER);
        //vb.setSpacing(15);
        lblDreta.setTranslateY(14);

        TabPane tp = new TabPane();
        Tab tab1 = new Tab("Col·lectius", cursosColectius());
        Tab tab2 = new Tab("Competició", cursosCompeticio());
        Tab tab3 = new Tab("Individuals", cursosIndividuals());
        tp.getTabs().add(tab1);
        tp.getTabs().add(tab2);
        tp.getTabs().add(tab3);
        tp.setTranslateY(8);

        /*ImageView logo3 = new ImageView("Imatges/esqui.png");
        logo3.setFitHeight(150);
        logo3.setFitWidth(150);
        logo3.setTranslateY(20);*/
        vb.getChildren().addAll(lblDreta, tp);
        return vb;
    }

    private Pane cursosColectius() throws Exception {
        Connexio con = new Connexio("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/esqui", "root", "Fat/3232");

        VBox vb = new VBox();
        TableView<CursColectiu> tblCursos = new TableView<>();
        TableColumn<CursColectiu, String> colID = new TableColumn<>("ID");
        TableColumn<CursColectiu, String> colNom = new TableColumn<>("Nom");
        TableColumn<CursColectiu, String> colPreu = new TableColumn<>("Preu hora");

        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("preu_hora"));

        tblCursos.getColumns().addAll(colID, colNom, colPreu);

        tblCursos.getItems().addAll(con.getCursColList());

        //CursColectiu c1 = new CursColectiu("5", "Curs col·lectiu", "12€");
        vb.getChildren().add(tblCursos);

        tblCursos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                CursColectiu curs = (CursColectiu) newValue;

                if (curs != null) {
                    txtCliCursID.setText(Integer.toString(curs.getID()));
                    txtCliCursNom.setText(curs.getNom());
                    txtCliCursPreu.setText(Integer.toString(curs.getPreu_hora()));
                }

            }
        });
        return vb;
    }

    private Pane cursosCompeticio() throws Exception {
        Connexio con = new Connexio("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/esqui", "root", "Fat/3232");

        VBox vb = new VBox();
        TableView<CursCompeticio> tblCursos = new TableView<>();
        TableColumn<CursCompeticio, String> colID = new TableColumn<>("ID");
        TableColumn<CursCompeticio, String> colNom = new TableColumn<>("Nom");
        TableColumn<CursCompeticio, String> colPreu = new TableColumn<>("Preu hora");
        TableColumn<CursCompeticio, String> colNivell = new TableColumn<>("Nivell");

        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("preu_hora"));
        colNivell.setCellValueFactory(new PropertyValueFactory<>("nivell"));

        tblCursos.getColumns().addAll(colID, colNom, colPreu, colNivell);

        tblCursos.getItems().addAll(con.getCursComList());

        //CursColectiu c1 = new CursColectiu("5", "Curs col·lectiu", "12€");
        vb.getChildren().add(tblCursos);

        tblCursos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                CursCompeticio curs = (CursCompeticio) newValue;

                if (curs != null) {
                    txtCliCursID.setText(Integer.toString(curs.getID()));
                    txtCliCursNom.setText(curs.getNom());
                    txtCliCursPreu.setText(Integer.toString(curs.getPreu_hora()));
                    txtCliCursNivell.setText(curs.getNivell());

                }

            }
        });
        return vb;
    }

    private Pane cursosIndividuals() throws Exception {
        Connexio con = new Connexio("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/esqui", "root", "Fat/3232");

        VBox vb = new VBox();
        TableView<CursIndividual> tblCursos = new TableView<>();
        TableColumn<CursIndividual, String> colID = new TableColumn<>("ID");
        TableColumn<CursIndividual, String> colNom = new TableColumn<>("Nom");
        TableColumn<CursIndividual, String> colPreu = new TableColumn<>("Preu hora");
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("preu_hora"));

        tblCursos.getColumns().addAll(colID, colNom, colPreu);

        tblCursos.getItems().addAll(con.getCursIndList());

        //CursColectiu c1 = new CursColectiu("5", "Curs col·lectiu", "12€");
        vb.getChildren().add(tblCursos);

        tblCursos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                CursIndividual curs = (CursIndividual) newValue;

                if (curs != null) {
                    txtCliCursID.setText(Integer.toString(curs.getID()));
                    txtCliCursNom.setText(curs.getNom());
                    txtCliCursPreu.setText(Integer.toString(curs.getPreu_hora()));

                }

            }
        });
        return vb;
    }

    private Pane partEsquerra() throws Exception {
        Connexio con = new Connexio("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/esqui", "root", "Fat/3232");
        VBox vLateral = new VBox();
        Label lblClient = new Label("Clients");
        lblClient.setFont(new Font(30));
        lblClient.setTextFill(Color.web("#0e0edb"));
        vLateral.setAlignment(Pos.CENTER);

        TableView<Client> tblClients = new TableView<>();
        TableColumn<Client, String> colDni = new TableColumn<>("DNI");
        TableColumn<Client, String> colNom = new TableColumn<>("Nom");
        TableColumn<Client, String> colCognoms = new TableColumn<>("Cognoms");
        tblClients.getColumns().addAll(colDni, colNom, colCognoms);

        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCognoms.setCellValueFactory(new PropertyValueFactory<>("cognoms"));

        //ObservableList<Clients> dades = FXCollections.observableArrayList(Clients.Dades(""));
        //Client c1 = new Client("47137446G", "Bruno", "Tomé Arias");
        //Client c2 = new Client("67900534A", "Daniel", "Alcobé Orellana");
        //Client c3 = new Client("03595023P", "Pau", "Rubio Silva");
        //Client c4 = new Client("94334295W", "Sergi", "Florensa Rojo");
        //Client c5 = new Client("23706082M", "Matías", "Jurado Rodríguez");
        /*ImageView logo2 = new ImageView("Imatges/User.png");
        logo2.setFitHeight(100);
        logo2.setFitWidth(100);
        logo2.setTranslateY(20);*/
        tblClients.getItems().addAll(con.getPersonList());

        vLateral.getChildren().addAll(lblClient, tblClients);

        tblClients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Client client = (Client) newValue;

                if (client != null) {
                    txtCliNom.setText(client.getNom());
                    txtCliDni.setText(client.getDni());
                    txtCliCognoms.setText(client.getCognoms());
                }

            }
        });

        return vLateral;

    }

}
