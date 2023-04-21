import java.util.Random;
import java.util.Scanner;

public class CaptchaGenerator {
    private static final Random random = new Random();
    private static final int captchaLength = 4 + random.nextInt(5);
    
    public static void main(String[] args) {
    
        String captcha = generateCaptchaString();
        asciiArt(captcha);
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CAPTCHA: ");
        String userInput = scanner.nextLine();
        
        if (userInput.equals(captcha)) System.out.println("CAPTCHA validated!");
        else System.out.println("Invalid CAPTCHA.");
    }
    
    private static String generateCaptchaString() {
        StringBuilder captchaBuilder = new StringBuilder();
        for (int i = 0; i < captchaLength; i++) {
            String CHARACTERS = "0123456789";
            captchaBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return captchaBuilder.toString();
    }
    
    
    public static void asciiArt(String captcha) {
        
        int captchaLength = captcha.length();
        int captchaNumber = Integer.parseInt(captcha);
        
        int[] captchaDigits = new int[captchaLength];
        for (int i = captchaLength - 1; i >= 0; i--) {
            captchaDigits[i] = captchaNumber % 10;
            captchaNumber /= 10;
        }
        
        String[] line = new String[8];
        for (int i = 1; i < 8; i++) {
            line[i] = "";
        }
        
       
        
        //noise
        int toBeNoisedLine1 = (4 + (int) (Math.random() * 5)) % 7 + 1;
        int toBeNoisedLine2 = (4 + (int) (Math.random() * 5)) % 7 + 1;
        while(toBeNoisedLine2 == toBeNoisedLine1) {
            toBeNoisedLine2 = (4 + (int) (Math.random() * 5)) % 7 + 1;
        }
        line[toBeNoisedLine1] += " ";
        line[toBeNoisedLine2] += " ";
        
        
        for (int i = 0; i < captchaLength; i++) {
            switch (captchaDigits[i]) {
                case 0: {
                    line[1] += " ******      ";
                    line[2] += "*      *     ";
                    line[3] += "*      *     ";
                    line[4] += "*      *     ";
                    line[5] += "*      *     ";
                    line[6] += "*      *     ";
                    line[7] += " ******      ";
                    break;
                }
                case 1: {
                    line[1] += "*     ";
                    line[2] += "*     ";
                    line[3] += "*     ";
                    line[4] += "*     ";
                    line[5] += "*     ";
                    line[6] += "*     ";
                    line[7] += "*     ";
                    break;
                }
                case 2: {
                    line[1] += "********     ";
                    line[2] += "       *     ";
                    line[3] += "       *     ";
                    line[4] += "********     ";
                    line[5] += "*            ";
                    line[6] += "*            ";
                    line[7] += "********     ";
                    break;
                }
                case 3: {
                    line[1] += "********     ";
                    line[2] += "       *     ";
                    line[3] += "       *     ";
                    line[4] += " *******     ";
                    line[5] += "       *     ";
                    line[6] += "       *     ";
                    line[7] += "********     ";
                    break;
                }
                case 4: {
                    line[1] += "*      *     ";
                    line[2] += "*      *     ";
                    line[3] += "********     ";
                    line[4] += "       *     ";
                    line[5] += "       *     ";
                    line[6] += "       *     ";
                    line[7] += "       *     ";
                    break;
                }
                case 5: {
                    line[1] += "********     ";
                    line[2] += "*            ";
                    line[3] += "*            ";
                    line[4] += "********     ";
                    line[5] += "       *     ";
                    line[6] += "       *     ";
                    line[7] += "********     ";
                    break;
                }
                case 6: {
                    line[1] += "********     ";
                    line[2] += "*            ";
                    line[3] += "*            ";
                    line[4] += "********     ";
                    line[5] += "*      *     ";
                    line[6] += "*      *     ";
                    line[7] += "********     ";
                    break;
                }
                case 7: {
                    line[1] += "********     ";
                    line[2] += "*      *     ";
                    line[3] += "*      *     ";
                    line[4] += "       *     ";
                    line[5] += "       *     ";
                    line[6] += "       *     ";
                    line[7] += "       *     ";
                    break;
                }
                case 8: {
                    line[1] += "********     ";
                    line[2] += "*      *     ";
                    line[3] += "*      *     ";
                    line[4] += "********     ";
                    line[5] += "*      *     ";
                    line[6] += "*      *     ";
                    line[7] += "********     ";
                    break;
                }
                case 9: {
                    line[1] += "********     ";
                    line[2] += "*      *     ";
                    line[3] += "*      *     ";
                    line[4] += "********     ";
                    line[5] += "       *     ";
                    line[6] += "       *     ";
                    line[7] += "********     ";
                    break;
                }
            }
        }
        
        for (int i = 1; i < 8; i++) {
            System.out.println(line[i]);
        }
    }
}