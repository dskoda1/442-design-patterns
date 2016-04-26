package genericCheckpointing.util;

public interface FileProcessorI{
	public String readLine();
	public void writeLine(String line, String fileName);
	public void close();
}