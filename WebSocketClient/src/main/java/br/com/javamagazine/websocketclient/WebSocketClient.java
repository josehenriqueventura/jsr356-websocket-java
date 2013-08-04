/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.javamagazine.websocketclient;

import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;

/**
 *
 * @author José Henrique Ventura
 */
@ClientEndpoint
public class WebSocketClient {
    private static final Logger LOG = Logger.getLogger(WebSocketClient.class.getName());

    @OnError
    public void onError(Throwable t) {
       LOG.info("Client message : " + t.getMessage());

    }

    @OnClose
    public void onClose(CloseReason cr) {
        LOG.info("Closed connection : " + cr.getReasonPhrase());
    }
}
