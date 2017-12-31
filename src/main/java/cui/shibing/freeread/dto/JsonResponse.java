package cui.shibing.freeread.dto;

import java.io.Serializable;

public class JsonResponse implements Serializable {
    private static final long serialVersionUID = -1286882642556615780L;
    private boolean isSuccess;
    private String message;
    private Object data;

    public JsonResponse() {
    }

    public JsonResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
