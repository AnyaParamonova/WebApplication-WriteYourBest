package com.dao;

import com.dao.connectors.Connector;
import com.dao.connectors.MySQLConnector;

/**
 * Created by Anastasia_Paramonova on 28.11.2016.
 */
public class CompositionDao extends Dao{

    public CompositionDao(Connector connector) {
        super(connector);
    }

    public CompositionDao(){
        super(new MySQLConnector());
    }
/*
    public Iterable<Composition> getCompositionsList(long userId, int offset, int count){

    }*/
/*
    public boolean addComposition(){

    }*/
}
