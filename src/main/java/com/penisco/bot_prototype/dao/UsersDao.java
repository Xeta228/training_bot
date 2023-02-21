/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.penisco.bot_prototype.dao;

import com.penisco.bot_prototype.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author baron
 */
public interface UsersDao extends JpaRepository<User, Long>{

    public User findByChatId(long chatId);
    
}
