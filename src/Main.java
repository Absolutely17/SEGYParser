import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static SEGYTextHeader textHeader;
    private static SEGYBinHeader binHeader;
    private static List<SEGYTrace> traces = new ArrayList<>();
    private static FileChannel channel;
    public static void main(String[] args) {
        System.out.println("Введите путь до файла SGY.");
        //Scanner scanner = new Scanner(System.in);
        String path = "D:\\SEGYParser\\src\\longv2.sgy";
        //scanner.close();
        if (path == null)
            System.out.println("Введенный путь пустой.");
        try {
            channel = new FileInputStream(path).getChannel();
            readTextHeader();
            readBinHeader();
            printTextHeader();
            printBinaryHeader();
            readTrace();
            printTrace();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
    }
    public static void readTextHeader()
    {
        textHeader = new SEGYTextHeader(channel);
        textHeader.Read();
    }
    public static void readBinHeader(){
        binHeader = new SEGYBinHeader(channel);
        binHeader.Read();
    }
    public static void readTrace()
    {
        SEGYTrace trace = new SEGYTrace(channel);
        while (trace.Read(binHeader.getDataSampleFormatCode())) {
            traces.add(trace);
            trace = new SEGYTrace(channel);
        }
    }
    public static void printTextHeader(){
        for (String s : textHeader.getRecords())
        {
            System.out.println(s);
        }
    }
    public static void printBinaryHeader(){
        System.out.println(binHeader.GetOutPrint());
    }
    public static void printTrace()
    {
        for(SEGYTrace s : traces)
        {
            System.out.println(s.printTraceHeader());
            System.out.println(s.printDataTrace());
        }
    }
}
