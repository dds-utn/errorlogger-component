package ar.utn.frba.dds.logger.adapters;

import ar.utn.frba.dds.logger.adapters.connectors.MySQLConnector;
import ar.utn.frba.dds.logger.utils.ConfigReader;

import java.util.Map;

public class MySQLDatabaseAdapter  implements DatabaseAdapter {
    private MySQLConnector mySQLConnector;
    private ConfigReader config;

    public MySQLDatabaseAdapter() {
        this.mySQLConnector = new MySQLConnector();
        this.config = new ConfigReader();
    }

    @Override
    public void connect() {
        try {
            this.mySQLConnector.connect(
                    this.config.getProperty("mySQLurlConnection"),
                    this.config.getProperty("mySQLusername"),
                    this.config.getProperty("mySQLpassword")
            );
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void insert(String errorLog, Map<String, Object> stringObjectMap) {
        try {
            String[] columnNames = stringObjectMap.keySet().toArray(new String[0]);
            Object[] values = stringObjectMap.values().toArray();
            this.mySQLConnector.insert(errorLog, columnNames, values);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void disconnect() {
        try {
            this.mySQLConnector.disconnect();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
