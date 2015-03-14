package CopiarFichero;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopiarFicheroEjemplo {
	
	public CopiarFicheroEjemplo() {
		try {
			// Create channel on the source
			FileChannel srcChannel = new FileInputStream("C:\\xxx.sqm").getChannel();

			// Create channel on the destination
			FileChannel dstChannel = new FileOutputStream("C:\\xxx2.sqm").getChannel();

			// Copy file contents from source to destination
			dstChannel.transferFrom(srcChannel, 0, srcChannel.size());

			// Close the channels
			srcChannel.close();
			dstChannel.close();
		} catch (IOException e) {
		}
		
	}
	
	public static void main(String[] args) {
		new CopiarFicheroEjemplo();
	}

}
