package cui.shibing.freeread.dao;

import cui.shibing.freeread.model.SecretNovel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class SecretNovelDaoTest extends CustomDaoTest {
    @Autowired
    private SecretNovelDao secretNovelDao;

    @Test
    public void testInsertSecretNovel(){
        SecretNovel secretNovel = new SecretNovel();
        secretNovel.setUserName(UUID.randomUUID().toString());
        secretNovel.setNovelId(UUID.randomUUID().toString());
        secretNovel.setLastReadChapter(12);
        secretNovel.setNovelName(UUID.randomUUID().toString());
        assertTrue(secretNovelDao.insertSecretNovel(secretNovel) == 1);
    }

    @Test
    public void testDeleteSecretNovel(){
        SecretNovel secretNovel = new SecretNovel();
        String userName,novelId;
        secretNovel.setUserName(userName = UUID.randomUUID().toString());
        secretNovel.setNovelId(novelId = UUID.randomUUID().toString());
        secretNovel.setLastReadChapter(12);
        secretNovel.setNovelName(UUID.randomUUID().toString());
        secretNovelDao.insertSecretNovel(secretNovel);
        assertTrue(secretNovelDao.deleteSecretNovel(userName,novelId) == 1);
    }

    @Test
    public void testSelectSecretNovelByUserName(){
        SecretNovel secretNovel1 = new SecretNovel();
        String userName;
        secretNovel1.setUserName(userName = UUID.randomUUID().toString());
        secretNovel1.setNovelId(UUID.randomUUID().toString());
        secretNovel1.setLastReadChapter(12);
        secretNovel1.setNovelName(UUID.randomUUID().toString());

        SecretNovel secretNovel2 = new SecretNovel();
        secretNovel2.setUserName(userName);
        secretNovel2.setNovelId(UUID.randomUUID().toString());
        secretNovel2.setLastReadChapter(12);
        secretNovel2.setNovelName(UUID.randomUUID().toString());

        secretNovelDao.insertSecretNovel(secretNovel1);
        secretNovelDao.insertSecretNovel(secretNovel2);

        assertTrue(secretNovelDao.selectSecretNovelByUserName(userName).size() == 2);
    }
}
