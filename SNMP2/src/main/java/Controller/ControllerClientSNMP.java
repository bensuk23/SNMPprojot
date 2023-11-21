package Controller;

import GUISNMP.ClientSNMP;
import GUISNMP.FenetreSET;

public class ControllerClientSNMP
{
    private static ClientSNMP fenetreClient;
    public ControllerClientSNMP(ClientSNMP frame)
    {
        this.fenetreClient = frame;

        fenetreClient.setTitle("(SNMP) NMS");
        fenetreClient.setSize(900,500);
    }
}
