package Controller;

import EnrgistrementFichier.PropertiesManager;
import GUISNMP.ClientSNMP;
import GUISNMP.FenetreProperties;
import GUISNMP.FenetreSET;
import SNMP2.DATAASS;
import SNMP2.SnmpListener;

import SNMP2.SnmpListenerSet;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControllerClientSNMP
{
    private static ClientSNMP fenetreClientsnmp;

    private static FenetreSET dialog;

    private List<DATAASS> data;



    protected String IP;

    protected String OID;


    private String typesp;




    private String fileName = "configprop.properties";

    private PropertiesManager propertiesManager;
    public ControllerClientSNMP(ClientSNMP frame)
    {
        this.fenetreClientsnmp = frame;

        data = new ArrayList<>();

        fenetreClientsnmp.setTitle("(SNMP) NMS");
        fenetreClientsnmp.setSize(900,500);
        fenetreClientsnmp.getGOButton();
        fenetreClientsnmp.getComboOperations().addItem("GET");
        fenetreClientsnmp.getComboOperations().addItem("GETNEXT");
        fenetreClientsnmp.getComboOperations().addItem("SET");

        propertiesManager = new PropertiesManager(fileName);


        fenetreClientsnmp.getGOButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                IP = fenetreClientsnmp.getTextadresse().getText();
                OID = fenetreClientsnmp.getTextOID().getText();
                typesp = (String) fenetreClientsnmp.getComboOperations().getSelectedItem();

                if(typesp.equals("GET"))
                {
                    SNMPGET();
                }
                else if(typesp.equals("GETNEXT"))
                {
                    SNMPGETNEXT();
                }

            }
        });

        fenetreClientsnmp.getButtonClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DefaultTableModel model = (DefaultTableModel) fenetreClientsnmp.getTableAffichageRequete().getModel();
                int taille = fenetreClientsnmp.getTableAffichageRequete().getRowCount();

                for (int i=0;i<taille;i++)
                {
                    model.removeRow(0);
                    System.out.println("Remove pass = " );
                }

            }
        });


        fenetreClientsnmp.getParametreAvanceButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                FenetreProperties dialog1 = new FenetreProperties();



                dialog1.pack();
                dialog1.setVisible(true);


            }
        });

        fenetreClientsnmp.getComboOperations().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Réagir à la sélection de la ComboBox
                IP = fenetreClientsnmp.getTextadresse().getText();
                String selectedItem = (String) fenetreClientsnmp.getComboOperations().getSelectedItem();
                if (selectedItem != null) {
                    if (selectedItem.equals("SET"))
                    {
                        // Remplacez cela par le code pour ouvrir la nouvelle fenêtre souhaitée
                        dialog = new FenetreSET();

                        dialog.pack();

                        dialog.setVisible(true);

                        if (dialog.isOk())
                        {

                            SNMPSET();
                            JOptionPane.showMessageDialog(null, "Requete SNMP SET envoyé à l'agent");
                        }

                    }
                }
            }
        });
    }

    public void SNMPGET()
    {

        try
        {
            TransportMapping transport = null;
            try {
                transport = new DefaultUdpTransportMapping();
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
            try {
                transport.listen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



            Snmp snmp = new Snmp(transport);

            CommunityTarget target = new CommunityTarget();




            target.setCommunity(new OctetString(propertiesManager.getProperty("communityRO")));

            Address targetAddress ;
            targetAddress = GenericAddress.parse("udp:"+IP+"/"+propertiesManager.getProperty("port"));
            target.setAddress(targetAddress);
            target.setRetries(2);
            target.setTimeout(1500);

            if(propertiesManager.getProperty("Ver") != null)
            {
                int vertemp = Integer.parseInt(propertiesManager.getProperty("Ver"));
                if(vertemp == 1)
                {
                    target.setVersion(SnmpConstants.version1);
                }
                else if (vertemp == 2)
                {
                    target.setVersion(SnmpConstants.version2c);
                }
            }

            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(OID)));


            pdu.setType(PDU.GET);

            if(propertiesManager.getProperty("SA") != null)
            {
                String temp = propertiesManager.getProperty("SA");
                if(temp.equals("Synchrone"))
                {
                    ResponseEvent paquetReponse = null;

                    try
                    {
                        paquetReponse = snmp.get(pdu, target);
                        System.out.println("Requete SNMP envoyée à l'agent");
                    }
                    catch (IOException ex) {}

                    if (paquetReponse !=null)
                    {
                        PDU pduReponse = paquetReponse.getResponse();
                        System.out.println("Status réponse = " + pduReponse.getErrorStatus());
                        System.out.println("Status réponse = " + pduReponse.getErrorStatusText());

                        DefaultTableModel model = (DefaultTableModel) fenetreClientsnmp.getTableAffichageRequete().getModel();
                        int taille = fenetreClientsnmp.getTableAffichageRequete().getRowCount();



                        List<VariableBinding> vecReponse = (List<VariableBinding>) pduReponse.getVariableBindings();



                        for (int i = 0; i < vecReponse.size(); i++) {
                            VariableBinding variableBinding = vecReponse.get(i);
                            String variable = variableBinding.getOid().toString();
                            String valeur = variableBinding.getVariable().toString();
                            String val = variableBinding.getVariable().getSyntaxString();
                            model.addRow(new Object[]{variable, valeur,val,"161"}); // Ajout de la ligne au modèle de tableau
                        }


                    }
                }
                else if (temp.equals("Asynchrone"))
                {
                    SnmpListener listener = new SnmpListener(snmp,data);
                    snmp.send(pdu, target, null, listener);
                    synchronized(snmp)
                    {

                        snmp.wait();

                    }
                    DefaultTableModel model = (DefaultTableModel) fenetreClientsnmp.getTableAffichageRequete().getModel();
                    int taille = fenetreClientsnmp.getTableAffichageRequete().getRowCount();



                    for (int i = 0; i < data.size(); i++) {

                        String variable = data.get(i).getNameOrOID();
                        String valeur = data.get(i).getValue();
                        String val = data.get(i).getType();
                        model.addRow(new Object[]{variable, valeur,val,"161"}); // Ajout de la ligne au modèle de tableau
                    }

                }
            }
        }
        catch (IOException ex)
        {

        }
            catch (InterruptedException ex)
        {

        }
    }

    public void SNMPGETNEXT()
    {
        try
        {
            TransportMapping transport = null;

            transport = new DefaultUdpTransportMapping();


            transport.listen();


            Snmp snmp = new Snmp(transport);

            CommunityTarget target = new CommunityTarget();


            target.setCommunity(new OctetString(propertiesManager.getProperty("communityRO")));

            Address targetAddress ;
            targetAddress = GenericAddress.parse("udp:"+IP+"/"+propertiesManager.getProperty("port"));
            target.setAddress(targetAddress);
            target.setRetries(2);
            target.setTimeout(1500);

            if(propertiesManager.getProperty("Ver") != null)
            {
                int vertemp = Integer.parseInt(propertiesManager.getProperty("Ver"));
                if(vertemp == 1)
                {
                    target.setVersion(SnmpConstants.version1);
                }
                else if (vertemp == 2)
                {
                    target.setVersion(SnmpConstants.version2c);
                }
            }

            PDU pdu = new PDU();

            pdu.add(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,4})));
            pdu.add(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,5})));
            pdu.add(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,6})));


            pdu.setType(PDU.GETNEXT);


            SnmpListener listener = new SnmpListener(snmp,data);
            snmp.send(pdu, target, null, listener);
            synchronized(snmp)
            {

                snmp.wait();

            }
            DefaultTableModel model = (DefaultTableModel) fenetreClientsnmp.getTableAffichageRequete().getModel();
            int taille = fenetreClientsnmp.getTableAffichageRequete().getRowCount();



            for (int i = 0; i < data.size(); i++) {

                String variable = data.get(i).getNameOrOID();
                String valeur = data.get(i).getValue();
                String val = data.get(i).getType();
                model.addRow(new Object[]{variable, valeur,val,"161"}); // Ajout de la ligne au modèle de tableau
            }

        }
        catch (IOException ex)
        {

        }
        catch (InterruptedException ex)
        {

        }
    }

    public  void SNMPSET()
    {

        TransportMapping transport=null;
        try
        {
            transport = new DefaultUdpTransportMapping();
            transport.listen();
        }
        catch (IOException ex)
        {

        }

        CommunityTarget target = new CommunityTarget();

        if(propertiesManager.getProperty("Ver") != null)
        {
            int vertemp = Integer.parseInt(propertiesManager.getProperty("Ver"));
            if(vertemp == 1)
            {
                target.setVersion(SnmpConstants.version1);
            }
            else if (vertemp == 2)
            {
                target.setVersion(SnmpConstants.version2c);
            }
        }

        target.setCommunity(new OctetString(propertiesManager.getProperty("communityRW")));

        Address targetAddress;
        targetAddress = GenericAddress.parse("udp:"+IP+"/"+propertiesManager.getProperty("port"));
        System.out.println(targetAddress);
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);

        PDU pdu = new PDU();
        pdu.setType(PDU.SET);

        pdu.add(new VariableBinding(new OID(dialog.getOID()), new OctetString(dialog.getValue())));

        Snmp snmp = new Snmp(transport);

        if(propertiesManager.getProperty("SA") != null)
        {
            String temp = propertiesManager.getProperty("SA");
            if(temp.equals("Synchrone"))
            {
                ResponseEvent paquetReponse = null;

                try
                {
                    paquetReponse = snmp.set(pdu, target);
                    System.out.println("Requete SNMP envoyée à l'agent");
                }
                catch (IOException ex) {}
                if (paquetReponse !=null)
                {
                    PDU pduReponse = paquetReponse.getResponse();
                    System.out.println("Status réponse = " + pduReponse.getErrorStatus());
                    System.out.println("Status réponse = " + pduReponse.getErrorStatusText());

                    List<VariableBinding> vecReponse = (List<VariableBinding>) pduReponse.getVariableBindings();

                    for (int i=0; i<vecReponse.size(); i++)
                    {
                        System.out.println("Elément n°"+i+ " : "+vecReponse.get(i));
                    }

                }
            }
            else if (temp.equals("Asynchrone"))
            {
                SnmpListenerSet listener = new SnmpListenerSet(snmp);
                try {
                    snmp.send(pdu, target, null, listener);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                synchronized(snmp)
                {

                    try {
                        snmp.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        }



    }
}

