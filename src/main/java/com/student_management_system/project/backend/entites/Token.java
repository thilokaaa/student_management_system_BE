package com.student_management_system.project.backend.entites;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "table_token")
public class Token {

   @Id
   @Column(name = "email")
   private String email;

   @Column(name = "token_value", length = 40000)
   private String tokenValue;

   @Column(name= "isValid")
   private Boolean isValid;

   @Column(name = "createTime")
   private Instant createTime;

   @Column(name = "expireTime")
   private Instant expireTime;
    
}
