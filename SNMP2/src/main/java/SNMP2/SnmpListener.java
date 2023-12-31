package SNMP2;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import java.util.List;

public class SnmpListener implements ResponseListener
{
    private Snmp snmpManager;

    private List<DATAASS> data;
    public SnmpListener(Snmp s, List<DATAASS> datap)
    {
        snmpManager = s;
        data = datap;
    }


    public void onResponse(ResponseEvent event)
    {
        ((Snmp)event.getSource()).cancel(event.getRequest(), this);
        PDU rep = event.getResponse();
        int nValues = rep.size();
        data.clear();
        for (int i=0; i<nValues; i++)
        {
            VariableBinding vb = rep.get(i);
            System.out.println(i + ") OID: "+ vb.getOid());
            Variable value = vb.getVariable();
            System.out.println("Value = "+ value.toString());
            System.out.println("Syntax = "+ value.getSyntax());
            System.out.println("SyntaxString = "+ value.getSyntaxString());

            DATAASS temp = new DATAASS(String.valueOf(vb.getOid()),value.toString(),value.getSyntaxString(),"161") ;

            data.add(temp);



        }

        synchronized(snmpManager)
        {

            snmpManager.notify();
        }

    }



}




