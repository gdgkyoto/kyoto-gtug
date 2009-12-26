/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MindMapGadget.src.org.kyotogtug.client;

import com.google.gwt.core.client.JavaScriptObject;
//import com.google.gwt.user.client.ui.Mouse
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.canvas.client.Canvas;
import com.google.gwt.user.client.Window;

/**
 *
 * 　とりあえず、MouseListerを実装してますが、本番では、これはやめようかと思います。
 *
 *
 * @author ochi
 */
public class MMCanvas extends Canvas implements MouseListener {

    JavaScriptObject context;
    RootPanel root;



    /**
     * 　コンストラクタ
     * 　ここも本番は修正が必要（RootPanelをここで取ってくる必要はないか）
     *
     */
    public MMCanvas(RootPanel rp, int width, int height) {


        this.addMouseListener(this);


        Node node1 = new Node();

        node1.setText("root");
        node1.setX(150);
        node1.setY(150);
        


        this.root = rp;
        //super(width, height);
        root.add(this);
        context = getCanvasContext();
        fillText("テすとー", 20, 20);
        this.drawNode("あいうえお", 50, 50);

    }

    public void drowEdge(Edge edge){
        Node fromNode=edge.getFromNode();
        Node toNode=edge.getToNode();

        //TODO 右側にある場合
        if((fromNode.getX()-toNode.getX())>0){
            


        }else{// TODO 左側にある場合



        }






    }
/**
 *  TODO drowMapdクラス
 *
 * @param rootNode
 */

    public void drowMap(Node rootNode){


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

    public void eraseNode(int x, int y){
        int w=6;
        int height = 10;
        this.setFillStyle("rgb(255,255,255)");
        this.fillRect(x-w, y-w-height, 20,20);

    }

    public void drawEdge(){


    }
    public int drawNode(String text,int x, int y) {

        String tWidth,tHeight;
        int w=6;
        int xx=x,yy=y;
        int width,height;

        //文字の長さを調べる
        tWidth= textWidth(context,text);
       
        width = Integer.parseInt(tWidth);
        height = 10;
        //this.setGlobalAlpha(0.5);
        this.setFillStyle("rgba(192,80,77,0.5");

        //gcanvas.setFillStyle(Color.RED);
         this.fillRect(x-w, y-w-height, width+2*w, 2*w+height);
         //this.setGlobalAlpha(1.0);
         this.setFillStyle("rgb(255,255,255)");
         this.fillText(text, x, y);

         


//        //線を引く（時計の反対回り）
//        this.beginPath();
//       //初期値移動
//        xx=xx-w;
//        yy =yy+w;
//        this.moveTo(xx, yy);
//        //下線（右へ）
//        xx=xx+width+2*w;
//        this.lineTo(xx, yy);
//        //縦線（右）
//        yy=yy-(2*w+height);
//        this.lineTo(xx, yy);
//        //上線（左へ）
//        xx=x-w;
//        this.lineTo(xx, yy);
//        //縦線
//        yy=y+w;
//        this.lineTo(xx, yy);
//        this.stroke();

        //        Node node = new Node();

        // 座標を決めるアルゴリズム
  //      node.x = 10 + 380*Math.random();
	//node.y = 10 + 380*Math.random();
        //TODO 未実装
//        node.lbl = lbl;
//	nodes[nnodes] = n;
//	return nnodes++;
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
    protected native void dString(JavaScriptObject ctx,
            String str, int x, int y) /*-{
    ctx.fillText(str, x, y);
    }-*/;

    public int getTextWidth(String text){
       String slength= this.textWidth(context, text);
       return(Integer.parseInt(slength));
   }


    /**
     * JSNIでmeasureTextを呼び出す
     *
     */
    protected native String textWidth(JavaScriptObject ctx,String str) /*-{
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

    public void onMouseDown(Widget sender, int x, int y) {


        this.eraseNode(x, y);
//
//     String i;
//    fillText("あああ",x,y);
//    i=mText(context,"あああああ");
//    Window.alert(i);
//

	//TODO クリック位置を探っていく（未実装）
//        for (int i = 0 ; i < nnodes ; i++) {
//	    Node n = nodes[i];
//	    double dist = (n.x - x) * (n.x - x) + (n.y - y) * (n.y - y);
//	    if (dist < bestdist) {
//		pick = n;
//		bestdist = dist;
//	    }
//	}



    }

    public void onMouseEnter(Widget sender) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onMouseLeave(Widget sender) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onMouseMove(Widget sender, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onMouseUp(Widget sender, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
