package assignment.library.domain.book.entity;

public enum BookStatus {
    AVAILABLE("대출 가능"),
    BORROWED("대출 중"),
    RESERVED("예약됨");

    private final String description;

    BookStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
