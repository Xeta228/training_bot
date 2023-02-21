package com.penisco.bot_prototype.service;


import com.penisco.bot_prototype.config.BotConfig;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

//@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private AdsRepository adsRepository;
    
    final BotConfig config;


    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            
            
        } 


    }

    

    

    
}