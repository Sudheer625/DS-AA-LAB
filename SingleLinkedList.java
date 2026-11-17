
import java.util.Scanner;

public class SinglyLinkedList {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    static Node head;

    static void printList() {
        Node temp = head;
        System.out.print("List: ");
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    static void insertBeginning(int x) {
        Node n = new Node(x);
        n.next = head;
        head = n;
    }

    static void insertEnd(int x) {
        Node n = new Node(x);
        if (head == null) { head = n; return; }
        Node t = head;
        while (t.next != null) t = t.next;
        t.next = n;
    }

    static boolean insertAtPosition(int x, int p) {
        if (p < 1) return false;
        if (p == 1) { insertBeginning(x); return true; }
        Node t = head;
        for (int i = 1; t != null && i < p - 1; i++)
            t = t.next;
        if (t == null) return false;
        Node n = new Node(x);
        n.next = t.next;
        t.next = n;
        return true;
    }

    static boolean deleteBeginning() {
        if (head == null) return false;
        head = head.next;
        return true;
    }

    static boolean deleteEnd() {
        if (head == null) return false;
        if (head.next == null) { head = null; return true; }
        Node t = head;
        while (t.next.next != null) t = t.next;
        t.next = null;
        return true;
    }

    static boolean deleteAtPosition(int p) {
        if (p < 1 || head == null) return false;
        if (p == 1) return deleteBeginning();
        Node t = head;
        for (int i = 1; t != null && i < p - 1; i++)
            t = t.next;
        if (t == null || t.next == null) return false;
        t.next = t.next.next;
        return true;
    }

    static int search(int x) {
        Node t = head;
        int p = 1;
        while (t != null) {
            if (t.data == x) return p;
            t = t.next;
            p++;
        }
        return -1;
    }

    static boolean update(int p, int x) {
        Node t = head;
        for (int i = 1; t != null && i < p; i++)
            t = t.next;
        if (p < 1 || t == null) return false;
        t.data = x;
        return true;
    }

    static void reverse() {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++)
            insertEnd(sc.nextInt());

        int ch, x, p;
        do {
            System.out.println("\n1.Traverse  2.Insert Begin  3.Insert End");
            System.out.println("4.Insert Position  5.Delete Begin  6.Delete End");
            System.out.println("7.Delete Position  8.Search  9.Update");
            System.out.println("10.Reverse  0.Exit");
            System.out.print("Choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1: printList(); break;
                case 2:
                    System.out.print("Value: ");
                    insertBeginning(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Value: ");
                    insertEnd(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Value and Position: ");
                    x = sc.nextInt(); p = sc.nextInt();
                    System.out.println(insertAtPosition(x, p) ? "Inserted" : "Invalid Position");
                    break;
                case 5:
                    System.out.println(deleteBeginning() ? "Deleted" : "List Empty");
                    break;
                case 6:
                    System.out.println(deleteEnd() ? "Deleted" : "List Empty");
                    break;
                case 7:
                    System.out.print("Position: ");
                    p = sc.nextInt();
                    System.out.println(deleteAtPosition(p) ? "Deleted" : "Invalid Position");
                    break;
                case 8:
                    System.out.print("Search Value: ");
                    p = search(sc.nextInt());
                    System.out.println(p == -1 ? "Not Found" : "Found at " + p);
                    break;
                case 9:
                    System.out.print("Position and New Value: ");
                    p = sc.nextInt(); x = sc.nextInt();
                    System.out.println(update(p, x) ? "Updated" : "Invalid Position");
                    break;
                case 10:
                    reverse();
                    System.out.println("Reversed");
                    break;
                case 0:
                    System.out.println("Program Ended");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
            if (ch != 0) printList();

        } while (ch != 0);

        sc.close();
    }
}
