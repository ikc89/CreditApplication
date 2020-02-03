package com.kocfinans.creditapplication.client.creditscore;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CreditScoreServiceClient {

    public static final Map<String, BigDecimal> creditCoreMap = new HashMap<>();


    static {
        creditCoreMap.put("19282190784", BigDecimal.valueOf(300));
        creditCoreMap.put("12314132133", BigDecimal.valueOf(600));
        creditCoreMap.put("12314131311", BigDecimal.valueOf(400));
        creditCoreMap.put("12341414141", BigDecimal.valueOf(900));
    }

    public static BigDecimal getCreditScore(final String identityNo) {
        if (!creditCoreMap.containsKey(identityNo))
            throw IdentityNoNotFoundException.create(identityNo);

        return creditCoreMap.get(identityNo);
    }
}
