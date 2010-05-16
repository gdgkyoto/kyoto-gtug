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
public class CircleComparator implements Comparator{

        public int compare(Object o1,Object o2) {
//        int result =0;
        return ((SvgCircle) o1).getX() - ((SvgCircle) o2).getX();
//        if(((SvgCircle) o1).getGx() - ((SvgCircle) o2).getX()>0){
//         result =1;
//        }
//        else{
//            result=-1;
//        }
//         return result;
    }

}
