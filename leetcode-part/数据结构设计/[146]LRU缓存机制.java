//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。 
//
// 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。 
//写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在
//写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
//
// 
//
// 进阶: 
//
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例: 
//
// LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回  1
//cache.put(3, 3);    // 该操作会使得关键字 2 作废
//cache.get(2);       // 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得关键字 1 作废
//cache.get(1);       // 返回 -1 (未找到)
//cache.get(3);       // 返回  3
//cache.get(4);       // 返回  4
// 
// Related Topics 设计 
// 👍 919 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    /**
     * 利用hash表 + 双端链表（队列）模拟实现LRU缓存机制。
     * 1.通过hash表根据key值计算链表结点的位置。
     * 2.把最近使用的结点加到链表头
     */

    Map<Integer, Node> map;
    Node dummyHead;
    Node dummyTail;
    int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap();
        this.capacity = capacity;
        dummyHead = new Node(-1, -1);
        dummyTail = new Node(-1, -1);
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            addToHead(node);
            if (map.size() > capacity) {
                Node removedNode = removeTail();
                map.remove(removedNode.key);
            }
        }
    }

    // 删除某个结点，即把该节点的前后结点直接相互连接
    private void deleteNode(Node node) {
        Node preNode = node.pre;
        Node nextNode = node.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
    }

    // 把某个结点加到链表的头结点
    private void addToHead(Node node) {
        Node temp = dummyHead.next;
        dummyHead.next = node;
        node.pre = dummyHead;
        node.next = temp;
        temp.pre = node;
    }

    // 把链表中间的某个结点转移到链表的头结点
    private void moveToHead(Node node) {
        deleteNode(node);
        addToHead(node);
    }

    // 删除链表的最后一个结点，并返回
    private Node removeTail() {
        Node tailNode = dummyTail.pre;
        tailNode.pre.next = dummyTail;
        dummyTail.pre = tailNode.pre;

        return tailNode;
    }
}

// 链表结点的定义
class Node {
    int key;
    int value;
    Node pre;
    Node next;
    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
