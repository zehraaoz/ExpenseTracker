import java.time.LocalDate;

public class Expense {
    private String description;
    private int amount;
    private String category;
    private LocalDate date;

    public Expense(String description, int amount, String category, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
    public String getDescription() { return description; };
    public int getAmount() { return this.amount; }
    public String getCategory() { return this.category; }
    public LocalDate getDate() { return this.date; }
}
