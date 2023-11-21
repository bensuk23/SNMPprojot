package ManagerFichier;

import java.io.*;
import java.util.*;

public class PropertiesManager
{
    private static final String CONFIG_FILE = "config.properties";

    public static Properties loadConfig() {
        Properties config = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            config.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static void saveConfig(Properties config) {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            config.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Exemple de chargement de la configuration
        Properties config = loadConfig();
        System.out.println("Configuration actuelle : " + config);

        // Exemple de modification de la configuration
        config.setProperty("agent.ip", "192.168.1.2");
        config.setProperty("agent.port", "162");

        // Modification du paramètre avancé
        config.setProperty("advanced.parameter", "new_value");

        saveConfig(config);
        System.out.println("Nouvelle configuration : " + loadConfig());
    }
}
