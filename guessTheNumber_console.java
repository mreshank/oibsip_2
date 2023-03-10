import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class guessTheNumber_console 
{
    private static int userPoints = 0;

    public static void main(String[] args) 
    {
        guessTheNumber_console classObj = new guessTheNumber_console();
        classObj.runGame();
    }

    private void runGame() 
    {
        Scanner sc  = new Scanner(System.in);
        System.out.print( "\n\n~ Current Points : " + userPoints
                        + "\nAvailable Levels : \n> " + 1 
                        + ". Easy Level (0~10)\n> " + 2 
                        + ". Medium Level (0~500)\n> " + 3 
                        + ". Hard Level (-500~500)\n> " + 4 
                        + ". Extreme Level (-2.5K~2.5k)\n> " + 5 
                        + ". Custom Level (custom range)\n> " + 0 
                        + ". Exit Game!\nEnter Your Choice : ");
        switch ( sc.nextInt() ) 
        {
            case 1: level(0,10);
                    break;
            case 2: level(0,500);
                    break;
            case 3: level(-500,500);
                    break;
            case 4: level(-2500,2500);
                    break;
            case 5: System.out.print("Enter the Lower Range : ");
                    int lowerRange = new Scanner(System.in).nextInt();
                    System.out.print("Enter the Upper Range : ");
                    level( lowerRange , new Scanner(System.in).nextInt());
                    break;
            case 0: System.exit(0);
                    return;
            default: System.out.println("Invalid Choice !\n");
                    runGame();
        }
        runGame();
    }

    private int calculatePoints( int n ) 
    { 
        return (int) Math.ceil( n / Math.sqrt(n) ); 
    }

    private void level( int lowerLimit, int upperLimit ) 
    {
        int generatedNumber = new Random().nextInt( upperLimit - lowerLimit + 1 ) + lowerLimit, 
            guessedNumber = upperLimit + 100 ;
        int totalAttempts = (int) Math.ceil( 0.2 * Math.sqrt( upperLimit + Math.abs( lowerLimit ) ) );
        totalAttempts += Math.abs( totalAttempts - 3 ) < 8 ? Math.abs( totalAttempts - 3 ) : 0;
        System.out.print("Hey Player, A number has been generated between " 
                         + lowerLimit + " and " + upperLimit + ",\nAnd you've got " 
                         + totalAttempts + " chances to guess it right ...\nEnter a number : ");
        for (int i = totalAttempts; i > 0; i--) 
        {
            guessedNumber = new Scanner(System.in).nextInt();
            if (guessedNumber == generatedNumber) 
            {
                System.out.println("Congratulations ! You Guessed It Right...\nIt took you " + ( totalAttempts - i - 1 ) + " attempt(s) to guess it correctly ...");
                userPoints += calculatePoints( i>1 ? i-1 : i );
            } 
            else if (i == 1) 
            {
                System.out.print("Sorry, You Lost ! It was " + generatedNumber + ",\nWell... Better luck next time !");
                break;
            } 
            else if (guessedNumber < generatedNumber)
            {
                System.out.print("Enter a number Greater than " + guessedNumber + " : ");
            }
            else if (guessedNumber > generatedNumber)
            {
                System.out.print("Enter a number Less than " + guessedNumber + " : ");
            }
        }
    }
}
