import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Chatbot {

    private Map<String, String> knowledgeBase;

    public Chatbot() {
        knowledgeBase = new HashMap<>();
        initializeKnowledgeBase();
    }

    private void initializeKnowledgeBase() {
        knowledgeBase.put("hello", "Hi there! How can I help you today?");
        knowledgeBase.put("how are you", "I'm a bot, so I don't have feelings, but thanks for asking!");
        knowledgeBase.put("what is your name", "I'm a chatbot created to help you with your queries.");
        knowledgeBase.put("bye", "Goodbye! Have a great day!");
        // Add more responses as needed
    }

    public String getResponse(String input) {
        input = input.toLowerCase().trim();
        if (knowledgeBase.containsKey(input)) {
            return knowledgeBase.get(input);
        } else {
            return "I'm sorry, I don't understand that. Can you rephrase?";
        }
    }

    public static void main(String[] args) {
        Chatbot chatbot = new Chatbot();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Chatbot! Type 'bye' to exit.");
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Chatbot: " + chatbot.getResponse(userInput));
                break;
            }
            String response = chatbot.getResponse(userInput);
            System.out.println("Chatbot: " + response);
        }
        scanner.close();
    }
}
