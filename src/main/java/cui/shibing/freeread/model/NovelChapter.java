package cui.shibing.freeread.model;

import java.io.Serializable;

public class NovelChapter implements Serializable{
   
	/** 
	  * {@inheritDoc}   
	  * @see java.lang.Object#toString() 
	  */
	@Override
	public String toString() {
		return "NovelChapter [novelId=" + novelId + ", novelChapterIndex=" + novelChapterIndex + ", novelChapterName=" + novelChapterName
				+ ", novelChapterContent=" + novelChapterContent + "]";
	}

	private static final long serialVersionUID = 2197176711435078226L;
	/** 主键id */
    private String novelId;
    /** 章节 */
    private Integer novelChapterIndex;
    /** 章节名 */
    private String novelChapterName;
    /** 内容 */
    private String novelChapterContent;

	public String getNovelId() {
		return novelId;
	}

	public void setNovelId(String novelId) {
		this.novelId = novelId;
	}

	public Integer getNovelChapterIndex() {
		return novelChapterIndex;
	}

	public void setNovelChapterIndex(Integer novelChapterIndex) {
		this.novelChapterIndex = novelChapterIndex;
	}

	public String getNovelChapterName() {
		return novelChapterName;
	}

	public void setNovelChapterName(String novelChapterName) {
		this.novelChapterName = novelChapterName;
	}

	public String getNovelChapterContent() {
		return novelChapterContent;
	}

	public void setNovelChapterContent(String novelChapterContent) {
		this.novelChapterContent = novelChapterContent;
	}
}