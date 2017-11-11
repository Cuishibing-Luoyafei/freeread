package cui.shibing.freeread.service;

import java.io.Serializable;
import java.util.List;

import cui.shibing.freeread.dto.NovelClassDto;

public interface NovelClassService {
	
	/**
	 * 获取所有的小说类别
	 * 
	 * @return 当前所有的小说类别
	 */
	NovelClassServiceOutputBean getAllNovelClasses(NovelClassServiceInputBean inputBean);
	
	/**
	 * 获取所有的小说类别
	 * 
	 * @return 当前所有的小说类别
	 */
	List<NovelClassDto> getAllNovelClasses();

	static class NovelClassServiceInputBean {
		
	}

	static class NovelClassServiceOutputBean implements Serializable{

		private static final long serialVersionUID = 1392089039250152991L;
		private List<NovelClassDto> novelClasses;

		public List<NovelClassDto> getNovelClasses() {
			return novelClasses;
		}

		public void setNovelClasses(List<NovelClassDto> novelClasses) {
			this.novelClasses = novelClasses;
		}
	}
}
