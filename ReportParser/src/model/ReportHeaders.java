package model;

import org.apache.poi.ss.usermodel.Row;

import java.util.*;
import java.util.regex.*;

public class ReportHeaders {

    private List<List<String>> lineOfHeaders = new ArrayList<>();

    private List<String> headers = new ArrayList<>();

    private final Pattern pattern = Pattern.compile("[^{│,}]+");

    public ReportHeaders() {
    }

    // Считывание заголовки столбцов из текстового файла
    public void combineAndReadHeadersFromTextFile(List<String> allines, int startRow, int endRow) {
        int maxSize = 0;
        for (int iterator = startRow - 4; iterator < startRow - 1; iterator++) {
            List<String> lineOfHeader = new ArrayList<>();
            Matcher matcher = pattern.matcher(allines.get(iterator));
            while (matcher.find()) {
                lineOfHeader.add(matcher.group());
            }
            if (maxSize < lineOfHeader.size()) {
                maxSize = lineOfHeader.size();
            }
            lineOfHeaders.add(lineOfHeader);
        }
        for (int iterator = 0; iterator < maxSize; iterator++) {
            StringBuilder header = new StringBuilder();
            for (List<String> s : lineOfHeaders) {
                if (s.size() > iterator) {
                    header.append(s.get(iterator)).append(" ");
                }
            }
            headers.add(header.toString().replaceAll("[\\s]{2,}", " "));
        }
    }
    // Считывание заголовков столбцов из Excel файла
    public void combineAndReadHeadersFromExcelFile(List<Row> rows) {
        for (int iterator = 0; iterator < rows.get(0).getLastCellNum(); iterator++) {
            StringBuilder header = new StringBuilder();
            for (Row r : rows) {
                header.append(r.getCell(iterator)).append(" ");
            }
            headers.add(header.toString().replaceAll("[\\s]{2,}", " "));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringOut = new StringBuilder();
        for (int i=0;i<headers.size();i++) {
            stringOut.append(headers.get(i).trim()).append(" - ").append("{").append(i).append("}").append('\n');
        }
        for (int i = 0; i < headers.size(); i++) {
            stringOut.append(String.format("%13s", "(" + i + ")"));
        }
        stringOut.append("\n");
        return stringOut.toString();
    }
}
