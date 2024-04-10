package ar.utn.frba.dds.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class Error {
    private String message;
    private String stackTrace;
    private LocalDateTime timestamp;
}
