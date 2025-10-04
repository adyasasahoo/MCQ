import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

class Home extends JFrame implements ActionListener {
    // Attributes
    JButton practice, logout;
    String Name;
    public int score;

    public Home(String name) {
        Name = name;

        setSize(800, 600);
        setTitle("Home - " + name);
        setLayout(null);
        setLocationRelativeTo(null);

        // Set background color
        getContentPane().setBackground(Color.WHITE);

        // Set buttons
        practice = new JButton("Practice Test");
        logout = new JButton("Logout");

        // Set font and styling
        Font buttonFont = new Font("Verdana", Font.PLAIN, 18);
        practice.setFont(buttonFont);
        logout.setFont(buttonFont);

        practice.setBackground(Color.BLUE);
        practice.setForeground(Color.WHITE);

        logout.setBackground(Color.RED);
        logout.setForeground(Color.WHITE);

        // Set bounds for buttons
        practice.setBounds(300, 200, 200, 50); // x, y, width, height
        logout.setBounds(300, 300, 200, 50);

        // Add action listeners
        practice.addActionListener(this);
        logout.addActionListener(this);

        // Add buttons to the frame
        add(practice);
        add(logout);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logout) {
            dispose(); // Close the window
            new LoginPage(); // Go to login page
        }
        if (e.getSource() == practice) {
            setVisible(false);
            new Question(Name, this, score); // Open the practice test
        }
    }
}
