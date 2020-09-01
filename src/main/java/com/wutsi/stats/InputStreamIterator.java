package com.wutsi.stats;

import java.io.IOException;
import java.io.InputStream;

public interface InputStreamIterator {
    InputStream next() throws IOException;
    boolean hasNext();
}
