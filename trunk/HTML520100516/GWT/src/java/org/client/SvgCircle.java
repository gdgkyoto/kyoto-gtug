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
import org.vectomatic.dom.svg.OMSVGCircleElement;
import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.utils.SVGConstants;

/**
 *
 * @author oyouji
 */
public class SvgCircle {

    // x,y は中心の座標です。
    private String id;
    private int x;
    private int y;
    private int gx;
    private int gy;
    private int clickX;
    private int clickY;
    private String color;
    private int radius; //半径
    private boolean moveFlag;
    private OMSVGCircleElement element;
    
    
    static int max = 0;//IDの状態
    private int colorInt=0;
    private int radiusInt=0;

    int[] radiuses = new int[]{
               20,40,60,80,100
            };




    SvgCircle(OMSVGDocument doc, float x, float y, float radius, String color) {
        element = doc.createSVGCircleElement(x, y, radius);
        setCircleColor(element, color);
        id = "circle" + String.valueOf(max);
        element.setAttribute("id", id);
        max++;



        // Set a mousedown event handler
        element.addMouseDownHandler(new MouseDownHandler() {

            /*
             *     * black
    * gray
    * silver
    * white
    * maroon
    

             * 
             */
            
            
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
                    if(radiusInt==4){
                        radiusInt=0;
                    }
                    //大きさを変える
                    radiusInt++;
                    setRadius(radiuses[radiusInt]);

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

                    //円の元の位置
                    int cx = Integer.parseInt(element.getAttribute("cx"));
                    int cy = Integer.parseInt(element.getAttribute("cy"));
                    //現在クリックした位置
                    _x = event.getClientX();
                    _y = event.getClientY();
                    element.setAttribute("cx", String.valueOf(cx + (_x - clickX)));
                    element.setAttribute("cy", String.valueOf(cy + (_y - clickY)));
                    setX(cx + (_x - clickX));
                    setY(_y - clickY);
                    clickX = _x;
                    clickY = _y;

//                    //text.setAttribute("y","100");
//                    text.getChildNodes().getItem(0).setNodeValue(String.valueOf(x) + "," + String.valueOf(y));
//                    // drawText(String.valueOf(x)+","+String.valueOf(y),500,500);
                }
            }
        });




    }

    /**
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
        element.setAttribute("r", String.valueOf(radius));
    }

    /**
     * @return the element
     */
    public OMSVGCircleElement getElement() {
        return element;
    }

    /**
     * @param element the element to set
     */
    public void setElement(OMSVGCircleElement element) {
        this.element = element;
    }

    private String getCircleColor(OMSVGCircleElement circle) {

        return circle.getStyle().getSVGProperty(SVGConstants.CSS_FILL_PROPERTY);

    }

    private void setCircleColor(OMSVGCircleElement circle, String color) {
        circle.getStyle().setSVGProperty(SVGConstants.CSS_FILL_PROPERTY, color);
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
        setCircleColor(element, color);
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
    public int getX() {
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
    public int getY() {
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
    public int getGx() {
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
    public int getGy() {
        return y;
    }

    /**
     * @param gy the gy to set
     */
    public void setGy(int gy) {
        this.gy = gy;
    }


}
