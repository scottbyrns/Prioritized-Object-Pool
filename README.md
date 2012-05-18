Prioritized-Object-Pool
=======================

Holds onto a pool of objects and return them in order of priority.


### Example
```java

        PrioritizedObjectPool<String> stringPrioritizedObjectPool = new PrioritizedObjectPool<String>();

        stringPrioritizedObjectPool.add("WhenEver", Priority.WHENEVER);
        stringPrioritizedObjectPool.add("Critical", Priority.CRITICAL);
        stringPrioritizedObjectPool.add("WhenEver", Priority.WHENEVER);

        String next = stringPrioritizedObjectPool.next();
        assertEquals(next, "Critical");
        next = stringPrioritizedObjectPool.next();
        assertEquals(next, "WhenEver");
        next = stringPrioritizedObjectPool.next();
        assertEquals(next, "WhenEver");
        next = stringPrioritizedObjectPool.next();
        assertEquals(next, null);
        
```