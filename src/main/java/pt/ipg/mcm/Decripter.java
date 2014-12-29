package pt.ipg.mcm;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Decripter extends Encription {
  public Decripter(byte[] msg, byte[] key) {
    super(msg, key, Type.DECRIPTER);
  }

  public void decript() {
    byte repChar = msg[2];
    int rep = repChar - 'f';

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    for (int i = 0; i < msg.length; i++) {
      if(i!=2){
        byteArrayOutputStream.write(msg[i]);
      }
    }

    String b64Str = new String(byteArrayOutputStream.toByteArray());

    msg = DatatypeConverter.parseBase64Binary(b64Str);

    for (int i = rep-1; i >= 0; i--) {
      System.out.print(i + ",");
      try {
        alternatedShift(i);
        changeBits(i);
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }
    System.out.println();

  }
}
