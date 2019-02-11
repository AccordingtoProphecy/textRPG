import java.util.Scanner;
import java.util.Random;

public class game {
    public static void main(String[] args) {
        game game = new game();
        Scanner s = new Scanner(System.in);
        Random rand = new Random();

        // Maybe create a character class with all this information
        int playRoll = rand.nextInt(20) + 1;
        int op1Roll = rand.nextInt(20) + 1;
        boolean playFirst;
        boolean yourTurn;

        int playHealth = rand.nextInt(20) + 10;
        int playStamina = rand.nextInt(15) + 5;
        int playUseStamina = rand.nextInt(3) + 1;
        int playDamage = rand.nextInt(5) + 2;

        int op1Health = rand.nextInt(15) + 10;
        int op1Stamina = rand.nextInt(10) + 5;
        int op1UseStamina = rand.nextInt(3) + 2;
        int op1Damage = rand.nextInt(5) + 2;

        game.printStats(playHealth, playStamina, playUseStamina, playDamage, op1Health, op1Stamina, op1UseStamina,
                op1Damage);
        System.out.println("At the start of each turn, you gain 1 stamina.");

        System.out.println("");
        if (playRoll > op1Roll) {
            playFirst = true;
            System.out.println("You rolled a " + playRoll + ". Your opponent rolled a " + op1Roll + ". You go first.");
        } else {
            // Add action to fix if both rolls are identical
            playFirst = false;
            System.out.println(
                    "You rolled a " + playRoll + ". Your opponent rolled a " + op1Roll + ". Your opponent goes first.");
        }

        if (playFirst) {
            yourTurn = true;
            System.out.println("");
            System.out.println("What would you like to do?");
            System.out.println("ATTACK or DODGE?");
            System.out.println("");
            String playMove = s.next();
            if (playMove.equalsIgnoreCase("attack")) {
                System.out.println("You attack your opponent for " + playDamage + " damage.");
                game.op1TakeDamage(playDamage, op1Health);
                op1Health -= playDamage;
                System.out.println("It is your opponent's turn.");

            } else if (playMove.equalsIgnoreCase("dodge")) {
                System.out.println("You will dodge your opponent's next attack.");
                System.out.println("It is your opponent's turn.");
                yourTurn = false;

            } else {
                System.out.println("That is not a valid input.");
            }

        } else {
            yourTurn = false;
            System.out.println("");
            System.out.println("Your opponent attacks you for " + op1Damage + " damage. What do you want to do?");
            System.out.println("TAKE IT or DODGE?");
            System.out.println("");
            String playMove = s.next();
            playMove.toLowerCase();

            if (playMove.equalsIgnoreCase("take it")) {
                game.playTakeDamage(op1Damage, playHealth);

            } else if (playMove.equalsIgnoreCase("dodge")) {
                System.out.println("You dodge your opponent's attack, costing " + " stamina.");
                System.out.println("You are at " + (playStamina - playUseStamina) + " stamina.");
                playStamina -= playUseStamina;

            } else {
                System.out.println("That is not a valid input.");
            }
        }

        s.close();
    }

    public void playTakeDamage(int op1Damage, int playHealth) {
        System.out.println("You take " + op1Damage + " damage.");
        System.out.println("You are at " + (playHealth - op1Damage) + " health.");
    }

    public void op1TakeDamage(int playDamage, int op1Health) {
        System.out.println("Your opponent takes " + playDamage + " damage.");
        System.out.println("Your opponent is at " + (op1Health - playDamage) + " health.");
    }

    public void printStats(int playHealth, int playStamina, int playUseStamina, int playDamage, int op1Health,
            int op1Stamina, int op1UseStamina, int op1Damage) {
        System.out.println("");
        System.out.println("You have " + playHealth + " health.");
        System.out.println("You have " + playStamina + " stamina. Your attacks use " + playUseStamina + " stamina.");
        System.out.println("You deal " + playDamage + " damage.");
        System.out.println("");
        System.out.println("Your opponent has " + op1Health + " health.");
        System.out.println(
                "Your opponent has " + op1Stamina + " stamina. Their attacks use " + op1UseStamina + " stamina.");
        System.out.println("Your opponent deals " + op1Damage + " damage.");
    }
}