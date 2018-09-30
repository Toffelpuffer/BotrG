package com.yourmom.botrgchar;

/**
 * Created by Ben on 8/17/2018.
 */

public enum BotrGModifier {
    SWOLE_UP, KI_UP, GOD_UP, EVIL_UP, SWAG_UP, THOT_UP, NORMIE_UP, DANK_UP, HERO_UP, SMART_UP,
    STUPID_UP, SPEED_UP, MUSIC_UP,
    SWOLE_DOWN, KI_DOWN, GOD_DOWN, EVIL_DOWN, SWAG_DOWN, THOT_DOWN, NORMIE_DOWN, DANK_DOWN, HERO_DOWN, SMART_DOWN,
    STUPID_DOWN, SPEED_DOWN, MUSIC_DOWN,
    ADDNAMEPARTS,
    MAXHP_UP, MAXHP_DOWN, EVASIVENESS_UP, EVASIVENESS_DOWN, DMG_UP, DMG_DOWN, DEF_UP, DEF_DOWN,
    ACCURACY_UP, ACCURACY_DOWN;

    public static boolean isElementalModUp(BotrGModifier mod){
        switch(mod){
            case SWOLE_UP:     return true;
            case KI_UP:        return true;
            case GOD_UP:       return true;
            case EVIL_UP:      return true;
            case SWAG_UP:      return true;
            case THOT_UP:      return true;
            case NORMIE_UP:    return true;
            case DANK_UP:      return true;
            case HERO_UP:      return true;
            case SMART_UP:     return true;
            case STUPID_UP:    return true;
            case SPEED_UP:     return true;
            case MUSIC_UP:     return true;
            default:        return false;
        }
    }

    public static boolean isElementalModDown(BotrGModifier mod){
        switch(mod){
            default: return false;
            case SWOLE_DOWN: return true;
            case KI_DOWN: return true;
            case GOD_DOWN: return true;
            case EVIL_DOWN: return true;
            case SWAG_DOWN: return true;
            case THOT_DOWN: return true;
            case NORMIE_DOWN: return true;
            case DANK_DOWN: return true;
            case HERO_DOWN: return true;
            case SMART_DOWN: return true;
            case STUPID_DOWN: return true;
            case SPEED_DOWN: return true;
            case MUSIC_DOWN: return true;
        }
    }

    public static String getRandomPreName(BotrGModifier mod){
        String preName = "";
        switch(mod){
            case SWOLE_UP:
                switch((int)(Math.random() * 15)){
                    default: preName = "Swole"; break;
                    case 1: preName = "Wide"; break;
                    case 2: preName = "Swell"; break;
                    case 3: preName = "Bloat"; break;
                    case 4: preName = "Fat"; break;
                } break;
            case KI_UP:
                switch((int)(Math.random() * 5)){
                    default: preName = "Energy Master"; break;
                    case 1: preName = "Ki Apprentice"; break;
                    case 2: preName = "Ki Master"; break;
                    case 3: preName = "Chakra Master"; break;
                    case 4: preName = "Chakra Apprentice"; break;
                } break;
            case GOD_UP:
                switch((int)(Math.random() * 15)){
                    default: preName = "Holy"; break;
                    case 1: preName = "Sacred"; break;
                    case 2: preName = "Divine"; break;
                    case 3: preName = "Saint"; break;
                    case 4: preName = "Sacramental"; break;
                } break;
            case EVIL_UP:
                switch((int)(Math.random() * 10)){
                    default: preName = "Evil"; break;
                    case 1: preName = "Vicious"; break;
                    case 2: preName = "Wicked"; break;
                    case 3: preName = "Naughty"; break;
                    case 4: preName = "Mean"; break;
                    case 5: preName = "Villainous"; break;
                    case 6: preName = "Diabolic"; break;
                    case 7: preName = "Darkness Overlord"; break;
                    case 8: preName = "Shady"; break;
                    case 9: preName = "Shadow"; break;
                } break;
            case SWAG_UP:
                switch((int)(Math.random() * 15)){
                    default: preName = "Swagy"; break;
                    case 1: preName = "Swag-God"; break;
                    case 2: preName = "Style Master"; break;
                    case 3: preName = "Fashionista"; break;
                    case 4: preName = "Ballin'"; break;
                } break;
            case THOT_UP:
                switch((int)(Math.random() * 4)){
                    default: preName = "Thoty"; break;
                    case 1: preName = "THICC"; break;
                    case 2: preName = "Boujee"; break;
                    case 3: preName = "Bitch Queen"; break;
                } break;
            case NORMIE_UP:
                switch((int)(Math.random() * 1)){
                    default: preName = "Normie"; break;
                } break;
            case DANK_UP:
                switch((int)(Math.random() * 7)){
                    default: preName = "Dank"; break;
                    case 1: preName = "Living Meme"; break;
                    case 2: preName = "Memester"; break;
                    case 3: preName = "Blazzin"; break;
                    case 4: preName = "Quick-Scooper"; break;
                    case 5: preName = "Dankster"; break;
                    case 6: preName = "xXx"; break;
                } break;
            case HERO_UP:
                switch((int)(Math.random() * 10)){
                    default: preName = "Heroic"; break;
                    case 1: preName = "Helpful"; break;
                    case 2: preName = "Your Friendly"; break;
                    case 3: preName = "Good Guy"; break;
                    case 4: preName = "Champion"; break;
                    case 5: preName = "Super"; break;
                    case 6: preName = "The Fantastic"; break;
                    case 7: preName = "The Incredible"; break;
                    case 8: preName = "The Infamous"; break;
                    case 9: preName = "Captain"; break;
                } break;
            case SMART_UP:
                switch((int)(Math.random() * 8)){
                    default: preName = "Smart"; break;
                    case 1: preName = "Sharp"; break;
                    case 2: preName = "Clever"; break;
                    case 3: preName = "Intelligent"; break;
                    case 4: preName = "Highly Intelligent"; break;
                    case 5: preName = "Bright"; break;
                    case 6: preName = "Big Brain"; break;
                    case 7: preName = "Master Mind"; break;
                } break;
            case STUPID_UP:
                switch((int)(Math.random() * 12)){
                    default: preName = "Stupid"; break;
                    case 1: preName = "Retarded"; break;
                    case 2: preName = "Foolish"; break;
                    case 3: preName = "Dumb"; break;
                    case 5: preName = "Brain Dead"; break;
                    case 6: preName = "Imbecile"; break;
                    case 7: preName = "Dull"; break;
                    case 8: preName = "Idiotic"; break;
                    case 9: preName = "Brainless"; break;
                    case 10: preName = "FatHeaded"; break;
                    case 11: preName = "Underdeveloped"; break;
                } break;
            case SPEED_UP:
                switch((int)(Math.random() * 10)){
                    default: preName = "Fast"; break;
                    case 1: preName = "High Speed"; break;
                    case 2: preName = "Quick"; break;
                    case 3: preName = "Swift"; break;
                    case 4: preName = "Speedy"; break;
                    case 5: preName = "Tempo"; break;
                    case 6: preName = "The Fastest"; break;
                    case 7: preName = "Speedster"; break;
                    case 8: preName = "Rash"; break;
                    case 9: preName = "Unlimited Velocity"; break;
                } break;
            case MUSIC_UP:
                switch((int)(Math.random() * 7)){
                    default: preName = "Musical Genius"; break;
                    case 1: preName = "Pop-Star"; break;
                    case 2: preName = "One-Hit-Wonder"; break;
                    case 3: preName = "Sing-a-Song-Writer"; break;
                    case 4: preName = "lil"; break;
                    case 5: preName = "Mc"; break;
                    case 6: preName = "DJ"; break;
                } break;

            case MAXHP_UP:
                switch((int)(Math.random() * 4)){
                    default: preName = "Healthy"; break;
                    case 1: preName = "Vigorous"; break;
                    case 2: preName = "Robust"; break;
                    case 3: preName = "Resistant"; break;
                } break;
            case MAXHP_DOWN:
                break;
            case EVASIVENESS_UP:
                switch((int)(Math.random() * 2)){
                    default: preName = "Evasive"; break;
                    case 1: preName = "Hard to Hit"; break;
                } break;
            case EVASIVENESS_DOWN:
                break;
            case DMG_UP:
                switch((int)(Math.random() * 3)){
                    default: preName = "Strong"; break;
                    case 1: preName = "Powerful"; break;
                    case 2: preName = "Damaging"; break;
                } break;
            case DMG_DOWN:
                break;
            case DEF_UP:
                switch((int)(Math.random() * 4)){
                    default: preName = "Armored"; break;
                    case 1: preName = "Resistant"; break;
                    case 2: preName = "Robust"; break;
                    case 3: preName = "Enduring"; break;
                } break;
            case DEF_DOWN:
                break;
            case ACCURACY_UP:
                switch((int)(Math.random() * 2)){
                    default: preName = "Precise"; break;
                    case 1: preName = "Unerring"; break;
                } break;
            case ACCURACY_DOWN:
                break;
            case ADDNAMEPARTS:
                switch((int)(Math.random() * 5)) {
                    case 0: preName = "Alpha"; break;
                    case 1: preName = "Beta"; break;
                    case 2: preName = "Gamma"; break;
                    case 3: preName = "Delta"; break;
                    case 4: preName = "Omega"; break;
                }break;
        }
        System.out.println("STATUS: preName: " + preName);
        return preName;
    }

    public static String getRandomPostName(BotrGModifier mod){
        String postName = "";
        switch(mod){
            case SWOLE_UP:
                switch((int)(Math.random() * 7)){
                    default: postName = "the Swole"; break;
                    case 1: postName = "the Round"; break;
                    case 2: postName = "the Curvy"; break;
                    case 3: postName = "the Fatty"; break;
                    case 4: postName = "the Porky"; break;
                    case 5: postName = "the Plump"; break;
                    case 6: postName = "of Grease"; break;
                } break;
            case KI_UP:
                switch((int)(Math.random() * 2)){
                    default: postName = ""; break;
                    case 1: postName = "the Chakra Master"; break;
                } break;
            case GOD_UP:
                switch((int)(Math.random() * 9)){
                    default: postName = "the Divine"; break;
                    case 1: postName = "the Holy"; break;
                    case 2: postName = "the God"; break;
                    case 3: postName = "from Heaven"; break;
                    case 4: postName = "Herald of the Gods"; break;
                    case 5: postName = "Herald of God"; break;
                    case 6: postName = "the Templar"; break;
                    case 7: postName = "Guardian of Divinity"; break;
                    case 8: postName = "the Higher Being"; break;
                } break;
            case EVIL_UP:
                switch((int)(Math.random() * 18)){
                    default: postName = "the Evil"; break;
                    case 1: postName = "of Doom"; break;
                    case 2: postName = "of Darkness"; break;
                    case 3: postName = "Master of Destruction"; break;
                    case 4: postName = "the Destructor"; break;
                    case 5: postName = "the Painbringer"; break;
                    case 6: postName = "Herald of Darkness"; break;
                    case 7: postName = "Herald of Pain"; break;
                    case 8: postName = "Herald of Evisceration"; break;
                    case 9: postName = "Herald of Destruction"; break;
                    case 10: postName = "the Devourer"; break;
                    case 11: postName = "Soul Devourer"; break;
                    case 12: postName = "bane of Existence"; break;
                    case 13: postName = "the Demolisher"; break;
                    case 14: postName = "of the Dark"; break;
                    case 15: postName = "from the Shadows"; break;
                    case 16: postName = "from Hell"; break;
                    case 17: postName = "the Desecrator"; break;
                } break;
            case SWAG_UP:
                switch((int)(Math.random() * 1)){
                    default: postName = ""; break;

                } break;
            case THOT_UP:
                switch((int)(Math.random() * 2)){
                    default: postName = "the Thot"; break;
                    case 1: postName = "the Bitch"; break;

                } break;
            case NORMIE_UP:
                switch((int)(Math.random() * 1)){
                    default: postName = ""; break;
                } break;
            case DANK_UP:
                switch((int)(Math.random() * 7)){
                    default: postName = "the Dank"; break;
                    case 1: postName = "the living Meme"; break;
                    case 2: postName = "on Weed"; break;
                    case 3: postName = "on Crack"; break;
                    case 4: postName = "420"; break;
                    case 5: postName = "xXx"; break;
                    case 6: postName = "69"; break;
                } break;
            case HERO_UP:
                switch((int)(Math.random() * 7)){
                    default: postName = "the Hero"; break;
                    case 1: postName = "the Protector"; break;
                    case 2: postName = "Hero of the Peasants"; break;
                    case 3: postName = "the Fantastic"; break;
                    case 4: postName = "the Incredible"; break;
                    case 5: postName = "the Infamous"; break;
                    case 6: postName = "the Defender"; break;
                } break;
            case SMART_UP:
                switch((int)(Math.random() * 4)){
                    default: postName = "the Smartest"; break;
                    case 1: postName = "the Mathematician"; break;
                    case 2: postName = "the Engineer"; break;
                    case 3: postName = "the Brain"; break;
                } break;
            case STUPID_UP:
                switch((int)(Math.random() * 3)){
                    default: postName = "the Retard"; break;
                    case 1: postName = "the Idiot"; break;
                    case 2: postName = "Herald of Idiocy"; break;
                } break;
            case SPEED_UP:
                switch((int)(Math.random() * 6)){
                    default: postName = "the Fastest"; break;
                    case 1: postName = "the Speeddemon"; break;
                    case 2: postName = "faster then Sound"; break;
                    case 3: postName = "faster then Light"; break;
                    case 4: postName = "the Speedster"; break;
                    case 5: postName = "with insane Velocity"; break;
                } break;
            case MUSIC_UP:
                switch((int)(Math.random() * 14)){
                    default: postName = "the Musician"; break;
                    case 1: postName = "the Streetartist"; break;
                    case 2: postName = "the Gangster Rapper"; break;
                    case 3: postName = "the Opera Singer"; break;
                    case 4: postName = "the Baritone"; break;
                    case 5: postName = "the Soprano"; break;
                    case 6: postName = "the Guitarist"; break;
                    case 7: postName = "the Bassist"; break;
                    case 8: postName = "the Drummer"; break;
                    case 9: postName = "the Violinist"; break;
                    case 10: postName = "the Clout Rapper"; break;
                    case 11: postName = "the Rapper"; break;
                    case 12: postName = "the Pianist"; break;
                    case 13: postName = "with Hot Bars"; break;
                } break;

            case MAXHP_UP:
                switch((int)(Math.random() * 1)){
                    default: postName = ""; break;

                } break;
            case MAXHP_DOWN:
                switch((int)(Math.random() * 1)){
                    default: postName = ""; break;

                } break;
            case EVASIVENESS_UP:
                switch((int)(Math.random() * 10)){
                    default: postName = "the Evasive"; break;
                    case 1: postName = "the Dodge Roll Spammer"; break;
                    case 2: postName = "the Master of Evasiveness"; break;
                } break;
            case EVASIVENESS_DOWN:
                switch((int)(Math.random() * 2)){
                    default: postName = "the Sluggish"; break;
                    case 1: postName = "with Slow Movement"; break;
                } break;
            case DMG_UP:
                switch((int)(Math.random() * 10)){
                    default: postName = "the Powerful"; break;
                    case 1: postName = "with Strong Muscles"; break;
                    case 2: postName = "with Muscle Power"; break;
                    case 3: postName = "with Gains"; break;
                    case 4: postName = "with hard Gains"; break;
                } break;
            case DMG_DOWN:
                switch((int)(Math.random() * 6)){
                    default: postName = ""; break;
                    case 1: postName = "the Weak"; break;
                } break;
            case DEF_UP:
                switch((int)(Math.random() * 6)){
                    default: postName = ""; break;
                    case 1: postName = "the Robust"; break;

                } break;
            case DEF_DOWN:
                switch((int)(Math.random() * 1)){
                    default: postName = ""; break;

                } break;
            case ACCURACY_UP:
                switch((int)(Math.random() * 3)){
                    default: postName = "the Unerring"; break;
                    case 1: postName = "the Precise"; break;
                    case 2: postName = "with Deadly Precision"; break;
                } break;
            case ACCURACY_DOWN:
                switch((int)(Math.random() * 1)){
                    default: postName = ""; break;

                } break;
            case ADDNAMEPARTS:
                switch((int)(Math.random() * 30)){
                    default: postName = ""; break;
                    case 0: postName = "from da Hood"; break;
                    case 1: postName = "the friendly Neighbor"; break;
                    case 2: postName = "the Coach"; break;
                    case 3: postName = "the Great"; break;
                    case 4: postName = "the Builder"; break;
                    case 5: postName = "the Archer"; break;
                    case 6: postName = "the Swordsman"; break;
                    case 7: postName = "the Warrior"; break;
                    case 8: postName = "the Assassin"; break;
                    case 9: postName = "the Mighty"; break;
                    case 10: postName = "the Capitalist"; break;
                    case 11: postName = "the Communist"; break;
                    case 12: postName = "Master of the Martial Arts"; break;
                    case 13: postName = "Master of Chopsticks"; break;
                    case 14: postName = "Master of Masters"; break;
                    case 15: postName = "Master of Board Games"; break;
                    case 16: postName = "Master of Languages"; break;
                    case 17: postName = "from the Holy Land"; break;
                    case 18: postName = "from the Forbidden Zone"; break;
                    case 19: postName = "of the Jungle"; break;
                    case 20: postName = "the Peasant"; break;
                    case 21: postName = "the Boss"; break;
                    case 22: postName = "the Coolest Kid in Town"; break;
                    case 23: postName = "from the Underground"; break;
                    case 24: postName = "the Pillar Man"; break;
                } break;
        }
        System.out.println("STATUS: postName: " + postName);
        return postName;
    }
}
