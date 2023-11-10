package havitch;

import havitch.expressions.math.factories.*;

import java.util.Hashtable;
import java.util.Scanner;
public class Start {
    public Start(){
    }
    String username;
    String lifeInf;

    Hashtable<String, AbstractMathCommandFactory> supportedOperatorCommands = new Hashtable<>() {
        {
            put("add", new AdditionFactory());
            put("sub", new SubtractionFactory());
            put("div", new DivisionFactory());
            put("multi", new MultiplicationFactory());
        }
    };
    public static void main(String[] args) {
        Start m = new Start();
        m.act();
    }
    public void  act(){
        welcome();
        username = getLineInfo();
        education(username);
        parseMathExpression(inputMathExpression());


    }

    public void welcome(){
        System.out.println("-Hello, my name is Havitch.");
        System.out.println("-I want to learn how to do many things, so please, can you teach me and I would be useful!");
        System.out.println("-Firstly, give me your name - it would be easier to talk.");
    }
    public String getLineInfo(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public int getNumberInfo(){
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    public void education(String name){
        System.out.println("-So "+ name +", I want to get some information about you.");
        System.out.println("-How old are you?");
        int age = getNumberInfo();
        ageComparison(age);
        System.out.println("-What is your favourite color?");
        String favColor = getLineInfo();
        System.out.println("-Ok, now lets get started!");
        System.out.println("I have some commands you can use.");
        System.out.println("You can find all functions by using command (commands).");
    }
    public void commandsList(){
        System.out.println("calc - this command would help you with counting.");
    }
    public void ageComparison(int age){
        if(age < 20){
            lifeInf = youngTalk();
        }
        if((age > 89) && (age <110)){
            lifeInf = luckyTalk();
        }
        if((age > 19) && (age < 90)){
            lifeInf = eldTalk();
        }
        if(age > 109){
            wrongTalk();
        }
    }
    public String youngTalk(){
        System.out.println("-You are young, well what are you going to do in your life?");
        return getLineInfo();
    }
    public String eldTalk(){
        System.out.println("-What are you doing in your life?");
        return getLineInfo();
    }
    public String luckyTalk(){
        System.out.println("-Oh, I am lucky to talk with you!");
        System.out.println("-What have you done during your life?");
        return getLineInfo();
    }
    public void wrongTalk(){
        System.out.println("It's is a bad joke,tell me your real age!");
        ageComparison(getNumberInfo());
    }
    public String inputMathExpression(){
        return getLineInfo();
    }

}