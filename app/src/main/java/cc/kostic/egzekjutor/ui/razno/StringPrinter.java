package cc.kostic.egzekjutor.ui.razno;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class StringPrinter {
	private final String TAG = "TAG " + getClass().getSimpleName();


	// https://medium.com/android-news/executor-framework-understanding-the-basics-43d575e72310
	// https://www.baeldung.com/java-executor-service-tutorial


//	private final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	private final ExecutorService executor = Executors.newSingleThreadExecutor();

	public void printString() {
		for (int i = 1; i <= 6; i++) {
			executor.execute(getRunnable(i));
		}
	}

	private Runnable getRunnable(final int i) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				String randomString = RandomGen.getRandomString(i);
				Log.d(TAG, "onc-sp: String returned is :" + randomString);
			}
		};
		return runnable;
	}
}
