import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.List;

public class TelefonBuchSuchenLoeschenPanel
        extends JPanel implements ActionListener {

    private TelefonBuch telBuch;
    private JComboBox suloe;
    private JButton anwendenButton;
    private JTextField name;
    private JTextField zusatz;
    private JTextArea ausgabe;
    private JFileChooser fc;

    public TelefonBuchSuchenLoeschenPanel(TelefonBuch tb) {

        this.telBuch = tb;

        String[] auswahl = {"Exakte Suche", "Prefix- Suche", "Löschen"};
        suloe = new JComboBox<String >(auswahl);
        suloe.setSelectedIndex(2);
        suloe.addActionListener(this);

        anwendenButton = new JButton("Anwenden");
        anwendenButton.addActionListener(this);

        name = new JTextField("", 10);
        name.addActionListener(this);

        zusatz = new JTextField("", 10);
        zusatz.addActionListener(this);

        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(2,1 ));
        pan.add(new JLabel("Name"));
        pan.add(new JLabel("Zusatz"));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(name);
        panel.add(zusatz);

        JPanel panel1 = new JPanel();
        panel1.add(suloe);

        JPanel panel2 = new JPanel();
        panel2.add(anwendenButton);




        ausgabe = new JTextArea(30, 40);
        ausgabe.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ausgabe);
        this.add(scrollPane);

        JPanel mainPanel = new JPanel();
        mainPanel.add(pan);
        mainPanel.add(panel);
        mainPanel.add(panel1);
        mainPanel.add(panel2);

        JPanel endPanel = new JPanel();
        endPanel.setLayout(new BoxLayout(endPanel,BoxLayout.Y_AXIS));
        endPanel.add(mainPanel);
        endPanel.add(ausgabe);


        fc = new JFileChooser();
        this.add(endPanel);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == anwendenButton) {
                if (suloe.getSelectedIndex() == 0) {
                    String g = telBuch.exactSearch(name.getText(), zusatz.getText());
                    ausgabe.setText(g);
                } else if (suloe.getSelectedIndex() == 1) {
                List<String> h = telBuch.prefixSearch(name.getText());
                StringBuilder sb = new StringBuilder();
                if (h == null) {
                    JOptionPane.showMessageDialog(this, "keine Namen gefunden");
                    return;
                }
                for (String s : h ) {
                    sb.append(s);
                }
                String s = sb.toString();
                ausgabe.setText(s);
            } else if (suloe.getSelectedIndex() == 2) {
                    boolean sucess = telBuch.remove(name.getText(), zusatz.getText());
                    ausgabe.setText("");
                    if (!sucess) {
                        JOptionPane.showMessageDialog(this,
                                "Name nicht gefunden");
                    }
                }
        }
    }
}