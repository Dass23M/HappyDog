package com.example.happydog;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContentAdapter contentAdapter;
    private List<Content> contentList;
    private SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        contentList = new ArrayList<>();
        contentAdapter = new ContentAdapter(this, contentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contentAdapter);

        loadSampleContent();
        setupSearchView();
    }

    private void loadSampleContent() {
        // Populate the content list with sample data
        contentList.add(new Content(1, "1 Natural Mosquito Repellents for Dogs", "Labrador Retrievers are one of the most popular dog breeds in the United States. They are friendly, outgoing, and high-spirited companions who have more than enough affection to go around for a family looking for a medium-to-large dog. They are also known for their intelligence, making them highly trainable for various tasks such as assistance dogs or in roles with search and rescue teams. Labradors have a short, dense, water-resistant coat and come in three colors: black, yellow, and chocolate.", R.drawable.dog1));
        contentList.add(new Content(2, "2 Natural Treatments for Insect Bites", "Ignoring insect bites can make your dog...", R.drawable.dog2));
        contentList.add(new Content(3, "3 Natural Treatments for Insect Bites", "Ignoring insect bites can make your dog...", R.drawable.dog3));
        contentList.add(new Content(4, "4 Natural Treatments for Insect Bites", "Ignoring insect bites can make your dog...", R.drawable.dog4));
        contentList.add(new Content(5, "5 Natural Treatments for Insect Bites", "Ignoring insect bites can make your dog...", R.drawable.dog5));
        contentList.add(new Content(6, "6 Natural Treatments for Insect Bites", "Ignoring insect bites can make your dog...", R.drawable.dog1));
        contentList.add(new Content(7, "7 Natural Treatments for Insect Bites", "Ignoring insect bites can make your dog...", R.drawable.dog2));





        contentAdapter.notifyDataSetChanged();
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contentAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
