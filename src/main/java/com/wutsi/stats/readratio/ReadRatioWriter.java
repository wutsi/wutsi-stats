package com.wutsi.stats.readratio;
import com.wutsi.stats.impl.AbstractWriter;

import java.io.IOException;
import java.io.Writer;

public class ReadRatioWriter extends AbstractWriter<ReadRatio> {
    @Override
    protected void writeHeader(Writer writer) throws IOException {
        writer.append("\"time\",\"hitid\",\"readtime\",\"readratio\"\n");
    }
}
