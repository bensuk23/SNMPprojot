package SNMP2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMP4JGetNext
{
    public static void main(String[] args)
    {
        try
        {
            TransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();

            Snmp snmp = new Snmp(transport);

            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString("COMMURO"));

            Address targetAddress = GenericAddress.parse("udp:192.168.68.109/161");
            target.setAddress(targetAddress);
            target.setRetries(2);
            target.setTimeout(1500);
            target.setVersion(SnmpConstants.version2c);
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,4})));
            pdu.add(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,5})));
            pdu.setType(PDU.GETNEXT);

            SnmpListener listener = new SnmpListener(snmp);
            snmp.send(pdu, target, null, listener);
            synchronized(snmp)
            {
                snmp.wait();
            }
        }
        catch (IOException ex)
        {
            //Logger.getLogger(Snmp4J.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            //Logger.getLogger(Snmp4J.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}