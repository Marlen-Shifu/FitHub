package com.example.fithub;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

import com.example.fithub.db.SQLiteHandler;

public class RegisterController {

    @FXML
    private TextField TxtUser;

    @FXML
    private PasswordField TxtPass;

    @FXML
    private TextField TxtMail;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private RadioButton otherRadioButton;

    @FXML
    private Label LblMssg;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnSignIn;

    private final SQLiteHandler dbHandler = new SQLiteHandler();

    @FXML
    private void initialize() {
        // You can add initialization code here if needed
    }

    @FXML
    private void handleSignUpButton() {
        String username = TxtUser.getText();
        String password = TxtPass.getText();
        String email = TxtMail.getText();
        String gender = maleRadioButton.isSelected() ? "Male" :
                femaleRadioButton.isSelected() ? "Female" :
                        otherRadioButton.isSelected() ? "Other" : "";

        if (validateInput(username, password, email, gender)) {
            // Perform registration logic using the obtained values
            // Insert the data into the SQLite database

            // Use a prepared statement to prevent SQL injection
            List<String> columnNames = Arrays.asList("username", "password", "email", "gender");
            List<String> values = Arrays.asList(username, password, email, gender);

            // Insert data into the SQLite database
            dbHandler.insertData("users", columnNames, values);

            LblMssg.setText("Registration successful!");
        } else {
            LblMssg.setText("Please fill in all fields.");
        }
    }

    private boolean validateInput(String username, String password, String email, String gender) {
        // Add any additional validation logic here if needed
        return !username.isEmpty() && !password.isEmpty() && !email.isEmpty() && !gender.isEmpty();
    }

    @FXML
    private void handleSignInButton() {
        // Handle the sign-in logic here
        // You can navigate to the sign-in scene or perform any other actions
        // For demonstration purposes, let's print a message to the console
        System.out.println("Redirecting to sign-in page");
    }
}
