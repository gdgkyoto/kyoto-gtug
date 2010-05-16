/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.client;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author oyouji
 */
public class ObjectManager {

    private ArrayList circleArray = new ArrayList();
    private ArrayList rectArray = new ArrayList();
    private ArrayList TriangleArray = new ArrayList();

    /**
     * @return the circleArray
     */
    public ArrayList getCircleArray() {

        Collections.sort(circleArray, new CircleComparator());
        return circleArray;
    }

    /**
     * @param circleArray the circleArray to set
     */
    public void setCircleArray(ArrayList circleArray) {
        this.circleArray = circleArray;
    }

    /**
     * @return the rectArray
     */
    public ArrayList getRectArray() {
        return rectArray;
    }

    /**
     * @param rectArray the rectArray to set
     */
    public void setRectArray(ArrayList rectArray) {
        this.rectArray = rectArray;
    }

    /**
     * @return the TriangleArray
     */
    public ArrayList getTriangleArray() {
        return TriangleArray;
    }

    /**
     * @param TriangleArray the TriangleArray to set
     */
    public void setTriangleArray(ArrayList TriangleArray) {
        this.TriangleArray = TriangleArray;
    }



    



}
