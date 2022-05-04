from io import StringIO

class Node:
    left = None
    right = None
    data = None

    def __init__(self):
        self.left = None
        self.right = None

    def set_data(self, data):
        self.data = data

    def __str__(self):
        return str(self.data)

    def __iter__(self):
        yield self.data

class BinarySearchTree:
    root = None
    name = None

    def __init__(self, name, root):
        self.root = root
        self.name = name

    def add(self,node,data):
        if node.data is None:
            node.set_data(data)
        else:
            if data < node.data:
                if node.left is not None:
                    self.add(node.left, data)
                else:
                    node.left = Node()
                    node.left.set_data(data)
            else:
                if node.right is not None:
                    self.add(node.right, data)
                else:
                    node.right = Node()
                    node.right.set_data(data)

    def add_all(self, *values):
        for value in values:
            self.add(self.root, value)
        return

    def inOrder(self,lst: list, root: Node):
        if(root == None):
            return []
        self.inOrder(lst, root.left);
        lst.append(root.data);
        self.inOrder(lst, root.right);

    def __iter__(self):
        list_of_nodes = []
        self.inOrder(list_of_nodes, self.root)
        for node in list_of_nodes:
            yield node

    def preOrder(self, string: StringIO, root: Node):

        if root is None:
            string.write(")")
            return

        string.write(str(root.data))
        if root.left is not None:
            string.write(" L:(")
        self.preOrder(string, root.left)
        if root.right is not None:
            string.write(" R:(")
        self.preOrder(string, root.right)

    def __str__(self):
        bst_string = StringIO()
        self.preOrder(string = bst_string, root = self.root)
        return bst_string.getvalue()

if __name__ == "__main__":
    tree = BinarySearchTree(name="Oak", root=Node())
    tree.add_all(5, 3, 9, 0) # adds the elements in the order 5, 3, 9, and then 0
    print(tree)

    t1 = BinarySearchTree(name="Oak", root=Node())
    t2 = BinarySearchTree(name="Birch", root=Node())
    t1.add_all(5, 3, 9, 0)
    t2.add_all(1, 0, 10, 2, 7)
    for x in t1:
        print(x)
    for x in t2:
        print(x)
