package com.wutsi.stats.share;

import com.wutsi.stats.Aggregator;
import com.wutsi.stats.AggregatorTestBase;
import org.junit.Test;

import java.time.LocalDate;

public class DailyShareAggregatorTest extends AggregatorTestBase {
    protected Aggregator getAggregator() {
        return new DailyShareAggregator(LocalDate.parse("2020-04-14"));
    }

    @Test
    public void happyPath() throws Exception {
        test(
                "/tracks/share/happy-path/2020-04-14-output.csv",
                "/tracks/share/happy-path/2020-04-14-000.csv",
                "/tracks/share/happy-path/2020-04-14-001.csv"
        );
    }

    /**
     * Make sure that we do not process bot events
     */
    @Test
    public void bot() throws Exception {
        test(
                "/tracks/share/bot/2020-04-14-output.csv",
                "/tracks/share/bot/2020-04-14-000.csv",
                "/tracks/share/bot/2020-04-14-001.csv"
        );
    }

    /**
     * Make sure that we only process events associated with the provided date
     */
    @Test
    public void date() throws Exception {
        test(
                "/tracks/share/date/2020-04-14-output.csv",
                "/tracks/share/date/2020-04-14-000.csv",
                "/tracks/share/date/2020-04-14-001.csv",
                "/tracks/share/date/2020-04-15-000.csv"
        );
    }

}
