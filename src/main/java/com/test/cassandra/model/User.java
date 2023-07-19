package com.test.cassandra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.nio.ByteBuffer;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @PrimaryKey
    private Integer idUser;
    private String userName;

    private String emailUser;

    private String password;

    private ByteBuffer photoUser;

}
