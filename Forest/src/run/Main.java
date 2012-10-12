package run;

import java.util.Random;
import java.util.Scanner;
import trees.AVL;
import trees.Binary_Search;
import trees.Splay;
import trees.Trie;

/**
 *
 * @author Ronsupihvi
 */
public class Main extends Demo {

    public static void main(String[] args) {

        Binary_Search b;
        AVL a;
        Splay s;
        Trie t;
        Scanner scan = new Scanner(System.in);
        String tree;
        int timesInt;
        long start, end;
        Random r = new Random();

        while (true) {

            System.out.println("Choose a tree, demo or quit:\n[B]inary search, [A]VL, [S]play, [T]rie, [D]emo or [Q]uit");
            tree = scan.nextLine().toUpperCase();

            // Käyttäjä antanut väärän komennon
            if (!tree.equals("B") && !tree.equals("A") && !tree.equals("S") && !tree.equals("T")
                    && !tree.equals("Q") && !tree.equals("D")) {
                System.out.println("Command does not exist!");
            } // Käyttäjä antanut olemassa olevan komennon
            else {
                if (tree.equals("Q")) {
                    break;
                }
                if (tree.equals("D")) {
                    demo();
                } else {
                    System.out.println("How many inserts, searches, and deletes would you like to make? (1-20000000 or [Q]uit)");
                    if (scan.hasNextInt()) {
                        timesInt = scan.nextInt();
                        if (timesInt > 0 && timesInt <= 20000000) {

                            if (tree.equals("B")) {
                                b = new Binary_Search();
                                System.out.println("BINARY SEARCH");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    b.insert(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " inserts OK in " + (end - start) + " ms.");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    b.search(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " searches OK in " + (end - start) + " ms.");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    b.delete(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " deletes OK in " + (end - start) + " ms.");
                                b = null;
                            } else if (tree.equals("A")) {
                                a = new AVL();
                                System.out.println("AVL");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    a.AVLinsert(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " inserts OK in " + (end - start) + " ms.");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    a.search(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " searches OK in " + (end - start) + " ms.");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    a.AVLdelete(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " deletes OK in " + (end - start) + " ms.");
                                a = null;
                            } else if (tree.equals("S")) {
                                s = new Splay();
                                System.out.println("SPLAY");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    s.splayInsert(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " inserts OK in " + (end - start) + " ms.");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    s.search(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " searches OK in " + (end - start) + " ms.");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    s.delete(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " deletes OK in " + (end - start) + " ms.");
                                s = null;
                            } else {
                                t = new Trie();
                                System.out.println("TRIE");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    t.insert(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " inserts OK in " + (end - start) + " ms.");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    t.trieSearch(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " searches OK in " + (end - start) + " ms.");
                                start = System.currentTimeMillis();
                                for (int i = 0; i < timesInt; i++) {
                                    t.delete(r.nextInt());
                                }
                                end = System.currentTimeMillis();
                                System.out.println(timesInt + " deletes OK in " + (end - start) + " ms.");
                                t = null;
                            }
                            scan.nextLine();
                        } else {
                            System.out.println("Integer too large!");
                            tree = null;
                        }
                    } else if (scan.nextLine().equalsIgnoreCase("q")) {
                        break;
                    } else {
                        System.out.println("Command does not exist!");
                        tree = null;
                    }
                }
            }
        }
        System.out.println("TREES\nWAT R U DOING?\nTREES\nSTAHP!");
    }
}
