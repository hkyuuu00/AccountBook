package kr.ac.baekseok.accountbookpj.Model;

public class DeleteItem {
    private final String deleteDate;
    private final String deleteCategory;
    private final String deleteDetail;
    private final String deletePrice; // 가격 정보 추가

    public DeleteItem(String deleteDate, String deleteCategory, String deleteDetail, String deletePrice) {
        this.deleteDate = deleteDate;
        this.deleteCategory = deleteCategory;
        this.deleteDetail = deleteDetail;
        this.deletePrice = deletePrice;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public String getDeleteCategory() {
        return deleteCategory;
    }

    public String getDeleteDetail() {
        return deleteDetail;
    }

    public String getDeletePrice() {
        return deletePrice;
    }
}
