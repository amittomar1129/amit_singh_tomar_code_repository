package datastructure_algorithms.datastructure.linear.arraylist;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TreeVisitor;

import java.util.Objects;

public class App {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        Iterator<String> iterator = list.iterator();
        list.add("Amit1");
        list.add("Amit2");
        list.add("Amit3");
        list.add("Amit4");
        list.add("Amit5");
        list.add("Amit6");

        list.add("Amit7");
        System.out.println(list);

        java.util.ArrayList<String> arrayList = new java.util.ArrayList();
        arrayList.add("Amit1");
        arrayList.add("Amit2");
        arrayList.add("Amit3");
        arrayList.add("Amit4");
        arrayList.set(0, "AmitQ");
        System.out.println(arrayList.size());
        System.out.println(arrayList);

    }
}
