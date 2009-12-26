package org.kyotogtug.client;

import java.util.List;

import org.cobogw.gwt.waveapi.gadget.client.StateUpdateEvent;
import org.cobogw.gwt.waveapi.gadget.client.StateUpdateEventHandler;
import org.cobogw.gwt.waveapi.gadget.client.WaveGadget;
import org.kyotogtug.client.data.Node;
import org.kyotogtug.client.data.NodeParser;
import org.kyotogtug.client.data.NodeUtility;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.widgetideas.graphics.client.GWTCanvas;

@com.google.gwt.gadgets.client.Gadget.ModulePrefs(title = "SimpleGadget", author = "yournamehere", author_email = "yournamehere@gmail.com", height = 500)
public class MindMapGadget extends WaveGadget<UserPreferences> {

    /** このガジェットのタイトル */
    private static final String TITLE = "Mind Map version 0.8";

    /** ルートノード */
    private Node rootNode;

    /** 選択中のノード */
    private Node selectionNode;

    public Node getSelectionNode() {
        return selectionNode;
    }

    public void setSelectionNode(Node selectionNode) {
        this.selectionNode = selectionNode;
    }

    /** マインドマップを描画するCanvas */
    private MindMapCanvas gwtCanvas;

    /** 新しいノードのタイトルを入力するためのテキストボックス */
    private TextBox nodeTitleTextBox = new TextBox();

    /** 投稿ボタン */
    private Button submitButton = new Button();

    /** ノードの削除ボタン */
    private Button deleteButton = new Button();

    /** リセットボタン */
    private Button resetButton = new Button();

    /** Debugテキストエリア */
    private TextArea textArea;

    @Override
    protected void init(UserPreferences preferences) {
        VerticalPanel vpanel = new VerticalPanel();
        HorizontalPanel hvpanel = new HorizontalPanel();

        //canvas = new MMCanvas();
        gwtCanvas = new MindMapCanvas(this);

        submitButton.setText("Submit");
        submitButton.addClickHandler(new SubmitClickHandler(this));

        deleteButton.setText("Delete");
        deleteButton.addClickHandler(new DeleteClickHandler(this));

        resetButton.setText("Reset");
        resetButton.addClickHandler(new ClickHandler() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void onClick(ClickEvent event) {
                initRootNode();
                saveToSharedState();
            }
        });

        textArea = new TextArea();
        textArea.setCharacterWidth(40);
        textArea.setVisibleLines(10);
        textArea.setText("ほがほが\nほがふ");

        vpanel.add(new Label(TITLE));
        ScrollPanel scroll = new ScrollPanel(gwtCanvas);
        scroll.setSize("800", "300");
        vpanel.add(scroll);
        vpanel.add(hvpanel);
        vpanel.add(textArea);
        hvpanel.add(nodeTitleTextBox);
        hvpanel.add(submitButton);
        hvpanel.add(deleteButton);
        hvpanel.add(resetButton);

        RootPanel.get().add(vpanel);
        //debug();
        getWave().addStateUpdateEventHandler(new StateUpdateEventHandler() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void onUpdate(StateUpdateEvent event) {
                rootNode = new NodeParser().getRootNodeFromSharedState(getWave().getState());
                if (rootNode == null) {
                    initRootNode();
                }
                draw();
            }
        });
    }

    private static native void canvasFillText(GWTCanvas c, String str,
                                              double x, double y)/*-{
        var impl = c.@com.google.gwt.widgetideas.graphics.client.GWTCanvas::impl;
        (impl.@com.google.gwt.widgetideas.graphics.client.impl.GWTCanvasImplDefault::canvasContext).fillText(str,
        x, y);
    }-*/;

    /**
     * マインドマップを描画する
     */
    public void draw() {
        gwtCanvas.clear();
        try {
            LineUpNodes lineUpNodes = new LineUpNodes();
            lineUpNodes.measureNodeSizes(rootNode);
            lineUpNodes.lineUp(rootNode, this);
            gwtCanvas.drawMap(rootNode);
        } catch (JavaScriptException e) {
            log("draw時に例外 " + e.getMessage());
        }
        log("\n----------------\n");
        List<Node> nodeList = NodeUtility.getAllNodeList(rootNode);
        for (Node tmpNode : nodeList) {
            log(tmpNode.getNodeId() + ":" + tmpNode.getText());
        }
    }

    /**
     * RootNodeの初期化
     */
    private void initRootNode() {
        log("initRootNode()");
        rootNode = new Node();
        rootNode.setNodeId(0);
        rootNode.setText("Wave");
    }

    /**
     * 現状の状態をSharedStateに保存
     */
    public void saveToSharedState() {
        log("saveToSharedState()");
        new NodeParser().saveToSharedState(getWave().getState(), rootNode);
    }

    /**
     * デバッグ
     */
    private void debug() {
        rootNode = new Node();
        Node node1 = new Node();
        Node node2 = new Node();
        Node node1_1 = new Node();
        Node node1_2 = new Node();

        rootNode.setNodeId(1);
        rootNode.setText("ROOT");
        rootNode.setBlipId("BLIP1");
        rootNode.setX(50);
        rootNode.setY(50);
        rootNode.setWidth(50);
        rootNode.setHeight(50);

        node1.setNodeId(2);
        node1.setText("NODE1");
        node1.setBlipId("BLIP2");
        node1.setX(100);
        node1.setY(50);
        node1.setWidth(50);
        node1.setHeight(50);

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

        node1_2.setNodeId(5);
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
        log(xml);

        /* 整列テスト */
        LineUpNodes lineUpNodes = new LineUpNodes();
        log("LineUpを実行中");
        try {
            lineUpNodes.lineUp(rootNode, this);
        } catch (Exception e) {
            log(e.getMessage());
        }
        log("LineUpの結果を表示中");
        String xmlStr;
        xmlStr = parser.toString(rootNode);
        log(xmlStr);

        Node resultRootNode = parser.parseNode(xml);
        log("\n----------------\n" + parser.toString(resultRootNode));
        List<Node> nodeList = NodeUtility.getAllNodeList(rootNode);
        for (Node tmpNode : nodeList) {
            log(tmpNode.getText());
        }

        // 次のID生成のテスト
        log("-----------------------");
        log("nextId=" + NodeUtility.nextNodeId(rootNode));

        // クリック位置のノード取得のテスト
        int clickX = 55;
        int clickY = 55;
        Node resultNode = NodeUtility.getNodeByPotision(rootNode,
                                                        clickX,
                                                        clickY);
        if (resultNode == null) {
            log(clickX + " , " + clickY + " のノードはROOTだけど取得できなかった");
        } else {
            log(clickX + " , " + clickY + " = " + resultNode.getText());
        }

        clickX = 105;
        clickY = 55;
        resultNode = NodeUtility.getNodeByPotision(rootNode, clickX, clickY);
        if (resultNode == null) {
            log(clickX + " , " + clickY + " のノードはNODE1だけど取得できなかった");
        } else {
            log(clickX + " , " + clickY + "= " + resultNode.getText());
        }

        clickX = 155;
        clickY = 55;
        resultNode = NodeUtility.getNodeByPotision(rootNode, clickX, clickY);
        if (resultNode == null) {
            log(clickX + " , " + clickY + "のノードは無いので取得できなくてOK");
        } else {
            log(clickX + " , " + clickY + "のノードが間違って取得している！ ERROR= "
                    + resultNode.getText());
        }

        // 描画テスト
        log("draw before");
        new LineUpNodes().lineUp(rootNode, this);
        //canvas.drowMap(rootNode);
        log("draw after");

    }

    /**
     * @return the rootNode
     */
    public Node getRootNode() {
        return rootNode;
    }

    /**
     * ノードIDからNodeを検索するメソッド
     * @param nodeId
     * @return Node
     */
    public Node findNode(int nodeId) {
        if (rootNode != null) {
            for (Node aNode : NodeUtility.getAllNodeList(rootNode)) {
                if (aNode.getNodeId() == nodeId) {
                    return aNode;
                }
            }
        }
        return null;
    }

    /**
     * @return the nodeTitleTextBox
     */
    public TextBox getNodeTitleTextBox() {
        return nodeTitleTextBox;
    }

    /**
     * デバッグ用テキストエリアにログを表示する
     * @param text
     */
    public void log(String text) {
        String log = textArea.getText();
        log = log + text + "\n";
        textArea.setText(log);
    }
}
