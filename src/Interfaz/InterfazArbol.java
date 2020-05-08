/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    public InterfazArbol() {
        root = new VBox();
        avlTree = new AVL<>(Integer::compareTo);
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
        TextField txtInsertar = new TextField();
        Button btnInsertar = new Button("Insertar");
        TextField txtDelete = new TextField();
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
        StackPane arbol = new StackPane();
        arbol.setMinHeight(200);
        arbol.setMinWidth(1366);
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
            Integer num = 0;
            try {
                num = Integer.parseInt(txtInsertar.getText());
                txtInsertar.setText("");
                txtInsertar.requestFocus();
                avlTree.Insert(num);

                arbol.getChildren().clear();
                Pane avlPane = avlTree.mostrarArbol();
                if (avlTree.height() <= 7)
                    avlPane.setTranslateX(100 * (7 - avlTree.height()));
                arbol.getChildren().add(avlPane);
                arbol.setMinWidth(avlTree.height() * 100);
            } catch (Exception ex) {

            }

        });

        btnDelete.setOnMouseClicked(e -> {
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

            }
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

    public Pane getRoot() {
        return root;
    }
}
