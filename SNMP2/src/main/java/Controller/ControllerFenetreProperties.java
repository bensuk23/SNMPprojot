package Controller;

import GUISNMP.FenetreProperties;
import GUISNMP.FenetreSET;

import java.io.IOException;

public class ControllerFenetreProperties
{

    private static FenetreProperties fenetreClient;

    public ControllerFenetreProperties(FenetreProperties frame) throws IOException
    {
        this.fenetreClient = frame;

        fenetreClient.setTitle("(SNMP) Properties");
        fenetreClient.setSize(1000,500);
    }
}
