package br.com.api.device.application.common;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    private final MessageSource messageSource;

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ApiResponseMessage getMessage(MessageType messageType, Object... params) {
        String description = messageSource.getMessage(messageType.getCode(), params, Locale.getDefault());
        return new ApiResponseMessage(messageType.getCode(), description);
    }
}
