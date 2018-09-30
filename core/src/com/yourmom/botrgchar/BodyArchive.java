package com.yourmom.botrgchar;

import static com.yourmom.botrgchar.EleType.*;

/**
 * Created by Ben on 10.07.2018.
 */

public final class BodyArchive {
    private BodyArchive(){}

    public static BodyType getBody(int bodyId){
        switch(bodyId){
            case 0: return new BodyType("Son Goku", "Goku", 0, 1000, 1000,
                    Element.getEleArray(KI, 3));

            case 4000: return new BodyType("Jesus Christ", "Jesus", 1, 1001, 1000,
                    Element.getEleArray(GOD, 3));
            case 4001: return new BodyType("Bear Grylls", "B. Grylls", 3, 1003, 1000,
                    Element.getEleArray(NORMIE, 2));
            case 4012: return new BodyType("Detlef D Soost", "Detlef",2, 1002, 1000,
                    Element.getEleArray(NORMIE, 2, STUPID, 1));

            default: return new BodyType("NOT_FOUND","NOT_FOUND", 999999, 999999,1000,
                    Element.getEleArray(NORMAL, 1));
        }
    }
}

