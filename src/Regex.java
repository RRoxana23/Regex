import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class Regex extends JFrame {
    private JTextArea patternField;
    private JTextArea textField;
    private JButton checkButton;
    private JLabel resultLabel;
    private JTextArea matchArea;

    public Regex() {
        setTitle("Expresii Regulate");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        patternField = new JTextArea(1, 30);

        JScrollPane patternScrollPane = new JScrollPane(patternField);
        patternScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        textField = new JTextArea(5, 30);
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);
        JScrollPane textScrollPane = new JScrollPane(textField);
        textScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        checkButton = new JButton("Verifica");
        resultLabel = new JLabel();
        matchArea = new JTextArea();
        JScrollPane matchScrollPane = new JScrollPane(matchArea);
        matchScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 10, 0, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel patternLabel = new JLabel("Pattern:");
        patternLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(patternLabel, gbc);

        gbc.gridy = 1;
        inputPanel.add(patternScrollPane, gbc);

        gbc.gridy = 2;
        JLabel textLabel = new JLabel("Text:");
        textLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(textLabel, gbc);

        gbc.gridy = 3;
        inputPanel.add(textScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        checkButton.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(checkButton, gbc);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(2, 1));
        resultPanel.add(resultLabel);
        resultPanel.add(matchScrollPane);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(resultPanel, BorderLayout.CENTER);

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pattern = patternField.getText();
                String text = textField.getText();

                try {
                    //Pattern regexPattern = Pattern.compile("(?i)\\b(" + pattern + ")\\b");
                    Pattern regexPattern = Pattern.compile("(?i)(" + pattern + ")");
                    Matcher matcher = regexPattern.matcher(text);

                    matchArea.setText("");

                    if (matcher.find()) {
                        resultLabel.setText("Textul are match cu pattern-ul.");
                        do {
                            matchArea.append(matcher.group() + "\n");
                        } while (matcher.find());
                    } else {
                        resultLabel.setText("Textul nu are match cu pattern-ul!");
                    }
                } catch (PatternSyntaxException ex) {
                    JOptionPane.showMessageDialog(null, "Pattern-ul introdus este invalid: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}