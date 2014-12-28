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
      System.out.print(i + ",");
      try {
        changeBits(i);
        alternatedShift(i);
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }
    System.out.println();

    for (int i = 0; i < msg.length; i++) {
      System.out.print(msg[i] + ",");
    }
    System.out.println();

    String b64 = DatatypeConverter.printBase64Binary(msg);
    System.out.println("b64 enc:" + b64);

    StringBuilder stringBuilder = new StringBuilder(b64.substring(0, 2));
    stringBuilder.append((char) ('f' + rep));
    stringBuilder.append(b64.substring(2));
    msg = stringBuilder.toString().getBytes();
  }


}
