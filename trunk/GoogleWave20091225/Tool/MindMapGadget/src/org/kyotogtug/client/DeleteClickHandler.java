package org.kyotogtug.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;

public class DeleteClickHandler implements ClickHandler {

    private MindMapGadget gadget;

    public DeleteClickHandler(MindMapGadget gadget) {
        super();
        this.gadget = gadget;
    }

    @Override
    public void onClick(ClickEvent event) {
        Window.alert(gadget.getNodeIdTextBox().getText());
    }

}
