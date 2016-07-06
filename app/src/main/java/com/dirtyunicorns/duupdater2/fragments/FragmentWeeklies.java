package com.dirtyunicorns.duupdater2.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dirtyunicorns.duupdater2.R;
import com.dirtyunicorns.duupdater2.adapters.CardAdapter;
import com.dirtyunicorns.duupdater2.utils.File;
import com.dirtyunicorns.duupdater2.utils.NetUtils;
import com.dirtyunicorns.duupdater2.utils.ServerUtils;

import java.util.ArrayList;

/**
 * Created by mazwoz on 7/5/16.
 */
public class FragmentWeeklies extends Fragment {

    private ArrayList<File> files;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view,containter,false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);
        if (NetUtils.isOnline(getActivity())) {
            ServerUtils su = new ServerUtils();
            files = su.getFiles("Weeklies");

            for (File file : files) {
                System.out.println(file.GetFileName());
            }

            System.out.println("We are in Weeklies");

            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            rv.setLayoutManager(llm);

            if (files.size() > 0) {
                CardAdapter adapter = new CardAdapter(files,getActivity());
                rv.setAdapter(adapter);
                Button download = (Button) rv.findViewById(R.id.btnDownload);
                download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            } else {
                Snackbar.make(rootView,"No files to show", Snackbar.LENGTH_INDEFINITE);
            }
        }

        return rootView;
    }
}
