package org.example.datastructures;

import java.util.HashMap;

public class LRUCache {
    HashMap<Integer, Node> map = new HashMap<>();
    int capacity;
    //least recently used
    Node left;
    //most recent;
    Node right;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        left = new Node(0, 0);
        right = new Node(0, 0);

        //initiate doubly linked list
        left.next = right;
        right.previous = left;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.put(4, 4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }

    //remove node from list
    public void remove(Node node) {
        Node prev = node.previous;
        Node next = node.next;
        prev.next = next;
        next.previous = prev;
    }

    //insert node at right
    public void insert(Node node) {
        Node prev = right.previous;
        Node next = right;
        prev.next = node;
        next.previous = node;
        node.next = next;
        node.previous = prev;

    }

    public int get(int key) {
        if (map.containsKey(key)) {
            remove(map.get(key));
            insert(map.get(key));
            return map.get(key).value;
        } else return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        map.put(key, new Node(key, value));
        insert(map.get(key));
        if (map.size() > capacity) {
            //remove/delete/evict from the doubly list and from map
            Node lru = left.next;
            remove(lru);
            map.remove(lru.key);
        }
    }

}

class Node {
    public int key;
    public int value;
    public Node previous;
    public Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}