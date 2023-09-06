package cc.kostic.egzekjutor.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import cc.kostic.egzekjutor.databinding.FragmentHomeBinding;
import cc.kostic.egzekjutor.ui.razno.RandomGen;
import cc.kostic.egzekjutor.ui.razno.StringPrinter;

public class HomeFragment extends Fragment {
	private final String TAG = "TAG " + getClass().getSimpleName();


	private FragmentHomeBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

		binding = FragmentHomeBinding.inflate(inflater, container, false);
		View root = binding.getRoot();

//		for ( int i = 0; i < 10; i++ ) {
//			String rg = RandomGen.getRandomString(i);
//			Log.d(TAG, "onCreateView: " + rg);
//
//		}



		// https://stackoverflow.com/a/52738831
		// https://www.tutorialspoint.com/java/java_thread_synchronization.htm
		// https://developer.android.com/guide/background/asynchronous/java-threads

		new StringPrinter().printString();











		final TextView textView = binding.textHome;
		homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}