/* Koon Chua
 * EN 605.202.81
 * Lab 3
 *
 * Mat_LL class instantiates Doubly-Linked List matrix
 * Contains getSize, isEmpty, setHeader, insertNode, removeNode, and getValue methods.
 */

public class Mat_LL {
    private Node header;    // header node
    private Node first;     // first node in LL
    private Node last;      // Last Node
    private int mat_size;   // Matrix size
    private int list_size;  // row size

    /**
     * Nested Node class for our double linked list.
     */
    private class Node{
        int data;
        Integer col;
        Node next;
        Node prev;

        /**
         * Instansiate Node object with value attribute
         * @param val   data value of the node
         */
        private Node(int val) {
            data = val;
        }

        /**
         * Print current node
         */
        private void printNode() {
            System.out.println(data + "");
        }

        /**
         * Returns value of current node
         * @return  int value attribute of current node
         */
        private int value() {
            return data;
        }
    }

    /**
     * Constructor:
     * Instantiate Mat_LL object
     * Set header, first, last to null
     */
    public Mat_LL() {
        header = null;
        first = null;
        last = null;
        mat_size = 0;
        list_size = 0;
    }

    public int matSize() {
        return mat_size;
    }

    /**
     * Check if list is empty
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return (first == null);
    }

    /**
     * Sets header node as the input value
     * contains row number
     * @param val row number of list
     */
    public void setHeader(int val) {
        Node head_node = new Node(val);
        header = head_node;
        header.next = first;
    }

    /**
     * Returns size of list
     * @return int size of list
     */
    public int getSize() {
        Node curr_node = first;
        list_size = 0;

        while (curr_node != null) {
            curr_node = curr_node.next;
            list_size++;
        }
        return list_size;
    }

    /**
     * Inserts a node at the end of linked list.
     * Increase size each time we insert a node.
     * @param val   Value of the new node
     */
    public void insertNode(int val) {
        Node node = new Node(val);

        if (first == null) {
            first = node;
            first.next = last;
            first.prev = header;
        } else if (last == null) {
            last = node;
            last.prev = first;
            first.next = last;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        mat_size = mat_size+1;
    }

    /**
     * Deletes node from specified column of a given linked list
     * @param list  Specified list to delete item
     * @param col   Column index of the node to be deleted
     * @return      Deleted node
     */
    public Node removeNode(Mat_LL list, Integer col) {
        Node curr_node = first;
        Node temp = null;

        int size = list.getSize();
        int last_item = size-1;

        // If only one node, clear list and return node.
        if (list.isEmpty()) {
            System.err.println("List is empty.");
                return null;

        } else if (col == 0) {
            temp = curr_node;
            first = curr_node.next;
            curr_node.next.prev = header;
            curr_node = null;
            return temp;

        } else if (col == last_item) {
            temp = last;
            last = temp.prev;
            last.next = null;
            return temp;

        } else for (int i = 1; i < size; i++) {
            if (col == i) {
                temp = curr_node;
                curr_node.prev.next = curr_node.next;
                curr_node.next.prev = curr_node.prev;
                curr_node = null;
                return temp;
            }
        }
        // out of scope of list
        return null;
    }

    /**
     * Returns value of the node in the specified column
     * @param col column of specified node
     * @return int value of the node
     */
    public int getValue(int col) {
        Node curr_node = first;
        int i = 0;

        while (curr_node != null) {
            if (col == i) {
                return curr_node.value();

            } else {
                curr_node = curr_node.next;
                i++;
            }
        }
        // no longer in scope of list
        return 0;
    }
}
