package com.wutsi.stats.viewers;

import com.wutsi.stats.InputStreamIterator;
import com.wutsi.stats.impl.ClasspathInputStreamIterator;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DailyViewersAggregatorTest {
    DailyViewersAggregator aggregator;

    @Test
    public void aggregate() throws Exception {
        // GIVEN
        InputStreamIterator iterator = createIterator("/tracks/2020-04-14-000.csv", "/tracks/2020-04-14-001.csv");
        OutputStream out = new ByteArrayOutputStream();

        // WHEN
        aggregator = new DailyViewersAggregator(LocalDate.parse("2020-04-14"));
        aggregator.aggregate(iterator, out);

        // THEN
        String result = out.toString();
        assertEquals("Hello world", result);
    }


    private InputStreamIterator createIterator(String...paths) {
        return new ClasspathInputStreamIterator(Arrays.asList(paths));
    }
}
