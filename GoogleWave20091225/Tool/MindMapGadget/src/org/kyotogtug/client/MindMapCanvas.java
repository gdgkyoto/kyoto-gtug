package org.kyotogtug.client;

import java.util.Iterator;
import java.util.List;

import org.kyotogtug.client.data.Edge;
import org.kyotogtug.client.data.Node;
import org.kyotogtug.client.data.NodeUtility;

import com.google.gwt.user.client.Event;
import com.google.gwt.widgetideas.graphics.client.Color;
import com.google.gwt.widgetideas.graphics.client.GWTCanvas;

public class MindMapCanvas extends GWTCanvas {
	
	private MindMapGadget mindMapGadget;
	
	public MindMapCanvas( MindMapGadget mindMapGadget ){
		super(500, 300);
		
		this.mindMapGadget = mindMapGadget;
		sinkEvents(Event.ONCLICK);
		sinkEvents(Event.ONMOUSEDOWN);
		sinkEvents(Event.ONMOUSEMOVE);
		sinkEvents(Event.ONMOUSEUP);
	}
	
	@Override
	public void onBrowserEvent(Event event) {
		//mindMapGadget.log("onBrowserEvent");
		super.onBrowserEvent(event);
        int x = event.getClientX() - getAbsoluteLeft();
        int y = event.getClientY() - getAbsoluteTop();
        switch (event.getTypeInt()) {
            case Event.ONMOUSEDOWN:
            	mindMapGadget.log("ONMOUSEDOWN X="+x +" Y="+y);
            	Node node = NodeUtility.getNodeByPotision(mindMapGadget.getRootNode(), x, y);
            	if( node != null ){
            		mindMapGadget.log("クリックされたノードは "+node.getText()+" です");
            		mindMapGadget.setSelectionNode(node);
            		mindMapGadget.draw();
            	}
                break;
            case Event.ONMOUSEMOVE:
            	//mindMapGadget.log("ONMOUSEMOVE X="+x +" Y="+y);
                break;
            case Event.ONMOUSEUP:
            	mindMapGadget.log("ONMOUSEUP X="+x +" Y="+y);
                break;
            case Event.ONCLICK:
            	mindMapGadget.log("CLICK X="+x +" Y="+y);
            break;		
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
            fromY = fromNode.getY() + (fromNode.getHeight() / 2);
            //toNodeについて
            toX = toNode.getX();
            toY = toNode.getY() + (toNode.getHeight() /2);

            //初期値移動
            mindMapGadget.log("EDGE - FROM(" + fromX + "," + fromY + ")");
            mindMapGadget.log("EDGE - TO(" + toX + "," + toY + ")");
            drawArrow(fromX, fromY, toX, toY);
            //this.beginPath();
            //this.moveTo(fromX, fromY);
            //this.lineTo(toX, toY);
            //this.stroke();

        } else {// TODO 左側にある場合

            fromX = fromNode.getX();
            fromY = fromNode.getY() + (fromNode.getHeight() / 2);
            toX = toNode.getX() + toNode.getWidth();
            toY = toNode.getY() + (toNode.getHeight() /2);

            mindMapGadget.log("EDGE - FROM(" + fromX + "," + fromY + ")");
            mindMapGadget.log("EDGE - TO(" + toX + "," + toY + ")");
            this.beginPath();
            this.moveTo(fromX, fromY);
            this.lineTo(toX, toY);
            this.stroke();
        }

    }
    
    /**
     * fromからtoへ矢印を引く
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     */
    private void drawArrow(int fromX , int fromY , int toX , int toY){
    	for(int i = -7; i < 7; i++){
            this.beginPath();
            this.moveTo(fromX, fromY + i);
            this.lineTo(toX, toY);
            this.stroke();
    	}
    }
    
    public int drawNode(Node node) {

        drawNode(node.getText(),
                 node.getX(),
                 node.getY(),
                 node.getWidth(),
                 node.getHeight(),
                 node);

        return 0;
    }
    
    private int drawNode(String text, int x, int y, int width, int height , Node node) {

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
        if(	mindMapGadget.getSelectionNode() != null &&
        	mindMapGadget.getSelectionNode().getNodeId() == node.getNodeId()){
        	setFillStyle(Color.SKY_BLUE);
        }else{
        	setFillStyle(Color.LIGHTGREY);
        }
        //rect(x - w, y - w - height, width + 2 * w, 2 * w + height);
        //rect(20,20,20,20);
        fillRect(x, y, width, height);
        setFillStyle(Color.BLACK);
        canvasSetFont(this, "bold 22px 'Monotype Corsiva'");
        canvasFillText(this ,text , x , y + 20 );

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
	
	private static native void canvasSetFont(GWTCanvas c, String f)/*-{
		var impl = c.@com.google.gwt.widgetideas.graphics.client.GWTCanvas::impl;
		(impl.@com.google.gwt.widgetideas.graphics.client.impl.GWTCanvasImplDefault::canvasContext).font = f;
	}-*/;
}
