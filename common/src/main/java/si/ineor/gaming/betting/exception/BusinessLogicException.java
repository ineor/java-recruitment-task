package si.ineor.gaming.betting.exception;

import lombok.Getter;
import si.ineor.gaming.betting.constant.ResponseCodeEnum;

import java.util.function.Supplier;

import static java.lang.String.format;

@Getter
public class BusinessLogicException extends RuntimeException implements Supplier<BusinessLogicException> {
    private final Integer code;
    private final String messageCode;

    public BusinessLogicException(ResponseCodeEnum responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
        this.messageCode = responseCode.getMessageCode();
    }

    public BusinessLogicException(ResponseCodeEnum responseCode, String... messageParts) {
        super(format(responseCode.getMessage(), (Object[]) messageParts));
        this.code = responseCode.getCode();
        this.messageCode = responseCode.getMessageCode();
    }

    public BusinessLogicException(String message) {
        super(message);
        this.code = 400;
        this.messageCode = message;
    }

    @Override
    public BusinessLogicException get() {
        return this;
    }
}
