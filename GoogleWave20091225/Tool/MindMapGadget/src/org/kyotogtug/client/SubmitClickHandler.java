package org.kyotogtug.client;

import org.kyotogtug.client.data.Node;
import org.kyotogtug.client.data.NodeUtility;

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
            Node target = gadget.findNode(nodeId);
            if (target == null) {
                Window.alert("target is null");
            } else {
                Window.alert("target:" + target.getNodeId() + ":"
                        + target.getText());
                Node newNode = new Node();
                newNode.setNodeId(NodeUtility.nextNodeId(root));
                newNode.setText(gadget.getNodeTitleTextBox().getText());
                target.addChildNode(newNode);
                gadget.saveToSharedState();
            }
        } catch (NumberFormatException e) {}
    }

    private Node findNode(Node parent, int nodeId) {
        if (parent != null) {
            for (Node aNode : NodeUtility.getAllNodeList(parent)) {
                if (aNode.getNodeId() == nodeId) {
                    return aNode;
                }
            }
        }
        return null;
    }
}
