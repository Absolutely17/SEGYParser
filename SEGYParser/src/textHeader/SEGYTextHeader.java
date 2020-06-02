package textHeader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SEGYTextHeader {
    private int SIZE = 3200;
    private int MAX_LENGTH = 80;
    private int COUNT = 40;

    private List<String> records;
    private byte[] bytes;

    public SEGYTextHeader(ReadableByteChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        try {
            channel.read(buffer);
            bytes = buffer.array();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    public void Read(){
        records = new ArrayList<String>(COUNT);
        for (int i=0;i<COUNT;i++)
        {
            int offset = i * MAX_LENGTH;
            String record = new String(bytes, offset, MAX_LENGTH, Charset.forName("Cp1047"));
            records.add(record);
        }
    }

    public List<String> getRecords()
    {
        return records;
    }
}
