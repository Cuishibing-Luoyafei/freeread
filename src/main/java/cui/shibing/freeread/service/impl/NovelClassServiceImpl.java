package cui.shibing.freeread.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cui.shibing.freeread.dao.NovelClassDao;
import cui.shibing.freeread.dto.NovelClassDto;
import cui.shibing.freeread.model.NovelClass;
import cui.shibing.freeread.service.NovelClassService;
import cui.shibing.freeread.tools.MyBeanUtils;

@Service
public class NovelClassServiceImpl implements NovelClassService {

	@Autowired
	private NovelClassDao novelClassDao;
	
	@Override
	@Cacheable(value="default",cacheManager="cacheManager",key="#root.targetClass+'.'+#root.methodName")
	public List<NovelClassDto> getAllNovelClasses() {
		List<NovelClass> classes = novelClassDao.selectAllClass();
		List<NovelClassDto> classesDto = new ArrayList<NovelClassDto>(classes.size());
		MyBeanUtils.copyListProperties(classes, classesDto,NovelClassDto.class);
		return classesDto;
	}

	@Override
	@Cacheable(value="default",cacheManager="cacheManager",key="#root.targetClass+'.'+#root.methodName+'.hasOutputBean'")
	public NovelClassServiceOutputBean getAllNovelClasses(NovelClassServiceInputBean inputBean) {
		NovelClassServiceOutputBean outputBean = new NovelClassServiceOutputBean();
		outputBean.setNovelClasses(getAllNovelClasses());
		return outputBean;
	}

}
