package converter;

import java.nio.ByteBuffer;

public class Converter {
    public static short ConvertByteToShort(byte[] bytes, int offset){
        return ByteBuffer.wrap(bytes, offset, 2).getShort();
    }
    public static int ConvertByteToInt(byte[] bytes, int offset)
    {
        return ByteBuffer.wrap(bytes, offset,4 ).getInt();
    }
    public static long ConvertByteToLong(byte[] bytes, int offset)
    {
        return ByteBuffer.wrap(bytes, offset, 8).getLong();
    }
}
