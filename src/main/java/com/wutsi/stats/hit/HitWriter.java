package com.wutsi.stats.hit;
import com.wutsi.stats.impl.AbstractWriter;

import java.io.IOException;
import java.io.Writer;

public class HitWriter extends AbstractWriter<Hit> {
    @Override
    protected void writeHeader(Writer writer) throws IOException {
        writer.append("\"time\",\"hitid\",\"storyid\",\"deviceid\",\"userid\"\n");
    }
}
