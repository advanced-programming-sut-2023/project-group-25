package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    //testing
    /*not sure*/CREATEUSER("user create -(?<firstInputIdentifier[a-z]+) (?<firstInput>.+) -(?<secondInputIdentifier[a-z]+) (?<secondInput>.+) -(?<thirdInputIdentifier[a-z]+) (?<thirdInput>.+) -(?<fourthInputIdentifier[a-z]+) (?<fourthInput>.+) -(?<fifthInputIdentifier[a-z]+) (?<fifthInput>.+)"),
    PICKQUESTION("question pick -q (?<questionNumber>\\d+) -a (?<answer>.+) -c (?<answerConfirm>.+)"),
    NEWCAPTCHA("show me a new captcha"),
    /*not sure*/LOGIN("user login -(?<firstInputIdentifier[a-z]+) (?<firstInput>.+) -(?<secondInputIdentifier[a-z]+) (?<secondInput>.+)( --stay-logged-in)?"),
    FORGOTPASSWORD("forgot my password -u (?<username>\\S+)"),
    LOGOUT("user logout"),
    CHANGEINFO("profile change -(?<input[a-z]+) (?<input>.+)"),
    CHANGEPASSWORD("profile change password -o (?<oldPassword>\\S+) -n (?<newPassword>\\S+)"),
    CHANGESLOGAN("profile change slogan -s (?<slogan>.+)"),
    DISPLAYINFO("profile display( (?<field>.+))?"),
    SHOWMAP("show map -x (?<x>\\d+) -y (?<y>\\d+)"),
    MOVEONMAP("map (?<verticalNumber>\\d+) (?<verticalDirection>.+) (?<horizontalNumber>\\d+) (?<horizontalDirection>.+)"),
    SHOWDETAILS("show details -x (?<x>\\d+) -y (?<y>\\d+)"),
    SHOWPOPULARITYFACTORS("show popularity factors"),
    SHOWPOPULARITY("show popularity"),
    SHOWFOODLIST("show food list"),
    //the next two can be merged.
    RATEPOPULARITYFACTOR("(?<popularityFactor>.+) rate -r (?<rateNumber>\\d+)"),
    SHOWPOPULARITYFACTORRATE("food rate show"),
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
    //problem: ready templates should be able to change according to the size of the map
    CHOOSEMAP("choose map -t (?<templateNumber>\\d+) -w (?<width>.+) -l (?<length>.+)"),
    BLOCKSETTEXTURE("settexture -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>.+)"),
    RECTANGLESETTEXTURE("settexture -x1 (?<x1>\\d+) -y1 (?<y1>\\d+) -x2 (?<x2>\\d+) -y2 (?<y2>\\d+) -t (?<type>.+)"),
    CLEARBLOCK("clear -x (?<x>\\d+) -y (?<y>\\d+)"),
    //next ones can be merged or separated
    DROPROCK("droprock -x (?<x>\\d+) -y (?<y>\\d+) -d (?<direction>[a-z]"),
    DROPOBJECT("drop(?<object>.+) -x (?<x>\\d+) -y (?<y>\\d+) -t (?<type>.+)( -c (?<count>\\d+))?"),
    CHOOSCOLOR("choose color -c (?<color>.+)"),
    /*not sure*/TRADEREQUEST("trade -(?<firstInputIdentifier[a-z]+) (?<firstInput>.+) -(?<secondInputIdentifier[a-z]+) (?<secondInput>.+) -(?<thirdInputIdentifier[a-z]+) (?<thirdInput>.+) -(?<fourthInputIdentifier[a-z]+) (?<fourthInput>.+)"),
    TRADELIST("trade list"),
    /*not sure*/TRADEACCEPT("trade accept -(?<firstInputIdentifier[a-z]+) (?<firstInput>.+) -(?<secondInputIdentifier[a-z]+) (?<secondInput>.+)"),
    TRADEHISTORY("trade history"),
    SHOWPRICELIST("show price list"),
    BUYORSELL("(?<activity>[(buy)|(sell)] -(?<firstInputIdentifier[a-z]+) (?<firstInput>.+) -(?<secondInputIdentifier[a-z]+) (?<secondInput>.+)"),
    NEXTTURN("next turn");

    private final String regex;

    private Commands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, Commands commands) {
        Matcher matcher = Pattern.compile(commands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
