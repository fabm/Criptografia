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

    StringBuilder stringBuilder = new StringBuilder();
    char repChar = (char) ('f'+ rep);
    stringBuilder.append(repChar);
    stringBuilder.append(b64);
    msg = stringBuilder.toString().getBytes();
  }


}
