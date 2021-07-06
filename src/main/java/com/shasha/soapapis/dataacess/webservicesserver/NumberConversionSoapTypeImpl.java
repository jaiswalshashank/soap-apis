package com.shasha.soapapis.dataacess.webservicesserver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class NumberConversionSoapTypeImpl implements NumberConversionSoapType{

    @Override
    public String numberToWords(BigInteger ubiNum) {
        return "hello";
    }

    @Override
    public String numberToDollars(BigDecimal dNum) {
        return "Hello";
    }
}
