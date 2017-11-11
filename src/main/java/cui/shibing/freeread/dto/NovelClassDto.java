package cui.shibing.freeread.dto;

import java.io.Serializable;

public class NovelClassDto implements Serializable{
	private static final long serialVersionUID = 5719232887467784797L;
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
