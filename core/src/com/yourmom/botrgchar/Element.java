package com.yourmom.botrgchar;

import static com.yourmom.botrgchar.EleType.*;

/** This class stores an element type (enum EleType) and a level.
 * Created by Ben on 11.07.2018.
 */

public class Element {
    public EleType type;
    public int lvl;

    public Element(EleType eleType, int eleTypeLvl){
        this.type = eleType;
        this.lvl = eleTypeLvl;
    }

    public void setType(EleType type) {this.type = type;}
    public void setLvl(int lvl) {this.lvl = lvl;}

    public EleType getType() {
        return type;
    }
    public int getLvl() {
        return lvl;
    }

    public static float getDmgMod(EleType attackType, int attackTypeLvl, EleType defendType){
        float mod = 0.0f;
        if(getElementRelation(attackType, defendType) == 's'){
            switch(attackTypeLvl){
                case 1: mod = 0.5f;
                    break;
                case 2: mod = 0.75f;
                    break;
                case 3: mod = 1.0f;
                    break;
            }
        }
        if(getElementRelation(attackType, defendType) == 'w'){
            switch(attackTypeLvl){
                case 1: mod = -0.5f;
                    break;
                case 2: mod = -0.75f;
                    break;
                case 3: mod = -0.9f;
                    break;
            }
        }

        return mod;
    }

    public static Element[] getEleArray(EleType element1, int element1Lvl){
        return new Element[] {
                new Element(element1, element1Lvl)
        };
    }

    public static Element[] getEleArray(EleType element1, int element1Lvl, EleType element2, int element2Lvl){
        return new Element[] {
                new Element(element1, element1Lvl),
                new Element(element2, element2Lvl)
        };
    }

    public static boolean checkForElement(EleType eleType, Element[] elements){
        for(Element element: elements)
            if(element.getType() == eleType) return true;
        return false;
    }

    public static EleType modToEle(BotrGModifier mod){
        switch(mod){
            default: return NORMAL;
            case SWOLE_UP:     return SWOLE;
            case KI_UP:        return KI;
            case GOD_UP:       return GOD;
            case EVIL_UP:      return EVIL;
            case SWAG_UP:      return SWAG;
            case THOT_UP:      return THOT;
            case NORMIE_UP:    return NORMIE;
            case DANK_UP:      return DANK;
            case HERO_UP:      return HERO;
            case SMART_UP:     return SMART;
            case STUPID_UP:    return STUPID;
            case SPEED_UP:     return SPEED;
            case MUSIC_UP:     return MUSIC;

            case SWOLE_DOWN:   return SWOLE;
            case KI_DOWN:      return KI;
            case GOD_DOWN:     return GOD;
            case EVIL_DOWN:    return EVIL;
            case SWAG_DOWN:    return SWAG;
            case THOT_DOWN:    return THOT;
            case NORMIE_DOWN:  return NORMIE;
            case DANK_DOWN:    return DANK;
            case HERO_DOWN:    return HERO;
            case SMART_DOWN:   return SMART;
            case STUPID_DOWN:  return STUPID;
            case SPEED_DOWN:   return SPEED;
            case MUSIC_DOWN:   return MUSIC;
        }
    }

    public static char getElementRelation(EleType attackType, EleType defendType){

        switch(attackType){
            default: return 'n';

            case NORMAL: switch(defendType){
                case SWOLE:     return 'w';
                case GOD:       return 'w';
                case EVIL:      return 'w';
                default: return 'n';
            }

            case SWOLE: switch(defendType){
                case SWOLE:     return 'w';
                case GOD:       return 's';
                case SWAG:      return 'w';
                case THOT:      return 'w';
                case NORMIE:    return 'w';
                case HERO:      return 'w';
                case SMART:     return 'w';
                case SPEED:     return 'w';
                case MUSIC:     return 's';
                default: return 'n';
            }

            case KI: switch(defendType){
                case SWOLE:     return 's';
                case KI:        return 's';
                case GOD:       return 'w';
                case EVIL:      return 's';
                case SWAG:      return 's';
                case DANK:      return 's';
                case SMART:     return 'w';
                case STUPID:    return 's';
                case MUSIC:     return 's';
                default: return 'n';
            }

            case GOD: switch(defendType){
                case KI:        return 's';
                case GOD:       return 'w';
                case SWAG:      return 'w';
                case NORMIE:    return 's';
                case DANK:      return 'w';
                case HERO:      return 's';
                case STUPID:    return 's';
                case MUSIC:     return 'w';
                default: return 'n';
            }

            case EVIL: switch(defendType){
                case SWOLE:     return 'w';
                case KI:        return 's';
                case THOT:      return 's';
                case NORMIE:    return 'w';
                case STUPID:    return 's';
                default: return 'n';
            }

            case SWAG: switch(defendType){
                case SWOLE:     return 'w';
                case GOD:       return 'w';
                case EVIL:      return 's';
                case NORMIE:    return 's';
                case HERO:      return 's';
                case SMART:     return 'w';
                case STUPID:    return 'w';
                case SPEED:     return 's';
                case MUSIC:     return 'w';
                default: return 'n';
            }

            case THOT: switch(defendType){
                case SWOLE:     return 'w';
                case KI:        return 's';
                case SWAG:      return 's';
                case THOT:      return 'w';
                case DANK:      return 'w';
                case HERO:      return 'w';
                case SMART:     return 's';
                case SPEED:     return 'w';
                default: return 'n';
            }

            case NORMIE: switch(defendType){
                case SWOLE:     return 's';
                case GOD:       return 'w';
                case SWAG:      return 'w';
                case DANK:      return 'w';
                case HERO:      return 'w';
                default: return 'n';
            }

            case DANK: switch(defendType){
                case SWOLE:     return 'w';
                case KI:        return 'w';
                case GOD:       return 's';
                case EVIL:      return 'w';
                case THOT:      return 's';
                case NORMIE:    return 's';
                case STUPID: return 'w';
                default: return 'n';
            }

            case HERO: switch(defendType){
                case SWOLE:     return 'w';
                case EVIL:      return 's';
                case THOT:      return 'w';
                case NORMIE:    return 's';
                case DANK:      return 'w';
                case SMART:     return 's';
                case MUSIC:     return 'w';
                default: return 'n';
            }

            case SMART: switch(defendType){
                case SWOLE:     return 'w';
                case KI:        return 's';
                case GOD:       return 'w';
                case SWAG:      return 's';
                case NORMIE:    return 'w';
                case DANK:      return 'w';
                case STUPID:    return 's';
                case SPEED:     return 's';
                case MUSIC:     return 'w';
                default: return 'n';
            }

            case STUPID: switch(defendType){
                case EVIL:      return 'w';
                case SWAG:      return 's';
                case THOT:      return 'w';
                case DANK:      return 's';
                case HERO:      return 'w';
                case SMART:     return 'w';
                case MUSIC:     return 's';
                default: return 'n';
            }

            case SPEED: switch(defendType){
                case SWOLE:     return 'w';
                case SWAG:      return 'w';
                case DANK:      return 's';
                case SMART:     return 's';
                case SPEED:     return 'w';
                default: return 'n';
            }

            case MUSIC: switch(defendType){
                case SWOLE:     return 'w';
                case KI:        return 's';
                case GOD:       return 'w';
                case NORMIE:    return 's';
                case DANK:      return 's';
                case STUPID:    return 'w';
                default: return 'n';
            }
        }
    }
}
