package ar.utn.frba.dds.logger;

import ar.utn.frba.dds.logger.adapters.MongoDBDatabaseAdapter;
import ar.utn.frba.dds.logger.adapters.MySQLDatabaseAdapter;
import ar.utn.frba.dds.logger.strategies.DatabaseErrorLoggingStrategy;
import ar.utn.frba.dds.logger.strategies.ErrorLoggingStrategy;
import ar.utn.frba.dds.logger.strategies.FileErrorLoggingStrategy;
import ar.utn.frba.dds.logger.strategies.TerminalErrorLoggingStrategy;

public class LoggerFactory {

    public static Logger create(String strategy) {
        return createInstance(strategy, "mysql");
    }

    public static Logger create(String strategy, String dbAdapterName) {
        return createInstance(strategy, dbAdapterName);
    }

    private static Logger createInstance(String strategy, String dbAdapterName) {
        return switch (strategy) {
            case "db" -> new Logger(createDBStrategy(dbAdapterName));
            case "file" -> new Logger(new FileErrorLoggingStrategy());
            case "terminal" -> new Logger(new TerminalErrorLoggingStrategy());
            default -> null;
        };
    }

    public static ErrorLoggingStrategy createDBStrategy(String adapterName) {
        return switch(adapterName) {
            case "mysql" -> new DatabaseErrorLoggingStrategy(new MySQLDatabaseAdapter());
            case "mongo" -> new DatabaseErrorLoggingStrategy(new MongoDBDatabaseAdapter());
            default -> null;
        };
    }
}
