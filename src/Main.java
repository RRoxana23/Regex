import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Regex app = new Regex();
                app.setVisible(true);
            }
        });
    }
}