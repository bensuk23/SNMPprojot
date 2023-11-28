package SNMP2;


public class DATAASS {
    private String nameOrOID;
    private String value;
    private String type;
    private String port;

    // Constructeur
    public DATAASS(String nameOrOID, String value, String type, String port) {
        this.nameOrOID = nameOrOID;
        this.value = value;
        this.type = type;
        this.port = port;
    }

    // Méthodes getters et setters
    public String getNameOrOID() {
        return nameOrOID;
    }

    public void setNameOrOID(String nameOrOID) {
        this.nameOrOID = nameOrOID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


}
