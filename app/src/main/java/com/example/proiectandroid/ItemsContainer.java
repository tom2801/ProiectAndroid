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

public class ItemsContainer extends Fragment implements OnItemClickListener,ProductOperationsListener {

    public static List<ProductModel> ProductModelList = new ArrayList<>();

    public static String PIZZA_NAME = "pizza_name";

    public static String PIZZA= "pizza";

    public static View currentView;

    public static boolean looped = false; // for some reason onViewCreated rula de 2 ori si imi incarca de 2 ori bd-ul
    public ItemsContainer() { super(R.layout.fragment_items_container);} //aici e fragment_items_container

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentView=view;
        initializeList();
    }

    private void initializeList() {
        ProductModelList.clear();
        new SelectProductsOperation(this).execute();
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

    @Override
    public void insertProductsResponse(String result) {
        // ca sa nu dea eroare
    }

    @Override
    public void selectProductsResponse(List<Product> result) {
        if(result != null && !result.isEmpty() && !looped){
           for (Product produs: result){
               ProductModelList.add(new ProductModel(
                       produs.productName,
                       produs.price,
                       produs.id_product

               ));
           }
            looped=true;
            CustomAdapter adapter = new CustomAdapter(ProductModelList, this);
            RecyclerView rv = currentView.findViewById(R.id.recycler_view);
            rv.setAdapter(adapter);
        }
    }
}
