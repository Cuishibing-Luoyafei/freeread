package cui.shibing.freeread.service;

import cui.shibing.freeread.model.NovelHead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface NovelHeadService {
	/**
	 * 根据小说名称查找小说
	 * @param novelName 要查找的小说名
	 * @return 返回 novelName 对应的小说
	 * */
	Page<NovelHead> searchByNovelName(String novelName,Pageable pageable);
	
	/**
	 * 查询小说名称对应记录的数量
	 * @param novelName 小说名称
	 * @return 对应的数量
	 * */
	long searchCountByNovelName(String novelName);

	/**
     * 根据小说作者查询小说
     * @param userName 用户名称
     * @return 该作者对应的小说
     * */
	Page<NovelHead> searchByAuthor(String userName,Pageable pageable);

	/**
     * 查询一个作者写的小说的数量
     * @param userName 用户名称
     * @return 该作者写的小说的数据
     * */
	long searchCountByAuthor(String userName);
	
	/**
	 * 根据小说类别查找小说
	 * @param className 小说类别名
	 * @param pageable 分页对象
	 * @return 返回 className 对应的小说
	 * */
	Page<NovelHead> searchByNovelClass(String className,Pageable pageable);
	
	/**
	 * 根据小说的热度查找小说
	 * @param pageable 分页对象
	 * @return 返回相应的小说
	 * */
	Page<NovelHead> searchByPopularity(Pageable pageable);
	
	/**
	 * 查询小说总数
	 * @return 小说数量
	 * */
	long searchNovelHeadCount();
	
	/**
	 * 查询某一类别小说的总数
	 * @param className 类别名称
	 * @return 该类别小说的数量
	 * */
	long searchCountByClassName(String className);
	
	/**
	 * 根据小说Id查找小说
	 * @param novelId 小说Id
	 * @return 返回相应的小说
	 * */
	NovelHead searchByNovelId(String novelId);

	/**
	 * 添加一部新的小说
	 * @param head 要添加的小说
	 * @return 是否成功
	 * */
	boolean addNovelHead(NovelHead head);

    /**
     * 删除一个小说
     *
     * @param novelId 小说id
     *
     * @return 删除是否成功
     */
    boolean removeNovelHead(String novelId);

	/**
	 * 更新一本小说
	 *
	 * @param novelHead 要更新的小说
	 *
	 * @return 是否成功
	 */
	boolean updateNovelHead(NovelHead novelHead);
}
