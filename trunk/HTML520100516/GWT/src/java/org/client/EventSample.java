/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import java.util.Collections;


import org.vectomatic.dom.svg.OMSVGCircleElement;
import org.vectomatic.dom.svg.OMSVGDocument;
import org.vectomatic.dom.svg.OMSVGSVGElement;
import org.vectomatic.dom.svg.utils.OMSVGParser;
import org.vectomatic.dom.svg.utils.SVGConstants;
//import org.vectomatic.svg.samples.client.SampleBase;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.vectomatic.dom.svg.OMSVGLength;
import org.vectomatic.dom.svg.OMSVGPathElement;
import org.vectomatic.dom.svg.OMSVGPathSegList;
import org.vectomatic.dom.svg.OMSVGPolylineElement;
import org.vectomatic.dom.svg.OMSVGRectElement;
import org.vectomatic.dom.svg.OMSVGTextElement;

/**
 * Class to demonstrate the SVG event handling
 * @author laaglu
 */
public class EventSample extends Composite {

    private static Element div;
    // private static EventSampleBinder binder = GWT.create(EventSampleBinder.class);
    @UiField
    HTML svgContainer;
    @UiField
    Button btnCircle;
    @UiField
    Button btnSquare;
    @UiField
    Button btnTriangle;
    ArrayList al = new ArrayList();
    OMSVGSVGElement svg;
    OMSVGDocument doc;
    boolean moveFlag = false;
    OMSVGTextElement text;
    int clickX, clickY;

    // interface EventSampleUiBinder extends UiBinder<Widget, EventSample> {
    //  }
    interface EventSampleUiBinder extends UiBinder<Widget, EventSample> {
    }
    private static EventSampleUiBinder uiBinder = GWT.create(EventSampleUiBinder.class);

//    OMSVGPathElement drawTriangle() {
//        OMSVGPathElement path = doc.createSVGPathElement();
//        path.setAttribute("stroke", "black");
//        OMSVGPathSegList segs = path.getPathSegList();
//        //      segs.appendItem(path.cre
//        segs.appendItem(path.createSVGPathSegMovetoAbs(0f, 0f));
//        segs.appendItem(path.createSVGPathSegLinetoAbs(100f, 0f));
//        segs.appendItem(path.createSVGPathSegLinetoAbs(50f, 100f));
//        segs.appendItem(path.createSVGPathSegClosePath());
//        return path;
//    }
    //テキストタグ生成メソッド
    OMSVGTextElement drawText(String str, int x, int y) {
        // Create a text
        text = doc.createSVGTextElement(x, y, OMSVGLength.SVG_LENGTHTYPE_PX, str);

        text.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_BLUE_VALUE);
        return text;
    }

    public EventSample() {



        initWidget(uiBinder.createAndBindUi(this));

        // Cast the document into a SVG document
        div = svgContainer.getElement();
        doc = OMSVGParser.currentDocument();

        //SVGタグの設定（ベース部分）
        svg = doc.createSVGSVGElement();
        // svg.setViewBox(0f, 0f, 300f, 500f);
        svg.getWidth().getBaseVal().setValueAsString("750px");
        svg.getHeight().getBaseVal().setValueAsString("250px");

//        // Create a circle
//        final OMSVGCircleElement circle = doc.createSVGCircleElement(80f, 80f, 40f);
//        circle.getStyle().setSVGProperty(SVGConstants.CSS_STROKE_PROPERTY, SVGConstants.CSS_BLACK_VALUE);
//        setCircleColor(circle, SVGConstants.CSS_RED_VALUE);




        //四角形 x, y, width, height, rx, ry
        final OMSVGRectElement rect = doc.createSVGRectElement(100, 100, 50, 200, 0, 0);
        final OMSVGPolylineElement poly = doc.createSVGPolylineElement();








        //３角形の描画
        //OMSVGPathElement path = drawTriangle();


        text = drawText("起動", 500, 200);


        // svg.appendChild(rect);
        // svg.appendChild(path);
        svg.appendChild(text);





        // Insert the SVG root element into the HTML UI
        div.appendChild(svg.getElement());



    }
//
//    private static final String getCircleColor(OMSVGCircleElement eleCircle) {
//
//        return eleCircle.getStyle().getSVGProperty(SVGConstants.CSS_FILL_PROPERTY);
//
//    }
//
//    private static final void setCircleColor(OMSVGCircleElement eleCircle, String color) {
//        eleCircle.getStyle().setSVGProperty(SVGConstants.CSS_FILL_PROPERTY, color);
//    }

    @UiHandler("btnCircle")
    void btnCircleClick(com.google.gwt.event.dom.client.ClickEvent event) {
        SvgCircle circle = new SvgCircle(doc, 100f, 100f, 40f, SVGConstants.CSS_RED_VALUE);
        final OMSVGCircleElement eleCircle = circle.getElement();
        svg.appendChild(eleCircle);
        al.add(circle);


    }

    @UiHandler("btnSquare")
    void btnSquareClick(com.google.gwt.event.dom.client.ClickEvent event) {
        //Window.alert(text.getChildNodes().getItem(0).toString());
        Window.alert(div.getString());
    }

    @UiHandler("btnSort")
    void btnSortClick(com.google.gwt.event.dom.client.ClickEvent event) {
        String str = "";
        Collections.sort(al, new CircleComparator());
        Iterator it = al.iterator();
        //SvgCircle result= (SvgCircle)al.get(0);
        while (it.hasNext()) {
            SvgCircle temp = (SvgCircle) it.next();
            str += temp.getId();
        }
        Window.alert(str);
    }

    @UiHandler("btnTriangle")
    void btnTriangleClick(com.google.gwt.event.dom.client.ClickEvent event) {
        SvgTriangle triangle = new SvgTriangle(doc, 100f, 100f);
        final OMSVGPathElement eleTriangle = triangle.getElement();
        svg.appendChild(eleTriangle);

        //TODO 管理リストへ登録


    }

    public native void ttt()/*-{    
    $wnd.test();
    }-*/;
}
/**
 *
 * @author ochi
 */
//public class EventSample extends Composite {
//
//    private static EventSampleUiBinder uiBinder = GWT.create(EventSampleUiBinder.class);
//
//    interface EventSampleUiBinder extends UiBinder<Widget, EventSample> {
//    }
//
//    public EventSample() {
//        initWidget(uiBinder.createAndBindUi(this));
//    }
//}

