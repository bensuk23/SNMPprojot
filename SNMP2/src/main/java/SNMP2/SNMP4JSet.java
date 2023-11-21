package SNMP2;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMP4JSet
{
    public static void main(String[] args)
    {
        TransportMapping transport=null;
        try
        {
            transport = new DefaultUdpTransportMapping();
            transport.listen();
        }
        catch (IOException ex)
        {
            Logger.getLogger(SNMP4JGet.class.getName()).log(Level.SEVERE, null, ex);
        }

        CommunityTarget target = new CommunityTarget();
        target.setVersion(SnmpConstants.version1);
        target.setCommunity(new OctetString("COMMURW"));

        Address targetAddress;
        targetAddress = GenericAddress.parse("udp:192.168.68.109/161");
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);

        PDU pdu = new PDU();
        pdu.setType(PDU.SET);
        pdu.add(new VariableBinding(new OID(new int[]{1,3,6,1,2,1,1,4,0}), new OctetString("wtf les ami")));

        Snmp snmp = new Snmp(transport);
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
    }}

