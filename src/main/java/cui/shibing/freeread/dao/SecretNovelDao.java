package cui.shibing.freeread.dao;

import cui.shibing.freeread.model.SecretNovel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecretNovelDao {

    /**
     * 插入一个SecretNovel对象
     * @param secretNovel 要插入的对象
     * @return 成功插入的数目
     * */
    int insertSecretNovel(@Param("secretNovel")SecretNovel secretNovel);

    /**
     * 删除一个SecretNovel对象
     * @param userName 用户id
     * @param novelId 小说id
     * @return 成功删除的数目
     * */
    int deleteSecretNovel(@Param("userName") String userName,@Param("novelId") String novelId);

    /**
     * 查询一个用户加入书架内的小说
     * @param userName 用户id
     * @return 用户加入书架内的小说集合
     * */
    List<SecretNovel> selectSecretNovelByUserName(@Param("userName") String userName);
}
