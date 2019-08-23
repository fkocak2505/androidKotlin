package tr.com.fkocak.bytranslator.common;

import java.util.UUID;

public class UUIDGenerator {

    public String generateUUID(){
        return UUID.randomUUID().toString();
    }

}
