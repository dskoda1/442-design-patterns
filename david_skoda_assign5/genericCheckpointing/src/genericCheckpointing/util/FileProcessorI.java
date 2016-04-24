package genericCheckpointing.util;

public interface FileProcessorI{
	public String readLine();
	public void writeLine(String line);
	public void close();
}