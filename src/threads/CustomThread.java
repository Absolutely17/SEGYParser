package threads;

import trace.*;

import java.nio.ByteBuffer;
import java.util.List;

public class CustomThread extends Thread {

    private byte[] bytes;

    private List<SEGYTrace> traces;

    private long numberTraces;

    private short dataSampleFormatCode;

    public CustomThread(ByteBuffer buff, List<SEGYTrace> traces, long numberTraces, short dataSampleFormatCode) {
        this.bytes = buff.array();
        this.traces = traces;
        this.numberTraces = numberTraces;
        this.dataSampleFormatCode = ConvertSampleFormat(dataSampleFormatCode);
    }

    public void run() {
        int readTraces = 0;
        SEGYTrace trace = new SEGYTrace(bytes);
        while (readTraces != numberTraces) {
            trace.readByThread(dataSampleFormatCode, readTraces);
            traces.add(trace);
            trace = new SEGYTrace(bytes);
            readTraces++;
        }
    }

    public short ConvertSampleFormat(short dataSampleFormatCode) {
        if (dataSampleFormatCode == 1 || dataSampleFormatCode == 2 || dataSampleFormatCode == 4 || dataSampleFormatCode == 5)
            return 4;
        else if (dataSampleFormatCode == 3)
            return 2;
        else return 1;
    }
}
