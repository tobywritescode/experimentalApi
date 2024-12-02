package com.example.demoapi.model.entity.people;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo implements Serializable {
    public String name;
    public String email;
    public Integer age;
}
