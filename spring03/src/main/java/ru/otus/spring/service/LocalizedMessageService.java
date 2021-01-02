package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProps;

import java.util.Locale;

@Service
public class LocalizedMessageService implements LocalizedMessage{
    private final MessageSource messageSource;
    private final AppProps appProps;

    @Override
    public String getMessage(String key, Object... args) {

        Locale locale = new Locale(appProps.getLocale());

        return messageSource.getMessage(key,
                args,
                locale
        );
    }

    public LocalizedMessageService(MessageSource messageSource,
                                   AppProps appProps) {
        this.messageSource = messageSource;
        this.appProps = appProps;
    }
}
