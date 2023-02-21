package com.penisco.bot_prototype.service;


import com.penisco.bot_prototype.config.BotConfig;
import com.penisco.bot_prototype.dao.ChatIdDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.penisco.bot_prototype.dao.UsersDao;
import com.penisco.bot_prototype.entity.ChatId;
import com.penisco.bot_prototype.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private AdsRepository adsRepository;
    
    final BotConfig config;
    @Autowired
    private UsersDao userdao;
    @Autowired
    private ChatIdDao chatIdDao;

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
            long chatId = update.getMessage().getChatId();
            
            switch (messageText){ 
                case "/start" -> {
                    User user = userdao.findByChatId(chatId);
                    if (user == null || !update.getMessage().getChat().getFirstName().equals(user.getFirstName()) || 
                            !update.getMessage().getChat().getLastName().equals(user.getLastName()) || 
                                !update.getMessage().getChat().getUserName().equals(user.getUserName()))
                    registerUser(update);
                }
               
                default -> {
                 
                    executeMessage(sendMessage(chatId, "Sorry, this is not supported yet"));
                
                }
            }
            
            
        } 


    }

    
    private SendMessage sendMessage(long chatId, String textMessage){
                    SendMessage message = new SendMessage();
                    message.setChatId(String.valueOf(chatId));
                    message.setText(textMessage);
                    return message;
    }
    
    
    private void registerUser(Update update){
                    
                   ChatId chatId = new ChatId(update.getMessage().getChatId());
                   chatIdDao.save(chatId); 
               
                   User user = new User(); 
                   user.setChatId(update.getMessage().getChatId());
                   user.setFirstName(update.getMessage().getChat().getFirstName());
                   user.setLastName(update.getMessage().getChat().getLastName());
                   user.setUserName(update.getMessage().getChat().getUserName());
                   userdao.save(user);
                   
                   
                
         executeMessage(sendMessage(update.getMessage().getChatId(), "Welcome, " + update.getMessage().getChat().getFirstName() + 
                 " You have successfully registered"));
    }
    
    private void executeMessage(SendMessage message){
                try {
                    execute(message);
                } catch (TelegramApiException ex) {
                   // Logger.getLogger(TelegramBot.class.getName()).log(Level.SEVERE, null, ex);
                }

    }

    private void validateUser(){
        
    }
}