package com.wutsi.stats.follower;

import com.wutsi.stats.Aggregator;
import com.wutsi.stats.AggregatorTestBase;
import org.junit.Test;

import java.time.LocalDate;

public class DailyFollowerAggregatorTest extends AggregatorTestBase {
    protected Aggregator getAggregator() {
        return new DailyFollowerAggregator(LocalDate.parse("2020-04-14"));
    }

    @Test
    public void happyPath() throws Exception {
        test(
                "/tracks/follower/happy-path/2020-04-14-output.csv",
                "/tracks/follower/happy-path/2020-04-14-000.csv",
                "/tracks/follower/happy-path/2020-04-14-001.csv"
        );
    }


}
