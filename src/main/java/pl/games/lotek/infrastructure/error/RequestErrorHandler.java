package pl.games.lotek.infrastructure.error;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.games.lotek.infrastructure.numbersreceiver.controller.NumbersReceiverController;

@ControllerAdvice(assignableTypes = NumbersReceiverController.class)
@Log4j2
public class RequestErrorHandler {

    @ExceptionHandler(UserGaveDifferentNumberCountThanSix.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RequestErrorResponseDto handleException(UserGaveDifferentNumberCountThanSix exception) {
        log.warn("User gave different number count than six");
        return new RequestErrorResponseDto(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserGaveNumberOutsideTheRange.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RequestErrorResponseDto handleException(UserGaveNumberOutsideTheRange exception) {
        log.warn("User gave number outside the range 1-99");
        return new RequestErrorResponseDto(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserGaveRepeatedNumbers.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RequestErrorResponseDto handleException(UserGaveRepeatedNumbers exception) {
        log.warn("User gave repeated numbers");
        return new RequestErrorResponseDto(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

