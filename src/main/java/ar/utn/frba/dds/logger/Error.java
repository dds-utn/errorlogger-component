package ar.utn.frba.dds.logger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Error {
    private String message;
    private String stackTrace;
    private LocalDateTime timestamp;

    public static Error of(Object o) {
        return Error
                .builder()
                .message(o.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static Error of(String message) {
        return Error
                .builder()
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static Error of(String message, String stackTrace) {
        return Error
                .builder()
                .message(message)
                .stackTrace(stackTrace)
                .timestamp(LocalDateTime.now())
                .build();
    }


    public static Error of(String message, String stackTrace, LocalDateTime dateTime) {
        return Error
                .builder()
                .message(message)
                .stackTrace(stackTrace)
                .timestamp(dateTime)
                .build();
    }
}
