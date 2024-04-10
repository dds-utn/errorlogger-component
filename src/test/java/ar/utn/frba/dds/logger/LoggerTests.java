package ar.utn.frba.dds.logger;

import ar.utn.frba.dds.logger.adapters.DatabaseAdapter;
import ar.utn.frba.dds.logger.strategies.DatabaseErrorLoggingStrategy;
import ar.utn.frba.dds.logger.strategies.ErrorLoggingStrategy;
import ar.utn.frba.dds.logger.strategies.TerminalErrorLoggingStrategy;
import ar.utn.frba.dds.logger.utils.ObjectToMapConversor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class LoggerTests {
    private Logger logger;

    @BeforeEach
    public void init() {
        ErrorLoggingStrategy loggingStrategyDefault = new TerminalErrorLoggingStrategy();

        this.logger = new Logger(loggingStrategyDefault);
    }

    @Test
    public void logWithDBTest() {
        DatabaseAdapter mockedDBAdapter = mock(DatabaseAdapter.class);

        ErrorLoggingStrategy dbLoggingStrategy = new DatabaseErrorLoggingStrategy(mockedDBAdapter);

        this.logger.setErrorLoggingStrategy(dbLoggingStrategy);

        Error error = new Error(
                "An arithmetic error occurred when dividing by zero.",
                "Division.java line 815",
                LocalDateTime.now()
        );

        logger.log(error);

        verify(mockedDBAdapter).connect();
        verify(mockedDBAdapter).insert("error_log", ObjectToMapConversor.convertObjectToMap(error));
        verify(mockedDBAdapter).disconnect();
    }
}
