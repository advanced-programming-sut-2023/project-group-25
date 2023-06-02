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
    SHOW_MAP("^show map -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    CHOOSE_MAP("choose map -n (?<number>\\d+)"),
    ENTER_LENGTH_AND_WIDTH("initialize map size -l (?<length>\\d+) -w (?<width>\\d+)"),
    MOVE_ON_MAP("^map (?<verticalNumber>\\d+) (?<verticalDirection>(up)|(down)) (?<horizontalNumber>\\d+) (?<horizontalDirection>(right)|(left))$"),
    SHOW_DETAILS("^show details -(?<option1>[a-z]) (?<input1>\\d+) -(?<option2>[a-z]) (?<input2>\\d+)$"),
    SHOW_POPULARITY_FACTORS("^show popularity factors$"),
    SHOW_POPULARITY("^show popularity$"),
    SHOW_FOOD_LIST("^show food list$"),
    RATE_POPULARITY_FACTOR("(?<popularityFactor>.+) rate -r (?<rateNumber>-?\\d+)"),
    SHOW_POPULARITY_FACTOR_RATE("(?<popularityFactor>.+) rate show"),
    SELECT_BUILDING("select building -x (?<x>\\d+) -y (?<y>\\d+)"),
    CREATE_UNIT("createunit -(?<option1>c|t) (?<input1>.+) -(?<option2>c|t) (?<input2>.+)"),
    REPAIR("repair"),
    SELECT_UNIT("select unit -(?<option1>x|y) (?<input1>\\d+) -(?<option2>x|y) (?<input2>\\d+)"),
    MOVE_UNIT("move unit to -(?<option1>x|y) (?<input1>\\d+) -(?<option2>x|y) (?<input2>\\d+)"),
    PATROL("patrol unit -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+)"),
    SET_MODE("set -x (?<x>\\d+) -y (?<y>\\d+) -s (?<mode>.+)"),
    ATTACK_ENEMY("attack -e (?<x>\\d+) (?<y>\\d+)"),
    AERIAL_ATTACK("attack -x (?<x>\\d+) -y (?<y>\\d+)"),
    POUR_OIL("pour oil -d (?<direction>.+)"),
    DIG_TUNNEL("dig tunnel -(?<option1>x|y) (?<input1>\\d+) -(?<option2>x|y) (?<input2>\\d+)"),
    BUILD_EQUIPMENT("build -q (?<equipmentName>.+)"),
    MOVE_EQUIPMENT("move equipment -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+)"),
    DISBAND("disband unit"),
    FETCH_OIL("fetch oil"),//the engineer has already been selected
    BURN_OIL("burn oil"), //the selected slave burns all the surrounding oil.
    BLOCK_SET_TEXTURE("settexture -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>.+)"),
    RECTANGLE_SET_TEXTURE("settexture -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+) -t (?<type>.+)"),
    CLEAR_BLOCK("clear -x (?<x>\\d+) -y (?<y>\\d+)"),
    DROP_ROCK("droprock -x (?<x>\\d+) -y (?<y>\\d+) -d (?<direction>[a-z])"),
    DROP_UNIT("drop(?<object>.+) -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>\\S+) -c (?<count>\\d+)"),
    DROP_OBJECT("drop(?<object>.+) -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>[^-]+)"),
    TRADE_REQUEST("trade -(?<option1>\\S+) (?<input1>.+) -(?<option2>\\S+) (?<input2>.+) -(?<option3>\\S+) (?<input3>.+) -(?<option4>\\S+) (?<input4>.+)"),
    TRADE_LIST("trade list"),
    TRADE_ACCEPT("trade accept -(?<option1>\\S+) (?<input1>.+) -(?<option2>\\S+) (?<input2>.+)"),
    TRADE_HISTORY("trade history"),
    SHOW_PRICE_LIST("show price list"),
    BUY_PRODUCT("buy -(?<option1>\\S+) (?<input1>.+) -(?<option2>\\S+) (?<input2>.+)"),
    SELL_PRODUCT("sell -(?<option1>\\S+) (?<input1>.+) -(?<option2>\\S+) (?<input2>.+)"),

    PRODUCE_SOURCE("produce source -t (?<t>.+) -c (?<count>\\d+)"),
    PRODUCE_FOOD("produce food -t (?<t>.+) -c (?<count>\\d+)"),
    PRODUCE_EQUIPMENT("produce equipment -t (?<t>.+) -c (?<count>\\d+)"),
    
    NEXT_TURN("next turn"),
    ENTER_TRADE_MENU("enter trade menu"),
    ENTER_SHOP_MENU("enter shop menu"),
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
