package ar.utn.frba.dds.logger;

import ar.utn.frba.dds.logger.adapters.MySQLDatabaseAdapter;
import ar.utn.frba.dds.logger.strategies.DatabaseErrorLoggingStrategy;
import ar.utn.frba.dds.logger.strategies.TerminalErrorLoggingStrategy;

import java.time.LocalDateTime;

public class Example {

    public static void main(String[] args) {
        Logger logger = new Logger(new TerminalErrorLoggingStrategy());

        logger.log(new Error(
                "An arithmetic error occurred when dividing by zero.",
                "Division.java line 815",
                LocalDateTime.now()
        ));

        logger.setErrorLoggingStrategy(new DatabaseErrorLoggingStrategy(new MySQLDatabaseAdapter()));

        logger.log(new Error(
                "An arithmetic error occurred when dividing by zero.",
                "Division.java line 815",
                LocalDateTime.now()
        ));
    }
}
