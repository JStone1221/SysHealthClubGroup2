import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HealthClubAccountApp extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneNumberField;
    private JTextField emailField;

    public HealthClubAccountApp() {
        setTitle("Health Club Account Creation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 2, 10, 10));

        mainPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        mainPanel.add(usernameField);

        mainPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        mainPanel.add(passwordField);

        mainPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        mainPanel.add(firstNameField);

        mainPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        mainPanel.add(lastNameField);

        mainPanel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        mainPanel.add(phoneNumberField);

        mainPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        mainPanel.add(emailField);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });

        mainPanel.add(createAccountButton);

        add(mainPanel);

        setVisible(true);
    }

    private void createAccount() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String email = emailField.getText();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date expirationDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String userInfo = "Expiration Date: " + dateFormat.format(expirationDate) + "\n"
                + "First Name: " + firstName + "\n"
                + "Last Name: " + lastName + "\n"
                + "Phone Number: " + phoneNumber + "\n"
                + "Email: " + email;

        String fileName = username + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Password: " + password);
            writer.println(userInfo);
            System.out.println("Account created and information saved to " + fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error saving user information to file");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HealthClubAccountApp();
            }
        });
    }
}
