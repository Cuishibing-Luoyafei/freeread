package cui.shibing.freeread.service;

import java.util.ArrayList;
import java.util.List;

import cui.shibing.freeread.dto.NovelClassDto;
import cui.shibing.freeread.model.NovelClass;

public interface NovelClassService {
	/**
	 * 获取所有的小说类别
	 * 
	 * @return 当前所有的小说类别
	 */
	@Deprecated
	List<NovelClass> getAllNovelClasses();
	
	/**
	 * 获取所有的小说类别
	 * 
	 * @return 当前所有的小说类别
	 */
	NovelClassServiceOutputBean getAllNovelClasses(NovelClassServiceInputBean inputBean);

	static class NovelClassServiceInputBean {
		
	}

	static class NovelClassServiceOutputBean {
		public NovelClassServiceOutputBean() {
			this.novelClasses = new ArrayList<NovelClassDto>();
		}
		private List<NovelClassDto> novelClasses;

		public List<NovelClassDto> getNovelClasses() {
			return novelClasses;
		}

		public void setNovelClasses(List<NovelClassDto> novelClasses) {
			this.novelClasses = novelClasses;
		}
	}
}
