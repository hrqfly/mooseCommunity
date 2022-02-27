package com.moose.community.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AlphaldaoHibernatelmp implements Alphadao{


    @Override
    public String select() {
        return "alphadao";
    }
}
