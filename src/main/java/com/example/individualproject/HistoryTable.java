/**

 * File: HistoryTable.java

 * Author: Lyuboslav Gigov

 * Date: 11/28/2023

 */

package com.example.individualproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HistoryTable {
    private TableView<String> historyTable;
    private TableColumn<String, String> historyColumn;
    public static ObservableList<String> historyData = FXCollections.observableArrayList();

    public HistoryTable(){
        // History Table Setup
        // Create a TableView with one column
        historyTable = new TableView<>();
        historyColumn = new TableColumn<>("History");
        historyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        historyTable.getColumns().add(historyColumn);
        historyTable.setItems(historyData);
        historyTable.setStyle("-fx-font-size: 14; -fx-font-family: 'Comic Sans MS';");
        historyTable.setVisible(false);
    }

    public HBox createHistoryTableLayout(TextArea encryptedTextArea){
        // Create a Button to copy selected text
        Button copyButton = new Button("Copy");
        copyButton.setStyle("-fx-font-size: 14; -fx-font-family: 'Comic Sans MS';");

        copyButton.setAlignment(Pos.CENTER);
        copyButton.setOnAction(e -> {
            String selectedHistoryItem = historyTable.getSelectionModel().getSelectedItem();
            if (selectedHistoryItem != null) {
                // Copy the selected text to the Decrypt TextArea
                encryptedTextArea.setText(selectedHistoryItem);
            }
        });

        // Button to clear table
        Button clearButton = new Button("Clear");
        clearButton.setStyle("-fx-font-size: 14; -fx-font-family: 'Comic Sans MS';");
        clearButton.setOnAction(e ->{
            clearHistoryTable();
        });

        VBox buttonsVBox = new VBox(copyButton, clearButton);
        buttonsVBox.setSpacing(5);

        HBox historyLayout = new HBox(historyTable, buttonsVBox);
        historyLayout.setSpacing(5);

        return historyLayout;
    }
    public void clearHistoryTable(){
        historyData.clear();
    }
    public void toggleHistoryVisibility(){
        historyTable.setVisible(true);
    }
    // Getter for history data
    public static ObservableList<String> getHistoryData() {
        return historyData;
    }
}
