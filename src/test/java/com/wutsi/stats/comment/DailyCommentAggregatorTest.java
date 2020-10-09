package com.wutsi.stats.comment;

import com.wutsi.stats.Aggregator;
import com.wutsi.stats.AggregatorTestBase;
import org.junit.Test;

import java.time.LocalDate;

public class DailyCommentAggregatorTest extends AggregatorTestBase {
    protected Aggregator getAggregator() {
        return new DailyCommentAggregator(LocalDate.parse("2020-04-14"));
    }

    @Test
    public void happyPath() throws Exception {
        test(
                "/tracks/comment/happy-path/2020-04-14-output.csv",
                "/tracks/comment/happy-path/2020-04-14-000.csv",
                "/tracks/comment/happy-path/2020-04-14-001.csv"
        );
    }

}
