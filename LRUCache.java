package leetcode.sqrtx;

import java.util.HashMap;

public class LRUCache {

	Entry[] bucket ;
	int capacity;
	int bucketSize;
	static int timeTag;
	static int oldest;
	static int lastSet;
	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(3);
		
		LRUCache lruCache1=lruCache;

		//这里用hashmap的原因在于，因为两个这里运用hash表来决定存储地址，如果两个存储地址相同，那么存在一个数组上就会被覆盖，因此，必须用链表。
		//1,[set(2,1),get(2),set(3,2),get(2),get(3)]
		lruCache.set(1, 1);
		lruCache.set(2, 2);//''
		lruCache.set(3, 3);
		lruCache.set(4, 4);
		//System.out.println(lruCache.get(2));
		//lruCache.set(4, 1);
		System.out.println(lruCache.get(4));
		System.out.println(lruCache.get(3));
		System.out.println(lruCache.get(2));
		System.out.println(lruCache.get(1));
//		
//		System.out.println(lruCache.get(2));
//		lruCache.set(4, -1);
//		
//		System.out.println(lruCache.get(1));
//		System.out.println(lruCache.get(2));
//		System.out.println(lruCache.get(5));
		
		lruCache.set(10,13);lruCache.set(3,17);lruCache.set(6,11);lruCache.set(10,5);lruCache.set(9,10);lruCache.get(13);lruCache.set(2,19);lruCache.get(2);lruCache.get(3);lruCache.set(5,25);lruCache.get(8);lruCache.set(9,22);lruCache.set(5,5);lruCache.set(1,30);lruCache.get(11);lruCache.set(9,12);lruCache.get(7);lruCache.get(5);lruCache.get(8);lruCache.get(9);lruCache.set(4,30);lruCache.set(9,3);lruCache.get(9);lruCache.get(10);lruCache.get(10);lruCache.set(6,14);lruCache.set(3,1);lruCache.get(3);lruCache.set(10,11);lruCache.get(8);lruCache.set(2,14);lruCache.get(1);lruCache.get(5);lruCache.get(4);lruCache.set(11,4);lruCache.set(12,24);lruCache.set(5,18);lruCache.get(13);lruCache.set(7,23);lruCache.get(8);lruCache.get(12);lruCache.set(3,27);lruCache.set(2,12);lruCache.get(5);lruCache.set(2,9);lruCache.set(13,4);lruCache.set(8,18);lruCache.set(1,7);lruCache.get(6);lruCache.set(9,29);lruCache.set(8,21);lruCache.get(5);lruCache.set(6,30);lruCache.set(1,12);lruCache.get(10);lruCache.set(4,15);lruCache.set(7,22);lruCache.set(11,26);lruCache.set(8,17);lruCache.set(9,29);lruCache.get(5);lruCache.set(3,4);lruCache.set(11,30);lruCache.get(12);lruCache.set(4,29);lruCache.get(3);lruCache.get(9);lruCache.get(6);lruCache.set(3,4);lruCache.get(1);lruCache.get(10);lruCache.set(3,29);lruCache.set(10,28);lruCache.set(1,20);lruCache.set(11,13);lruCache.get(3);lruCache.set(3,12);lruCache.set(3,8);lruCache.set(10,9);lruCache.set(3,26);lruCache.get(8);lruCache.get(7);lruCache.get(5);lruCache.set(13,17);lruCache.set(2,27);lruCache.set(11,15);lruCache.get(12);lruCache.set(9,19);lruCache.set(2,15);lruCache.set(3,16);lruCache.get(1);lruCache.set(12,17);lruCache.set(9,1);lruCache.set(6,19);lruCache.get(4);lruCache.get(5);lruCache.get(5);lruCache.set(8,1);lruCache.set(11,7);lruCache.set(5,2);lruCache.set(9,28);lruCache.get(1);lruCache.set(2,2);lruCache.set(7,4);lruCache.set(4,22);lruCache.set(7,24);lruCache.set(9,26);lruCache.set(13,28);lruCache.set(11,26);

	}
	
	static void t(int i ){
		System.out.println(i);
	}
	
	public LRUCache(int capacity) {
	     // 初始化 table 数组  
	    bucket = new Entry[capacity];            // ①  
		this.capacity = capacity;
		bucketSize = 0;
		oldest = 0;
		timeTag = 0;
		lastSet = 0;
    }
    
    public int get(Integer key) {
    	if (key == null)   
    	     return -1;   
    	 // 根据该 key 的 hashCode 值计算它的 hash 码  
    	 int hash = hash(key.hashCode());   
    	 // 直接取出 table 数组中指定索引处的值，  // 搜索该 Entry 链的下一个 Entr
    	 for (Entry e = bucket[indexFor(hash, bucket.length)];   e != null;    e = e.next)         // ①  
    	 {   
    	     int k;   
    	     // 如果该 Entry 的 key 与被搜索 key 相同  
    	     if (e.hash == hash && ((k = e.key) == key   || key.equals(k)))  {
    	    	 if (e.time == oldest) {
					oldest++;
				}
    	    	e.time = timeTag;
				timeTag++;
				lastSet = 0;
    	        return e.value;   
    	     }
    	 }    
        return -1;
    }
    
    public void set(Integer key, int value) {
    	
    	
		 // 根据 key 的 keyCode 计算 Hash 值  
		int hash = hash(key.hashCode());   
		 // 搜索指定 hash 值在对应 table 中的索引  
		int i = indexFor(hash, bucket.length); 
    	for (Entry e = bucket[i]; e != null; e=e.next) {
			int k;
			// 找到指定 key 与需要放入的 key 相等（hash 值相同  
		     // 通过 equals 比较放回 true）  
		     if (e.hash == hash && ((k = e.key) == key  || key.equals(k)))   
		     {   
		         int oldValue = e.value;   
		         e.value = value; 
		         e.time = timeTag;
		         timeTag++;
		         lastSet = 1;
		         return ;   
		     }  
		}

    	// 如果 i 索引处的 Entry 为 null，表明此处还没有 Entry  将 key、value 添加到 i 索引处  
    	addEntry(hash, key, value, i); 
    }
    
    void addEntry(int hash, int key,int value, int bucketIndex){
    	// 获取指定 bucketIndex 索引处的 Entry   
        Entry e = bucket[bucketIndex];     // ①  
        // 将新创建的 Entry 放入 bucketIndex 索引处，并让新的 Entry 指向原来的 Entry   时间戳和从0开始
        bucket[bucketIndex] = new Entry(hash, timeTag,key, value, e); 
        timeTag++;
        lastSet = 1;
        //bucketSize++;
        // 如果 Map 中的 key-value 对的数量超过了极限  
         if (bucketSize++ >=capacity)   {
        	while (!delOldEntry(oldest)) {
				oldest++;
			}
        	
        	bucketSize--;
        }
    }
    
    boolean delOldEntry(int timeTag){
    	for (int i = 0; i < bucket.length; i++) {
    		Entry temp = bucket[i];
    		for (Entry e = bucket[i]; e != null;temp = e, e=e.next) {
    			
    			if (e.time == timeTag) {
    				if (e.next != null) {
						temp.next= e.next;
					}else {
						if (temp != e) {
							temp.next = null;
						}else {
							bucket[i] = null;
						}
					} 
    				oldest++;
    				return true;
    			}
    			
    		}
    		
		}
    	return false;
    }
    
    static int hash(int h)   
    {   
        h ^= (h >>> 20) ^ (h >>> 12);   
        return h ^ (h >>> 7) ^ (h >>> 4);   
    } 
    
    static int indexFor(int h, int length)   
    {   
        return h & (length-1);   
    }
}

class Entry
{
	int key;
	int value;
	int hash;
	int time;
	Entry next;
	
	public Entry(int hash,int time,int key,int value,Entry e){
		this.key = key;
		this.value = value;
		this.next = e;
		this.hash = hash;
		this.time = time;
	}
}
