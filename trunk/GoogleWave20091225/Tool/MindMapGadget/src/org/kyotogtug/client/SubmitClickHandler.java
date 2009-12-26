package org.kyotogtug.client;

import org.kyotogtug.client.data.Node;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

public class SubmitClickHandler implements ClickHandler {

    private MindMapGadget gadget;

    public SubmitClickHandler(MindMapGadget gadget) {
        super();
        this.gadget = gadget;
    }

    @Override
    public void onClick(ClickEvent event) {
        try {
            int nodeId = Integer.parseInt(gadget.getNodeIdTextBox().getText());
            Window.alert(nodeId + ":" + gadget.getNodeTitleTextBox().getText());
            Node root = gadget.getRootNode();
            Node target = findNode(root, nodeId);
            if (target == null) {
                Window.alert("target is null");
            } else {
                Window.alert("target:" + target.getNodeId() + ":"
                        + target.getText());
            }
        } catch (NumberFormatException e) {}
    }

    private Node findNode(Node parent, int nodeId) {
        if (parent != null) {
            for (Node aNode : parent.getChildrenNode()) {
                if (aNode.getNodeId() == nodeId) {
                    return aNode;
                }
                Node tmpNode = findNode(aNode, nodeId);
                if (tmpNode != null) {
                    return tmpNode;
                }
            }
        }
        return null;
    }
}
