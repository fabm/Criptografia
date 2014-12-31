package pt.ipg.mcm;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.Arrays;

public class Decripter extends Encription {
  public Decripter(byte[] msg, byte[] key) {
    super(msg, key, Type.DECRIPTER);
  }

  public void decript() {
    byte repChar = msg[0];
    int rep = repChar - 'f';

    String b64Str = new String(Arrays.copyOfRange(msg, 1, msg.length));

    msg = DatatypeConverter.parseBase64Binary(b64Str);

    for (int i = rep - 1; i >= 0; i--) {
      try {
        alternatedShift(i);
        changeBits(i);
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }

  }
}
