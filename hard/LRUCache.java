package hard;

import java.util.Hashtable;

class DLinkedNode 
{
    int key;
    int value;
    DLinkedNode pre;
    DLinkedNode next;
}

public class LRUCache 
{
	private Hashtable<Integer, DLinkedNode>  cache = new Hashtable<Integer, DLinkedNode>();
	private int count;
	private int capacity;
	private DLinkedNode head;
	private DLinkedNode tail;
	
	
	
	/**
	 * It will create an LRU cache with just two dummy nodes, head and tail linked to each other.
	 */
	public LRUCache(int capacity) 
	{
	    this.count = 0;
	    this.capacity = capacity;

	    head = new DLinkedNode();
	    head.pre = null;

	    tail = new DLinkedNode();
	    tail.next = null;

	    head.next = tail;
	    tail.pre = head;
	}
	
	/**
	 * Always add the new node right after head;
	 */
	private void addNode(DLinkedNode node)
	{
		// Step 1: Link the node's left and right pointers
	    node.pre = head;
	    node.next = head.next;

	    // Step 2: Update next and head
	    head.next.pre = node;
	    head.next = node;
	}

	/**
	 * Remove an existing node from the linked list.
	 */
	private void removeNode(DLinkedNode node)
	{
	    DLinkedNode pre = node.pre;
	    DLinkedNode next = node.next;

	    pre.next = next;
	    next.pre = pre;
	}

	/**
	 * Move certain node in between to the head.
	 */
	private void moveToHead(DLinkedNode node)
	{
	    this.removeNode(node);
	    this.addNode(node);
	}

	// pop the current tail. 
	private DLinkedNode popTail()
	{
	    DLinkedNode res = tail.pre;
	    this.removeNode(res);
	    return res;
	}

	

	public int get(int key) 
	{
	    DLinkedNode node = cache.get(key);
	    if(node == null)
	    {
	        return -1; // should raise exception here.
	    }

	    // move the accessed node to the head;
	    this.moveToHead(node);

	    return node.value;
	}


	public void set(int key, int value) 
	{
	    DLinkedNode node = cache.get(key);

	    // It means, it is a new key
	    if(node == null)
	    {
	        DLinkedNode newNode = new DLinkedNode();
	        newNode.key = key;
	        newNode.value = value;

	        this.cache.put(key, newNode);
	        this.addNode(newNode);

	        ++count;

	        if(count > capacity)
	        {
	            // pop the tail
	            DLinkedNode tail = this.popTail();
	            this.cache.remove(tail.key);
	            --count;
	        }
	    }
	    else
	    {
	        // update the value and move it to head
	        node.value = value;
	        this.moveToHead(node);
	    }
	}
	
	
}

