/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.client;


import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGPathElement;
import org.vectomatic.dom.svg.OMSVGPathSegList;
import org.vectomatic.dom.svg.utils.SVGConstants;


/**
 *
 * @author oyouji
 */
public class SvgTriangle {

        // x,y は頂点の座標です。
    private String id;
    private float x;
    private float y;
    private int height;
    private float width;

    private int gx;
    private int gy;
    private int clickX;
    private int clickY;
    private String color;

    private boolean moveFlag;
    private OMSVGPathElement element;


    static int max = 0;//IDの状態
    private int colorInt=0;
    private int widthInt=0;

    int[] widths = new int[]{
               20,40,60,80,100
            };




    SvgTriangle(OMSVGDocument doc, float _x, float _y) {

        this.x=_x;
        this.y=_y;

        float defaultWidth = 60f;
        element = doc.createSVGPathElement();
        element.setAttribute("stroke", "black");
        element.setAttribute("fill", "red");
        OMSVGPathSegList segs = element.getPathSegList();
        //      segs.appendItem(path.cre
        segs.appendItem(element.createSVGPathSegMovetoAbs(_x, _y));
        segs.appendItem(element.createSVGPathSegLinetoAbs(_x-defaultWidth/2, _y+defaultWidth/2));
        segs.appendItem(element.createSVGPathSegLinetoAbs(_x+defaultWidth/2, _y+defaultWidth/2));
        segs.appendItem(element.createSVGPathSegClosePath());

        id = "triangle" + String.valueOf(max);
        element.setAttribute("id", id);
        max++;
        width= defaultWidth;


        // Set a mousedown event handler
        element.addMouseDownHandler(new MouseDownHandler() {

            final String[] colors = new String[]{
                SVGConstants.CSS_RED_VALUE,
                SVGConstants.CSS_BLUE_VALUE,
                SVGConstants.CSS_GREEN_VALUE,
                SVGConstants.CSS_YELLOW_VALUE,
                SVGConstants.CSS_PURPLE_VALUE,
                SVGConstants.CSS_FUCHSIA_VALUE,
                SVGConstants.CSS_LIME_VALUE,
                SVGConstants.CSS_OLIVE_VALUE,
                SVGConstants.CSS_NAVY_VALUE,
                SVGConstants.CSS_TEAL_VALUE,
                SVGConstants.CSS_AQUA_VALUE,
                SVGConstants.CSS_MAROON_VALUE,
                SVGConstants.CSS_GRAY_VALUE,
                SVGConstants.CSS_BLACK_VALUE,
                SVGConstants.CSS_SILVER_VALUE,
            };

            //マウスクリック時
            @Override
            public void onMouseDown(MouseDownEvent event) {

                //シフトキーを押しながら
                if (event.isShiftKeyDown()) {

                    if(colorInt==14){
                        colorInt=-1;
                    }

                    colorInt++;
                    //色を変える
                    setColor(colors[colorInt]);

                }
                //コントロールキーを押しながら
                else if(event.isControlKeyDown()){
                    if(widthInt==4){
                        widthInt=0;
                    }
                    //大きさを変える
                    widthInt++;
                    setWidth(widths[widthInt]);

                } else {
                    //移動処理

                    moveFlag = true;
//                    String color = getCircleColor(element);
//                    while (color.equals(getCircleColor(element))) {
//                        setCircleColor(element, colors[Random.nextInt(colors.length)]);
//                    }

                    clickX = event.getClientX();
                    clickY = event.getClientY();

                    //circle.setAttribute("cy", "100");
                }
            }
        });


        element.addMouseUpHandler(new MouseUpHandler() {

            public void onMouseUp(MouseUpEvent event) {
                moveFlag = false;


            }
        });

        element.addMouseMoveHandler(new MouseMoveHandler() {

            int _x, _y;

            public void onMouseMove(MouseMoveEvent event) {
                if (moveFlag == true) {

                    //<<未実装>>

//                    //元の位置
                    float cx = x;
                    float cy = y;
//                    //現在クリックした位置
                    _x = event.getClientX();
                    _y = event.getClientY();

                    setPosition(cx + (_x - clickX),cy + (_y - clickY));

                    //element.setAttribute("cx", String.valueOf(cx + (_x - clickX)));
                    //element.setAttribute("cy", String.valueOf(cy + (_y - clickY)));

                    //setX(cx + (_x - clickX));
                    //setY(_y - clickY);

                    clickX = _x;
                    clickY = _y;

                    //text.setAttribute("y","100");
                    //text.getChildNodes().getItem(0).setNodeValue(String.valueOf(x) + "," + String.valueOf(y));
                    // drawText(String.valueOf(x)+","+String.valueOf(y),500,500);
                }
            }
        });




    }

    /**
     * @return the radius
     */
    public float getWidth() {
        return width;
    }

    /**
     * @param radius the radius to set
     */
    public void setWidth(int _width) {

        this.width = _width;
        element.setAttribute("d", "");
        OMSVGPathSegList segs = element.getPathSegList();

        //      segs.appendItem(path.cre
        segs.appendItem(element.createSVGPathSegMovetoAbs(x, y));
        segs.appendItem(element.createSVGPathSegLinetoAbs(x-width/2, y+width/2));
        segs.appendItem(element.createSVGPathSegLinetoAbs(x+width/2, y+width/2));
        segs.appendItem(element.createSVGPathSegClosePath());



    }

    public void setPosition(float _x, float _y){

        element.setAttribute("d", "");
        OMSVGPathSegList segs = element.getPathSegList();

        this.x=_x;
        this.y=_y;

        //      segs.appendItem(path.cre
        segs.appendItem(element.createSVGPathSegMovetoAbs(x, y));
        segs.appendItem(element.createSVGPathSegLinetoAbs(x-width/2, y+width/2));
        segs.appendItem(element.createSVGPathSegLinetoAbs(x+width/2, y+width/2));
        segs.appendItem(element.createSVGPathSegClosePath());


    }


    /**
     * @return the element
     */
    public OMSVGPathElement getElement() {
        return element;
    }

    /**
     * @param element the element to set
     */
    public void setElement(OMSVGPathElement element) {
        this.element = element;
    }


    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
        element.setAttribute("fill", color);

    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the gx
     */
    public float getGx() {
        return x;
    }

    /**
     * @param gx the gx to set
     */
    public void setGx(int gx) {
        this.gx = gx;
    }

    /**
     * @return the gy
     */
    public float getGy() {
        return y;
    }

    /**
     * @param gy the gy to set
     */
    public void setGy(int gy) {
        this.gy = gy;
    }

}
