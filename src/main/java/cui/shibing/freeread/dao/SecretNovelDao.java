package cui.shibing.freeread.dao;

import cui.shibing.freeread.datasource.DataSource;
import cui.shibing.freeread.model.SecretNovel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import static cui.shibing.freeread.datasource.DataSourceType.SLAVER;

public interface SecretNovelDao {

    /**
     * 插入一个SecretNovel对象
     * @param secretNovel 要插入的对象
     * @return 成功插入的数目
     * */
    @DataSource
    int insertSecretNovel(@Param("secretNovel")SecretNovel secretNovel);

    /**
     * 删除一个SecretNovel对象
     * @param userName 用户名
     * @param novelId 小说id
     * @return 成功删除的数目
     * */
    @DataSource
    int deleteSecretNovel(@Param("userName") String userName,@Param("novelId") String novelId);

    /**
     * 查询一个用户加入书架内的小说
     * @param userName 用户名
     * @return 用户加入书架内的小说集合
     * */
    @DataSource(SLAVER)
    List<SecretNovel> selectSecretNovelByUserName(@Param("userName") String userName);

    /**
     * 更新书架内小说的最后阅读状态
     *
     * @param novelId 小说id
     * @param userName 用户名
     */
    @DataSource
    int updateSecretNovelLastReadIndex(@Param("userName") String userName,
                                       @Param("novelId") String novelId,
                                       @Param("chapter") Integer chapter);
}
