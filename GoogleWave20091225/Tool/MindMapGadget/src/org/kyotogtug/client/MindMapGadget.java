package org.kyotogtug.client;

import org.cobogw.gwt.waveapi.gadget.client.StateUpdateEvent;
import org.cobogw.gwt.waveapi.gadget.client.StateUpdateEventHandler;
import org.cobogw.gwt.waveapi.gadget.client.WaveGadget;
import org.kyotogtug.client.data.Node;
import org.kyotogtug.client.data.NodeParser;
import org.mortbay.log.Log;

import gwt.canvas.client.Canvas;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

@com.google.gwt.gadgets.client.Gadget.ModulePrefs(title = "SimpleGadget", author = "yournamehere", author_email = "yournamehere@gmail.com", height = 500)
public class MindMapGadget extends WaveGadget<UserPreferences> {

    /** マインドマップを描画するCanvas */
    private Canvas canvas;

    /** ノードのIDを入力するためのテキストボックス */
    private TextBox nodeIdTextBox = new TextBox();

    /** 新しいノードのタイトルを入力するためのテキストボックス */
    private TextBox nodeTitleTextBox = new TextBox();

    /** デバッグボタン */
    private Button debugButton = new Button();

    /** 投稿ボタン */
    private Button submitButton = new Button();

    /** ノードの削除ボタン */
    private Button deleteButton = new Button();

    /** Debugテキストエリア */
    private TextArea textArea;

    @Override
    protected void init(UserPreferences preferences) {
        VerticalPanel vpanel = new VerticalPanel();
        HorizontalPanel hvpanel = new HorizontalPanel();

        canvas = new Canvas();

        debugButton.setText("Debug");
        submitButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                debug();
            }
        });

        submitButton.setText("Submit");
        submitButton.addClickHandler(new SubmitClickHandler(this));

        deleteButton.setText("Delete");
        deleteButton.addClickHandler(new DeleteClickHandler(this));

        textArea = new TextArea();
        textArea.setCharacterWidth(80);
        textArea.setVisibleLines(50);
        textArea.setText("ほがほが\nほがふ");

        vpanel.add(canvas);
        vpanel.add(hvpanel);
        vpanel.add(textArea);
        hvpanel.add(nodeIdTextBox);
        hvpanel.add(nodeTitleTextBox);
        hvpanel.add(submitButton);
        hvpanel.add(deleteButton);
        hvpanel.add(debugButton);

        RootPanel.get().add(vpanel);
        debug();

        getWave().addStateUpdateEventHandler(new StateUpdateEventHandler() {
            @Override
            public void onUpdate(StateUpdateEvent event) {
                draw();
            }
        });
    }

    /**
     * マインドマップを描画する
     */
    private void draw() {

    }

    private void debug() {
        Node rootNode = new Node();
        Node node1 = new Node();
        Node node2 = new Node();
        Node node1_1 = new Node();
        Node node1_2 = new Node();

        rootNode.setNodeId(1);
        rootNode.setText("ROOT");
        rootNode.setBlipId("BLIP1");
        rootNode.setX(1);
        rootNode.setY(2);
        rootNode.setWidth(3);
        rootNode.setHeight(4);

        node1.setNodeId(2);
        node1.setText("NODE1");
        node1.setBlipId("BLIP2");
        node1.setX(1);
        node1.setY(2);
        node1.setWidth(3);
        node1.setHeight(4);

        node2.setNodeId(3);
        node2.setText("NODE2");
        node2.setBlipId("BLIP3");
        node2.setX(1);
        node2.setY(2);
        node2.setWidth(3);
        node2.setHeight(4);

        node1_1.setNodeId(4);
        node1_1.setText("NODE1-1");
        node1_1.setBlipId("BLIP4");
        node1_1.setX(1);
        node1_1.setY(2);
        node1_1.setWidth(3);
        node1_1.setHeight(4);

        node1_2.setNodeId(4);
        node1_2.setText("NODE1-2");
        node1_2.setBlipId("BLIP5");
        node1_2.setX(1);
        node1_2.setY(2);
        node1_2.setWidth(3);
        node1_2.setHeight(4);

        rootNode.addChildNode(node1);
        rootNode.addChildNode(node2);

        node1.addChildNode(node1_1);
        node1.addChildNode(node1_2);

        NodeParser parser = new NodeParser();
        String xml;
        xml = parser.toString(rootNode);
        textArea.setText(xml);

        Node resultRootNode = parser.parseNode(xml);
        textArea.setText(xml + "\n----------------\n"
                + parser.toString(resultRootNode));

    }

    /**
     * @return the nodeIdTextBox
     */
    public TextBox getNodeIdTextBox() {
        return nodeIdTextBox;
    }

    /**
     * @return the nodeTitleTextBox
     */
    public TextBox getNodeTitleTextBox() {
        return nodeTitleTextBox;
    }

}
