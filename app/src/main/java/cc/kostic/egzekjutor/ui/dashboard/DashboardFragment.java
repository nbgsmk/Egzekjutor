package cc.kostic.egzekjutor.ui.dashboard;

import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import cc.kostic.egzekjutor.databinding.FragmentDashboardBinding;
import cc.kostic.egzekjutor.ui.razno.RandomGen;

public class DashboardFragment extends Fragment {
	private final String TAG = "TAG " + getClass().getSimpleName();


	private FragmentDashboardBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

		binding = FragmentDashboardBinding.inflate(inflater, container, false);
		View root = binding.getRoot();

		ExecutorService es = Executors.newSingleThreadExecutor();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				Log.d(TAG, "run: " + RandomGen.getRandomString(4) + " thread " + Thread.currentThread().getName());
			}
		};
		Callable<String> c = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Log.d(TAG, "cal: " + RandomGen.getRandomString(4) + " thread " + Thread.currentThread().getName());
				return RandomGen.getRandomString(2);
			}
		};
		List<Callable<String>> lc = new ArrayList<>();
		lc.add(c);
		lc.add(c);
		lc.add(c);

//		es.submit(r);
//		es.submit(c);

		Log.d(TAG, "onCreateView: FUTURE START");
		Future<String> f = es.submit(c);
		Log.d(TAG, "onCreateView: FUTURE DONE");
		try {
			Log.d(TAG, "onCreateView: FUTURE GET");
			Log.d(TAG, "onCreateView: " + f.get());
			Log.d(TAG, "onCreateView: FUTURE G O T");
		} catch ( ExecutionException | InterruptedException e ) {
			throw new RuntimeException(e);
		}

//		try {
//			List<Future<String>> f = es.invokeAll(lc);
//		} catch ( InterruptedException e ) {
//			throw new RuntimeException(e);
//		}

//		try {
//			es.invokeAll(lc);
//		} catch ( InterruptedException e ) {
//			throw new RuntimeException(e);
//		}




		final TextView textView = binding.textDashboard;
		dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}