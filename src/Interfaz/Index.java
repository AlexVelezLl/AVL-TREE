/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author AlexVelezLl
 */
public class Index extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        InterfazArbol ia = new InterfazArbol();
        Scene sc = new Scene(ia.getRoot(), 1366, 768);
        primaryStage.setScene(sc);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
