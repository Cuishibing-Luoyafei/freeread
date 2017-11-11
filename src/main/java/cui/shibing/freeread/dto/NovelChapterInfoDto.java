package cui.shibing.freeread.dto;

import java.io.Serializable;

public class NovelChapterInfoDto implements Serializable{
	private static final long serialVersionUID = 3774233317273046547L;
	private String novelId;
	private Integer novelChapterIndex;

	private String novelChapterName;

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

}
