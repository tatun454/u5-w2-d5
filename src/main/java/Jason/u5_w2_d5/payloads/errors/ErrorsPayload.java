package Jason.u5_w2_d5.payloads.errors;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorsPayload {
    private String message;
    private LocalDateTime timestamp;
    private List<String> errors;

    public ErrorsPayload(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public ErrorsPayload(String message, LocalDateTime timestamp, List<String> errors) {
        this.message = message;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }
}
