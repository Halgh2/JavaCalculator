import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SignPage {
    private static final Map<String, String> userMap = new HashMap<>();
    private static int Attempts = 0;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> ShowGUI());
    }
    private static void ShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame loginFrame = new JFrame("Login System");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 200);
        loginFrame.getContentPane().setBackground(new Color(247, 231, 206));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(38, 224, 20));
        loginButton.setForeground(Color.BLACK);
        loginButton.addActionListener(e -> showLoginPage());

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(194, 23, 23));
        signUpButton.setForeground(Color.BLACK);
        signUpButton.addActionListener(e -> showSignUpPage());

        JPanel panel = new JPanel();
        panel.add(loginButton);
        panel.add(signUpButton);
        loginFrame.add(panel);
        panel.setBackground(new Color(255, 255, 255));
        loginFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
        loginFrame.setVisible(true);
    }

    private static void showLoginPage() {
        if (Attempts >= 3) {
            JOptionPane.showMessageDialog(null, "Too many login attempts. Please try again later.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        JFrame loginPageFrame = new JFrame("Login Page");
        loginPageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginPageFrame.setSize(300, 200);
        loginPageFrame.getContentPane().setBackground(new Color(0, 0, 0));

        JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        panel.add(usernameLabel);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        panel.add(passwordLabel);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(38, 224, 20));
        loginButton.setForeground(Color.BLACK);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = String.valueOf(passwordChars);

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginPageFrame, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (userMap.containsKey(username) && userMap.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(loginPageFrame, "Correct!", "Login Successful", JOptionPane.PLAIN_MESSAGE);
                    new JavaCalculator();

                } else {
                    Attempts++;
                    if (Attempts >= 3) {
                        JOptionPane.showMessageDialog(loginPageFrame, "Too many login attempts. Please try again later.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(loginPageFrame, "Incorrect username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panel.add(loginButton);

        loginPageFrame.add(panel);
        loginPageFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
        loginPageFrame.setVisible(true);
    }

    private static void showSignUpPage() {
        JFrame signUpFrame = new JFrame("Sign Up Page");
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signUpFrame.setSize(400, 300);
        signUpFrame.getContentPane().setBackground(new Color(0, 0, 0));

        JPanel login = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        login.add(usernameLabel);
        login.add(usernameField);

        JLabel passwordLabel = new JLabel("Password (Use at least 6 characters, 2 numbers, and 2 underscores):");
        JPasswordField passwordField = new JPasswordField(20);
        login.add(passwordLabel);
        login.add(passwordField);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(38, 224, 20));
        signUpButton.setForeground(Color.BLACK);
        signUpButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = String.valueOf(passwordChars);

            if (isValidPassword(password)) {
                userMap.put(username, password);
                JOptionPane.showMessageDialog(signUpFrame, "Sign-up Successful!", "Success", JOptionPane.PLAIN_MESSAGE);
                signUpFrame.dispose();
                showLoginPage();
            } else {
                JOptionPane.showMessageDialog(signUpFrame, "Invalid password format. Please use a stronger password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        login.add(signUpButton);

        signUpFrame.add(login);
        signUpFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
        signUpFrame.setVisible(true);
    }

    private static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9].*[0-9])(?=.*_.*_).{6,}$");
    }
}



