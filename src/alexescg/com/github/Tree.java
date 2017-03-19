package alexescg.com.github;

/**
 * @author alex
 */
public class Tree<Key extends Comparable<Key>, Value> {

    private Node root;

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value);
        }
        int compare = key.compareTo(x.key);
        if (compare == 0) {
            x.count++;
        } else if (compare < 0) {
            x.leftChild = put(x.leftChild, key, value);
        } else if (compare > 0) {
            x.rightChild = put(x.rightChild, key, value);
        } else {
            x.value = value;
        }
        return x;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int compare = key.compareTo(x.key);
            if (compare < 0) {
                x = x.leftChild;
            } else if (compare > 0) {
                x = x.rightChild;
            } else {
                return x.value;
            }
        }
        return null;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int compare = key.compareTo(x.key);
        //Search key
        if (compare < 0) {
            x.leftChild = delete(x.leftChild, key);
        } else if (compare > 0) {
            x.rightChild = delete(x.rightChild, key);
        } else {
            //No right child
            if (x.rightChild == null) {
                return x.leftChild;
            }
            //No left child
            if (x.leftChild == null) {
                return x.rightChild;
            }
            //Reemplazar con sucesor
            Node t = x;
            x = min(t.rightChild);
            x.rightChild = deleteMin(t.rightChild);
            x.leftChild = t.leftChild;
        }
        return x;
    }

    private Node min(Node x) {
        if (x.leftChild == null) {
            return x;
        } else {
            return min(x.leftChild);
        }
    }

    private Node deleteMin(Node x) {
        if (x.leftChild == null) {
            return x.rightChild;
        }
        x.leftChild = deleteMin(x.leftChild);
        return x;
    }

    public int count(Key key) {
        return count(root, key);
    }

    private int count(Node x, Key key) {
        if (x == null) {
            return 0;
        }
        int compare = key.compareTo(x.key);
        if (compare == 0) {
            return x.count;
        } else if (compare < 0) {
            return count(x.leftChild, key);
        } else if (compare > 0) {
            return count(x.rightChild, key);
        }
        return 0;
    }

    public int findLevel(Key key) {
        return findLevel(root, key, 1);
    }

    private int findLevel(Node x, Key key, int currentLevel) {
        if (x == null) {
            return -1;
        }
        int compare = key.compareTo(x.key);
        if (compare < 0) {
            return findLevel(x.leftChild, key, currentLevel + 1);
        } else if (compare > 0) {
            return findLevel(x.rightChild, key, currentLevel + 1);
        } else {
            return currentLevel;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    private class Node {
        private Key key;
        private Value value;
        private Node leftChild;
        private Node rightChild;
        private int count;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    ", count=" + count +
                    '}';
        }
    }
}
