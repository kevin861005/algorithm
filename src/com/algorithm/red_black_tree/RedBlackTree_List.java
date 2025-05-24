package com.algorithm.red_black_tree;

public class RedBlackTree_List {

    public static class RBTree_Node {
        RBTree_Node parent;
        RBTree_Node left;
        RBTree_Node right;
        Integer value;
        String color;   // "red", "black"

        public RBTree_Node (Integer value, String color) {
            this.value = value;
            this.color = color;
        }
    }

    private RBTree_Node root;
    private int[] nums;

    public RedBlackTree_List (int[] nums) {
        this.nums = nums;
    }

    public void build_tree () {
        for (int i = 0; i < this.nums.length ;i++) {
            add(this.nums[i]);
        }
    }

    public void add (int value) {
        RBTree_Node node_to_add = new RBTree_Node(value, "red");

        /** BST add **/
        RBTree_Node root_after_add = add(this.root, node_to_add);
        if (this.root != root_after_add) {
            this.root = root_after_add;
        }

        /** RBTree balance **/
        balance(node_to_add);
    }

    private void balance (RBTree_Node node) {
        if (node == root) {
            System.out.println("Insert Case Root");
            node.color = "black";
            return;
        }

        if (is_red(node.parent)) {
            // two red nodes next to each other
            String deeper_side = get_deeper_side(node); // "left" "right"
            RBTree_Node uncle = get_uncle(node);

            if (is_black(uncle)) {
                if ("left".equals(deeper_side)){
                    if (node.parent.left == node) {
                        System.out.println("Insert Case A1 (Right Rotation)");
                        balanceA1(node);
                    }
                    else if (node.parent.right == node) {
                        System.out.println("Insert Case A2 (Left-Right Rotation)");
                        balanceA2(node);
                    }
                }
                else if ("right".equals(deeper_side)) {
                    if (node.parent.right == node) {
                        System.out.println("Insert Case A1 (Left Rotation)");
                        balanceA1(node);
                    }
                    else if (node.parent.left == node) {
                        System.out.println("Insert Case A2 (Right-Left Rotation)");
                        balanceA2(node);
                    }
                }
            }
            else if (is_red(uncle)) {
                // color
                System.out.println("Insert Case B");
                RBTree_Node node_next_round = balanceB(node);
                balance(node_next_round);
            }
        }
    }

    private RBTree_Node balanceB (RBTree_Node node) {
        // move color up
        RBTree_Node uncle = get_uncle(node);
        node.parent.color = "black";
        uncle.color = "black";
        node.parent.parent.color = "red";

        return node.parent.parent;
    }

    private void balanceA2 (RBTree_Node node) {
        String deeper_side = get_deeper_side(node);
        if ("left".equals(deeper_side)) {
            rotate_left(node.parent); // smaller-scale rotate
            balanceA1(node.left);
        }
        else if ("right".equals(deeper_side)) {
            rotate_right(node.parent); // smaller-scale rotate
            balanceA1(node.right);
        }
    }

    private void balanceA1 (RBTree_Node node) {
        // move color up
        node.parent.color = "black";
        node.parent.parent.color = "red";

        // rotate
        RBTree_Node node_grandparent_before_node = node.parent.parent;
        RBTree_Node node_grandparent_after_node = null;
        String deeper_side = get_deeper_side(node);
        if ("left".equals(deeper_side)) {
            node_grandparent_after_node = rotate_right(node.parent.parent);
        }
        else if ("right".equals(deeper_side)) {
            node_grandparent_after_node = rotate_left(node.parent.parent);
        }

        if (this.root == node_grandparent_before_node) {
            this.root = node_grandparent_after_node;
        }
    }

    private RBTree_Node rotate_left (RBTree_Node node) {
        RBTree_Node root_old = node;
        RBTree_Node root_old_parent = node.parent;
        RBTree_Node root_new = node.right;
        RBTree_Node subtree = node.right.left;

        root_old.right = subtree;
        if (subtree != null) {
            subtree.parent = root_old;
        }
        root_old.parent = root_new;
        root_new.left = root_old;

        root_new.parent = root_old_parent;
        if (root_old_parent != null) {
            if (root_old_parent.right == root_old) {
                root_old_parent.right = root_new;
            }
            else if (root_old_parent.left == root_old) {
                root_old_parent.left = root_new;
            }
        }
        return root_new;
    }

    private RBTree_Node rotate_right (RBTree_Node node) {
        RBTree_Node root_old = node;
        RBTree_Node root_old_parent = node.parent;
        RBTree_Node root_new = node.left;
        RBTree_Node subtree = node.left.right;

        root_old.left = subtree;
        if (subtree != null) {
            subtree.parent = root_old;
        }
        root_old.parent = root_new;
        root_new.right = root_old;

        root_new.parent = root_old_parent;
        if (root_old_parent != null) {
            if (root_old_parent.left == root_old) {
                root_old_parent.left = root_new;
            }
            else if (root_old_parent.right == root_old) {
                root_old_parent.right = root_new;
            }
        }

        return root_new;
    }

    private RBTree_Node get_uncle (RBTree_Node node) {
        String deeper_side = get_deeper_side(node);
        if ("left".equals(deeper_side)) {
            return node.parent.parent.right;
        }
        else if ("right".equals(deeper_side)) {
            return node.parent.parent.left;
        }
        return null;
    }

    private String get_deeper_side (RBTree_Node node) {
        String deeper_side = "";
        if (node.parent.parent.left == node.parent) {
            deeper_side = "left";
        }
        else if (node.parent.parent.right == node.parent) {
            deeper_side = "right";
        }
        return deeper_side;
    }

    private boolean is_black (RBTree_Node node) {
        if (node == null || node.color.equals("black")) {
            return true;
        }
        return false;
    }

    private boolean is_red (RBTree_Node node) {
        if (node != null && node.color.equals("red")) {
            return true;
        }
        return false;
    }

    public RBTree_Node add (RBTree_Node node, RBTree_Node node_to_add) {
        if (node == null) {
            // found the right spot for inserting new node!
            return node_to_add;
        }

        if (node_to_add.value == node.value) {
            System.out.print("\nnode:" + node_to_add.value + " already exists. skip insert." );
        }
        else if (node_to_add.value < node.value) {
            // left
            RBTree_Node node_child = add(node.left, node_to_add);
            if (node.left != node_child) {
                node.left = node_child;
                node.left.parent = node;
            }
        }
        else if (node_to_add.value > node.value) {
            // right
            RBTree_Node node_child = add(node.right, node_to_add);
            if (node.right != node_child) {
                node.right = node_child;
                node.right.parent = node;
            }
        }

        return node;
    }

    public Integer search (int value) {
        if (this.root == null) return null;
        return search(this.root, value);
    }

    public Integer search (RBTree_Node node, Integer value) {
        if (node == null) {
            return null;
        }

        if (value == node.value) {
            return node.value;
        }
        else if (value < node.value) {
            // left
            return search(node.left, value);
        }
        else if (value > node.value) {
            // right
            return search(node.right, value);
        }

        return null;
    }

    public void delete (int value) {
        if (this.root == null) return;
        delete(this.root, value);
    }

    public void delete (RBTree_Node node, Integer value) {
        if (node == null) return;

        if (value == node.value) {
            if (node.left == null && node.right == null) {
                /** it's leaf node **/
                balance_before_delete(node);
                delete_helper(node);

            }
            else {
                /** swap value and go to leaf node **/

                if (node.left == null && node.right != null) {
                    RBTree_Node node_min = getMin(node.right);
                    swap_node_value(node, node_min);
                    delete(node_min, value);
                }
                else if (node.left != null && node.right == null) {
                    RBTree_Node node_max = getMax(node.left);
                    swap_node_value(node, node_max);
                    delete(node_max, value);
                }
                else if (node.left != null && node.right != null) {
                    RBTree_Node node_min = getMin(node.right);
                    swap_node_value(node, node_min);
                    delete(node_min, value);
                }
            }
        }
        else if (value < node.value) {
            // left
            delete(node.left, value);
        }
        else if (value > node.value) {
            // right
            delete(node.right, value);
        }
    }

    private void delete_helper (RBTree_Node node) {
        if (node == this.root) {
            this.root = null;
        }
        else {
            if (node.parent.right == node) {
                node.parent.right = null;
            }
            else if (node.parent.left == node) {
                node.parent.left = null;
            }
        }
    }

    private void balance_before_delete (RBTree_Node node) {
        RBTree_Node parent = node.parent;
        RBTree_Node sibling = get_sibling(node);
        RBTree_Node sibling_near_child = get_sibling_near_child(node);
        RBTree_Node sibling_far_child = get_sibling_far_child(node);

        if (is_red(node)) {
            System.out.println("Delete Type A");
            return;
        }

        if (node == this.root) {
            System.out.println("Delete Type B");
            node.color = "black";
            return;
        }

        if (is_red(sibling)) {
            System.out.println("Delete Type C - Case 1");
            sibling.color = "black";
            parent.color = "red";
            rotate_delete(node, parent);
            balance_before_delete(node); /** next round **/
            return;
        }

        if (is_black(sibling)) {
            if (is_black(sibling_near_child) && is_black(sibling_far_child)) {
                if (is_red(parent)) {
                    System.out.println("Delete Type C - Case 2.1");
                    sibling.color = "red";
                    parent.color = "black";
                }
                else if (is_black(parent)) {
                    System.out.println("Delete Type C - Case 2.2");
                    sibling.color = "red";
                    balance_before_delete(parent); /** next round **/
                }
            }
            else if (is_black(sibling_far_child) && is_red(sibling_near_child)) {
                System.out.println("Delete Type C - Case 3");
                sibling_near_child.color = "black";
                sibling.color = "red";
                rotate_delete(node, sibling);
                balance_before_delete(node); /** next round (Case 4) **/
            }
            else if (is_red(sibling_far_child)) {
                if (is_black(parent)) {
                    System.out.println("Delete Type C - Case 4.1");
                    rotate_delete(node, parent);
                    sibling_far_child.color = "black";
                }
                else if (is_red(parent)) {
                    System.out.println("Delete Type C - Case 4.2");
                    parent.color = "black";
                    sibling.color = "red";
                    rotate_delete(node, parent);
                    sibling_far_child.color = "black";
                }
            }
            return;
        }
        return;
    }

    private void rotate_delete (RBTree_Node node, RBTree_Node node_to_rotate) {
        if (node_to_rotate == node.parent) {
            RBTree_Node parent_before_rotate = node.parent;
            RBTree_Node parent_after_rotate = null;
            if (node.parent.left == node) {
                parent_after_rotate = rotate_left(node.parent);
            }
            else if (node.parent.right == node) {
                parent_after_rotate = rotate_right(node.parent);
            }
            if (this.root == parent_before_rotate) {
                this.root = parent_after_rotate;
            }
        }
        else if (node_to_rotate == get_sibling(node)) {
            RBTree_Node sibling = get_sibling(node);
            RBTree_Node sibling_near_child = get_sibling_near_child(node);
            if (sibling.left == sibling_near_child) {
                rotate_right(sibling);
            }
            else if (sibling.right == sibling_near_child) {
                rotate_left(sibling);
            }
        }
    }

    private RBTree_Node get_sibling_near_child (RBTree_Node node) {
        if (node.parent != null) {
            if (node.parent.right == node) {
                if (node.parent.left != null) {
                    return node.parent.left.right;
                }
            }
            else if (node.parent.left == node) {
                if (node.parent.right != null) {
                    return node.parent.right.left;
                }
            }
        }
        return null;
    }

    private RBTree_Node get_sibling_far_child (RBTree_Node node) {
        if (node.parent != null) {
            if (node.parent.right == node) {
                if (node.parent.left != null) {
                    return node.parent.left.left;
                }
            }
            else if (node.parent.left == node) {
                if (node.parent.right != null) {
                    return node.parent.right.right;
                }
            }
        }
        return null;
    }

    private RBTree_Node get_sibling (RBTree_Node node) {
        if (node.parent != null) {
            if (node.parent.right == node) {
                return  node.parent.left;
            }
            else if (node.parent.left == node) {
                return node.parent.right;
            }
        }
        return null;
    }

    private void swap_node_value (RBTree_Node a, RBTree_Node b) {
        int tmp = a.value;
        a.value = b.value;
        b.value = tmp;
    }

    private RBTree_Node getMax (RBTree_Node node) {
        if (node == null) return null;
        RBTree_Node node_max = node;
        while (true) {
            if (node_max.right == null) break;
            node_max = node_max.right;
        }
        return node_max;
    }

    private RBTree_Node getMin (RBTree_Node node) {
        if (node == null) return null;
        RBTree_Node node_min = node;
        while (true) {
            if (node_min.left == null) break;
            node_min = node_min.left;
        }
        return node_min;
    }

    public static void main (String[] args) {
        int[] nums = new int[]{50, 37, 25, 18, 22, 31, 34, 33};
        RedBlackTree_List rbtree = new RedBlackTree_List(nums);
        rbtree.build_tree();

        Integer result = null;
        result = rbtree.search(33);
        result = rbtree.search(35);

        rbtree.delete(50); // Type C4.2
        rbtree.delete(37); // Type C2.1
        rbtree.delete(34); // (BST delete) Type A
        rbtree.delete(33); // Type C1 -> Case 2.1
        rbtree.delete(18); // Type C3 -> C4.1
        rbtree.delete(31); // Type C2.2 -> Type B
        rbtree.delete(22); // Type A
        rbtree.delete(25); // Type B

        System.out.println();
    }
}
