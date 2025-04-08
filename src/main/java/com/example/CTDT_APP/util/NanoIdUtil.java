package com.example.CTDT_APP.util;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class NanoIdUtil extends SequenceStyleGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return NanoIdUtils.randomNanoId();
    }
}
