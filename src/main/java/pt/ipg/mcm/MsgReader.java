package pt.ipg.mcm;

import java.util.Iterator;

public class MsgReader implements Iterator<Byte> {
  private byte[] msg;
  private int i;

  public MsgReader(byte[] msg) {
    this.msg = msg;
    i = 0;
  }

  @Override
  public boolean hasNext() {
    return i < msg.length;
  }

  @Override
  public Byte next() {
    return msg[i++];
  }

  @Override
  public void remove() {

  }
}
