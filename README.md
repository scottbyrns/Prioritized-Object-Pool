Prioritized-Object-Pool
=======================

Holds onto a pool of objects and return them in order of priority.

#### Repository
```xml
<repositories>
    <repository>
        <id>scottbyrns-snapshots</id>
        <url>https://github.com/scottbyrns/Scottbyrns-Maven-Repo/raw/master/snapshots</url>
    </repository>
</repositories>
```

#### Dependency
```xml
<dependency>
    <groupId>com.scottbyrns</groupId>
    <artifactId>Mongo-Utilities</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

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