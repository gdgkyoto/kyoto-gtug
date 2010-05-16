/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Main entry point.
 *
 * @author ochi
 */
public class ochilabEntryPoint implements EntryPoint {
    /** 
     * Creates a new instance of ochilabEntryPoint
     */
    public ochilabEntryPoint() {
    }

    /** 
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    public void onModuleLoad() {
       // final Label label = new Label("Hello, GWT!!!");
       // final Button button = new Button("Click me!");
        final EventSample svg = new EventSample();

//        button.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                label.setVisible(!label.isVisible());
//            }
//        });
//
//        RootPanel.get().add(button);
//        RootPanel.get().add(label);
        RootPanel.get().add(svg);
    }
}
