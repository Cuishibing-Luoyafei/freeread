package cui.shibing.freeread.service;

import cui.shibing.freeread.model.SubscribNovel;

import java.util.List;

public interface SubscribNovelService {

    /**
     * 添加一个订阅小说
     *
     * @param userName 用户名
     * @param subscribNovel 订阅小说数据
     *
     * @return 是否成功
     */
    boolean addSubscribNovel(SubscribNovel subscribNovel, String userName);

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

}
