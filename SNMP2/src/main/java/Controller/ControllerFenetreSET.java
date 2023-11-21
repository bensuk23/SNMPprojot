package Controller;

import GUISNMP.FenetreSET;

import java.io.IOException;
import javax.swing.*;
import java.awt.*;

public class ControllerFenetreSET
{

    private static FenetreSET fenetreClient;

    public ControllerFenetreSET(FenetreSET frame) throws IOException
    {
        this.fenetreClient = frame;

        fenetreClient.setTitle("(SNMP) SET");
        fenetreClient.setSize(1000,500);
    }

}
