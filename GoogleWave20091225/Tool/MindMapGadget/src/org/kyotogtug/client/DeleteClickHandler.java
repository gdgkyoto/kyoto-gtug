package org.kyotogtug.client;

import org.kyotogtug.client.data.Node;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class DeleteClickHandler implements ClickHandler {

    private MindMapGadget gadget;

    public DeleteClickHandler(MindMapGadget gadget) {
        super();
        this.gadget = gadget;
    }

    @Override
    public void onClick(ClickEvent event) {
        Node target = gadget.getSelectionNode();
        if (target == null) {
            //Window.alert("target not found:" + nodeId);
        } else {
            target.getParentNode().deleteChildNode(target);
            gadget.saveToSharedState();
            gadget.setSelectionNode(null);
        }
    }
}
