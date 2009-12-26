/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kyotogtug.client;

import gwt.canvas.client.Canvas;

import java.util.Iterator;
import java.util.List;

import org.kyotogtug.client.data.Edge;
import org.kyotogtug.client.data.Node;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * 　とりあえず、MouseListerを実装してますが、本番では、これはやめようかと思います。
 *
 *
 * @author ochi
 */
public class MMCanvas extends Canvas {

    JavaScriptObject context;
    RootPanel root;
    int height = 10;
    private Node rootNode;

    /**
     * 　コンストラクタ
     * 　ここも本番は修正が必要（RootPanelをここで取ってくる必要はないか）
     *
     */
    public MMCanvas() {
        super(400, 400);
        /**
        rootNode = new Node();
        Node node1 = new Node();
        Node node2 = new Node();
        Node node1_1 = new Node();
        Node node1_2 = new Node();

        rootNode.setNodeId(1);
        rootNode.setText("ROOT");
        rootNode.setBlipId("BLIP1");
        rootNode.setX(0);
        rootNode.setY(0);
        rootNode.setWidth(10);
        rootNode.setHeight(10);

        node1.setNodeId(2);
        node1.setText("NODE1");
        node1.setBlipId("BLIP2");
        node1.setX(20);
        node1.setY(20);
        node1.setWidth(10);
        node1.setHeight(10);

        node2.setNodeId(3);
        node2.setText("NODE2");
        node2.setBlipId("BLIP3");
        node2.setX(0);
        node2.setY(20);
        node2.setWidth(10);
        node2.setHeight(10);

        node1_1.setNodeId(4);
        node1_1.setText("NODE1-1");
        node1_1.setBlipId("BLIP4");
        node1_1.setX(20);
        node1_1.setY(0);
        node1_1.setWidth(10);
        node1_1.setHeight(10);

        node1_2.setNodeId(4);
        node1_2.setText("NODE1-2");
        node1_2.setBlipId("BLIP5");
        node1_2.setX(40);
        node1_2.setY(40);
        node1_2.setWidth(10);
        node1_2.setHeight(10);

        rootNode.addChildNode(node1);
        rootNode.addChildNode(node2);

        node1.addChildNode(node1_1);
        node1.addChildNode(node1_2);

        //        context = getCanvasContext();
        //fillText("テすとー", 20, 20);

        //this.drawNode("あいうえお", 50, 50);
        //        this.drawNode(node1);
        //        this.drawNode(node2);
        //        this.drawNode(node3);
        //
        //        this.drowEdge(edge1);
        //         this.drowEdge(edge2);

        this.drowMap(rootNode);
        */
    }

    public void drowEdge(Edge edge) {
        Node fromNode = edge.getFromNode();
        Node toNode = edge.getToNode();

        int fromX, fromY;
        int toX, toY;

        //TODO 右側描画する場合
        if ((fromNode.getX() - toNode.getX()) < 0) {
            //fromNodeについて
            fromX = fromNode.getX() + fromNode.getWidth();
            fromY = fromNode.getY() - fromNode.getHeight() / 2;
            //toNodeについて
            toX = toNode.getX();
            toY = toNode.getY() - toNode.getHeight();

            this.beginPath();
            //初期値移動

            this.moveTo(fromX, fromY);
            this.lineTo(toX, toY);
            this.stroke();

        } else {// TODO 左側にある場合

            fromX = fromNode.getX();
            fromY = fromNode.getY() - fromNode.getHeight() / 2;
            toX = toNode.getX() + toNode.getWidth();
            toY = toNode.getY() - fromNode.getHeight() / 2;

            this.beginPath();
            this.moveTo(fromX, fromY);
            this.lineTo(toX, toY);
            this.stroke();
        }

    }

    /**
     *  TODO drowMapdクラス
     *
     * @param rootNode
     */
    public void drawMap(Node rootNode) {
        this.drawNode(rootNode);
        List elist = rootNode.getChildren();
        Iterator it = elist.iterator();
        while (it.hasNext()) {
            Edge edge = (Edge) it.next();
            Node node = edge.getToNode();
            this.drawNode(node);
            //this.drowEdge(edge);
            drawMap(node);
        }
    }

    int findNode(String lbl) {
        //TODO ノード検索処理
        //        for (int i = 0; i < nnodes; i++) {
        //            if (nodes[i].lbl.equals(lbl)) {
        //                return i;
        //            }
        //        }
        return 0;
    }

    public void eraseNode(int x, int y) {
        int w = 6;

        this.setFillStyle("rgb(255,255,255)");
        this.fillRect(x - w, y - w - height, 20, 20);

    }

    public int drawNode(Node node) {

        drawNode(node.getText(),
                 node.getX(),
                 node.getY(),
                 node.getWidth(),
                 node.getHeight());

        return 0;
    }

    private int drawNode(String text, int x, int y, int width, int height) {

        String tWidth, tHeight;
        //int w = 6;
        int w = 0;
        int xx = x, yy = y;

        //文字の長さを調べる
        //tWidth = textWidth(context, text);

        //width = Integer.parseInt(tWidth);
        //this.setGlobalAlpha(0.5);
        //this.setFillStyle("rgba(192,80,77,0.5");

        //gcanvas.setFillStyle(Color.RED);
        this.fillRect(x - w, y - w - height, width + 2 * w, 2 * w + height);

        //this.setGlobalAlpha(1.0);
        //this.setFillStyle("rgb(255,255,255)");
        //this.fillText(text, x, y);
        return 0;
    }

    /* JSNIでCanvasオブジェクトを取ってくる。。。
     * getElementsByTagNameでキャンバスのコンテキストをとってきてるので
     * 正直、この方法はちょっと怪しい気もしてますが
     * ここがうまくいかないと、JSNIが全く使えない
     */
    protected native JavaScriptObject getCanvasContext() /*-{
        var canvas= $doc.getElementsByTagName('canvas');
        $wnd.ctx= canvas[0].getContext('2d');
        return $wnd.ctx;
    }-*/;

    //JSNIでfillTextを呼び出している
    protected native void dString(JavaScriptObject ctx, String str, int x, int y) /*-{
        ctx.fillText(str, x, y);
    }-*/;

    public int getTextWidth(String text) {
        String slength = this.textWidth(context, text);
        return (Integer.parseInt(slength));
    }

    /**
     * JSNIでmeasureTextを呼び出す
     *
     */
    protected native String textWidth(JavaScriptObject ctx, String str) /*-{
        var tm2=ctx.measureText(str);
        ctx.fillText(tm2.width, 130, 70);
        var i = tm2.width;
        return i;
    }-*/;

    /**
     * fillTextのラッパークラス
     * あえて作らなくてもいい気もしてます。
     */
    public void fillText(String str, int x, int y) {
        dString(context, str, x, y);

    }
}
