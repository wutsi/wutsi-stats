package com.wutsi.stats.readratio;

import com.wutsi.stats.Aggregator;
import com.wutsi.stats.AggregatorTestBase;
import org.junit.Test;

import java.time.LocalDate;

public class DailyReadRatioAggregatorTest extends AggregatorTestBase {
    protected Aggregator getAggregator() {
        return new DailyReadRatioAggregator(LocalDate.parse("2020-04-14"));
    }

    @Test
    public void happyPath() throws Exception {
        test(
                "/tracks/readratio/happy-path/2020-04-14-output.csv",
                "/tracks/readratio/happy-path/2020-04-14-000.csv",
                "/tracks/readratio/happy-path/2020-04-14-001.csv"
        );
    }


}
