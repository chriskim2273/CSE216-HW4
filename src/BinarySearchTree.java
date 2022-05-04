import java.util.*;

public class BinarySearchTree<T extends Comparable> implements Iterable{
    class Node<T>{
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString(){
            return data.toString();
        }
    }

    private Node root;
    private String name;

    public String getName(){
        return this.name;
    }

    public void add(T value){
        root = add(root, value);
    }

    private Node add(Node root, T value){
        if(root == null) {
            root = new Node(value);
            return root;
        }
        if(value.compareTo(root.data) > 0)
            root.right = add(root.right, value);
        else if(value.compareTo(root.data) < 0)
            root.left = add(root.left, value);
        return root;
    }

    public void addAll(List<T> values){
        for(T value: values)
            add(value);
    }

    public BinarySearchTree(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        preOrder(string, root);
        return "[" + name + "] " + string.toString();
    }

    public void preOrder(StringBuilder str, Node node){
        if(node == null) {
            str.append(")");
            return;
        }

        str.append(node.data);

        if(node.left != null)
            str.append(" L:(");
        preOrder(str, node.left);

        if(node.right != null)
            str.append(" R:(");
        preOrder(str, node.right);
    }

    @Override
    public Iterator<Node> iterator() {
        Iterator<Node> iterator = new BSTIterator(root);
        return iterator;
    }

    private class BSTIterator implements Iterator<Node>{
        private int index = 0;
        private Queue<Node> nodes = new LinkedList();
        
        public BSTIterator(Node root){
            inOrder(root, nodes);
        }

        private void inOrder(Node root, Queue<Node> queue){
            if(root == null)
                return;

            inOrder(root.left, queue);
            queue.add(root);
            inOrder(root.right, queue);
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public Node next() {
            return nodes.poll();
        }

        @Override
        public void remove() {
            nodes.remove();
        }
    }

    public static Runnable mergeRunnable = new Runnable(){
        public void run(){
            System.out.println("Joe");
        }
    };



    public synchronized static <T extends Comparable<T>> List<T> merge(List<BinarySearchTree<T>> bstlist){
        ArrayList<Thread> threads = new ArrayList<>();
        
        for(int i = 0; i < bstlist.size(); i++){
            Thread thread = new Thread(mergeRunnable);
            threads.add(thread);
        }
    }

}
