package ar.utn.frba.dds.logger.strategies;

import ar.utn.frba.dds.logger.Error;

import java.time.format.DateTimeFormatter;

public class TerminalErrorLoggingStrategy implements ErrorLoggingStrategy {

    @Override
    public void logError(Error error) {
        System.out.printf("Error ocurred with message: %s, stacktrace: %s, timestamp: %s%n",
                error.getMessage(),
                error.getStackTrace(),
                error.getTimestamp().format(DateTimeFormatter.BASIC_ISO_DATE)
        );
    }
}
