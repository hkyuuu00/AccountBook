package kr.ac.baekseok.accountbookpj.Model;

public class Record {
    private final String date;
    private final String category;
    private final String history;
    private final String amount;

    public Record(String date, String category, String history, String amount) {
        this.date = date;
        this.category = category;
        this.history = history;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getHistory() {
        return history;
    }

    public String getAmount() {
        return amount;
    }
}