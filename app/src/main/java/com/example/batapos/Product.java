package com.example.batapos;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.batapos.R;

import java.util.ArrayList;
import java.util.HashMap;

///**
// * A simple {@link Fragment} subclass.
// */
//public class Product extends Fragment {
//
//    ListView listView;
//    SimpleAdapter simpleAdapter;
//    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
//
//    public Product() {
//        // Required empty public constructor
//    }
//}

//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_product, container, false);

//        listView = (ListView) view.findViewById(R.id.listView);
//        setProduct();
//        return view;
//    }
//
//    private void setProduct() {
//        for (int i = 1; i <= 10; i++) {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("artikel",  "012345");
//            map.put("kategori",  "wanita");
//            map.put("subkategori",  "sepatu");
//            map.put("size",  "40");
//            map.put("supplier",  "gudang");
//            map.put("harga",  "Rp " + String.valueOf(i) + "0.000");
//            map.put("kuantitas",  " " + String.valueOf(i) + "0");
//            arrayList.add(map);
//        }
//        simpleAdapter = new SimpleAdapter(getActivity(), arrayList, R.layout.activity_data_produk,
//                new String[] { "artikel", "kategori", "subkategori", "size", "supplier", "harga", "kuantitas" },
//                new int[] { R.id.tv_artikel, R.id.tv_kategori, R.id.tv_subkategori, R.id.tv_size, R.id.tv_supplier, R.id.tv_price, R.id.tv_qty });
//        listView.setAdapter(simpleAdapter);
//    }
//}