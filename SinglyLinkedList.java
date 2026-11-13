import java.util.Scanner;

public class SinglyLinkedList {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node head;

    static void printList() {
        Node current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    static void insertBeginning(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    static void insertEnd(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Positions in this program start at 1.
    static boolean insertAtPosition(int value, int position) {
        if (position < 1) {
            return false;
        }
        if (position == 1) {
            insertBeginning(value);
            return true;
        }

        Node current = head;
        for (int i = 1; current != null && i < position - 1; i++) {
            current = current.next;
        }
        if (current == null) {
            return false;
        }

        Node newNode = new Node(value);
        newNode.next = current.next;
        current.next = newNode;
        return true;
    }

    static boolean deleteBeginning() {
        if (head == null) {
            return false;
        }
        head = head.next;
        return true;
    }

    static boolean deleteEnd() {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            head = null;
            return true;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
        return true;
    }

    static boolean deleteAtPosition(int position) {
        if (position < 1 || head == null) {
            return false;
        }
        if (position == 1) {
            return deleteBeginning();
        }

        Node current = head;
        for (int i = 1; current != null && i < position - 1; i++) {
            current = current.next;
        }
        if (current == null || current.next == null) {
            return false;
        }
        current.next = current.next.next;
        return true;
    }

    static int findPosition(int value) {
        Node current = head;
        int position = 1;
        while (current != null) {
            if (current.data == value) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }

    static boolean updateAtPosition(int position, int newValue) {
        if (position < 1) {
            return false;
        }

        Node current = head;
        for (int i = 1; current != null && i < position; i++) {
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        current.data = newValue;
        return true;
    }

    static void reverse() {
        Node previous = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        head = previous;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("How many numbers should the list contain? ");
        int count = input.nextInt();
        for (int i = 1; i <= count; i++) {
            System.out.print("Enter number " + i + ": ");
            insertEnd(input.nextInt());
        }
        printList();

        int choice;
        do {
            System.out.println("\n1. Traverse");
            System.out.println("2. Insert at beginning");
            System.out.println("3. Insert at end");
            System.out.println("4. Insert at a position");
            System.out.println("5. Delete from beginning");
            System.out.println("6. Delete from end");
            System.out.println("7. Delete from a position");
            System.out.println("8. Search for a number");
            System.out.println("9. Update a position");
            System.out.println("10. Reverse the list");
            System.out.println("0. Exit");
            System.out.print("Choose an operation: ");
            choice = input.nextInt();

            int value;
            int position;
            boolean changed;
            switch (choice) {
                case 1:
                    printList();
                    break;
                case 2:
                    System.out.print("Enter number: ");
                    insertBeginning(input.nextInt());
                    printList();
                    break;
                case 3:
                    System.out.print("Enter number: ");
                    insertEnd(input.nextInt());
                    printList();
                    break;
                case 4:
                    System.out.print("Enter number: ");
                    value = input.nextInt();
                    System.out.print("Enter position (starting at 1): ");
                    position = input.nextInt();
                    changed = insertAtPosition(value, position);
                    System.out.println(changed ? "Number inserted." : "Invalid position.");
                    printList();
                    break;
                case 5:
                    changed = deleteBeginning();
                    System.out.println(changed ? "First number deleted." : "The list is empty.");
                    printList();
                    break;
                case 6:
                    changed = deleteEnd();
                    System.out.println(changed ? "Last number deleted." : "The list is empty.");
                    printList();
                    break;
                case 7:
                    System.out.print("Enter position to delete (starting at 1): ");
                    position = input.nextInt();
                    changed = deleteAtPosition(position);
                    System.out.println(changed ? "Number deleted." : "Invalid position.");
                    printList();
                    break;
                case 8:
                    System.out.print("Enter number to search for: ");
                    value = input.nextInt();
                    position = findPosition(value);
                    System.out.println(position == -1
                            ? "Number not found."
                            : "Number found at position " + position + ".");
                    printList();
                    break;
                case 9:
                    System.out.print("Enter position to update (starting at 1): ");
                    position = input.nextInt();
                    System.out.print("Enter the new number: ");
                    value = input.nextInt();
                    changed = updateAtPosition(position, value);
                    System.out.println(changed ? "Number updated." : "Invalid position.");
                    printList();
                    break;
                case 10:
                    reverse();
                    System.out.println("List reversed.");
                    printList();
                    break;
                case 0:
                    System.out.println("Program ended.");
                    break;
                default:
                    System.out.println("Please choose a valid option.");
                    printList();
            }
        } while (choice != 0);

        input.close();
    }
}