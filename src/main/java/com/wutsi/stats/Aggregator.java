package com.wutsi.stats;

import java.io.IOException;
import java.io.OutputStream;

public interface Aggregator {
    void aggregate(InputStreamIterator iterator, OutputStream output) throws IOException;
}
