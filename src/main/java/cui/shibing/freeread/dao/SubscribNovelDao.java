package cui.shibing.freeread.dao;

import cui.shibing.freeread.datasource.DataSource;
import cui.shibing.freeread.datasource.DataSourceType;
import cui.shibing.freeread.model.SubscribNovel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubscribNovelDao {

    /**
     * 获取一组订阅小说
     *
     * @param length 获取的最大数量
     * @param isSended 是否发送邮件
     *
     * @return 获取的订阅小说数据
     */
    @DataSource(DataSourceType.SLAVER)
    List<SubscribNovel> selectSubscribNovel(@Param("length") long length, @Param("isSended") boolean isSended);

    /**
     * 插入一个订阅小说数据
     */
    @DataSource
    int insertSubscribNovel(@Param("subscribNovel") SubscribNovel subscribNovel);

    /**
     * 更新订阅的发送状态
     */
    @DataSource
    int updateSubscribNoveStatus(@Param("subscribNovel") SubscribNovel subscribNovel);

    /**
     * 删除已经发送过邮件的订阅
     */
    @DataSource
    int deleteSendedSubscribNovel();

    /**
     * 根据小说名和邮箱来删除订阅
     */
    @DataSource
    int deleteSubscribNovelByNameAndEmail(@Param("novelName") String novelName, @Param("email") String email);

}