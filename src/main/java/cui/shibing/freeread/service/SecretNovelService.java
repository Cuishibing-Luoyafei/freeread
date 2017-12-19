package cui.shibing.freeread.service;

import cui.shibing.freeread.model.SecretNovel;

import java.util.List;

public interface SecretNovelService {
    /**
     * 添加一个小说到书架
     */
    boolean addSecretNovel(String userName, String novelId);

    /**
     * 从书架移除一个小说
     *
     * @param userName 用户名
     * @param novelId  小说id
     */
    boolean removeSecretNovel(String userName, String novelId);

    /**
     * 从书架移除一个小说
     */
    boolean removeSecretNovel(SecretNovel secretNovel);

    /**
     * 查询一个用户书架内的所有书籍
     */
    List<SecretNovel> getSecretNovels(String userName);

    /**
     * 更新最后阅读状态
     */
    boolean updateLastReadIndex(String userName, String novelId, Integer chapter);
}
