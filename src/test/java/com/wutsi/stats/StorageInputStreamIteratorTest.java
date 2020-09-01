package com.wutsi.stats;

import com.wutsi.core.storage.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StorageInputStreamIteratorTest {
    @Mock
    private StorageService storage;

    private StorageInputStreamIterator iterator;

    @Before
    public void setUp() throws Exception {
        List<URL> urls = new ArrayList();
        urls.add(new URL("http://www.google.ca/a.txt"));

        iterator = new StorageInputStreamIterator(urls, storage);
    }


    @Test
    public void next() throws Exception {
        assertNotNull(iterator.next());
    }

    @Test
    public void hasNext() throws Exception {
        assertTrue(iterator.hasNext());
    }

    @Test
    public void hasNextFalse() throws Exception {
        iterator.next();
        assertFalse(iterator.hasNext());
    }

}
