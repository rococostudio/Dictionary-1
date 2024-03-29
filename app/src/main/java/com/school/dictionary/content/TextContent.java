package com.school.dictionary.content;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "text_content")
public class TextContent {

    @Id(autoincrement = true)//设置自增长
    private Long id;
    //文字
    private String content;
    //搜索
    private String search;
    //拼音
    private String pinyin;
    //语音
    private String sound;
    //笔画（gif）
    private String action;
    //词义
    private String meaning;
    //词义图片（图片）
    private String img;
    //解析（图片）
    private String differentiate;
    //练习（图片）
    private String practice;
    //是否star
    private boolean star;
    //english
    private String english;


    @Generated(hash = 1983830986)
    public TextContent(Long id, String content, String search, String pinyin,
            String sound, String action, String meaning, String img,
            String differentiate, String practice, boolean star, String english) {
        this.id = id;
        this.content = content;
        this.search = search;
        this.pinyin = pinyin;
        this.sound = sound;
        this.action = action;
        this.meaning = meaning;
        this.img = img;
        this.differentiate = differentiate;
        this.practice = practice;
        this.star = star;
        this.english = english;
    }
    @Generated(hash = 1675015659)
    public TextContent() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getPinyin() {
        return this.pinyin;
    }
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    public String getSound() {
        return this.sound;
    }
    public void setSound(String sound) {
        this.sound = sound;
    }
    public String getAction() {
        return this.action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getMeaning() {
        return this.meaning;
    }
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getDifferentiate() {
        return this.differentiate;
    }
    public void setDifferentiate(String differentiate) {
        this.differentiate = differentiate;
    }
    public String getPractice() {
        return this.practice;
    }
    public void setPractice(String practice) {
        this.practice = practice;
    }
    public String getSearch() {
        return this.search;
    }
    public void setSearch(String search) {
        this.search = search;
    }
    public boolean getStar() {
        return this.star;
    }
    public void setStar(boolean star) {
        this.star = star;
    }
    public String getEnglish() {
        return this.english;
    }
    public void setEnglish(String english) {
        this.english = english;
    }

}
