package ar.utn.frba.dds.logger.strategies;

import ar.utn.frba.dds.logger.Error;
import ar.utn.frba.dds.logger.adapters.DatabaseAdapter;
import ar.utn.frba.dds.logger.utils.ObjectToMapConversor;

public class DatabaseErrorLoggingStrategy implements ErrorLoggingStrategy {
    private DatabaseAdapter databaseAdapter;

    public DatabaseErrorLoggingStrategy(DatabaseAdapter databaseAdapter) {
        this.databaseAdapter = databaseAdapter;
    }

    @Override
    public void logError(Error error) {
        this.databaseAdapter.connect();

        this.databaseAdapter.insert("error_log", ObjectToMapConversor.convertObjectToMap(error));

        this.databaseAdapter.disconnect();
    }
}
