import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable{
    class Node<T>{
        T data;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        Node(T data){
            this.data = data;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

        public T getData(){
            return data;
        }

        @Override
        public String toString(){
            return data.toString();
        }
    }

    private Node<T> root;
    private String name;

    public String getName(){
        return this.name;
    }

    public void add(T value){
        root = add(null, root, value);
    }

    private Node<T> add(Node<T> parent, Node<T> root, T value){
        if(root == null) {
            root = new Node(value);
            root.parent = parent;
            return root;
        }
        if(value.compareTo(root.data) > 0)
            root.right = add(root, root.right, value);
        else if(value.compareTo(root.data) < 0)
            root.left = add(root, root.left, value);
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

    public void preOrder(StringBuilder str, Node<T> node){
        if(node == null)
            return;

        str.append(node.data);

        if(node.left != null)
            str.append(" L:(");
        preOrder(str, node.left);

        if(node.right != null)
            str.append(" R:(");
        preOrder(str, node.right);

        if(node.left == null || node.right == null) {
            str.append(")");
        }
        else if(node.parent != null){
            if(node.parent.left != null || node.parent.right != null)
                str.append(")");
        }
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new BSTIterator(root);
        return iterator;
    }

    private class BSTIterator<T> implements Iterator<T>{
        private int index = 0;
        private Queue<Node<T>> nodes = new LinkedList();
        
        public BSTIterator(Node<T> root){
            inOrder(root, nodes);
        }

        private void inOrder(Node<T> root, Queue<Node<T>> queue){
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
        public T next() {
            return nodes.poll().data;
        }

        @Override
        public void remove() {
            nodes.remove();
        }
    }

    public static class MergeRunnable<T extends Comparable<T>> implements Runnable{

        private BinarySearchTree<T> tree;
        private BinarySearchTree<T> merge;

        public MergeRunnable(BinarySearchTree<T> tree, BinarySearchTree<T> merge){
            this.tree = tree;
            this.merge = merge;
        }

        public void run(){
            this.tree.forEach(node -> merge.add((T) node));
        }
    }

    public static <T extends Comparable<T>> List<T> merge(List<BinarySearchTree<T>> bstlist){
        ArrayList<Thread> threads = new ArrayList<>();
        BinarySearchTree<T> resultTree = new BinarySearchTree("Merged Tree");
        ArrayList<T> result = new ArrayList<>();

        bstlist.forEach(bst -> threads.add(new Thread(new MergeRunnable(bst, resultTree))));
        threads.forEach(thread -> thread.start());
        //Ensure it finishes
        try{threads.get(threads.size()-1).join();}catch(InterruptedException ie){}
        resultTree.forEach(val -> result.add((T) val));
        return result;
    }

}
