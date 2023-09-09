package online_examination;
import java.util.*;

public class OnlineExam{
    Scanner sc=new Scanner(System.in);

    HashMap<String,Integer> info=new HashMap<String,Integer>();
    HashMap<String, Integer> questionsAndAnswers = new HashMap<String, Integer>();

  public OnlineExam() {
	  questionsAndAnswers.put("Which planet is known as the 'Red Planet'?", 2); // Mars
	  questionsAndAnswers.put("What is the largest mammal on Earth?", 2); // Blue Whale
	  questionsAndAnswers.put("Which gas makes up the majority of Earth's atmosphere?", 3); // Nitrogen
	  questionsAndAnswers.put("What is the largest organ in the human body?", 4); // Skin
	  questionsAndAnswers.put("What is the chemical symbol for gold?", 1); // Au
  }

    //Exam
  public void sExam() {
    int s = 0;
    int answer;
    long duration = 5_000; // 5 seconds
    long start_Time = System.currentTimeMillis();
    long end_Time = start_Time + duration;

    System.out.println(" ----Exam has started----");
    System.out.println("You have 15 seconds to answer each question.");
    
    while (System.currentTimeMillis() < end_Time) {
        for (Map.Entry<String, Integer> entry : questionsAndAnswers.entrySet()) {
            String question = entry.getKey();
            int correctAnswer = entry.getValue();
            System.out.println(" -----------------------------------");
            
            System.out.println(question);
            displayOptions(question);

            System.out.print("Answer: ");
            answer = sc.nextInt();

            s += (answer == correctAnswer) ? 2 : -1;
            System.out.println("\n *********************");
        }

        if (System.currentTimeMillis() >= end_Time) {
            System.out.println("Time's up!!!");
        } else {
            System.out.println(displayResult(s)); // Display the result
        }
    }

    long elapsed_Time = System.currentTimeMillis() - start_Time;

    // Display the result if 15 seconds have passed
    if (elapsed_Time >= duration) {
        System.out.println(displayResult(s));
           }
        }
  private void displayOptions(String question) {
      switch (question) {
          case "Which planet is known as the 'Red Planet'?":
              System.out.println(" 1. Venus\n 2. Mars \n 3. Jupiter \n 4. Saturn \n");
              break;
          case "What is the largest mammal on Earth?":
              System.out.println(" 1. African Elephant \n 2. Blue Whale \n 3.  Hippopotamus \n 4. Giraffe");
              break;
          case "Which gas makes up the majority of Earth's atmosphere?":
              System.out.println(" 1. Oxygen \n 2. Carbon Dioxide \n 3. Nitrogen \n 4. Hydrogen");
              break;
          case "What is the largest organ in the human body?":
              System.out.println(" 1. Lungs \n 2. Heart \n 3. Brain \n 4. Skin");
              break;
          case "What is the chemical symbol for gold?":
              System.out.println(" 1. Au \n 2. Ag \n 3. Hg \n 4. Fe");
              break;
          // Add more cases for other questions
      }
  }
  
    //Result display
  public String displayResult(int score) {
	    String result = "-----End of Exam-----\n";
	    result += "Your Score is " + score + "\n";
	    
	    result += (score > 5) ? "Congratulations, You did it!\n" : "Hmm, I think you could've done better...\n";

	    result += " -----------------------------------\n";

	    return result;
	}


    //Update Operation
    public HashMap<String,Integer> update(){
        System.out.print(" Enter Username: ");
        String update_id=sc.next();
        System.out.print(" Enter Old Password: ");
        int update_password=sc.nextInt();

        if(info.containsKey(update_id) && info.get(update_id)==update_password){
            System.out.print(" Enter New Password: ");
            update_password=sc.nextInt();
            info.replace(update_id,update_password);
            System.out.println("\n Password Changed...");
        }else{
            System.out.println(" User Not Found!\n");
        }
        return info;
    }

    //Menu
    public void menu(){
        System.out.println(" ----Menu----");
        System.out.println(" 1.Update Profile \n 2.Start Exam \n 3.Logout");
        System.out.print("Enter Your choice: ");
        int choice=sc.nextInt();

        switch(choice){
            case 1:{
                //update
                info=update();
                menu();
                break;
            }
            case 2:{
                //start Exam
                sExam();
                menu();
                break;
            }
            case 3:{
                //log out
                System.exit(0);
                break;
            }
            default:{
                System.out.println(" Invalid");
            }
        }

    }

    //login student
    void signIn(){
        info.put("Ali",5700);
        info.put("Rayyan",6216);
        info.put("Usman",1590);
        info.put("Aqib",1357);
        info.put("Waleed",2468);
        
 
        System.out.println(" Please Login!");
        System.out.print(" Enter Username: ");
        String id=sc.next();
        System.out.print("\n Enter Password:");
        int password=sc.nextInt();

        if(info.containsKey(id) && info.get(id)==password){
            System.out.println("\n You have Successfully Logged in to the Exam!");
            menu();
        }else{
            System.out.println("\n Incorrect Credentials! \n Enter Correct ones... \n");
            signIn();
        }
    }

    public static void main(String args[]){
        OnlineExam o=new OnlineExam();
        System.out.println("\n---------------------------------------------");
        o.signIn();
    }
}



