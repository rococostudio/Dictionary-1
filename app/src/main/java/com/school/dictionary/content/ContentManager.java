package com.school.dictionary.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.school.dictionary.DaoMaster;
import com.school.dictionary.DaoSession;
import com.school.dictionary.DictionaryApplication;
import com.school.dictionary.TextContentDao;

import org.greenrobot.greendao.query.Query;

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
        if (getContent(textContent.getContent()) == null) {
            textContentDao.insert(textContent);
        }
    }

    /**
     * 修改
     */
    public void changeContent(TextContent textContent) {
        TextContent hasContent = getContent(textContent.getContent());
        if (hasContent != null) {
            hasContent.setStar(textContent.getStar());
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
     * 根据名字查询
     */
    public TextContent getContent(String content) {
        return textContentDao.queryBuilder().where(TextContentDao.Properties.Content.eq(content)).build().unique();
    }

    public List<TextContent> findContent(String content) {
        Query<TextContent> build = textContentDao.queryBuilder().where(TextContentDao.Properties.Search.like("%" + content + "%")).build();
        return build.list();
    }

    public List<TextContent> getStar() {
        Query<TextContent> build = textContentDao.queryBuilder().where(TextContentDao.Properties.Star.eq("TRUE")).build();
        return build.list();
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
        aiqingContent.setSearch("爱情aA");
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
        aiqingContent.setSound("aiqing_sound");

        //积极
        TextContent jijiContent = new TextContent();
        jijiContent.setContent("积 极");
        jijiContent.setSearch("积极jJ");
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
        jijiContent.setSound("jiji_sound");

        //偶然
        TextContent ouranContent = new TextContent();
        ouranContent.setContent("偶 然");
        ouranContent.setSearch("偶然oO");
        ouranContent.setPinyin("ǒu rán");
        ouranContent.setAction("ou_action;ran_action");
        ouranContent.setMeaning("adj. randomly, by chance (a chance occurrence), by accident (as in not intentional)\n" +
                "【形】没想到会发生。常用在：\n" +
                "\n偶然的N（机会/发现），如：" +
                "以前没有人敢吃西红柿，一个偶然的机会，一位画家发现它是一种美味的食物。" +
                "\n" +
                "\n……是（很）偶然的，如：" +
                "很多科学发明都是很偶然的，比如青霉素（penicillin）和蒸汽机（steam engine）。" +
                "这个事情的发生是很偶然的，你不必在意。" +
                "\n" +
                "\nSub偶然+V，如：" +
                "上个星期我去秀水市场，偶然碰到了我的高中同学，原来他现在也在中国留学。");
        ouranContent.setDifferentiate("ouran_jiexi");
        ouranContent.setPractice("ouran_prac");
        ouranContent.setSound("ouran_sound");

        //经验
        TextContent jingyanContent = new TextContent();
        jingyanContent.setContent("经 验");
        jingyanContent.setSearch("经验jJ");
        jingyanContent.setPinyin("jīng yàn");
        jingyanContent.setAction("jing_action;yan_action");
        jingyanContent.setMeaning("n. experience [gained via working in a position or field over time and having gained a particular skill or knowledge from] (as in work experience, life experience)\n" +
                "\n【名】因为长期从事某工作或事情，从中得到的知识或技能。常用在：\n" +
                "\n有（没有）经验，如：\n" +
                "\n很多病人都想找他看病，因为他是一位很有经验的大夫，在治疗癌症方面特别有经验。" +
                "我刚当老师半年，还没有教学经验。" +
                "\n" +
                "\n积累经验，如：\n" +
                "\n你在工作中要注意积累经验。" +
                "\n" +
                "\n经验丰富，如：\n" +
                "我们王老师教了30年汉语了，教得特别好，她的经验非常丰富。");
        jingyanContent.setDifferentiate("jingyan_jiexi");
        jingyanContent.setPractice("jingyan_prac");
        jingyanContent.setSound("jingyan_sound");

        addContent(aiqingContent);
        addContent(jijiContent);
        addContent(ouranContent);
        addContent(jingyanContent);

    }

}
