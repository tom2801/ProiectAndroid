package com.example.proiectandroid;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements OnItemClickListener {

    public static List<ProductModel> ProductModelList = new ArrayList<>();

    public static String PIZZA_NAME = "pizza_name";

    public static String PIZZA= "pizza";

    public FirstFragment() { super(R.layout.fragment_first);} //aici e fragment_items_container

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeList();

        CustomAdapter adapter = new CustomAdapter(ProductModelList, this);
        RecyclerView rv = view.findViewById(R.id.recycler_view);
        rv.setAdapter(adapter);
    }

    private void initializeList() {
        // cod care interactioneaza cu baza de date
    }

    @Override
    public void onItemClick(ProductModel item) { // replace code cu ce am eu chef
//        Bundle bundle = new Bundle();
//
//        bundle.putString(PIZZA_NAME, item.getName());
//        bundle.putParcelable(PIZZA, item);
//
//        SecondFragment secondFragment = new SecondFragment();
//        secondFragment.setArguments(bundle);
//
//        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//        fragmentTransaction.replace(R.id.fragment_container, secondFragment)
//                .addToBackStack(null);
//        fragmentTransaction.commit();
  }
}
