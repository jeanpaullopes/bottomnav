package br.edu.uniritter.bottonnavs.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import br.edu.uniritter.bottonnavs.Activity2;
import br.edu.uniritter.bottonnavs.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private int clickCount = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(),
                txt -> {
                    Log.d("no observe", "observe: "+txt);
                    if (txt.contains("count")) {
                        int x = Integer.parseInt(txt.split(" ")[2].trim());
                        if (x%2 == 0) {
                            textView.setText(txt);
                        }
                    } else {
                        textView.setText(txt);
                    }
                });
                //textView::setText);
        textView.setOnClickListener(v -> {
            homeViewModel.setText("Click count: " + clickCount++);
        });
        binding.btnAct2.setOnClickListener(v -> {

            this.startActivity(new Intent(getActivity(), Activity2.class));

        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}