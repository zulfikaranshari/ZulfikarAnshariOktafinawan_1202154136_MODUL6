package com.example.zulfikaranshari.zulfikaransharioktafinawan_1202154136_modul6;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TabProfile extends Fragment {
    private ArrayList<UploadModel> mUpload;
    private RecyclerView mRecycler;
    private ProfileAdapter mAdapter;

    private DatabaseReference mDatabaseRef;

    public TabProfile(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layoutView = inflater.inflate(R.layout.tab_profile, container, false);

        mUpload = new ArrayList<>();


        int gridColumn = getResources().getInteger(R.integer.grid_column_count);

        mRecycler = (RecyclerView) layoutView.findViewById(R.id.profileRecycler);
        mRecycler.setHasFixedSize(true);

        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), gridColumn));

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot post : dataSnapshot.getChildren()){
                    UploadModel model = post.getValue(UploadModel.class);
                    mUpload.add(model);
                }
                mAdapter = new ProfileAdapter(getContext(), mUpload);
                mRecycler.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return layoutView;


    }


}
