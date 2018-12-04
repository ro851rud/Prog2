
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class WuerfelApplikation
        extends JFrame
        implements ActionListener {
    private JLabel wuerfel;

    public WuerfelApplikation() {
        this.setTitle("Würfel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        JButton button = new JButton("Würfeln");
        this.add(button);
        button.addActionListener(this);
        this.wuerfel = new JLabel("Würfelzahl: 6");
        this.add(wuerfel);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int w = (int) (Math.random()*6 + 1);
        this.wuerfel.setText("Würfelzahl: " + w);
    }
    public static void main(String[] args) {
        JFrame meinApplikation
                = new WuerfelApplikation();
    }
}