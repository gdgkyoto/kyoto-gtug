package org.kyotogtug.client;

import org.kyotogtug.client.data.Node;
import org.kyotogtug.client.data.NodeUtility;

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
        try {
            //int nodeId = Integer.parseInt(gadget.getNodeIdTextBox().getText());
            Node target = gadget.getSelectionNode();
            if (target == null) {
                //Window.alert("target not found:" + nodeId);
            } else {
                target.getParentNode().deleteChildNode(target);
                gadget.saveToSharedState();
            }
        } catch (NumberFormatException e) {}
        gadget.draw();
    }

}
