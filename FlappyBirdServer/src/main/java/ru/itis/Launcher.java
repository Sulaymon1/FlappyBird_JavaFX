package ru.itis;

import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import java.util.Scanner;

/**
 * Date 03.06.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class Launcher {
    public static void main(String[] args) {
        Server server = new Server("localhost", 8080, "/ws", ServerEndPoint.class);
        try {
            server.start();
            System.out.println("Press any key to stop the server..");
            new Scanner(System.in).nextLine();
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        }
    }
}
