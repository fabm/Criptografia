package pt.ipg.mcm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Encription {
  protected byte[] msg;
  protected byte[] key;
  protected InfiniteReaderKey keyReader;
  protected final Type type;

  protected static enum Type {
    DECRIPTER(0), ENCRIPTER(1);

    private int parity;

    Type(int parity) {
      this.parity = parity;
    }
  }

  public Encription(byte[] msg, byte[] key, Type type) {
    this.msg = msg;
    this.key = key;
    this.keyReader = new InfiniteReaderKey(key);
    this.type = type;
  }

  public byte[] getMsg() {
    return msg;
  }

  public byte[] getKey() {
    return key;
  }

  protected void changeBits(int i) throws IOException {
    keyReader.startReader(i);
    MsgReader msgReader = new MsgReader(msg);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    while (msgReader.hasNext()) {
      byte data = msgReader.next();
      BinaryHelper binaryHelper = new BinaryHelper(data);
      byteArrayOutputStream.write(binaryHelper.changeBit(keyReader.getNext8Rest()));
    }
    msg = byteArrayOutputStream.toByteArray();
  }

  protected void alternatedShift(int i) throws IOException {
    keyReader.startReader(i);
    MsgReader msgReader = new MsgReader(msg);
    int order = 0;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    while (msgReader.hasNext()) {
      byte data = msgReader.next();
      byte shift = keyReader.getNext8Rest();
      BinaryHelper binaryHelper = new BinaryHelper(data);
      if (order % 2 == type.parity) {
        byteArrayOutputStream.write(binaryHelper.shiftLeft(shift));
      } else {
        byteArrayOutputStream.write(binaryHelper.shiftRight(shift));
      }
      order++;
    }
    msg = byteArrayOutputStream.toByteArray();
  }

}
