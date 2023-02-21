/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.penisco.bot_prototype.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author baron
 */

@Entity
@Table(name="chat_id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatId {
    
    @Id
    private long id;
}
