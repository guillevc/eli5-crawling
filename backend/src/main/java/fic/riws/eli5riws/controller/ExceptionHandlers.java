package fic.riws.eli5riws.controller;


import fic.riws.eli5riws.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {

    private static final ExceptionMapping DEFAULT_ERROR = new ExceptionMapping(
            "SERVER_ERROR",
            "Internal server error",
            INTERNAL_SERVER_ERROR
    );

    // lo hace @Slf4j
    //private static final Logger log = LoggerFactory.getLogger(ExceptionHandlers.class);

    private final Map<Class, ExceptionMapping> exceptionMappings = new HashMap<>();

    public ExceptionHandlers() {
        // Default errors
        registerMapping(
                MissingServletRequestParameterException.class,
                "MISSING_PARAMETER",
                "Missing request parameter",
                BAD_REQUEST);
        registerMapping(
                MethodArgumentTypeMismatchException.class,
                "ARGUMENT_TYPE_MISMATCH",
                "Argument type mismatch",
                BAD_REQUEST);
        registerMapping(
                HttpRequestMethodNotSupportedException.class,
                "METHOD_NOT_SUPPORTED",
                "HTTP method not supported",
                METHOD_NOT_ALLOWED);
        registerMapping(
                ServletRequestBindingException.class,
                "MISSING_HEADER",
                "Missing header in request",
                BAD_REQUEST);
        registerMapping(
                MethodArgumentNotValidException.class,
                "ARGUMENT_NOT_VALID",
                "Argument validation failed",
                BAD_REQUEST);
        registerMapping(
                HttpMessageNotReadableException.class,
                "MALFORMED_BODY",
                "Message not readable",
                BAD_REQUEST);
    }

    private void registerMapping(Class<?> clazz, String code, String message, HttpStatus status) {
        exceptionMappings.put(clazz, new ExceptionMapping(code, message, status));
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    private ErrorResponse handleThrowable(Throwable ex, HttpServletResponse response) {
        ExceptionMapping mapping = exceptionMappings.getOrDefault(ex.getClass(), DEFAULT_ERROR);

        log.warn("{} ({}): {}", mapping.message, mapping.code, ex.getMessage(), ex);

        response.setStatus(mapping.status.value());

        return new ErrorResponse(mapping.code, mapping.message);
    }

    private static class ExceptionMapping {
        private final String message;
        private final String code;
        private final HttpStatus status;

        public ExceptionMapping(String code, String message, HttpStatus status) {
            this.code = code;
            this.message = message;
            this.status = status;
        }
    }

}
