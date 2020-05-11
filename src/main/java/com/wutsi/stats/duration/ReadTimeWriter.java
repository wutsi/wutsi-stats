package com.wutsi.stats.duration;
import com.wutsi.stats.impl.AbstractWriter;
import com.wutsi.stats.readers.Reader;

import java.io.IOException;
import java.io.Writer;

public class ReadTimeWriter extends AbstractWriter<ReadTime> {

    @Override
    protected void writeHeader(Writer writer) throws IOException {
        writer.append("\"time\",\"productid\",\"readtime\"\n");
    }
}
