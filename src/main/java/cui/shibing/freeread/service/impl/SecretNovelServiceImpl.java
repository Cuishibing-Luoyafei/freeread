package cui.shibing.freeread.service.impl;

import cui.shibing.freeread.dao.SecretNovelDao;
import cui.shibing.freeread.model.NovelHead;
import cui.shibing.freeread.model.SecretNovel;
import cui.shibing.freeread.service.NovelHeadService;
import cui.shibing.freeread.service.SecretNovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SecretNovelServiceImpl implements SecretNovelService {

    @Autowired
    private SecretNovelDao secretNovelDao;

    @Autowired
    private NovelHeadService novelHeadService;

    @Override
    public boolean addSecretNovel(String userName, String novelId) {
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(novelId)) {
            NovelHead novelHead = novelHeadService.searchByNovelId(novelId);
            if (novelHead != null) {
                SecretNovel secretNovel = new SecretNovel();
                secretNovel.setUserName(userName);
                secretNovel.setNovelId(novelHead.getNovelId());
                secretNovel.setNovelName(novelHead.getNovelName());
                secretNovel.setLastReadChapter(0);
                return addSecretNovel(secretNovel);
            }
        }
        return false;
    }

    private boolean addSecretNovel(SecretNovel secretNovel) {
        return validateSecretNovel(secretNovel) &&
                secretNovelDao.insertSecretNovel(secretNovel) == 1;
    }

    @Override
    public boolean removeSecretNovel(String userName, String novelId) {
        return !StringUtils.isEmpty(userName) &&
                !StringUtils.isEmpty(novelId) &&
                secretNovelDao.deleteSecretNovel(userName, novelId) == 1;
    }

    @Override
    public boolean removeSecretNovel(SecretNovel secretNovel) {
        return validateSecretNovel(secretNovel) &&
                secretNovelDao.deleteSecretNovel(secretNovel.getUserName(), secretNovel.getNovelId()) == 1;
    }

    @Override
    public List<SecretNovel> getSecretNovels(String userName) {
        if (!StringUtils.isEmpty(userName)) {
            List<SecretNovel> secretNovels = secretNovelDao.selectSecretNovelByUserName(userName);
            for (SecretNovel secretNovel : secretNovels) {
                //如果小说不存在了，就设置下架标志
                if (novelHeadService.searchByNovelId(secretNovel.getNovelId()) == null) {
                    secretNovel.setOutOfStock(true);
                }
            }
            return secretNovels;
        }
        return null;
    }

    private boolean validateSecretNovel(SecretNovel secretNovel) {
        return secretNovel != null &&
                !StringUtils.isEmpty(secretNovel.getUserName()) &&
                !StringUtils.isEmpty(secretNovel.getNovelId()) &&
                !StringUtils.isEmpty(secretNovel.getNovelName());
    }
}
