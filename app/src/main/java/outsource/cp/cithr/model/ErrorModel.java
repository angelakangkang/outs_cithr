package outsource.cp.cithr.model;

/**
 * Created by Raymon on 2017/2/27.
 */

public class ErrorModel {
    private int status_code;
    private String message,error;

    public ErrorModel(){}
    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
