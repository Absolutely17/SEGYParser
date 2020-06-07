package model;

import org.apache.poi.ss.usermodel.Cell;

import java.text.*;
import java.util.*;
import java.util.regex.*;

public class ReportData {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm");

    private final Pattern pattern = Pattern.compile("[0-9]*[.,-:]?[0-9]+|[а-яА-Яa-zA-ZЁ].+");

    private List<String> dataFromTxt = new ArrayList<>();

    public ReportData() { }

    public ReportData(String line) throws ParseException {
        convertLineToData(line);
    }

    // Считывание данных с текстового файла
    private void convertLineToData(String line) throws ParseException {
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            dataFromTxt.add(line.substring(matcher.start(), matcher.end()).replace(",", ".").replaceAll("[\\s]{2,}", " "));
        }
    }
    // Считывание данных с Excel файла
    public void addNewValueToData(Cell cell) {
        switch (cell.getCellType()) {
            case 0:
                if (cell.getColumnIndex() == 0) {
                    dataFromTxt.add(dateFormat.format(cell.getDateCellValue()));
                } else {
                    dataFromTxt.add(String.valueOf(cell.getNumericCellValue()));
                }
                break;
            case 1:
                dataFromTxt.add(cell.getStringCellValue().replaceAll("[\\s]{2,}", " "));
                break;
        }
    }
    @Override
    public String toString() {
        StringBuilder outString = new StringBuilder();
        for(String s : dataFromTxt) {
            outString.append(String.format("%13s",s));
        }
        outString.append("\n");
        return outString.toString();
    }
}
