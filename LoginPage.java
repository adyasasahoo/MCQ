import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class LoginPage extends JFrame implements ActionListener {    
    JButton loginButton;
    JLabel user, l;
    JTextField userid;

    public LoginPage() {
        setSize(400, 300);
        setTitle("Quiz Test");
        setLayout(null);
        setLocationRelativeTo(null);

        // Set background color
        getContentPane().setBackground(new Color(224, 247, 250)); // Light blue

        // To close the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l = new JLabel("Login Page:");
        l.setFont(new Font("Verdana", Font.BOLD, 18));
        l.setForeground(Color.DARK_GRAY);

        user = new JLabel("Enter Name:");
        user.setFont(new Font("Verdana", Font.PLAIN, 16));
        user.setForeground(Color.DARK_GRAY);

        userid = new JTextField();
        userid.setFont(new Font("Verdana", Font.PLAIN, 14));

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Verdana", Font.BOLD, 16));
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);

        // Setting bounds for components
        l.setBounds(140, 20, 150, 30);
        user.setBounds(50, 80, 120, 30);
        userid.setBounds(180, 80, 150, 30);
        loginButton.setBounds(140, 150, 120, 40);

        loginButton.addActionListener(this);

        // Adding components to the frame
        add(l);
        add(user);
        add(userid);
        add(loginButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String name = userid.getText();
        if (!name.isEmpty()) {
            setVisible(false); // Close the current window
            // Call for home page with a personalized greeting
            new Home(name);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter your name.");
        }
    }

    // Main method
    public static void main(String[] args) {
        new LoginPage();
    }
}
