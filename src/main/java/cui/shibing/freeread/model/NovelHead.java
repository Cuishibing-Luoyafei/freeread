package cui.shibing.freeread.model;

import java.io.Serializable;

public class NovelHead implements Serializable{

	private static final long serialVersionUID = -8628870325251098944L;
    /**
     * 小说id
     */
    private String novelId;
    /**
     * 小说名称
     * */
    private String novelName;
    /**
     * 封面
     * */
    private String novelPicture;
    /**
     * 简介
     * */
    private String novelDesc;
    /**
     * 小说内容所在的表名，为分表做准备
     * */
    private String novelContentTableName;
    /**
     * 小说类别1
     * */
    private Integer novelClassId1;
    /**
     * 小说类别2
     * */
    private Integer novelClassId2;
    /**
     * 小说类别3
     * */
    private Integer novelClassId3;
    /**	
     * 小说状态
     * 0：未上架；1：更新中；2：已完结；
     * */
    private Integer novelStatus;
    /**
     * 章节数量
     * */
    private Integer novelChapterNum;
    /**
     * 作者
     * */
    private String novelAuthor;
    /**
     * 小说的权限，可以用来限制用户对小说的访问
     * */
    private Integer novelAccess;
    /**
     * 点击量，或者收藏量，还没想好
     * */
    private Integer novelPopularity;
    
	public String getNovelDesc() {
		return novelDesc;
	}

	public void setNovelDesc(String novelDesc) {
		this.novelDesc = novelDesc;
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

	public String getNovelPicture() {
		return novelPicture;
	}

	public void setNovelPicture(String novelPicture) {
		this.novelPicture = novelPicture;
	}

	public String getNovelContentTableName() {
		return novelContentTableName;
	}

	public void setNovelContentTableName(String novelContentTableName) {
		this.novelContentTableName = novelContentTableName;
	}

	public Integer getNovelClassId1() {
		return novelClassId1;
	}

	public void setNovelClassId1(Integer novelClassId1) {
		this.novelClassId1 = novelClassId1;
	}

	public Integer getNovelClassId2() {
		return novelClassId2;
	}

	public void setNovelClassId2(Integer novelClassId2) {
		this.novelClassId2 = novelClassId2;
	}

	public Integer getNovelClassId3() {
		return novelClassId3;
	}

	public void setNovelClassId3(Integer novelClassId3) {
		this.novelClassId3 = novelClassId3;
	}

	public Integer getNovelStatus() {
		return novelStatus;
	}

	public void setNovelStatus(Integer novelStatus) {
		this.novelStatus = novelStatus;
	}

	public Integer getNovelChapterNum() {
		return novelChapterNum;
	}

	public void setNovelChapterNum(Integer novelChapterNum) {
		this.novelChapterNum = novelChapterNum;
	}

	public String getNovelAuthor() {
		return novelAuthor;
	}

	public void setNovelAuthor(String novelAuthor) {
		this.novelAuthor = novelAuthor;
	}

	public Integer getNovelAccess() {
		return novelAccess;
	}

	public void setNovelAccess(Integer novelAccess) {
		this.novelAccess = novelAccess;
	}

	public Integer getNovelPopularity() {
		return novelPopularity;
	}

	public void setNovelPopularity(Integer novelPopularity) {
		this.novelPopularity = novelPopularity;
	}

}