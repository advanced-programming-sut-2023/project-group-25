package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    
    CREATE_USER_WITH_SLOGAN("^user create -(?<option1>\\S+) (?<input1>.+) -(?<option2>\\S+) (?<input2>.+) -(?<option3>\\S+) (?<input3>.+)" +
            " -(?<option4>\\S+) (?<input4>.+) -(?<option5>\\S+) (?<input5>.+)$"),
    CREATE_USER_WITHOUT_SLOGAN("^user create -(?<option1>\\S+) (?<input1>.+) -(?<option2>\\S+) (?<input2>.+) -(?<option3>\\S+) (?<input3>.+)" +
            " -(?<option4>\\S+) (?<input4>.+)$"),
    PICK_QUESTION("^question pick -(?<option1>.+) (?<input1>.+) -(?<option2>.+) (?<input2>.+) -(?<option3>.+) (?<input3>.+)$"),

    LOGIN("^user login -(?<option1>\\S+) (?<input1>.+) -(?<option2>\\S+) (?<input2>\\S+)$"),

    LOGIN_WITH_LOGGED_IN("^user login -(?<option1>\\S+) (?<input1>.+) -(?<option2>\\S+) (?<input2>\\S+) (--stay-logged-in)$"),

    ENTERPROFILEMENU("^enter profile menu"),
    FORGOT_PASSWORD("^forgot my password -u (?<username>\\S+)$"),
    LOGOUT("^user logout$"),
    CHANGE_INFO("^profile change -(?<option>[a-z]+) (?<input>.+)$"),
    CHANGE_PASSWORD("^profile change password (?<input1>\\S+) (?<input2>\\S+)$"),
    REMOVE_SLOGAN("^profile remove slogan$"),
    DISPLAY_INFO("^profile display( (?<field>.+))?$"),
    SHOW_MAP_BEFORE_STARTING_THE_GAME("^show map options$"),
    SHOWMAP("^show map -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    CHOOSE_MAP("choose map -n (?<number>\\d+)"),
    ENTER_LENGTH_AND_WIDTH("initialize map size -l (?<length>\\d+) -w (?<width>\\d+)"),
    MOVEONMAP("^map (?<verticalNumber>\\d+) (?<verticalDirection>.+) (?<horizontalNumber>\\d+) (?<horizontalDirection>.+)$"),
    SHOW_DETAILS("^show details -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    SHOW_POPULARITY_FACTORS("^show popularity factors$"),
    SHOW_POPULARITY("^show popularity$"),
    SHOW_FOOD_LIST("^show food list$"),
    //the next two can be merged.
    RATE_POPULARITY_FACTOR("(?<popularityFactor>.+) rate -r (?<rateNumber>\\d+)"),
    SHOW_POPULARITY_FACTOR_RATE("(?<popularityFactor>.+) rate show"),
    DROPBUILDING("dropbuilding -x (?<x>\\d+) -y (?<y>\\d+) -type (?<type>.+)"),
    SELECTBUILDING("select building -x (?<x>\\d+) -y (?<y>\\d+)"),
    CREATEUNIT("createunit -t (?<type>.+) -c (?<count>.+)"),
    REPAIR("repair"),
    SELECTUNIT("select unit -x (?<x>\\d+) -y (?<y>\\d+)"),
    MOVEUNIT("move unit to -x (?<x>\\d+) -y (?<y>\\d+)"),
    PATROL("patrol unit -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+)"),
    SETMODE("set -x (?<x>\\d+) -y (?<y>\\d+) -s (?<mode>.+)"),
    //the next two can be merged
    ATTACKENEMY("attack -e (?<enemy>.+)"),
    AERIALATTACK("ATTACK -x (?<x>\\d+) -y (?<y>\\d+)"),
    POUROIL("pour oil -d (?<direction>.+)"),
    DIGTUNNEL("dig tunnel -x (?<x>\\d+) -y (?<y>\\d+)"),
    BUILDEQUIPMENT("build -q (?<equipmentName>.+)"),
    MOVEEQUIPMENT("move equipment -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+)"),
    DISBAND("disband unit"),
    DIGMOAT("dig moat -x (?<x>\\d+) -y (?<y>\\d+)"),//the unit has already been selected
    CANCELDIGGINGMOAT("cancel digging moat"),
    DISABLEMOAT("disable moat -x (?<x>\\d+) -y (?<y>\\d+)"),
    FETCHOIL("fetch oil"),//the engineer has already been selected
    BURNOIL("burn oil"),//might need to take in some other information
    SUFFUSEMOAT("suffuse moat -x (?<x>\\d+) -y (?<y>\\d+)"),
    CHOOSEMAP("choose map -t (?<templateNumber>\\d+) -w (?<width>.+) -l (?<length>.+)"),
    BLOCK_SET_TEXTURE("settexture -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>.+)"),
    RECTANGLE_SET_TEXTURE("settexture -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+) -t (?<type>.+)"),
    CLEAR_BLOCK("clear -x (?<x>\\d+) -y (?<y>\\d+)"),
    //next ones can be merged or separated
    DROP_ROCK("droprock -x (?<x>\\d+) -y (?<y>\\d+) -d (?<direction>[a-z]"),
    DROP_Object("drop(?<object>.+) -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>.+)( -c (?<count>\\d+))?"),
    CHOOSCOLOR("choose color -c (?<color>.+)"),
    TRADEREQUEST("trade -(?<option1[a-z]+) (?<input1>.+) -(?<option2[a-z]+) (?<input2>.+) -(?<option3[a-z]+) (?<input3>.+) -(?<option4[a-z]+) (?<input4>.+)"),
    TRADELIST("trade list"),
    TRADEACCEPT("trade accept -(?<option1[a-z]+) (?<input1>.+) -(?<option2[a-z]+) (?<input2>.+)"),
    TRADEHISTORY("trade history"),
    SHOWPRICELIST("show price list"),
    BUYORSELL("(?<activity>[(buy)|(sell)] -(?<option1[a-z]+) (?<input1>.+) -(?<option2[a-z]+) (?<input2>.+)"),
    NEXTTURN("next turn"),
    ENTERTRADEMENU("enter trade menu"),
    EXIT("exit"),
    BACK("back"),
    NEW_GAME("^start new game$");
    
    
    private final String regex;
    
    private Commands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Commands commands) {
        Matcher matcher = Pattern.compile(commands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
