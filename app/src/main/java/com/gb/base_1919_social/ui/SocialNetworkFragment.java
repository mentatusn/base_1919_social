package com.gb.base_1919_social.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gb.base_1919_social.R;
import com.gb.base_1919_social.repository.CardData;
import com.gb.base_1919_social.repository.CardsSource;
import com.gb.base_1919_social.repository.LocalRepositoryImpl;


public class SocialNetworkFragment extends Fragment implements OnItemClickListener {

    SocialNetworkAdapter socialNetworkAdapter;
    CardsSource data;
    RecyclerView recyclerView;
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
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.cards_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {
                data.addCardData(new CardData("Заголовок новой карточки " + data.size(),
                        "Описание новой карточки " + data.size(), R.drawable.nature1, false));
                socialNetworkAdapter.notifyItemInserted(data.size() - 1);
                recyclerView.smoothScrollToPosition(data.size() - 1);
                //recyclerView.scrollToPosition(data.size() - 1);
                return true;
            }
            case R.id.action_clear: {
                data.clearCardsData();
                socialNetworkAdapter.notifyDataSetChanged();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.card_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int menuPosition = socialNetworkAdapter.getMenuPosition();
        switch (item.getItemId()) {
            case R.id.action_update: {
                data.updateCardData(menuPosition, new CardData("Заголовок обновленной карточки " + data.size(),
                        "Описание обновленной карточки " + data.size(), data.getCardData(menuPosition).getPicture(), false));
                socialNetworkAdapter.notifyItemChanged(menuPosition);
                return true;
            }
            case R.id.action_delete: {
                data.deleteCardData(menuPosition);
                socialNetworkAdapter.notifyItemRemoved(menuPosition);
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }


    void initAdapter() {
        socialNetworkAdapter = new SocialNetworkAdapter(this);
        data = new LocalRepositoryImpl(requireContext().getResources()).init();
        socialNetworkAdapter.setData(data);
        socialNetworkAdapter.setOnItemClickListener(SocialNetworkFragment.this);
    }

    void initRecycler(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(socialNetworkAdapter);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setChangeDuration(5000);
        animator.setRemoveDuration(5000);
        recyclerView.setItemAnimator(animator);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);
    }

    String[] getData() {
        String[] data = getResources().getStringArray(R.array.titles);
        return data;
    }

    @Override
    public void onItemClick(int position) {
        String[] data = getData();
        Toast.makeText(requireContext(), " Нажали на " + data[position], Toast.LENGTH_SHORT).show();
    }
}