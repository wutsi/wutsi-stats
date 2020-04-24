package com.wutsi.stats.shares;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class ShareWriter {

    public void write(List<Share> shares, OutputStream out) throws IOException, CsvException {
        Writer writer = new OutputStreamWriter(out);

        try{
            StatefulBeanToCsv<Share> csvWriter = new StatefulBeanToCsvBuilder<Share>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .build();

            writer.append("\"time\",\"productid\",\"count\"\n");
            csvWriter.write(shares);
        } finally {
            writer.close();
        }
    }
}
