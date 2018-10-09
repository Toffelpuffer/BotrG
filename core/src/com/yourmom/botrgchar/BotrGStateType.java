package com.yourmom.botrgchar;

/**
 * Created by Ben on 8/21/2018.
 */
public enum BotrGStateType {
    NONE,
    DIZZY, //{<active turns>, <interrupt chance>}
    LEGO, //{<active turns>, <interrupt chance>, <dmg>, <interrupted once>}
    BLEED,
    SHOCK, //{<active turns>, <interrupt chance>}
    POISON, //{<active turns>, <dmg>}
    IMPRISONED,
    DMG_UP,
    DMG_DOWN,
    ACC_UP,
    ACC_DOWN,
    EVA_UP,
    EVA_DOWN;
}
