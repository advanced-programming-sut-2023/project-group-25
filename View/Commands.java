package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    CREATE_USER_WITH_SLOGAN("^user create -(?<option1>\\S+) (?<input1>.+) -(?<option2>\\S+) (?<input2>.+) -(?<option3>\\S+) (?<input3>.+)" +
            " -(?<option4>\\S+) (?<input4>.+) -(?<option5>\\S+) (?<input5>.+)$"),
    CREATE_USER_WITHOUT_SLOGAN("^user create -(?<option1>\\S+) (?<input1>.+) -(?<option2>\\S+) (?<input2>.+) -(?<option3>\\S+) (?<input3>.+)" +
            " -(?<option4>\\S+) (?<input4>.+)$"),
    ENTERPROFILEMENU("^enter profile menu"),
    PICK_QUESTION("^question pick -(?<option1>.+) (?<input1>.+) -(?<option2>.+) (?<input2>.+) -(?<option3>.+) (?<input3>.+)$"),
    LOGIN("^user login -(?<option1>\\S+) (?<input1>.+) -(?<option2>\\S+) (?<input2>.+)( --stay-logged-in)?$"),
    FORGOTPASSWORD("^forgot my password -u (?<username>\\S+)$"),
    LOGOUT("^user logout$"),
    CHANGEINFO("^profile change -(?<option>[a-z]+) (?<input>.+)$"),
    CHANGEPASSWORD("^profile change password -(?<option1>[a-z]+) (?<input1>\\S+) -(?<option2>[a-z]) (?<input2>\\S+)$"),
    CHANGESLOGAN("^profile change slogan -s (?<slogan>.+)$"),
    DISPLAYINFO("^profile display( (?<field>.+))?$"),
    SHOWMAP("^show map -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<option2>\\d+)$"),
    MOVEONMAP("^map (?<verticalNumber>\\d+) (?<verticalDirection>.+) (?<horizontalNumber>\\d+) (?<horizontalDirection>.+)$"),
    SHOWDETAILS("^show details -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    SHOWPOPULARITYFACTORS("^show popularity factors$"),
    SHOWPOPULARITY("^show popularity$"),
    SHOWFOODLIST("^show food list$"),
    //the next two can be merged.
    RATEPOPULARITYFACTOR("^(?<popularityFactor>.+) rate -r (?<rateNumber>\\d+)$"),
    SHOWPOPULARITYFACTORRATE("^food rate show$"),
    DROPBUILDING("^dropbuilding -(?<option1>[a-z]+) (?<input1>.+) -(?<option2>[a-z]+) (?<input2>.+) -(?<option3>[a-z]+) (?<input3>.+)$"),
    SELECTBUILDING("^select building -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    CREATEUNIT("^createunit -(?<option1>[a-z]) (?<input1>.+) -(?<option2>[a-z]) (?<input2>.+)$"),
    REPAIR("^repair$"),
    SELECTUNIT("^select unit -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    MOVEUNIT("^move unit to -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    PATROL("^patrol unit -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+) -(?<option3>[a-z]) (?<input3>\\d+) -(?<option3>[a-z]) (?<input3>\\d+)$"),
    SETMODE("^set -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+) -(?<option3>[a-z]+) (?<input3>.+)$"),
    //the next two can be merged
    ATTACKENEMY("^attack -e (?<enemy>.+)$"),
    AERIALATTACK("^attack -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    POUROIL("^pour oil -d (?<direction>.+)$"),
    DIGTUNNEL("^dig tunnel -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    BUILDEQUIPMENT("^build -q (?<equipmentName>.+)$"),
    MOVEEQUIPMENT("^move equipment -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+) -(?<option3>[a-z]) (?<input3>\\d+) -(?<option4>[a-z]) (?<input4>\\d+)$"),
    DISBAND("^disband unit$"),
    DIGMOAT("^dig moat -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),//the unit has already been selected
    CANCELDIGGINGMOAT("^cancel digging moat$"),
    DISABLEMOAT("^disable moat -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    FETCHOIL("^fetch oil"),//the engineer has already been selected
    BURNOIL("^burn oil"),//might need to take in some other information
    SUFFUSEMOAT("^suffuse moat -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    //problem: ready templates should be able to change according to the size of the map
    CHOOSEMAP("^choose map -(?<option1>[a-z]) (?<input1>.+) -(?<option2>[a-z]) (?<input2>.+) -(?<option3>[a-z]) (?<input3>.+)$"),
    BLOCKSETTEXTURE("^settexture -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+) -(?<option3>[a-z]) (?<input3>.+)$"),
    RECTANGLESETTEXTURE("^settexture -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+) -(?<option3>[a-z]) (?<input3>\\d+) -(?<option4>[a-z]) (?<input4>\\d+)$"),
    CLEARBLOCK("^clear -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    //next ones can be merged or separated
    DROPROCK("^droprock -(?<option1>[a-z]) (?<input1>.+) -(?<option2>[a-z]) (?<input2>.+) -(?<option3>[a-z]) (?<input3>.+)$"),
    DROPOBJECT("^drop(?<object>.+) -(?<option1>[a-z]) (?<input1>.+) -(?<option2>[a-z]) (?<input2>.+) -(?<option3>[a-z]) (?<input3>.+)$"),
    DROPUNIT("^dropunit -(?<option1>[a-z]) (?<input1>.+) -(?<option2>[a-z]) (?<input2>.+) -(?<option3>[a-z]) (?<input3>.+) -(?<option4>.+) (?<input4>.+)$"),
    CHOOSCOLOR("^choose color -c (?<color>.+)$"),
    TRADEREQUEST("^trade -(?<option1[a-z]+) (?<input1>.+) -(?<option2[a-z]+) (?<input2>.+) -(?<option3[a-z]+) (?<input3>.+) -(?<option4[a-z]+) (?<input4>.+)$"),
    TRADELIST("^trade list$"),
    TRADEACCEPT("^trade accept -(?<option1[a-z]+) (?<input1>.+) -(?<option2[a-z]+) (?<input2>.+)$"),
    TRADEHISTORY("^trade history$"),
    SHOWPRICELIST("^show price list$"),
    BUYORSELL("^(?<activity>[(buy)|(sell)] -(?<option1[a-z]+) (?<input1>.+) -(?<option2[a-z]+) (?<input2>.+)$"),
    NEXTTURN("^next turn$"),
    ENTERTRADEMENU("^enter trade menu$"),
    EXIT("^exit$"),
    BACK("^back$");
    
    
    private final String regex;
    
    private Commands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Commands commands) {
        Matcher matcher = Pattern.compile(commands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
