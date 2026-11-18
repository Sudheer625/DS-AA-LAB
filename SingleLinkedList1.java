import java.util.Scanner;

class ListNode {
    int data;
    ListNode next;

    ListNode(int data) {
        this.data = data;
    }
}

public class SingleLinkedList1 {
    ListNode head;

    // Insert at end
    void add(int data) {
        ListNode newNode = new ListNode(data);

        if (head == null) {
            head = newNode;
            return;
        }

        ListNode temp = head;
        while (temp.next != null)
            temp = temp.next;

        temp.next = newNode;
    }

    // Display list
    void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // a) Reverse Linked List
    void reverse() {
        ListNode prev = null, curr = head, next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
        System.out.println("List after reversing:");
        display();
    }

    // b) Sort Linked List
    void sort() {
        for (ListNode i = head; i != null; i = i.next) {
            for (ListNode j = i.next; j != null; j = j.next) {
                if (i.data > j.data) {
                    int temp = i.data;
                    i.data = j.data;
                    j.data = temp;
                }
            }
        }

        System.out.println("List after sorting:");
        display();
    }

    // c) Remove Duplicates
    void removeDuplicates() {
        for (ListNode i = head; i != null; i = i.next) {
            ListNode prev = i;
            ListNode j = i.next;

            while (j != null) {
                if (i.data == j.data) {
                    prev.next = j.next;
                } else {
                    prev = j;
                }
                j = j.next;
            }
        }

        System.out.println("List after removing duplicates:");
        display();
    }

    // d) Merge Two Lists
    static SingleLinkedList1 merge(SingleLinkedList1 l1, SingleLinkedList1 l2) {
        SingleLinkedList1 result = new SingleLinkedList1();

        ListNode temp = l1.head;
        while (temp != null) {
            result.add(temp.data);
            temp = temp.next;
        }

        temp = l2.head;
        while (temp != null) {
            result.add(temp.data);
            temp = temp.next;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SingleLinkedList1 list = new SingleLinkedList1();

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        int choice;

        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Reverse Linked List");
            System.out.println("2. Sort Linked List");
            System.out.println("3. Remove Duplicates");
            System.out.println("4. Merge Another Linked List");
            System.out.println("5. Display List");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    list.reverse();
                    break;

                case 2:
                    list.sort();
                    break;

                case 3:
                    list.removeDuplicates();
                    break;

                case 4:
                    SingleLinkedList1 list2 = new SingleLinkedList1();

                    System.out.print("Enter number of elements in second list: ");
                    int m = sc.nextInt();

                    System.out.println("Enter elements:");
                    for (int i = 0; i < m; i++) {
                        list2.add(sc.nextInt());
                    }

                    list = merge(list, list2);

                    System.out.println("List after merging:");
                    list.display();
                    break;

                case 5:
                    list.display();
                    break;

                case 6:
                    System.out.println("Program Ended");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 6);

        sc.close();
    }
}
