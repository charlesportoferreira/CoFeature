/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofeature;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class ComparadorCofeature implements java.util.Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Feature f1 = (Feature) o1;
        Feature f2 = (Feature) o2;
        if (f1.getDominancia() > f2.getDominancia()) {
            return -1;
        }
        if (f1.getDominancia() < f2.getDominancia()) {
            return 1;
        }

        return 0;
    }

}
