import java.io.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;

import model.*;
import org.apache.commons.io.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ReportParser {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd H-mm-ss");

    private static FileWriter writer;

    private static int startRow;

    private static int endRow;

    private static List<ReportData> dataList = new ArrayList<>();

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
        String inputFile = "raport1.txt";
        // todo Добавить ввод строк от пользователя
        startRow = 32;
        endRow = 127;
        doActionsFromSuffixInputFile(inputFile);
        writeDataToFile();
    }
    private static void doActionsFromSuffixInputFile(String inputFile) throws IOException, ParseException {
        File tmpFile = createTmpFile(inputFile);
        Workbook excelFile;
        switch (inputFile.substring(inputFile.lastIndexOf('.') + 1)) {
            case "txt":
                readTxtFile(tmpFile.toPath());
                break;
            case "xls":
                excelFile = new HSSFWorkbook(new FileInputStream(tmpFile));
                readDataFromExcelFile(excelFile);
                break;
            case "xlsx":
                excelFile = new XSSFWorkbook(new FileInputStream(tmpFile));
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
    private static void readDataFromExcelFile(Workbook file) {
        Sheet sheet = file.getSheetAt(0);
        for (int iterator = startRow - 1; iterator < endRow - startRow - 1 && iterator <= sheet.getLastRowNum(); iterator++) {
            Row row = sheet.getRow(iterator);
            dataList.add(new ReportData(
                    row.getCell(0).getDateCellValue(),
                    row.getCell(1).getNumericCellValue(),
                    row.getCell(2).getStringCellValue(),
                    row.getCell(3).getStringCellValue(),
                    row.getCell(4).getNumericCellValue(),
                    row.getCell(5).getNumericCellValue(),
                    row.getCell(6).getNumericCellValue(),
                    row.getCell(7).getNumericCellValue(),
                    row.getCell(8).getNumericCellValue(),
                    row.getCell(9).getNumericCellValue(),
                    row.getCell(10).getNumericCellValue(),
                    row.getCell(11).getStringCellValue()
            ));
        }
    }

    private static void readTxtFile(Path targetFile) throws IOException, ParseException {
        List<String> allLines = Files.readAllLines(targetFile);
        for (int iterator = startRow; iterator < endRow; iterator++) {
            String currentLine = allLines.get(iterator);
            dataList.add(new ReportData(currentLine));
        }
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
