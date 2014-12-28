package pt.ipg.mcm;


public class BinaryHelper {
  String STRFORMAT = "%%%ds: %%-%ds\n";
  private byte b;


  public BinaryHelper(byte b) {
    this.b = b;
  }

  public byte getB() {
    return b;
  }

  public void setByte(byte b) {
    this.b = b;
  }

  public String getBinaryString() {
    StringBuilder sb = new StringBuilder(Integer.toBinaryString(Integer.valueOf(b)));
    sb.reverse();

    int fill0Bits = 8 - sb.length();
    while (fill0Bits-- > 0) {
      sb.append('0');
    }
    sb.reverse();

    return sb.toString();
  }

  public byte shiftLeft(byte shiftQuantity) {
    shiftQuantity %= 0x8;
    byte r = 0x8;
    r -= shiftQuantity;
    return shiftRight(r);
  }

  public byte shiftRight(byte shiftQuantity) {

    char c = (char) b;

    shiftQuantity %= 8;
    int bint = c & 255;

    int s = (bint >> shiftQuantity)&255;

    int sd = bint << (8 - shiftQuantity)&255;

    byte r = (byte) (s | sd);
    return r;
  }

  public byte changeBit(byte bit) {
    byte r = b;
    bit--;
    byte mask = (byte) (1 << bit);

    if ((mask & r) == 0) {
      r |= mask;
    } else {
      r &= ~mask;
    }
    return r;
  }

}
