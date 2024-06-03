package ar.utn.frba.dds.logger;

import ar.utn.frba.dds.logger.adapters.MySQLDatabaseAdapter;
import ar.utn.frba.dds.logger.strategies.DatabaseErrorLoggingStrategy;
import ar.utn.frba.dds.logger.strategies.TerminalErrorLoggingStrategy;

import java.time.LocalDateTime;

public class Example {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.create("terminal");

        logger.log(Error.of(
                "An arithmetic error occurred when dividing by zero.",
                "Division.java line 815"
        ));

        logger.setErrorLoggingStrategy(LoggerFactory.createDBStrategy("mysql"));

        logger.log(Error.of(
                "An arithmetic error occurred when dividing by zero.",
                "Division.java line 815",
                LocalDateTime.now()
        ));
    }
}
