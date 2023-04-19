package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Commands {
    
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
    MOVEONMAP("map (?<verticalNumber>\\d+) (?<verticalDirection>\\S+) (?<horizontalNumber>\\d+) (?<horizontalDirection>\\S+)"),
    SHOWDETAILS("show details -x (?<x>\\d+) -y (?<y>\\d+)"),
    
    ;
    
    private final String regex;
    
    private Commands(String regex) {
        this.regex = regex;
    }
    
    public static Matcher getMatcher(String input, Commands commands) {
        Matcher matcher = Pattern.compile(commands.regex).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}