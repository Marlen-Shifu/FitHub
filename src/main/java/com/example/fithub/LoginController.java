package com.example.fithub;
import com.example.fithub.db.SQLiteHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignIn;

    @FXML
    private Label lblErrors;

    @FXML
    private CheckBox ChBRemember;

    // Inject the SQLiteHandler
    private final SQLiteHandler dbHandler = new SQLiteHandler();
    private final UserSession userSession = UserSession.getInstance();


    @FXML
    private void initialize() {
        // You can add initialization code here if needed
    }

    @FXML
    private void signIn() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (validateInput(username, password)) {
            boolean loginSuccessful = dbHandler.authenticateUser(username, password);

            if (loginSuccessful) {
                userSession.setAuthenticated(true);
                userSession.setUsername(username);
                lblErrors.setText("Login successful!");

                // You can navigate to the main application or perform other actions
            } else {
                userSession.clearSession();
                lblErrors.setText("Invalid username or password.");
            }
        } else {
            userSession.clearSession();
            lblErrors.setText("Please enter both username and password.");
        }
    }

    @FXML
    private void signUp() {
        // Handle navigation to the sign-up scene or perform other actions
        // For demonstration purposes, let's print a message to the console
        System.out.println("Redirecting to sign-up page");
    }

    @FXML
    private void saveToPortal() {
        // Handle the "Remember Me" checkbox logic if needed
        // For demonstration purposes, let's print a message to the console
        System.out.println("Remember Me checkbox selected");
    }

    @FXML
    private void forgotPass() {
        // Handle the "Forgot Password" logic if needed
        // For demonstration purposes, let's print a message to the console
        System.out.println("Forgot Password clicked");
    }

    private boolean validateInput(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }

    @FXML
    private void loginAsAdmin() {
        // Handle login as admin logic
        // For demonstration purposes, let's print a message to the console
        System.out.println("Login as admin clicked");
    }
}
