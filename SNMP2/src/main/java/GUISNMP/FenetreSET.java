package GUISNMP;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FenetreSET extends JDialog {
    private JTextField textOID;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    private boolean ok;
    private JComboBox comboTypes;
    private JTextField textValue;
    private JPanel mainpanel;

    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    private String OID;

    public JTextField getTextOID() {
        return textOID;
    }

    public void setTextOID(JTextField textOID) {
        this.textOID = textOID;
    }

    public JButton getButtonOk() {
        return buttonOk;
    }

    public void setButtonOk(JButton buttonOk) {
        this.buttonOk = buttonOk;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    private JButton buttonOk;
    private JButton cancelButton;


    private String types ;

    private String Value;
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

    public FenetreSET()
    {
        super();
        setModal(true);
        setContentPane(mainpanel);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);


        pack();

        setTitle("(SNMP) SET");

        setSize(1000,500);



        getComboTypes().addItem("Int");
        getComboTypes().addItem("String");




       getButtonOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {


                OID = getTextOID().getText();


                types = (String) getComboTypes().getSelectedItem();

                Value = getTextValue().getText();

                setOk(true);

                setVisible(false);


            }
        });

       getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                setOk(false);
                setVisible(false);

            }
        });
    }

    public static void main(String[] args) throws IOException {
        FlatDarculaLaf.setup();

        FenetreSET frame = new FenetreSET();



        frame.setVisible(true);



    }
}
