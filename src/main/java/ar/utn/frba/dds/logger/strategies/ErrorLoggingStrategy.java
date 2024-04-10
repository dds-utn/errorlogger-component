package ar.utn.frba.dds.logger.strategies;

import ar.utn.frba.dds.logger.Error;

public interface ErrorLoggingStrategy {
    void logError(Error error);
}
