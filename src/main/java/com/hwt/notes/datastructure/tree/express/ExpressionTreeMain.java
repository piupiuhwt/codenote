package com.hwt.notes.datastructure.tree.express;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTreeMain {
    public static void main(String[] args) {
        int a = 10;
        int b = 11;
        int c = 13;
        int d = 14;
        int e = 15;
        String sum1 = "+";
        String multi = "*";
        String minus = "-";
        String division = "/";
        List express = new ArrayList();
        express.add(a);
        express.add(b);
        express.add(sum1);
        express.add(c);
        express.add(d);
        express.add(e);
        express.add(multi);
        express.add(minus);
        express.add(division);
        ExpressionTree<String, Integer> expressionTree = new ExpressionTree<>(String.class, Integer.class);
        ExpressionTree<String, Integer> newExpressionTree = expressionTree.listToExpressionTree(express, false);
        newExpressionTree.inOrderTraversal();
        System.out.println();
        newExpressionTree.preOrderTraversal();
        System.out.println();
        newExpressionTree.postOrderRecursionTraversal();
        System.out.println(System.currentTimeMillis());
    }
}
