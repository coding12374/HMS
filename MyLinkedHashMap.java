package hospital;

import java.util.*;

public class MyLinkedHashMap<K, V> {
    private Node<K, V> head;
    private Node<K, V> tail;
    private Map<K, Node<K, V>> map;

    public MyLinkedHashMap() {
        this.head = null;
        this.tail = null;
        this.map = new HashMap<>();
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            map.get(key).value = value;
        } else {
            Node<K, V> newNode = new Node<>(key, value);
            map.put(key, newNode);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }
    }

    public V get(K key) {
        Node<K, V> node = map.get(key);
        return node != null ? node.value : null;
    }

    public void remove(K key) {
        Node<K, V> node = map.remove(key);
        if (node != null) {
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                tail = node.prev;
            }
        }
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public Set<K> keySet() {
        Set<K> keys = new LinkedHashSet<>();
        Node<K, V> current = head;
        while (current != null) {
            keys.add(current.key);
            current = current.next;
        }
        return keys;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
}
