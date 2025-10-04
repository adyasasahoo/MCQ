import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Question extends JFrame implements ActionListener {

    Timer timer;
    private int timeRemaining = 20; // Time per question in seconds
    private JLabel timerLabel;

    ArrayList<AddQues> que = new ArrayList<AddQues>();
    JLabel qnum;
    JTextArea ques;
    JRadioButton op1, op2, op3, op4;
    ButtonGroup options;
    JButton next, skip;
    public static int score = 0;
    public static int questionNo = 0;

    private Home home;

    public Question(String name, Home home, int score) {

        this.home = home;
        setSize(800, 600);
        setTitle("Practice - " + name);

        // Set the location of the frame on the screen
        setLocationRelativeTo(null);
        setLayout(null);

        // To close the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Dark Theme Colors
        Color backgroundColor = new Color(45, 45, 45);
        Color textColor = new Color(255, 255, 255);
        Color buttonColor = new Color(70, 130, 180);

        // Set frame background color
        getContentPane().setBackground(backgroundColor);

        qnum = new JLabel(); // Question number
        qnum.setFont(new Font("Verdana", Font.BOLD, 18));
        qnum.setForeground(textColor);

        ques = new JTextArea(); // Question text
        ques.setFont(new Font("Verdana", Font.PLAIN, 18));
        ques.setLineWrap(true);
        ques.setWrapStyleWord(true);
        ques.setEditable(false);
        ques.setBackground(backgroundColor);
        ques.setForeground(textColor);

        // Options
        op1 = new JRadioButton();
        op2 = new JRadioButton();
        op3 = new JRadioButton();
        op4 = new JRadioButton();
        options = new ButtonGroup();
        options.add(op1);
        options.add(op2);
        options.add(op3);
        options.add(op4);

        op1.setFont(new Font("Verdana", Font.PLAIN, 18));
        op2.setFont(new Font("Verdana", Font.PLAIN, 18));
        op3.setFont(new Font("Verdana", Font.PLAIN, 18));
        op4.setFont(new Font("Verdana", Font.PLAIN, 18));

        // Set dark mode colors for options
        op1.setBackground(backgroundColor);
        op1.setForeground(textColor);
        op2.setBackground(backgroundColor);
        op2.setForeground(textColor);
        op3.setBackground(backgroundColor);
        op3.setForeground(textColor);
        op4.setBackground(backgroundColor);
        op4.setForeground(textColor);

        next = new JButton("Next");
        skip = new JButton("Skip");

        // Set dark mode style for buttons
        next.setFont(new Font("Verdana", Font.PLAIN, 18));
        skip.setFont(new Font("Verdana", Font.PLAIN, 18));
        next.setBackground(buttonColor);
        next.setForeground(textColor);
        skip.setBackground(buttonColor);
        skip.setForeground(textColor);

        // Set bounds for buttons
        int buttonWidth = 120; // Button width
        int buttonHeight = 40; // Button height
        int buttonX = 650; // X-coordinate for right alignment (adjust based on frame width)
        next.setBounds(buttonX, 400, buttonWidth, buttonHeight); // Positioned next to options
        skip.setBounds(buttonX, 450, buttonWidth, buttonHeight); // Positioned below "Next" button

        // Add action listeners to buttons
        next.addActionListener(this);
        skip.addActionListener(this);

        // Add buttons to the frame
        add(next);
        add(skip);

        add(qnum);
        add(ques);
        add(op1);
        add(op2);
        add(op3);
        add(op4);

        // Set bounds for the components
        qnum.setBounds(30, 85, 50, 30); // Position for question number
        ques.setBounds(70, 90, 600, 100); // Larger area for the question text
        op1.setBounds(50, 210, 600, 30); // Position for option 1
        op2.setBounds(50, 250, 600, 30); // Position for option 2
        op3.setBounds(50, 290, 600, 30); // Position for option 3
        op4.setBounds(50, 330, 600, 30);

        op1.addActionListener(this);
        op2.addActionListener(this);
        op3.addActionListener(this);
        op4.addActionListener(this);

        // Add timer to the window
        timerLabel = new JLabel("Time left: " + timeRemaining + "s");
        timerLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        timerLabel.setForeground(Color.RED);
        timerLabel.setBounds(650, 50, 150, 30);
        add(timerLabel);

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                if (timeRemaining > 0) {
                    timerLabel.setText("Time left: " + timeRemaining + "s");
                } else if (timeRemaining == 0) {
                    timerLabel.setText("Times up!!");
                    timer.stop(); // Stop the timer
                    skipQuestion(); // Automatically skip to the next question
                }
            }
        });

        loadQuestions();
        Collections.shuffle(que);

        start(questionNo);
    }

    // ... (The rest of the code remains unchanged)


        public void start(int count) {
                qnum.setText("" + (count + 1) + ".");
                ques.setText(que.get(count).qt);
                op1.setText(que.get(count).op1);
                op2.setText(que.get(count).op2);
                op3.setText(que.get(count).op3);
                op4.setText(que.get(count).op4);

                // start timer
                timeRemaining = 20; // Reset time for each question
                timer.start();
        }

        void loadQuestions() {
                // Arrays
                que.add(new AddQues("What is the time complexity of accessing an element in an array?", "O(1)",
                                "O(log n)",
                                "O(n)", "O(n^2)", "O(1)"));
                que.add(new AddQues("What is the time complexity of inserting an element at the beginning of an array?",
                                "O(1)",
                                "O(log n)", "O(n)", "O(n^2)", "O(n)"));
                que.add(new AddQues("What is the time complexity of deleting an element from the middle of an array?",
                                "O(1)",
                                "O(log n)", "O(n)", "O(n^2)", "O(n)"));
                que.add(new AddQues("What is the space complexity of an array?", "O(1)", "O(n)", "O(log n)", "O(n^2)",
                                "O(n)"));

                // Linked Lists
                que.add(new AddQues("Which operation takes the most time in a singly linked list?",
                                "Accessing an element",
                                "Inserting at the beginning", "Deleting from the end", "Accessing the last element",
                                "Accessing an element"));
                que.add(new AddQues(
                                "What is the time complexity of inserting a node at the beginning of a linked list?",
                                "O(1)", "O(n)", "O(log n)", "O(n^2)", "O(1)"));
                que.add(new AddQues("What is the space complexity of a linked list?", "O(1)", "O(n)", "O(log n)",
                                "O(n^2)",
                                "O(n)"));
                que.add(new AddQues(
                                "What is the time complexity of deleting a node from the middle of a singly linked list?",
                                "O(1)", "O(log n)", "O(n)", "O(n^2)", "O(n)"));

                // Stacks and Queues
                que.add(new AddQues("Which data structure uses LIFO order?", "Stack", "Queue", "Deque", "Array",
                                "Stack"));
                que.add(new AddQues("Which of these is used in depth-first search (DFS)?", "Queue", "Stack",
                                "Priority Queue",
                                "Array", "Stack"));
                que.add(new AddQues("What is the time complexity of pushing an element onto a stack?", "O(1)", "O(n)",
                                "O(log n)", "O(n^2)", "O(1)"));
                que.add(new AddQues("Which data structure is used to implement recursion?", "Stack", "Queue", "Deque",
                                "Array",
                                "Stack"));

                // Trees
                que.add(new AddQues(
                                "What is the time complexity to search an element in a balanced binary search tree?",
                                "O(1)", "O(log n)", "O(n)", "O(n^2)", "O(log n)"));
                que.add(new AddQues("In a binary tree, how many children can a node have?", "1", "2", "3", "4", "2"));
                que.add(new AddQues("What is the height of a binary tree with n nodes?", "log(n)", "log(n+1)", "n",
                                "n-1",
                                "log(n+1)"));
                que.add(new AddQues(
                                "Which traversal technique is used to get the elements of a binary tree in sorted order?",
                                "Pre-order", "Post-order", "In-order", "Level-order", "In-order"));

                // Heaps
                que.add(new AddQues("What is a min-heap?", "Binary tree where parent is smaller than children",
                                "Binary tree where parent is greater than children", "Complete binary tree",
                                "None of the above",
                                "Binary tree where parent is smaller than children"));
                que.add(new AddQues("What is the time complexity of inserting an element into a heap?", "O(1)",
                                "O(log n)",
                                "O(n)", "O(n^2)", "O(log n)"));

                // Sorting Algorithms
                que.add(new AddQues("What is the time complexity of bubble sort?", "O(n)", "O(n log n)", "O(n^2)",
                                "O(log n)",
                                "O(n^2)"));
                que.add(new AddQues("What is the best case time complexity of insertion sort?", "O(1)", "O(n)",
                                "O(n log n)",
                                "O(n^2)", "O(n)"));
                que.add(new AddQues("Which sorting algorithm is considered the fastest on average?", "Selection Sort",
                                "Quick Sort", "Merge Sort", "Bubble Sort", "Quick Sort"));
                que.add(new AddQues("What is the worst-case time complexity of merge sort?", "O(n log n)", "O(n)",
                                "O(n^2)",
                                "O(log n)", "O(n log n)"));

                // Searching Algorithms
                que.add(new AddQues("What is the time complexity of binary search in a sorted array?", "O(1)",
                                "O(log n)",
                                "O(n)", "O(n^2)", "O(log n)"));
                que.add(new AddQues("What is the time complexity of linear search?", "O(1)", "O(log n)", "O(n)",
                                "O(n^2)",
                                "O(n)"));
                que.add(new AddQues(
                                "Which of the following searching algorithms is the most efficient on a sorted array?",
                                "Linear Search", "Binary Search", "Jump Search", "Exponential Search",
                                "Binary Search"));

                // Graphs
                que.add(new AddQues("What is the time complexity of a depth-first search?", "O(n)", "O(n^2)",
                                "O(E + V)",
                                "O(E log V)", "O(E + V)"));
                que.add(new AddQues("Which graph traversal technique uses a stack?", "Breadth-first search",
                                "Depth-first search", "Topological sorting", "None of the above",
                                "Depth-first search"));
                que.add(new AddQues("In a directed graph, what does a cycle indicate?", "A loop",
                                "A path between two nodes",
                                "No path between nodes", "A tree structure", "A loop"));
                que.add(new AddQues("Which of the following is used to represent a graph?", "Adjacency matrix",
                                "Adjacency list", "Edge list", "All of the above", "All of the above"));

                // Dynamic Programming
                que.add(new AddQues("What is the time complexity of the Fibonacci algorithm using dynamic programming?",
                                "O(1)",
                                "O(log n)", "O(n)", "O(n^2)", "O(n)"));
                que.add(new AddQues("What is the principle of optimality in dynamic programming?",
                                "An optimal solution to a problem contains optimal solutions to its subproblems",
                                "A problem can be solved by breaking it down into smaller subproblems",
                                "A problem can be solved recursively", "A problem can be solved iteratively",
                                "An optimal solution to a problem contains optimal solutions to its subproblems"));

                // Miscellaneous
                que.add(new AddQues("Which data structure is used for implementing a priority queue?", "Stack", "Queue",
                                "Heap",
                                "Array", "Heap"));
                que.add(new AddQues("What is the space complexity of a hash table?", "O(1)", "O(n)", "O(log n)",
                                "O(n^2)",
                                "O(n)"));
                que.add(new AddQues("Which of the following is not a type of linked list?", "Singly linked list",
                                "Doubly linked list", "Circular linked list", "Multi-level linked list",
                                "Multi-level linked list"));
                que.add(new AddQues("What is the time complexity of deleting the last element in a singly linked list?",
                                "O(1)",
                                "O(log n)", "O(n)", "O(n^2)", "O(n)"));
                que.add(new AddQues(
                                "In which type of linked list is each node connected to its previous and next node?",
                                "Singly linked list", "Doubly linked list", "Circular linked list",
                                "Random linked list",
                                "Doubly linked list"));
                que.add(new AddQues("What is the best case time complexity of quicksort?", "O(n)", "O(n log n)",
                                "O(n^2)",
                                "O(log n)", "O(n log n)"));
                que.add(new AddQues("Which algorithm is used for finding minimum spanning trees?", "BFS", "DFS",
                                "Kruskal's Algorithm", "Merge Sort", "Kruskal's Algorithm"));

             
        }

        public void skipQuestion() {
                score -= 1; // Deduct 1 mark for skipping
                questionNo++;
                options.clearSelection(); // Clear the current selection

                if (questionNo < 15) {
                        start(questionNo); // Load the next question
                } else {
                        // End of quiz logic
                        JOptionPane.showMessageDialog(this, "Quiz Over! your score is : "+ score);
                       


                        // new Score(score);
                        home.score = score;
                        home.setVisible(true);
                        dispose();
                }
        }

        public void actionPerformed(ActionEvent e) {

                if (e.getSource() == next) {
                        // check if any option is selected
                        if (op1.isSelected() || op2.isSelected() || op3.isSelected() || op4.isSelected()) {
                                // extract the options
                                String ansGiven = "";

                                if (op1.isSelected())
                                        ansGiven = op1.getText();
                                if (op2.isSelected())
                                        ansGiven = op2.getText();
                                if (op3.isSelected())
                                        ansGiven = op3.getText();
                                if (op4.isSelected())
                                        ansGiven = op4.getText();

                                if (ansGiven.equals(que.get(questionNo).ans)) {
                                        score += 5; // Increment score for correct answer
                                } else {
                                        score -= 1; // Deduct score for incorrect answer
                                }

                                // move to next question
                                questionNo++;
                                options.clearSelection(); // clear the current selection

                                // total 15 questions are asked
                                if (questionNo < 15) {
                                        start(questionNo);
                                } else {
                                        // end of quiz
                                        skipQuestion();
                                }

                        } else // if any button is not selected
                        {
                                JOptionPane.showMessageDialog(null, "Please select any option!!");
                        }

                } else if (e.getSource() == skip) {

                        skipQuestion();
                }
        }
}
