package GUISNMP;

import com.formdev.flatlaf.FlatDarculaLaf;
import Controller.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class ClientSNMP extends JFrame
{
    public Object get;

    public JTextField getTextadresse() {
        return textadresse;
    }

    public void setTextadresse(JTextField textadresse) {
        this.textadresse = textadresse;
    }

    public JTextField getTextOID() {
        return textOID;
    }

    public void setTextOID(JTextField textOID) {
        this.textOID = textOID;
    }

    public JTable getTableAffichageRequete() {
        return tableAffichageRequete;
    }

    public void setTableAffichageRequete(JTable tableAffichageRequete) {
        this.tableAffichageRequete = tableAffichageRequete;
    }

    public JPanel getPanelPrincipale() {
        return panelPrincipale;
    }

    public void setPanelPrincipale(JPanel panelPrincipale) {
        this.panelPrincipale = panelPrincipale;
    }

    public JScrollPane getjScrollPaneAffichageRequete() {
        return jScrollPaneAffichageRequete;
    }

    public void setjScrollPaneAffichageRequete(JScrollPane jScrollPaneAffichageRequete) {
        this.jScrollPaneAffichageRequete = jScrollPaneAffichageRequete;
    }

    public JButton getParametreAvanceButton() {
        return parametreAvanceButton;
    }

    public void setParametreAvanceButton(JButton parametreAvanceButton) {
        this.parametreAvanceButton = parametreAvanceButton;
    }

    public JButton getButtonClear() {
        return buttonClear;
    }

    public void setButtonClear(JButton buttonClear) {
        this.buttonClear = buttonClear;
    }

    public JComboBox getComboOperations() {
        return comboOperations;
    }

    public void setComboOperations(JComboBox comboOperations) {
        this.comboOperations = comboOperations;
    }

    public JButton getGOButton() {
        return GOButton;
    }

    public void setGOButton(JButton GOButton) {
        this.GOButton = GOButton;
    }

    private JTextField textadresse;
    private JTextField textOID;
    private JTable tableAffichageRequete;
    private JPanel panelPrincipale;
    private JScrollPane jScrollPaneAffichageRequete;
    private JButton parametreAvanceButton;
    private JButton buttonClear;
    private JComboBox comboOperations;
    private JButton GOButton;


    public ClientSNMP()
    {





        setTitle("NMS (SNMP)");
        setContentPane(panelPrincipale);

        pack();
        //setSize(800,800);



        tableAffichageRequete = new JTable();
        DefaultTableModel tableModel1 = (DefaultTableModel) tableAffichageRequete.getModel();



        String[] nomsColonnes1 = { "Name/OID", "Value", "Type", "Port"};
        tableModel1.setColumnIdentifiers(nomsColonnes1);
        jScrollPaneAffichageRequete.setViewportView(tableAffichageRequete);
    }

    public static void main(String[] args) throws IOException {
        FlatDarculaLaf.setup();

        ClientSNMP frame = new ClientSNMP();



        frame.setVisible(true);
        ControllerClientSNMP controllerclientsnmp = new ControllerClientSNMP((ClientSNMP)frame);


    }


}
