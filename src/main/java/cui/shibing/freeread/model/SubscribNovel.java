package cui.shibing.freeread.model;

import java.io.Serializable;

public class SubscribNovel implements Serializable {
    private static final long serialVersionUID = -5809448438065490406L;
    private String novelName;
    private String email;
    private boolean isSended;

    public boolean isSended() {
        return isSended;
    }

    public void setSended(boolean sended) {
        isSended = sended;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
