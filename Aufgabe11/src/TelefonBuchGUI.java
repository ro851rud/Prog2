import javax.swing.*;

public class TelefonBuchGUI extends JFrame {

    private TelefonBuch telBuch;

    public TelefonBuchGUI() {
        // TelefonBuch anlegen:
        telBuch = new TelefonBuch();

        // Menuleiste einbauen:
        JMenuBar menuBar = new TelefonBuchMenuBar(telBuch);
        this.setJMenuBar(menuBar);

        // mainPanel mit Umrandung versehen und das
        // Einfuegen- und  SuchenLoeschenPanel einbauen:
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JPanel panel1 = new TelefonBuchEinfuegenPanel(telBuch);
        mainPanel.add(panel1);

        JPanel panel2 = new TelefonBuchSuchenLoeschenPanel(telBuch);
        mainPanel.add(panel2);

        this.setContentPane(mainPanel);

        // Sonstige Eigenschaften des Hauptfenster setzen:
        this.setTitle("Telefonbuch");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TelefonBuchGUI();
    }
}