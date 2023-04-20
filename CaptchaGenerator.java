import java.util.Random;
import java.util.Scanner;

public class CaptchaGenerator {
    private static final String CHARACTERS = "0123456789"; // characters to use in captcha
    private static int CAPTCHA_LENGTH = 4 + (int) (Math.random() * ((8 - 4) + 1)); // length of captcha

    public static void main(String[] args) {
        Random random = new Random();
        StringBuilder captchaBuilder = new StringBuilder();
        // generate captcha
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            captchaBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        String captcha = captchaBuilder.toString();

        // print captcha to command line
        System.out.println("CAPTCHA: " + captcha);
        asciiArt(captcha);

        // prompt user to enter captcha
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CAPTCHA: ");
        String userInput = scanner.nextLine();

        // validate user input
        if (userInput.equals(captcha)) {
            System.out.println("CAPTCHA validated!");
        } else {
            System.out.println("Invalid CAPTCHA.");
        }
    }


    public static void asciiArt(String captcha) {
        int count = captcha.length();                //'count' is 'array size
        int digit = Integer.parseInt(captcha);                  // input integer
        int[] arr = new int[count];                    //'arr' is number of digits

        String a1 = "", a2 = "", a3 = "", a4 = "", a5 = "";  //store ascii line by line

        for (int i = count - 1; i >= 0; i--) {
            arr[i] = digit % 10;
            digit /= 10;
        }

        for (int i = 0; i < count; i++) {
            switch (arr[i]) {
                case 0: {
                    a1 += " ..    ";
                    a2 += ".  .   ";
                    a3 += ".  .   ";
                    a4 += ".  .   ";
                    a5 += " ..    ";
                    break;
                }
                case 1: {
                    a1 += ".   ";
                    a2 += ".   ";
                    a3 += ".   ";
                    a4 += ".   ";
                    a5 += ".   ";
                    break;
                }
                case 2: {
                    a1 += "...   ";
                    a2 += "  .   ";
                    a3 += "...   ";
                    a4 += ".     ";
                    a5 += "...   ";
                    break;
                }
                case 3: {
                    a1 += "...   ";
                    a2 += "  .   ";
                    a3 += "...   ";
                    a4 += "  .   ";
                    a5 += "...   ";
                    break;
                }
                case 4: {
                    a1 += ". .   ";
                    a2 += ". .   ";
                    a3 += "...   ";
                    a4 += "  .   ";
                    a5 += "  .   ";
                    break;
                }
                case 5: {
                    a1 += "...   ";
                    a2 += ".     ";
                    a3 += "...   ";
                    a4 += "  .   ";
                    a5 += "...   ";
                    break;
                }
                case 6: {
                    a1 += "...   ";
                    a2 += ".     ";
                    a3 += "...   ";
                    a4 += ". .   ";
                    a5 += "...   ";
                    break;
                }
                case 7: {
                    a1 += "...   ";
                    a2 += ". .   ";
                    a3 += "  .   ";
                    a4 += "  .   ";
                    a5 += "  .   ";
                    break;
                }
                case 8: {
                    a1 += "...   ";
                    a2 += ". .   ";
                    a3 += "...   ";
                    a4 += ". .   ";
                    a5 += "...   ";
                    break;
                }
                case 9: {
                    a1 += "...   ";
                    a2 += ". .   ";
                    a3 += "...   ";
                    a4 += "  .   ";
                    a5 += "...   ";
                    break;
                }
            }
        }

        System.out.println(a1 + "\n" + a2 + "\n" + a3 + "\n" + a4 + "\n" + a5);
    }
}

