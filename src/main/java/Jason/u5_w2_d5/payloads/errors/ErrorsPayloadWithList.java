package Jason.u5_w2_d5.payloads.errors;

import java.util.Date;
import java.util.List;

public class ErrorsPayloadWithList {
    private String message;
    private Date timestamp;
    private List<String> errors;

    public ErrorsPayloadWithList(String message, Date timestamp, List<String> errors) {
        this.message = message;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }
}

