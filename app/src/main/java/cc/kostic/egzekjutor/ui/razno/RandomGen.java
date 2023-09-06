package cc.kostic.egzekjutor.ui.razno;

import android.os.SystemClock;

import java.time.Clock;
import java.util.Random;

public class RandomGen {
	private final String TAG = "TAG " + getClass().getSimpleName();

	private static String s = "qwertyuiopasdfghjklzxcvbnm";
	private static String rez;

	public RandomGen() {
	}

	public static String getRandomString(int len){
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for ( int i = 0; i < len; i++ ) {
			int r = random.nextInt(s.length());
			sb.append(s.charAt(r));
		}
		SystemClock.sleep(10000);
		return sb.toString();
	}
}
