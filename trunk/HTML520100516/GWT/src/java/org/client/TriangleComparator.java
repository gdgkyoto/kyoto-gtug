/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.client;

import java.util.Comparator;

/**
 *
 * @author oyouji
 */
public class TriangleComparator implements Comparator{

        public int compare(Object o1,Object o2) {
//        int result =0;
        return ((int)((SvgTriangle) o1).getX()) - (int)((SvgTriangle) o2).getX();

    }

}
