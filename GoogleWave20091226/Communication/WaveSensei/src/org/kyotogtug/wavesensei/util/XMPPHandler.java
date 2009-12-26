package org.kyotogtug.wavesensei.util;

import java.io.IOException;
import java.util.logging.ErrorManager;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.MessageType;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

public class XMPPHandler extends Handler{
    private XMPPService service = XMPPServiceFactory.getXMPPService();  
    private Formatter formatter = new SimpleFormatter();
    private String notifyTo;

    public XMPPHandler(String notifyTo){
        this.notifyTo = notifyTo;
    }
    public void close() throws SecurityException {}
    public void flush() {}

    public void publish(LogRecord record) {
        if (!isLoggable(record)) 
            return;
        try {
            String msg = formatter.format(record);
            write(msg);
        } catch (Exception ex) {
            reportError(null, ex, ErrorManager.FORMAT_FAILURE);
            return;
        }
    }
    private void write(String msg) throws IOException{
        JID jid = new JID(notifyTo);  
        Message message = new MessageBuilder()  
            .withMessageType(MessageType.CHAT)  
            .withRecipientJids(jid)  
            .withBody(msg)
            .build();  
        SendResponse resp = service.sendMessage(message);
        SendResponse.Status status = resp.getStatusMap().get(jid);
        if (status != SendResponse.Status.SUCCESS)
            throw new IOException("Failed to send message: " + status);
    }
}