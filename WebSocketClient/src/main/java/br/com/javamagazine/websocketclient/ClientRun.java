package br.com.javamagazine.websocketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author José Henrique Ventura
 */
public class ClientRun {

    private static final Logger LOG = Logger.getLogger(ClientRun.class.getName());

    public static void main(String a[]) {

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:8080/websocket-java/epalertproblemas";
        Session session = null;

        try {
            
            session = container.connectToServer(WebSocketClient.class, URI.create(uri));
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            
            LOG.info("\n Digite as coordenadas latitude e longitude separadas por virgula. "
                    + "\n Exemplo : 37.41939029487573, -122.15475082397461.");
            
            String line = reader.readLine();

            LOG.info("Coordenadas : "+line);

            session.getBasicRemote().sendText(line);

        } catch (DeploymentException ex) {
            Logger.getLogger(ClientRun.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientRun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
