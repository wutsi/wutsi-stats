package com.wutsi.stats.hit;

import com.wutsi.stats.Aggregator;
import com.wutsi.stats.AggregatorTestBase;
import org.junit.Test;

import java.time.LocalDate;

public class DailyHitAggregatorTest extends AggregatorTestBase {
    protected Aggregator getAggregator() {
        return new DailyHitAggregator(LocalDate.parse("2020-04-14"));
    }

    @Test
    public void happyPath() throws Exception {
        test(
                "/tracks/hit/happy-path/2020-04-14-output.csv",
                "/tracks/hit/happy-path/2020-04-14-000.csv",
                "/tracks/hit/happy-path/2020-04-14-001.csv"
        );
    }

}
