package com.wutsi.stats.device;
import com.wutsi.stats.impl.AbstractWriter;

import java.io.IOException;
import java.io.Writer;

public class DeviceWriter extends AbstractWriter<Device> {
    @Override
    protected void writeHeader(Writer writer) throws IOException {
        writer.append("\"deviceid\",\"userid\"\n");
    }
}
