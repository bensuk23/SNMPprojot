package GUISNMP;

import Controller.ControllerClientSNMP;
import Controller.ControllerFenetreSET;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.io.IOException;

public class FenetreSET extends JFrame {
    private JTextField textOID;
    private JComboBox comboTypes;
    private JTextField textValue;

    public JTextField getTextField1() {
        return textOID;
    }

    public void setTextField1(JTextField textField1) {
        this.textOID = textField1;
    }

    public JComboBox getComboTypes() {
        return comboTypes;
    }

    public void setComboTypes(JComboBox comboTypes) {
        this.comboTypes = comboTypes;
    }

    public JTextField getTextValue() {
        return textValue;
    }

    public void setTextValue(JTextField textValue) {
        this.textValue = textValue;
    }

    public FenetreSET ()
    {

    }

    public static void main(String[] args) throws IOException {
        FlatDarculaLaf.setup();

        FenetreSET frame = new FenetreSET();



        frame.setVisible(true);
        ControllerFenetreSET controllerfenetreset = new ControllerFenetreSET((FenetreSET)frame);


    }
}
