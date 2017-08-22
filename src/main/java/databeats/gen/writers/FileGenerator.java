package databeats.gen.writers;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileGenerator {

	public void bufferWrite(String filename, byte[] buffer, Integer numOfLines) throws Exception {
		if (numOfLines == 0 || numOfLines < 0)
			numOfLines = 0;
		FileChannel rwChannel = null;
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(filename, "rw");
			rwChannel = randomAccessFile.getChannel();
			ByteBuffer wrBuf = rwChannel.map(FileChannel.MapMode.READ_WRITE, 0, buffer.length * numOfLines);
			for (int i = 0; i < numOfLines; i++) {
				wrBuf.put(buffer);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (randomAccessFile != null)
				randomAccessFile.close();
			if (rwChannel != null && rwChannel.isOpen())
				rwChannel.close();
		}
	}

	public void bufferWrite(String filename, String data, Integer numOfLines) throws Exception {
		if (data != null) {
			bufferWrite(filename, data.getBytes(), numOfLines);
		}
	}

}
