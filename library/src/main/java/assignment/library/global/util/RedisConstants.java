package assignment.library.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class RedisConstants {
    public static final String BOOK_INFO ="bookInfo";
    public static final String LOAN_STATUS = "loanStatus";
    public static final String ALL_BOOK_INFO_KEY = "allBookInfoKey";
}
