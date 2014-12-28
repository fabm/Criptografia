package pt.ipg.mcm;

import java.io.IOException;

public class InfiniteReaderKey {
  private byte[] key;
  private int i;

  public InfiniteReaderKey(byte[] key) {
    this.key = key;
  }

  public void startReader(int i) throws IOException {
    this.i = i;
  }

  public byte getNext8Rest() throws IOException {
    if (!(i < key.length)) {
      i = 0;
    }
    byte v = key[i++];
    if (v < 0) {
      v *= -1;
    }
    return (byte) (v%8);
  }
}
