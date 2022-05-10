import java.util.Arrays;
import java.util.List;

public class Test {
    public static void testBST(){
        // each tree has a name, provided to its constructor
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>("Oak");
// adds the elements to t1 in the order 5, 3, 0, and then 9
        t1.addAll(Arrays.asList(5, 3, 0, 9));
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>("Maple");
// adds the elements to t2 in the order 9, 5, and then 10
        t2.addAll(Arrays.asList(9, 5, 10));
        System.out.println(t1); // see the expected output for exact format
        t1.forEach(System.out::println); // iteration in increasing order
        System.out.println(t2); // see the expected output for exact format
        t2.forEach(System.out::println); // iteration in increasing order
        BinarySearchTree<String> t3 = new BinarySearchTree<>("Cornucopia");
        t3.addAll(Arrays.asList("coconut", "apple", "banana", "plum", "durian",
                "no durians on this tree!", "tamarind"));
        System.out.println(t3); // see the expected output for exact format
        t3.forEach(System.out::println); // iteration in increasing order

        System.out.println("----");

        List<Integer> merged = BinarySearchTree.merge(Arrays.asList(t1, t2));
        merged.forEach(a -> System.out.println(a));

        return;
    }
    public static void main(String... args){
        testBST();
    }
}
