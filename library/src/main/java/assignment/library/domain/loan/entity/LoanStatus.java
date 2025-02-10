package assignment.library.domain.loan.entity;

public enum LoanStatus {
    LOANED("대출 중"),
    RETURNED("반납 완료"),
    OVERDUE("연체");

    private final String description;

    LoanStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
