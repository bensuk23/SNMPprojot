package GUISNMP;

import EnrgistrementFichier.PropertiesManager;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FenetreProperties extends JDialog
{
    private PropertiesManager propertiesManager;

    private String port;

    private String ComRO;

    private String ComRW;

    private String Version;

    private String SouA;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    private boolean ok;
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

    private String fileName = "configprop.properties";

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
    private JPanel MainPanel;

    public JComboBox getComboAS() {
        return comboAS;
    }

    public void setComboAS(JComboBox comboAS) {
        this.comboAS = comboAS;
    }

    private JComboBox comboAS;

    public FenetreProperties()
    {




        setContentPane(MainPanel);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);


        pack();

        setTitle("(SNMP) Properties");
        setSize(1000,500);





        // Création d'une instance de PropertiesManager
        propertiesManager = new PropertiesManager(fileName);


        getComboVersion().addItem("1");
        getComboVersion().addItem("2");

        getComboAS().addItem("Synchrone");
        getComboAS().addItem("Asynchrone");



        if(propertiesManager.getProperty("port") != null)
        {

            getTextPort().setText(propertiesManager.getProperty("port"));

        }

        if(propertiesManager.getProperty("communityRO") != null)
            getTextRC().setText(propertiesManager.getProperty("communityRO"));

        if(propertiesManager.getProperty("communityRW") != null)
            getTextWC().setText(propertiesManager.getProperty("communityRW"));

        if(propertiesManager.getProperty("Ver") != null)
        {
            int vertemp = Integer.parseInt(propertiesManager.getProperty("Ver"));
            if(vertemp == 1)
            {
              getComboVersion().setSelectedIndex(0);
            }
            else if (vertemp == 2)
            {
                getComboVersion().setSelectedIndex(1);
            }
        }

        if(propertiesManager.getProperty("SA") != null) {
            String SAtemp = propertiesManager.getProperty("SA");
            if (SAtemp.equals("Synchrone")) {
               getComboAS().setSelectedIndex(0); // Utiliser getComboAS() ici
            } else if (SAtemp.equals("Asynchrone")) {
               getComboAS().setSelectedIndex(1); // Utiliser getComboAS() ici
            }
        }






        getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                propertiesManager.saveProperties();
                setOk(false);
                setVisible(false);


            }
        });
        getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                port = getTextPort().getText();
                ComRO =getTextRC().getText();
                ComRW = getTextWC().getText();



                Version = (String) getComboVersion().getSelectedItem();


                SouA = (String) getComboAS().getSelectedItem();


                propertiesManager.setProperty("port", port);
                propertiesManager.setProperty("communityRO", ComRO);
                propertiesManager.setProperty("communityRW", ComRW);
                propertiesManager.setProperty("Ver", Version);
                propertiesManager.setProperty("SA", SouA);

                propertiesManager.saveProperties();
                setOk(true);

                setVisible(false);







            }
        });
        //setSize(800,800);





    }

    public static void main(String[] args) throws IOException {
        FlatDarculaLaf.setup();

        FenetreProperties frame = new FenetreProperties();



        frame.setVisible(true);



    }
}
