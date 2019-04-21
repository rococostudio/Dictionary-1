package com.school.dictionary.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.school.dictionary.R;
import com.school.dictionary.adapter.NotebookAdapter;
import com.school.dictionary.content.ContentManager;
import com.school.dictionary.content.TextContent;

import java.util.ArrayList;
import java.util.List;

public class NoteBookActivity extends AppCompatActivity implements View.OnClickListener, NotebookAdapter.NoteClickCallBack {

    private RecyclerView recycler;

    private NotebookAdapter notebookAdapter;

    private List<TextContent> showList;

    private RelativeLayout no_star_re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_book);
        init();
    }

    public void init() {
        showList = new ArrayList<>();

        no_star_re = findViewById(R.id.no_star_re);
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.back).setOnClickListener(this);

        showList.addAll(ContentManager.getInstance().getStar());
        if (showList.isEmpty()) {
            no_star_re.setVisibility(View.VISIBLE);
        } else {
            no_star_re.setVisibility(View.GONE);
        }
        notebookAdapter = new NotebookAdapter(this, showList, this);
        recycler.setAdapter(notebookAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void noteClickCallBack(int position) {
        TextContent content = showList.get(position);
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("content", content.getContent());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (notebookAdapter != null) {
            showList.clear();
            showList.addAll(ContentManager.getInstance().getStar());
            if (showList.isEmpty()) {
                no_star_re.setVisibility(View.VISIBLE);
            } else {
                no_star_re.setVisibility(View.GONE);
            }
            notebookAdapter.notifyDataSetChanged();
        }
    }
}
