package GUISNMP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClientSNMP extends JFrame
{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTable tableAffichageRequete;
    private JComboBox comboBox1;
    private JPanel panelPrincipale;
    private JScrollPane jScrollPaneAffichageRequete;

    public ClientSNMP()
    {

        setSize(1000, 1000);
        setMinimumSize(new Dimension(800, 800));




        setTitle("NMS (SNMP)");
        setContentPane(panelPrincipale);

        pack();
        //setSize(800,800);



        tableAffichageRequete = new JTable();
        DefaultTableModel tableModel1 = (DefaultTableModel) tableAffichageRequete.getModel();



        String[] nomsColonnes1 = { "Article", "Prix a l'unité", "Quantité"};
        tableModel1.setColumnIdentifiers(nomsColonnes1);
        jScrollPaneAffichageRequete.setViewportView(tableAffichageRequete);
    }


}
