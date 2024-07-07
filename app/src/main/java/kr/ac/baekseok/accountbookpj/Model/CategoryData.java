package kr.ac.baekseok.accountbookpj.Model;

public class CategoryData {
    private final String category;
    private final float percent;
    private final int amount;

    public CategoryData(String category, float percent, int amount) {
        this.category = category;
        this.percent = percent;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public float getPercent() {
        return percent;
    }

    public int getAmount() {
        return amount;
    }
}
