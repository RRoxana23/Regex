import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class Regex extends JFrame {
    private JTextArea patternField;
    private JTextField text1Field;
    private JTextArea text2Field;
    private JButton checkButtonText1;
    private JButton checkButtonText2;
    private JLabel resultLabel;
    private JTextArea matchArea;

    public Regex() {
        setTitle("Expresii Regulate");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        patternField = new JTextArea(1, 30);

        JScrollPane patternScrollPane = new JScrollPane(patternField);
        patternScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        text1Field = new JTextField(30);
        JScrollPane text1ScrollPane = new JScrollPane(text1Field);
        text1ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        text2Field = new JTextArea(5, 30);
        text2Field.setLineWrap(true);
        text2Field.setWrapStyleWord(true);
        JScrollPane text2ScrollPane = new JScrollPane(text2Field);
        text2ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        checkButtonText1 = new JButton("Verifica");
        checkButtonText2 = new JButton("Verifica");
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
        JLabel text1Label = new JLabel("Text1:");
        text1Label.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(text1Label, gbc);

        gbc.gridy = 3;
        inputPanel.add(text1ScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        checkButtonText1.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(checkButtonText1, gbc);

        gbc.gridy = 5;
        JLabel text2Label = new JLabel("Text2:");
        text2Label.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(text2Label, gbc);

        gbc.gridy = 6;
        inputPanel.add(text2ScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        checkButtonText2.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(checkButtonText2, gbc);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(2, 1));
        resultPanel.add(resultLabel);
        resultPanel.add(matchScrollPane);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(resultPanel, BorderLayout.CENTER);

        checkButtonText1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleCheckButtonClick(text1Field);
            }
        });

        checkButtonText2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleCheckButtonClick(text2Field);
            }
        });
    }

    private void handleCheckButtonClick(JComponent textComponent) {
        String pattern = patternField.getText();
        String text = "";

        if (textComponent instanceof JTextArea) {
            text = ((JTextArea) textComponent).getText();
        } else if (textComponent instanceof JTextField) {
            text = ((JTextField) textComponent).getText();
        }

        try {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Regex regex = new Regex();
            regex.setVisible(true);
        });
    }
}
