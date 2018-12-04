
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;

public class Taschenrechner extends JFrame implements ActionListener,
ItemListener {

    //Ein- und Ausgabefelder erstellen
    JTextField op1TextField;
    JTextField op2TextField;
    JTextField resTextField;
    JButton sumButton;
    JButton subButton;
    JButton mulButton;
    JButton difButton;
    JButton sinButton;
    JButton cosButton;
    JButton hochButton;
    JButton logButton;
    JButton clearButton;
    JRadioButton degButton;
    JRadioButton radButton;
    JCheckBox darkBox;
    boolean rad;
    boolean c;

    public Taschenrechner(){

        //Fenster erstellen
        this.setTitle("Taschenrechner");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setPreferredSize(new Dimension(200,200));
        //this.setBackground(Color.lightGray);

        //Schaltflaechen
        JLabel op1Label = new JLabel("Operand1");
        JLabel op2Label = new JLabel("Operand2");
        JLabel resLabel = new JLabel("Result");
        op1TextField = new JTextField("0", 10);
        op2TextField = new JTextField("0", 10);
        resTextField = new JTextField("0", 10);
        resTextField.setEditable(false);
        sumButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        difButton = new JButton("/");
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        hochButton = new JButton("x^y");
        logButton = new JButton("log2");
        clearButton = new JButton("clear");
        degButton = new JRadioButton("Deg");
        radButton = new JRadioButton("Rad");
        darkBox = new JCheckBox("Helles Display");


        sumButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        difButton.addActionListener(this);
        sinButton.addActionListener(this);
        cosButton.addActionListener(this);
        hochButton.addActionListener(this);
        logButton.addActionListener(this);
        clearButton.addActionListener(this);
        degButton.addActionListener(this);
        radButton.addActionListener(this);
        darkBox.addItemListener(this);
        darkBox.setSelected(true);

        //Radiobutten Gruppieren
        ButtonGroup group = new ButtonGroup();
        group.add(degButton);
        group.add(radButton);

        //Bedienfelder zusammensetzen
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 2));
        panel1.add(op1Label);
        panel1.add(op1TextField);
        panel1.add(op2Label);
        panel1.add(op2TextField);
        panel1.add(resLabel);
        panel1.add(resTextField);
        panel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 4));
        //panel2.setBackground(Color.lightGray);
        panel2.add(sumButton);
        panel2.add(subButton);
        panel2.add(mulButton);
        panel2.add(difButton);
        panel2.add(sinButton);
        panel2.add(cosButton);
        panel2.add(hochButton);
        panel2.add(logButton);
        panel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        JPanel panel3 = new JPanel();
        panel3.add(radButton);
        panel3.add(degButton);
        panel3.add(darkBox);

        JPanel panel4 = new JPanel();
        panel4.add(clearButton);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(panel1);
        panel.add(panel3);
        panel.add(panel2);
        panel.add(panel4);
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        panel.setBorder(BorderFactory.createEmptyBorder(70,70,70,70));

        setContentPane(panel);
        setLayout(new GridLayout(4, 4));

        setContentPane(panel);
        setSize(500, 350);

        //Hauptfenster ausgeben
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame myApp = new Taschenrechner();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == degButton) {
            this.rad = false;
        } else if (source == radButton) {
            this.rad = true;
        }
        String s1 = op1TextField.getText();
        String s2 = op2TextField.getText();
        double o1;
        double o2;

        try {
            o1 = Double.parseDouble(s1);
            o2 = Double.parseDouble(s2);
        } catch (IllegalArgumentException x){
            resTextField.setText("Falsche Eingabe");
            return;
        }
        if (source == sumButton) {
            resTextField.setText("" + (o1 + o2));
        } else if (source == mulButton) {
            resTextField.setText("" + (o1 * o2));
        } else if (source == difButton) {
            resTextField.setText("" + (o1 / o2));
        } else if (source == subButton) {
            resTextField.setText("" + (o1 - o2));
        } else if (source == hochButton) {
            resTextField.setText("" + Math.pow(o1, o2));
        } else if( source == sinButton) {
            if(rad == true) {
                resTextField.setText("" + Math.sin(o1));
                op2TextField.setText("0");
            } else if (rad == false){
                double sin = Math.toRadians(o1);
                resTextField.setText("" + Math.sin(sin));
                op2TextField.setText("0");

            } else {
                resTextField.setForeground(Color.red);
                resTextField.setText("keine Umrechenart gewählt!");
            }
        } else if (source == cosButton) {
            if (rad == true) {
                resTextField.setText("" + Math.cos(o1));
                op2TextField.setText("0");

            } else if (rad == false){
                double cos = Math.toRadians(o1);
                resTextField.setText("" + Math.cos(cos));
                op2TextField.setText("0");

            } else {
                resTextField.setForeground(Color.red);
                resTextField.setText("keine Umrechenart gewählt!");
            }
        } else if (source == logButton) {
            resTextField.setText("" + Math.log(o1)/Math.log(2));
        } else if (source == clearButton) {
            op2TextField.setText("0");
            op1TextField.setText("0");
            resTextField.setText("0");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        darkBox = (JCheckBox) e.getItem();
        int source = e.getStateChange();
        if (source == ItemEvent.SELECTED) {
            resTextField.setBackground(Color.white);
            resTextField.setForeground(Color.black);
            op1TextField.setBackground(Color.white);
            op1TextField.setForeground(Color.black);
            op2TextField.setBackground(Color.white);
            op2TextField.setForeground(Color.black);
        } else if (source == ItemEvent.DESELECTED) {
            resTextField.setBackground(Color.black);
            resTextField.setForeground(Color.yellow);
            op1TextField.setBackground(Color.black);
            op1TextField.setForeground(Color.yellow);
            op2TextField.setBackground(Color.black);
            op2TextField.setForeground(Color.yellow);

        }
    }
}
