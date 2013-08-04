package br.com.javamagazine;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author José Henrique Ventura
 */
@ServerEndpoint(value="/epalertproblemas")
public class EPAlertProbleman {
    
    private static final Logger LOG = Logger.getLogger(EPAlertProbleman.class.getName());
    
    @OnOpen()
    public void openConnection(final Session session) throws IOException{
       LOG.log(Level.INFO, "User connected : {0}", session.getId());
    }
    
    @OnClose
    public void closeConnection(final Session ssn, CloseReason cr){
        LOG.log(Level.INFO, "Close connection : {0} {1}",new Object[]{cr.getCloseCode(),cr.getReasonPhrase()});
    }
    
    @OnError
    public void errorConnection(Throwable th){
       LOG.log(Level.INFO, "Error connection : {0}",th.getMessage());
    }
    
    @OnMessage
    public void messageHandler(final Session currentSession, String coordinates){        
        LOG.log(Level.INFO, "Coordinates : {0}, sending by User : {1}", new Object[]{coordinates, currentSession.getId()});
        Set<Session> sessions = currentSession.getOpenSessions();
        
        for(Session ssn : sessions){
             //Verificação para não eviar as coordenadas para sí mesmo.
             if(!ssn.getId().equals(currentSession.getId())){
                try {
                    Basic basic = ssn.getBasicRemote();
                    basic.sendText(coordinates);
                    
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE,"Erro ao enviar as coordenadas", ex);
                }
            }
       }
    }
}
