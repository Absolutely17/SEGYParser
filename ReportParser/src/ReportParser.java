import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;

import model.*;
import org.apache.commons.io.*;
import org.apache.poi.hmef.extractor.HMEFContentsExtractor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.hmef.*;

public class ReportParser {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd H-mm-ss");

    private static FileWriter writer;

    private static int startRow;

    private static int endRow;

    private static List<ReportData> dataList = new ArrayList<>();

    private static ReportHeaders headers;

    public static void main(String[] args) throws IOException, ParseException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        writer = new FileWriter("data/report_" + dateFormat.format(new Date()) + ".txt", false);
        //System.out.println("Введите путь до файла с рапортом.");
        //Scanner scanner = new Scanner(System.in);
        //String path = scanner.nextLine();
        //scanner.close();
        //if (path == null)
        //  System.out.println("Введенный путь пустой.");
        // todo Добавить ввод строк от пользователя
        startRow = 32;
        endRow = 127;
        String inputFile = "raport1.xls";
        doActionsFromSuffixInputFile(inputFile);
        writeHeadersToFile();
        writeDataToFile();
        writer.close();
    }
    private static void doActionsFromSuffixInputFile(String inputFile) throws IOException, ParseException {
        File tmpFile = createTmpFile(inputFile);
        Workbook excelFile;
        switch (inputFile.substring(inputFile.lastIndexOf('.') + 1)) {
            case "dat":
                readTextFile(tmpFile.toPath());
                break;
            case "xls":
                excelFile = new HSSFWorkbook(new FileInputStream(tmpFile));
                readHeadersFormExcelFile(excelFile);
                readDataFromExcelFile(excelFile);
                break;
            case "xlsx":
                excelFile = new XSSFWorkbook(new FileInputStream(tmpFile));
                readHeadersFormExcelFile(excelFile);
                readDataFromExcelFile(excelFile);
                break;
        }
    }
    private static File createTmpFile(String inputFile) throws IOException {
        InputStream cpResource = getResourcesAsStream(inputFile);
        File tmpFile = File.createTempFile("file", "temp");
        FileUtils.copyInputStreamToFile(cpResource, tmpFile);
        return tmpFile;
    }

    // ---------------------------READ EXCEL FILE--------------------------------
    private static void readHeadersFormExcelFile(Workbook file) {
        headers = new ReportHeaders();
        Sheet sheet = file.getSheetAt(0);
        List<Row> rows = new ArrayList<>();
        rows.add(sheet.getRow(27));
        rows.add(sheet.getRow(28));
        rows.add(sheet.getRow(29));
        headers.combineAndReadHeadersFromExcelFile(rows);
    }
    private static void readDataFromExcelFile(Workbook file) {
        Sheet sheet = file.getSheetAt(0);
        for (int iterator = startRow - 1; iterator < endRow - startRow - 1 && iterator <= sheet.getLastRowNum(); iterator++) {
            Row row = sheet.getRow(iterator);
            ReportData data = new ReportData();
            for (Cell c :row) {
                data.addNewValueToData(c);
            }
            dataList.add(data);
        }
    }
    // ---------------------------READ TEXT FILE--------------------------------
    private static void readTextFile(Path targetFile) throws IOException, ParseException {
        List<String> allLines = Files.readAllLines(targetFile, Charset.forName("cp866"));
        readHeadersTextFile(allLines);
        for (int iterator = startRow; iterator < endRow; iterator++) {
            String currentLine = allLines.get(iterator);
            dataList.add(new ReportData(currentLine));
        }
    }

    private static void readHeadersTextFile(List<String> allLines) {
        headers = new ReportHeaders();
        headers.combineAndReadHeadersFromTextFile(allLines, startRow, endRow);
    }

    // ---------------------------WRITING--------------------------------
    private static void writeHeadersToFile() throws IOException {
        writer.write(headers.toString());
    }
    private static void writeDataToFile() throws IOException {
        for (ReportData reportData : dataList) {
            writer.write(reportData.toString());
        }
    }

    private static InputStream getResourcesAsStream(String s) {
        return ReportParser.class
                .getClassLoader()
                .getResourceAsStream(s);
    }
}
