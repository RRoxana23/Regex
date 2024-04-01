import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Regex regex = new Regex();
            regex.setVisible(true);
        });
    }
}