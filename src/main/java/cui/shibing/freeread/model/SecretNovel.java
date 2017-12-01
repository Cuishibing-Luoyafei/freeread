package cui.shibing.freeread.model;

/**
 * 个人书架对应的model类
 * 保存userId <---> novelId的对应关系
 * */
public class SecretNovel {
    /**
     * 用户名
     * */
    private String userName;
    /**
     * 小说id
     * */
    private String novelId;
    /**
     * 小说名称
     * */
    private String novelName;

    /**
     * 最后阅读的章节
     * */
    private Integer lastReadChapter;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNovelId() {
        return novelId;
    }

    public void setNovelId(String novelId) {
        this.novelId = novelId;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public Integer getLastReadChapter() {
        return lastReadChapter;
    }

    public void setLastReadChapter(Integer lastReadChapter) {
        this.lastReadChapter = lastReadChapter;
    }
}
