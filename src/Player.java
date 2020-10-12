import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Player extends Application {

    private static Socket socket;
    private static DataOutputStream dos;
    private static Scanner scn;

    private final static int PORT = 9000;
    private final static String IP = "127.0.0.1";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        scn = new Scanner(System.in);

        socket = new Socket(IP, PORT);
        dos = new DataOutputStream(socket.getOutputStream());

        //TODO: make new scene for key press handler
        Scene scene = new Scene(new Group(new Canvas(100,100)));
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(e -> {
            System.out.println(e.getCode().toString() + " was pressed");
            try {
                dos.writeUTF(e.getCode().toString());
            } catch (IOException i) {
                i.printStackTrace();
            }
        });

        scene.setOnKeyReleased(e -> {
            try {
                dos.writeUTF("R" + e.getCode().toString());
            } catch (IOException i){
                i.printStackTrace();
            }
        });

    }
}