package com.gb.base_1919_social.repository;

import android.content.res.Resources;
import android.content.res.TypedArray;

import androidx.annotation.NonNull;

import com.gb.base_1919_social.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class RemoteFireStoreRepositoryImpl implements PostsSource {

    private static final String POSTS_COLLECTION = "posts";

    private List<PostData> dataSource;

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    CollectionReference collectionReference = firebaseFirestore.collection(POSTS_COLLECTION);

    public RemoteFireStoreRepositoryImpl(){
        dataSource = new ArrayList<PostData>();


    }

    public RemoteFireStoreRepositoryImpl init(RemoteFireStoreResponse remoteFireStoreResponse){

        collectionReference.orderBy(PostDataMapping.Fields.DATE, Query.Direction.ASCENDING).get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if( task.isSuccessful()){
                            dataSource = new ArrayList<PostData>();
                            for( QueryDocumentSnapshot queryDocumentSnapshot:task.getResult()){
                                Map<String, Object> document = queryDocumentSnapshot.getData();
                                String id = queryDocumentSnapshot.getId();
                                dataSource.add(PostDataMapping.toPostData(id,document));
                            }
                        }
                        remoteFireStoreResponse.initialized(RemoteFireStoreRepositoryImpl.this);
                    }
                }
        );
        return this;
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public List<PostData> getAllCardsData() {
        return dataSource;
    }

    @Override
    public PostData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public void clearCardsData() {
        dataSource.clear();
        // HW
    }

    @Override
    public void addCardData(PostData postData) {
        dataSource.add(postData);
        collectionReference.add(PostDataMapping.toDocument(postData)).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                postData.setId(documentReference.getId());
            }
        });
    }

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
        // HW
    }

    @Override
    public void updateCardData(int position, PostData newPostData) {
        dataSource.set(position, newPostData);
        // HW
    }
}
