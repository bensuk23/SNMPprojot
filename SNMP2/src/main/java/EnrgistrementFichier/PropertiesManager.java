package EnrgistrementFichier;

import java.io.*;
import java.util.*;

public class PropertiesManager {
    private static Properties properties;
    private  String fileName ;

    public PropertiesManager(String fileName) {
        this.fileName = fileName;
        properties = new Properties();
        loadProperties();

    }

    // Charge les propriétés à partir du fichier .properties
    private void loadProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Enregistre les propriétés dans le fichier .properties
    public void saveProperties() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Obtient la valeur d'une clé
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Définit la valeur d'une clé
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }


    public static void main(String[] args) {
        String fileName = "configprop.properties";



        // Création d'une instance de PropertiesManager
        PropertiesManager propertiesManager = new PropertiesManager(fileName);
        propertiesManager.loadProperties();

        // Définition des clés et des valeurs


        propertiesManager.setProperty("port","");
        propertiesManager.setProperty("communityRO","");
        propertiesManager.setProperty("communityRW","");
        propertiesManager.setProperty("Ver", "");
        propertiesManager.setProperty("SA", "");


        // Récupération des valeurs à partir des clés


        String port = propertiesManager.getProperty("port");
        String communityRO = propertiesManager.getProperty("communityRO");
        String communityRW = propertiesManager.getProperty("communityRW");
        String Ver = propertiesManager.getProperty("Ver");
        String SA = propertiesManager.getProperty("SA");


        System.out.println("password: " + port);
        System.out.println("username: " + communityRO);
        System.out.println("password: " + communityRW);
        System.out.println("username: " + Ver);
        System.out.println("username: " + SA);


        propertiesManager.saveProperties();
    }


}