package pt.ipg.mcm;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

public class Encripter extends Encription {

  public Encripter(byte[] msg, byte[] key) {
    super(msg, key, Type.ENCRIPTER);
  }

  public void encript() {
    int rep = (int) ((Math.random() * 20) + 4.5);
    for (int i = 0; i < rep; i++) {
      try {
        changeBits(i);
        alternatedShift(i);
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }

    String b64 = DatatypeConverter.printBase64Binary(msg);

    StringBuilder stringBuilder = new StringBuilder(b64.substring(0, 2));
    stringBuilder.append((char) ('f' + rep));
    stringBuilder.append(b64.substring(2));
    msg = stringBuilder.toString().getBytes();
  }


}
