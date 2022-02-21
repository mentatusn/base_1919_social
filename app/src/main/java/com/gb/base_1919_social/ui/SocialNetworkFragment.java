package com.gb.base_1919_social.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gb.base_1919_social.R;
import com.gb.base_1919_social.repository.LocalRepositoryImpl;


public class SocialNetworkFragment extends Fragment implements OnItemClickListener {

    SocialNetworkAdapter socialNetworkAdapter;

    public static SocialNetworkFragment newInstance() {
        SocialNetworkFragment fragment = new SocialNetworkFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_social_network, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initRecycler(view);
    }

    void initAdapter(){
        socialNetworkAdapter = new SocialNetworkAdapter();
        LocalRepositoryImpl localRepositoryImpl = new LocalRepositoryImpl(requireContext().getResources());
        socialNetworkAdapter.setData(localRepositoryImpl.init());
        socialNetworkAdapter.setOnItemClickListener(SocialNetworkFragment.this);
    }
    void initRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(socialNetworkAdapter);
    }
    String[] getData(){
        String[] data = getResources().getStringArray(R.array.titles);
        return data;
    }

    @Override
    public void onItemClick(int position) {
        String[] data = getData();
        Toast.makeText(requireContext()," Нажали на "+data[position],Toast.LENGTH_SHORT).show();
    }
}