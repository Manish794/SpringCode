package com.manish.spring.jpa.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.List;

public class BookIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        return generateWithSomePattern(session);
    }

    private String generateWithSomePattern(SharedSessionContractImplementor session){
        String newId = "B-001";

        // Fetch Max id from table
        List list = session.createQuery("SELECT max(b.bookId) from BookEntity b").list();

        // Handle for first value
        if (null == list || list.size() == 0 || list.get(0) == null) {
            return newId;
        }

        String lastId = list.get(0).toString();
        int idIntPart = Integer.parseInt(lastId.substring(2));
        idIntPart++;

        if(idIntPart < 10)
            newId = "B-00"+idIntPart;
        else if (idIntPart < 100)
            newId = "B-0"+idIntPart;
        else
            newId = "B-"+idIntPart;
        return newId;
    }
}
