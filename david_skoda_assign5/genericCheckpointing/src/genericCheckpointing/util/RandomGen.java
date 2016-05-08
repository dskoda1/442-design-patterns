package genericCheckpointing.util;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Date;

public class RandomGen {

	private int minNum = -10000;
	private int maxNum = 10000;
	private SecureRandom rand;
	
	
	public RandomGen() {
		rand = new SecureRandom();
	    rand.setSeed(new Date().getTime());
	}

	int randomInt(){
	    return rand.nextInt(maxNum);
	}
	/*
	 * http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
	 */
	String randomString(){
	    return new BigInteger(130, rand).toString(32);
	}
	double randomDouble(){
		return rand.nextDouble();
	}
	long randomLong(){
		return rand.nextLong();
	}
	float randomFloat(){
		return rand.nextFloat();
	}
	short randomShort(){
		return (short) rand.nextInt(Short.MAX_VALUE);
	}
	char randomChar(){
		int randInt = randomInt();
		randInt = randInt % (127 - 33) + 33;
		if(randInt == 60 || randInt == 62){
			return randomChar();
		}
		return Character.toChars(randInt)[0];
	}
	
	

}
