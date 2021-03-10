package util;

public class MyList<T> extends MyCollection<T> {
    private Node<T> head;
    private int size;

    public MyList() {
        this.head = null;
        size = 0;
    }

    @Override
    public void add(T t){
        if (head == null){
            head = new Node(t);
        }
        else {
            Node node = new Node (t);
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.setNext (node);
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(T t) {
        if (head == null){
            return;
        }
        Node temp = head;
        if (temp.data.equals (t)){
            head = temp.next;
            return;
        }
        Node last = null;
        while(temp != null && (!(temp.data.equals (t)))){
            last = temp;
            temp = temp.next;
        }
        if (temp == null){
            return;
        }
        last.next = temp.next;
        size--;
    }

    @Override
    public String toString() {
        if(head == null){
            return "Nothing in the list";
        }
        StringBuilder res = new StringBuilder ();
        Node temp = head;
        do{
            res.append (temp.getData ()+"\n");
            temp = temp.next;
        }while (temp != null);
        return res.toString ();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int indexOf(T t) {
        int position = 0;
        if (head == null){
            return -1;
        }
        Node temp = head;
        while ( temp != null){
            if(temp.getData ().equals (t)){
                return position;
            }
            position++;
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public Node<T> atIndex(int i) {
        if (i < 0){
            System.out.println ("Not a valid index");
            return null;
        }else {
            int current = 0;
            Node temp = head;
            while (current != i) {
                temp = temp.next;
                current++;
            }
            return temp;
        }
    }

    public boolean contain(T t){
        Node temp = head;
        if (head == null){
            return false;
        }else {
            while (temp != null && (!temp.data.equals (t))){
                temp = temp.next;
            }
            if (temp == null){
                return false;
            } else{
                return true;
            }
        }
    }

}
