
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;


public class TelefonBuchMenuBar
        extends JMenuBar implements ActionListener {

    private TelefonBuch telBuch;
    JMenuBar menuBar;
    JMenu datei;
    JMenuItem lesen;
    JMenuItem speichern;
    JMenuItem beenden;
    JFileChooser fc;

    public TelefonBuchMenuBar(TelefonBuch tb) {
        telBuch = tb;
        menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setPreferredSize(new Dimension(200, 20));

        datei = new JMenu("Datei");
        datei.setMnemonic(KeyEvent.VK_A);

        lesen = new JMenuItem("Telefonbuch lesen...");
        lesen.setMnemonic(KeyEvent.VK_T);
        lesen.addActionListener(this);
        datei.add(lesen);

        speichern = new JMenuItem("Telefonbuch speichern...");
        speichern.setMnemonic(KeyEvent.VK_T);
        speichern.addActionListener(this);
        datei.add(speichern);

        beenden = new JMenuItem("Telefonbuch beenden");
        beenden.setMnemonic(KeyEvent.VK_T);
        beenden.addActionListener(this);
        datei.add(beenden);

        fc = new JFileChooser();

        this.add(datei);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == lesen) {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                telBuch.read(fc.getSelectedFile());
            }
        } else if (source == speichern) {
            int returnVal1 = fc.showSaveDialog(this);
            if (returnVal1 == JFileChooser.APPROVE_OPTION) {
                telBuch.save(fc.getSelectedFile());
            }
        } else if (source == beenden){
                int n = JOptionPane.showConfirmDialog(this,
                        "Wollen Sie das Programm wirklich beenden?",
                        "Beenden",
                        JOptionPane.YES_NO_OPTION
                        );
                if (n == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else if (n == JOptionPane.NO_OPTION) {
                    return;
                }
        }
    }
}