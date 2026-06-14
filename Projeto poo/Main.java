import javax.swing.SwingUtilities;
import ui.TelaCadastro;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaCadastro().setVisible(true);
        });
    }
}