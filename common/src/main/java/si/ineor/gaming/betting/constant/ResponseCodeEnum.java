package si.ineor.gaming.betting.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {
    ;

    private String message;
    private final Integer code;
    private final String messageCode;
}

