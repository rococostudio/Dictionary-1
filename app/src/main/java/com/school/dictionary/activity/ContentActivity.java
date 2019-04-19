package com.school.dictionary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.school.dictionary.R;
import com.school.dictionary.content.ContentManager;
import com.school.dictionary.content.TextContent;
import com.school.dictionary.tool.ResourceUtil;

import java.util.List;

public class ContentActivity extends AppCompatActivity {

    private TextView content, pinyin, meaning;
    private ImageView sound, action1, action2, img, differentiate, practice;
    private TextView title1, title2, title3, title4, title5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        init();
        initEvent();
    }


    private void init() {
        content = findViewById(R.id.content);
        pinyin = findViewById(R.id.pinyin);
        meaning = findViewById(R.id.meaning);
        sound = findViewById(R.id.sound);
        action1 = findViewById(R.id.action1);
        action2 = findViewById(R.id.action2);
        img = findViewById(R.id.img);
        differentiate = findViewById(R.id.differentiate);
        practice = findViewById(R.id.practice);
        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
        title3 = findViewById(R.id.title3);
        title4 = findViewById(R.id.title4);
        title5 = findViewById(R.id.title5);
    }

    private void initEvent() {
        List<TextContent> allContent = ContentManager.getInstance().getAllContent();

        TextContent showContent = null;

        for (TextContent textContent : allContent) {
            if (TextUtils.equals(textContent.getContent().replace(" ", ""), "积极")) {
                showContent = textContent;
            }
        }

        if (showContent == null)
            return;

        content.setText(showContent.getContent());
        pinyin.setText(showContent.getPinyin());
        meaning.setText(showContent.getMeaning());
        if (!TextUtils.isEmpty(showContent.getAction())) {
            String[] actions = showContent.getAction().split(";");
            Glide.with(this).load(ResourceUtil.getRaw(this, actions[0])).override(100, 100).into(action1);
            Glide.with(this).load(ResourceUtil.getRaw(this, actions[1])).override(100, 100).into(action2);
        }

        if (!TextUtils.isEmpty(showContent.getImg())) {
            Glide.with(this).load(ResourceUtil.getMipmap(this, showContent.getImg())).override(100, 100).into(img);
        } else {
            title2.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(showContent.getDifferentiate())) {
            Glide.with(this).load(ResourceUtil.getMipmap(this, showContent.getDifferentiate())).into(differentiate);
        } else {
            title4.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(showContent.getPractice())) {
            Glide.with(this).load(ResourceUtil.getMipmap(this, showContent.getPractice())).into(practice);
        } else {
            title5.setVisibility(View.GONE);
        }
    }

}
