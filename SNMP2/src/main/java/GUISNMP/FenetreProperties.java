package GUISNMP;

import Controller.ControllerFenetreProperties;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.io.IOException;

public class FenetreProperties extends JFrame
{


    public JTextField getTextAddress() {
        return textAddress;
    }

    public void setTextAddress(JTextField textAddress) {
        this.textAddress = textAddress;
    }

    public JTextField getTextPort() {
        return textPort;
    }

    public void setTextPort(JTextField textPort) {
        this.textPort = textPort;
    }

    public JTextField getTextRC() {
        return textRC;
    }

    public void setTextRC(JTextField textRC) {
        this.textRC = textRC;
    }

    public JTextField getTextWC() {
        return textWC;
    }

    public void setTextWC(JTextField textWC) {
        this.textWC = textWC;
    }

    public JComboBox getComboVersion() {
        return comboVersion;
    }

    public void setComboVersion(JComboBox comboVersion) {
        this.comboVersion = comboVersion;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public void setOkButton(JButton okButton) {
        this.okButton = okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    private JTextField textAddress;
    private JTextField textPort;
    private JTextField textRC;
    private JTextField textWC;
    private JComboBox comboVersion;
    private JButton okButton;
    private JButton cancelButton;

    public static void main(String[] args) throws IOException {
        FlatDarculaLaf.setup();

        FenetreProperties frame = new FenetreProperties();



        frame.setVisible(true);
        ControllerFenetreProperties controllerfenetreset = new ControllerFenetreProperties((FenetreProperties)frame);


    }
}
