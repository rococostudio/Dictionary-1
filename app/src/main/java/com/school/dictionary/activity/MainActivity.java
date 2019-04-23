package com.school.dictionary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.school.dictionary.R;
import com.school.dictionary.adapter.SearchAdapter;
import com.school.dictionary.content.ContentManager;
import com.school.dictionary.content.TextContent;
import com.school.dictionary.tool.SoundTool;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SearchAdapter.SearchClickCallBack {

    private ImageView main_more;
    private DrawerLayout main_draw;
    private EditText main_search;
    private ImageView main_search_icon;
    private RecyclerView main_recycler;
    private RelativeLayout main_search_re;

    private TextView left_book_tv;

    private SearchAdapter searchAdapter;

    private List<TextContent> showList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        showList = new ArrayList<>();
        main_search_icon = findViewById(R.id.main_search_icon);
        main_search = findViewById(R.id.main_search);
        main_more = findViewById(R.id.main_more);
        main_draw = findViewById(R.id.main_draw);
        main_search_re = findViewById(R.id.main_search_re);
        main_recycler = findViewById(R.id.main_recycler);
        left_book_tv = findViewById(R.id.left_book_tv);

        main_recycler.setLayoutManager(new LinearLayoutManager(this));

        searchAdapter = new SearchAdapter(this, showList, this);
        main_recycler.setAdapter(searchAdapter);

        main_more.setOnClickListener(this);
        main_search.setOnClickListener(this);
        main_search_icon.setOnClickListener(this);
        main_search_re.setOnClickListener(this);
        left_book_tv.setOnClickListener(this);

        hideKeybord(main_search);
        editListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_more:
                main_draw.openDrawer(GravityCompat.START);
                break;
            case R.id.main_search:
            case R.id.main_search_re:
                showKeybord(main_search);
                break;
            case R.id.main_search_icon:
                hideKeybord(main_search);
                break;
            case R.id.left_book_tv:
                startActivity(new Intent(this, NoteBookActivity.class));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (main_draw.isDrawerOpen(GravityCompat.START)) {
                main_draw.closeDrawer(GravityCompat.START);
                return true;
            }

            if (main_search.hasFocus()) {
                main_search.setText("");
                hideKeybord(main_search);
                showList.clear();
                searchAdapter.notifyDataSetChanged();
                return true;
            }

        }

        return super.onKeyDown(keyCode, event);
    }


    private void showKeybord(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.requestFocusFromTouch();
    }


    private void hideKeybord(EditText editText) {
        editText.clearFocus();
        editText.setFocusable(false);
    }

    //input listener
    private void editListener() {
        main_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //search from input
    private void checkText(String text) {
        if (TextUtils.isEmpty(text)) {
            showList.clear();
            searchAdapter.notifyDataSetChanged();
            return;
        }
        showList.clear();
        //Fuzzy query
        showList.addAll(ContentManager.getInstance().findContent(text));
        //According to English enquiries
        boolean isAdd;
        List<TextContent> contentForEnglish = ContentManager.getInstance().findContentForEnglish(text);
        for (TextContent english : contentForEnglish) {
            isAdd = false;
            for (TextContent content : showList) {
                if (TextUtils.equals(english.getContent(), content.getContent())) {
                    isAdd = true;
                }
            }
            if (!isAdd) {
                showList.add(english);
            }
        }

        searchAdapter.notifyDataSetChanged();
    }

    /**
     * click text
     */
    @Override
    public void searchClickCallBack(int position) {
        TextContent content = showList.get(position);
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("content", content.getContent());
        startActivity(intent);

        main_search.setText("");
        hideKeybord(main_search);
        showList.clear();
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SoundTool.destroy();
    }
}
