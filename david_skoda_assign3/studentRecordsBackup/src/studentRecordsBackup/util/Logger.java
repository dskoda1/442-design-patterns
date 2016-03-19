package studentRecordsBackup.util;

public class Logger{


	public static enum DebugLevel { 
		INSERT,
		SUM,
		UPDATE,
		PRINT,
		CONSTRUCTOR 
	};

	private static DebugLevel debugLevel;

	public static void setDebugValue (int levelIn) {
		switch (levelIn) {
			case 0: debugLevel = DebugLevel.SUM; break;
			case 1: debugLevel = DebugLevel.INSERT; break;
			case 2: debugLevel = DebugLevel.UPDATE; break;
			case 3: debugLevel = DebugLevel.PRINT; break;
			case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
		}
	}

	public static void setDebugValue (DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	public static void writeMessage (String message,
			DebugLevel levelIn ) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	public String toString() {
		return "Debug Level is " + debugLevel;
	}
}
