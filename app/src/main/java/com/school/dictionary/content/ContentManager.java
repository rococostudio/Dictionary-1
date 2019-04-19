package com.school.dictionary.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.school.dictionary.DaoMaster;
import com.school.dictionary.DaoSession;
import com.school.dictionary.DictionaryApplication;
import com.school.dictionary.TextContentDao;

import java.util.List;

public class ContentManager {

    private final String DB_NAME = "appcontent.db";

    private Context context;

    /**
     * Helper
     */
    private DaoMaster.DevOpenHelper mHelper;
    /**
     * DaoMaster
     */
    private DaoMaster mDaoMaster;
    /**
     * DaoSession
     */
    private DaoSession mDaoSession;
    /**
     * dao
     */
    private TextContentDao textContentDao;

    private ContentManager() {
        context = DictionaryApplication.getContext();
    }

    private static final class Buidler {
        private static final ContentManager manager = new ContentManager();
    }

    public static ContentManager getInstance() {
        return Buidler.manager;
    }

    public void initDB() {
        mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        textContentDao = mDaoSession.getTextContentDao();
        initData();
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        return mHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        return mHelper.getWritableDatabase();
    }

    /**
     * 自动添加或替换
     */
    public void autoAddContent(TextContent textContent) {
        textContentDao.insertOrReplace(textContent);
    }

    /**
     * 添加
     */
    public void addContent(TextContent textContent) {
        if (getContent(textContent.getId() + "") == null) {
            textContentDao.insert(textContent);
        }
    }

    /**
     * 修改
     */
    public void changeContent(TextContent textContent) {
        TextContent hasContent = getContent(textContent.getId() + "");
        if (hasContent != null) {
            textContentDao.update(hasContent);
        } else {
            textContentDao.insert(textContent);
        }
    }

    /**
     * 删除
     */
    public void deleteContent(TextContent textContent) {
        textContentDao.queryBuilder().where(TextContentDao.Properties.Id.eq(textContent.getId())).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    /**
     * 根据包名查询
     */
    public TextContent getContent(String id) {
        return textContentDao.queryBuilder().where(TextContentDao.Properties.Id.eq(id)).build().unique();
    }

    /**
     * 获取全部数据
     */
    public List<TextContent> getAllContent() {
        return textContentDao.queryBuilder().list();
    }

    /**
     * 初始化数据
     */
    private void initData() {

        //爱情
        TextContent aiqingContent = new TextContent();
        aiqingContent.setContent("爱 情");
        aiqingContent.setPinyin("ài qíng");
        aiqingContent.setAction("ai_action;qing_action");
        aiqingContent.setMeaning(
                "n.  romantic love\n" +
                        "\n【名】恋人或夫妻相爱的感情。常用在：\n" +
                        "①爱情N（故事、电影、小说）， 如：" +
                        "很多大学生都喜欢看爱情小说和电影。" +
                        "\n" +
                        "\n②V（有、产生）+爱情，如：" +
                        "她和他以前有过一段美好的爱情。" +
                        "两个十八九岁的少男少女在一起时间长了自然就产生了爱情。" +
                        "\n" +
                        "\n③……的爱情，如：" +
                        "   你认为人生中最重要的是健康、爱情还是是事业？");
        aiqingContent.setDifferentiate("aiqing_jiexi");
        aiqingContent.setImg("aiqing_img");
        aiqingContent.setPractice("aiqing_prac");

        //积极
        TextContent jijiContent = new TextContent();
        jijiContent.setContent("积 极");
        jijiContent.setPinyin("jī jí");
        jijiContent.setAction("ji1_action;ji2_action");
        jijiContent.setMeaning("adj  enthusiastic, proactive\n" +
                "\n【形】褒义词 形容态度主动、努力。常用在：\n" +
                "\n①工作/表现（很/非常/十分/特别/不太）积极，如：" +
                " 今天的话题很有意思，讨论的时候学生们的表现都非常积极。" +
                " 这个新员工态度认真，工作积极，大家都很喜欢他。" +
                "\n" +
                "\n②积极的态度/表现，如：" +
                "  在我看来，在工作中积极的态度比能力更重要。" +
                "\n" +
                "\n③积极V（回答/参与等双音节动词）" +
                " 在老师的帮助下，这个孩子不那么内向了，上课的时候也积极回答问题了。" +
                " 下周的活动希望大家都能积极参与，也尽量鼓励身边的人一起参与！" +
                "\n" +
                "\nadj positive (influence, effect, cause)\n" +
                "\n【形】有好的作用，对事情的发展有帮助的。常用在：\n" +
                "\n积极（的）N（影响/作用/因素等双音节抽象名词），如：" +
                "虽然人工智能（AI）的技术还不成熟，但是很多专家认为，人工智能将会对社会发展产生积极影响。" +
                "网上交友的机会多了，但面对面交流却少了，社交媒体真的拉近了人和人的关系、起到了积极作用吗？");
        jijiContent.setPractice("jiji_prac");

        addContent(aiqingContent);
        addContent(jijiContent);

    }

}
