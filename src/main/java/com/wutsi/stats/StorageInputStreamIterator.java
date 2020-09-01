package com.wutsi.stats;

import com.wutsi.core.storage.StorageService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class StorageInputStreamIterator implements InputStreamIterator {
    private int index = 0;
    private final List<URL> urls;
    private final StorageService storage;

    public StorageInputStreamIterator(final List<URL> urls, final StorageService storage) {
        this.urls = urls;
        this.storage = storage;
    }

    @Override
    public InputStream next() throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            storage.get(urls.get(index++), out);
            return new ByteArrayInputStream(out.toByteArray());
        }

    }

    @Override
    public boolean hasNext() {
        return index < urls.size();
    }
}
