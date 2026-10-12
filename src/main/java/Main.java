
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;


public class Main extends Application {
    private List<Expense> expenses = new ArrayList<>();

    @Override
    public void start(Stage stage){
        Label title = new Label("Expense Tracker");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Food", "Transport", "Shopping", "Entertainment", "Other");

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        TableView<Expense> table = new TableView<>();

        Label totalLabel = new Label("Total Expense");

        Button deleteButton = new Button("Delete Expense");
        deleteButton.setStyle("-fx-font-weight: bold;");
        deleteButton.setOnAction(event -> {
            Expense selectedExpense = table.getSelectionModel().getSelectedItem();
            if(selectedExpense != null){
                expenses.remove(selectedExpense);
                table.getItems().remove(selectedExpense);

                int total = 0;
                for(Expense expense : expenses){
                    total += expense.getAmount();
                }
                totalLabel.setText("Total Expense: " + total + " kr");
            }
        });

        Button addButton = new Button("Add expense");
        addButton.setStyle("-fx-font-weight: bold;");
        addButton.setOnAction(event -> {
            String description = descriptionField.getText();
            int amount = Integer.parseInt(amountField.getText());
            String category = categoryBox.getValue();
            LocalDate date = datePicker.getValue();
            Expense expense = new Expense(description, amount, category, date);
            expenses.add(expense);
            table.getItems().add(expense);

            int total = 0;
            for(Expense e: expenses){
                total += e.getAmount();
            }
            totalLabel.setText("Total Expense: " + total + " kr");
            descriptionField.clear();
            amountField.clear();
            categoryBox.setValue("Food");
            datePicker.setValue(LocalDate.now());
        });

        TableColumn<Expense, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescription()));

        TableColumn<Expense, Integer> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getAmount()).asObject());

        TableColumn<Expense, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategory()));


        TableColumn<Expense, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getDate()));

        table.getColumns().addAll(descriptionColumn, amountColumn, categoryColumn, dateColumn);
        table.getItems().addAll(expenses);

        VBox root = new VBox(title,
                descriptionField,
                amountField,
                categoryBox,
                datePicker,
                addButton,
                table,
                totalLabel,
                deleteButton
        );
        root.setSpacing(10);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Expense Tracker");
        stage.setScene(scene);
        stage.show();


    }
    public static void main(String[] args) {
        launch();
    }


}
