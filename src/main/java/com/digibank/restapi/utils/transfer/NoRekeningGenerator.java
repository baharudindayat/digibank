package com.digibank.restapi.utils.transfer;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;

public class NoRekeningGenerator implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return generateRandomNoRekening();
    }

    private String generateRandomNoRekening() {
        String characterSet = "01234567890";
        StringBuilder noRekening = new StringBuilder(16);
        Random random = new Random();

        for (int i = 0; i < 16; i++) {
            int index = random.nextInt(characterSet.length());
            noRekening.append(characterSet.charAt(index));
        }

        return noRekening.toString();
    }
}
