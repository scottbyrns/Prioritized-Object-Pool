package com.goodtech;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Copyright (C) 2012 by Scott Byrns
 * http://github.com/scottbyrns
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p/>
 * Created 5/17/12 6:21 PM
 */
public class TestPrioritizedObjectPool
{
    @Test
    public void testPool () {
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
    }

    @Test
    public void testRecycle () {
        PrioritizedObjectPool<String> stringPrioritizedObjectPool = new PrioritizedObjectPool<String>();

       stringPrioritizedObjectPool.add("WhenEver", Priority.WHENEVER);
       stringPrioritizedObjectPool.add("Critical", Priority.CRITICAL);
       stringPrioritizedObjectPool.add("WhenEver", Priority.WHENEVER);

       stringPrioritizedObjectPool.recycle();

       String next = stringPrioritizedObjectPool.next();
       assertEquals(next, null);
   }
}
