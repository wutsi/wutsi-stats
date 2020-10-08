package com.wutsi.stats.share;
import com.wutsi.stats.impl.AbstractWriter;

import java.io.IOException;
import java.io.Writer;

public class ShareWriter extends AbstractWriter<Share> {
    @Override
    protected void writeHeader(Writer writer) throws IOException {
        writer.append("\"time\",\"hitid\",\"count\"\n");
    }
}
