package com.wutsi.stats.viewers;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;

public class ViewerWriter {

    public void write(List<Viewer> Viewers, OutputStream out) throws IOException, CsvException {
        Writer writer = new OutputStreamWriter(out);

        try{
            StatefulBeanToCsv<Viewer> csvWriter = new StatefulBeanToCsvBuilder<Viewer>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .build();

            writer.append("\"time\",\"productid\",\"count\"\n");
            csvWriter.write(Viewers);
        } finally {
            writer.close();
        }
    }
}
