import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.*;
import java.util.*;

import binHeader.*;
import org.apache.commons.io.FileUtils;
import textHeader.*;
import threads.*;
import trace.*;

public class Main {

	private static final int NUMBERS_CORE = 4;

	private static SEGYTextHeader textHeader;

	private static SEGYBinHeader binHeader;

	private static List<SEGYTrace> traces = new ArrayList<>();

	private static FileChannel channel;

	private static FileWriter writer;

	private static List<CustomThread> threads = new ArrayList<>();

	private static List<List<SEGYTrace>> tracesThreads = new ArrayList<>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd H-mm-ss");

	public static void main(String[] args) throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		File directory = new File("data");
		if (!directory.exists())
			directory.mkdir();
		//System.out.println("Введите путь до файла SGY.");
		//Scanner scanner = new Scanner(System.in);
		//String path = scanner.nextLine();
		//scanner.close();
		//if (path == null)
		//  System.out.println("Введенный путь пустой.");
		writer = new FileWriter("data/" + dateFormat.format(new Date()) + ".txt", false);
		InputStream cpResource = getResourcesAsStream("2008_V021.sgy");
		File tmpFile = File.createTempFile("file", "temp");
		FileUtils.copyInputStreamToFile(cpResource, tmpFile);
		try {
			channel = new FileInputStream(tmpFile).getChannel();
		} finally {
			tmpFile.delete();
		}
		readTextHeader();
		readBinHeader();
		printTextHeader();
		printBinaryHeader();
		long numberTraces = calculateNumberTraces();
		long tracesOnThread = numberTraces / 4;
		if (tracesOnThread > 100) {
			System.out.println("Multithreading mode: enabled");
			readTraceWithThreads(numberTraces, tracesOnThread);
            for (CustomThread t : threads) {
                t.join();
            }
			System.out.println("Program time spent on read all info = " + (System.currentTimeMillis() - startTime) + " ms");
            printTraceWithThreads();
		} else {
			System.out.println("Multithreading mode: disabled");
			readTraceWithoutThreads();
			System.out.println("Program time spent on read all info = " + (System.currentTimeMillis() - startTime) + " ms");
			printTraceWithoutThreads();
		}
		writer.close();
		System.out.println("Program time spent on all actions = " + (System.currentTimeMillis() - startTime) + " ms");
	}

	public static InputStream getResourcesAsStream(String s) {
		return Main.class
			.getClassLoader()
			.getResourceAsStream(s);
	}

	public static long calculateNumberTraces() throws IOException {
		long sizeTraces = channel.size() - 3200 - 400;
		int formatSize;
		int dataSampleFormatCode = binHeader.getDataSampleFormatCode();
		if (dataSampleFormatCode == 1 || dataSampleFormatCode == 2 || dataSampleFormatCode == 4 || dataSampleFormatCode == 5)
			formatSize = 4;
		else if (dataSampleFormatCode == 3)
			formatSize = 2;
		else formatSize = 1;
		int sizeOneTrace = 240 + binHeader.getNumberSamplesPerDataTrace() * formatSize;
		return sizeTraces / sizeOneTrace;
	}

	//Read

	public static void readTextHeader() {
		textHeader = new SEGYTextHeader(channel);
		textHeader.Read();
	}

	public static void readBinHeader() {
		binHeader = new SEGYBinHeader(channel);
		binHeader.Read();
	}

	private static void readTraceWithThreads(long numberTraces, long tracesOnThread) throws IOException {
		ByteBuffer buff = ByteBuffer.allocate((int)(tracesOnThread * (240 + binHeader.getNumberSamplesPerDataTrace() * 4)));
		for (int iterator = 0; iterator < NUMBERS_CORE; iterator++) {
			List<SEGYTrace> tracesOnThisThread = new ArrayList<>();
			tracesThreads.add(tracesOnThisThread);
			if (iterator == NUMBERS_CORE - 1) {
				buff = ByteBuffer.allocate((int)
					((numberTraces - tracesOnThread * 3) * (240 + binHeader.getNumberSamplesPerDataTrace() * 4))
				);
				channel.read(buff);
				threads.add(new CustomThread(
					buff,
					tracesOnThisThread,
					numberTraces - tracesOnThread * 3,
					binHeader.getDataSampleFormatCode()
				));
			} else {
				channel.read(buff);
				threads.add(new CustomThread(
					buff,
					tracesOnThisThread,
					tracesOnThread,
					binHeader.getDataSampleFormatCode()
				));
			}
			threads.get(iterator).start();
		}
	}

	public static void readTraceWithoutThreads() throws IOException {
		SEGYTrace trace = new SEGYTrace(channel);
		while (trace.read(binHeader.getDataSampleFormatCode())) {
			traces.add(trace);
			trace = new SEGYTrace(channel);
		}
	}

	// Prints

	public static void printTextHeader() throws IOException {
		for (String s : textHeader.getRecords()) {
			writer.write(s + "\n");
		}
	}

	public static void printBinaryHeader() throws IOException {
		writer.write(binHeader.GetOutPrint());
	}
	public static void printTraceWithoutThreads() throws IOException {
		for (SEGYTrace s : traces) {
			writer.write(s.printTraceHeader());
			writer.write(s.printDataTrace());
		}
	}
	public static void printTraceWithThreads() throws IOException {
		for (List<SEGYTrace> s : tracesThreads) {
			for (SEGYTrace trace : s) {
				writer.write(trace.printTraceHeader());
				writer.write(trace.printDataTrace());
			}
		}
	}
}
