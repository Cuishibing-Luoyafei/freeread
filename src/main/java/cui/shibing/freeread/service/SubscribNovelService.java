package cui.shibing.freeread.service;

import cui.shibing.freeread.model.SubscribNovel;

import java.util.List;

public interface SubscribNovelService {

    /**
     * 添加一个订阅小说
     *
     * @param userName 用户名
     * @param novelName 小说名称
     *
     * @return 是否成功
     */
    boolean addSubscribNovel(String novelName, String userName);

    /**
     * 櫖删除已经发布的订阅
     */
    boolean removeSendedSubscribNovel();

    /**
     * 获取订阅的小说
     *
     * @param isSended 发送的状态
     * @param length 要获取的最大数量
     */
    List<SubscribNovel> getSubscribNovel(long length, boolean isSended);

    /**
     * 更新订阅小说的状态
     *
     * @param subscribNovel 订阅状态
     *
     * @return 是否成功
     */
    boolean updateSubscribNovel(SubscribNovel subscribNovel);

}
