package org.kyotogtug.client;

import java.util.Iterator;
import java.util.List;

import org.kyotogtug.client.data.Edge;
import org.kyotogtug.client.data.Node;

import com.google.gwt.widgetideas.graphics.client.Color;
import com.google.gwt.widgetideas.graphics.client.GWTCanvas;

public class MindMapCanvas extends GWTCanvas {
	
	public MindMapCanvas(){

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
            this.drowEdge(edge);
            drawMap(node);
        }
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
        //this.fillRect(x - w, y - w - height, width + 2 * w, 2 * w + height);
        setStrokeStyle(Color.RED);
        rect(x - w, y - w - height, width + 2 * w, 2 * w + height);
        canvasFillText(this ,text , x , y );

        //this.setGlobalAlpha(1.0);
        //this.setFillStyle("rgb(255,255,255)");
        //this.fillText(text, x, y);
        return 0;
    }

	private static native void canvasFillText(GWTCanvas c, String str,
			double x, double y)/*-{
		var impl = c.@com.google.gwt.widgetideas.graphics.client.GWTCanvas::impl;
		(impl.@com.google.gwt.widgetideas.graphics.client.impl.GWTCanvasImplDefault::canvasContext).fillText(str,
		x, y);
	}-*/;
}
