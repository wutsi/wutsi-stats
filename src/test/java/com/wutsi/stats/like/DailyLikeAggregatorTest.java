package com.wutsi.stats.like;

import com.wutsi.stats.Aggregator;
import com.wutsi.stats.AggregatorTestBase;
import org.junit.Test;

import java.time.LocalDate;

public class DailyLikeAggregatorTest extends AggregatorTestBase {
    protected Aggregator getAggregator() {
        return new DailyLikeAggregator(LocalDate.parse("2020-04-14"));
    }

    @Test
    public void happyPath() throws Exception {
        test(
                "/tracks/like/happy-path/2020-04-14-output.csv",
                "/tracks/like/happy-path/2020-04-14-000.csv",
                "/tracks/like/happy-path/2020-04-14-001.csv"
        );
    }

}
