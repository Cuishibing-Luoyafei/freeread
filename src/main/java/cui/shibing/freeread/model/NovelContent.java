package cui.shibing.freeread.model;

import java.io.Serializable;

public class NovelContent implements Serializable{
   
	private static final long serialVersionUID = 2197176711435078226L;

    private String novelId;

    private Integer novelChapterIndex;

    private String novelChapterPath;

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

	public String getNovelChapterPath() {
		return novelChapterPath;
	}

	public void setNovelChapterPath(String novelChapterPath) {
		this.novelChapterPath = novelChapterPath;
	}
	
}