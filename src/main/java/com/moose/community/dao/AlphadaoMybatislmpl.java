package com.moose.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class AlphadaoMybatislmpl implements Alphadao{

    @Override
    public String select() {
        return "alphadaomybatis";
    }
}
