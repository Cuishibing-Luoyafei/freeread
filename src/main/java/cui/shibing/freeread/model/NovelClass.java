package cui.shibing.freeread.model;

import java.io.Serializable;

public class NovelClass implements Serializable{
	
	private static final long serialVersionUID = 5233685107867115305L;
	private Integer novelClassId;
	private String novelClassName;
	public Integer getNovelClassId() {
		return novelClassId;
	}
	public void setNovelClassId(Integer novelClassId) {
		this.novelClassId = novelClassId;
	}
	public String getNovelClassName() {
		return novelClassName;
	}
	public void setNovelClassName(String novelClassName) {
		this.novelClassName = novelClassName;
	}
}
