/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tda.AVL;

/**
 *
 * @author CORE i7 ULTIMATE
 */
public class InterfazArbol {
    private Pane root;
    private AVL<Integer> avlTree;
    private ScrollPane sp;
    private final TextField txtInsertar;
    private final TextField txtDelete;
    StackPane arbol;

    public InterfazArbol() {
        root = new VBox();
        txtInsertar = new TextField();
        txtDelete = new TextField();
        avlTree = new AVL<>(Integer::compareTo);
        arbol = new StackPane();
        sp = new ScrollPane();
        createRoot();
    }

    private void createRoot() {
        Rectangle topRect = new Rectangle(1366, 75);
        topRect.setFill(Color.DARKCYAN);
        Label lblAvl = new Label("AVL TREE");
        lblAvl.setTranslateX(30);
        lblAvl.setTranslateY(20);
        Font theFont = Font.font("arial", FontWeight.EXTRA_BOLD, 40);
        lblAvl.setFont(theFont);
        lblAvl.setTextFill(Color.WHITE);
        Pane topPane = new Pane();
        topPane.getChildren().addAll(topRect, lblAvl);
        HBox hbOpc = new HBox();
        hbOpc.setAlignment(Pos.CENTER);
        hbOpc.setTranslateY(6);
        txtInsertar.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                insertar();
            }
        });
        txtDelete.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.ENTER){
                eliminar();
            }
        });
        Button btnInsertar = new Button("Insertar");
        Button btnDelete = new Button("Eliminar");
        HBox hbInst = new HBox();
        hbInst.getChildren().addAll(txtInsertar, btnInsertar);
        hbInst.setSpacing(5);
        HBox hbDel = new HBox();
        hbDel.getChildren().addAll(txtDelete, btnDelete);
        hbDel.setSpacing(5);
        Button btnClear = new Button("Clear");
        Pane pnClear = new Pane(btnClear);
        hbOpc.getChildren().addAll(hbInst, hbDel, pnClear);
        hbOpc.setSpacing(30);
        Rectangle optRect = new Rectangle(1366, 40);
        optRect.setOpacity(0.7);
        optRect.setFill(Color.CADETBLUE);
        StackPane optStck = new StackPane();
        optStck.getChildren().addAll(optRect, hbOpc);
        arbol.setMinHeight(200);
        arbol.setMinWidth(1366);
        arbol.setMinWidth(avlTree.height() * 100);
        btnClear.setOnMouseClicked(e -> {
            avlTree = new AVL<>(Integer::compareTo);
            arbol.getChildren().clear();
            Pane avlPane = avlTree.mostrarArbol();
            arbol.getChildren().add(avlPane);
            arbol.setMinWidth(avlTree.height() * 100);
        });
        sp.setContent(arbol);
        sp.setMinHeight(500);
        sp.setMaxHeight(500);
        btnInsertar.setOnAction(e -> {
            insertar();
        });

        btnDelete.setOnMouseClicked(e -> {
            eliminar();
        });

        StackPane pnSalir = new StackPane();
        Label lblSalir = new Label("Salir");
        lblSalir.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        lblSalir.setTextFill(Color.RED);
        pnSalir.getChildren().add(lblSalir);
        pnSalir.setOnMouseClicked(e -> {
            Platform.exit();
        });
        pnSalir.setTranslateY(30);
        pnSalir.setTranslateX(20);

        root.getChildren().addAll(topPane, optStck, sp, pnSalir);

    }

    private void eliminar() {
        Integer num = 0;
        try {
            num = Integer.parseInt(txtDelete.getText());
            txtDelete.setText("");
            txtDelete.requestFocus();
            avlTree.delete(num);
            arbol.getChildren().clear();
            Pane avlPane = avlTree.mostrarArbol();
            if (avlTree.height() <= 7)
                avlPane.setTranslateX(100 * (7 - avlTree.height()));
            arbol.getChildren().add(avlPane);
            arbol.setMinWidth(avlTree.height() * 100);

        } catch (Exception ex) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText("Por favor ingrese un numero valido");
            alerta.showAndWait();
        }

    }

    private void insertar() {
        Integer num = 0;
        try {
            num = Integer.parseInt(txtInsertar.getText());
            txtInsertar.setText("");
            txtInsertar.requestFocus();
            avlTree.insert(num);

            arbol.getChildren().clear();
            Pane avlPane = avlTree.mostrarArbol();
            if (avlTree.height() <= 7)
                avlPane.setTranslateX(100 * (7 - avlTree.height()));
            arbol.getChildren().add(avlPane);
            arbol.setMinWidth(avlTree.height() * 100);
        } catch (Exception ex) {
            // Si la tecla que inserto no es ningun numero
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("WOOPS!");
            alert.setContentText("Por favor ingrese un numero valido.");
            alert.showAndWait();
        }
    }

    public Pane getRoot() {
        return root;
    }
}
