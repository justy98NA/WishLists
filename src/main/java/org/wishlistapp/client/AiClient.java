package org.wishlistapp.client;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class AiClient {

    private final OpenAiService client;
    public AiClient() {
        String secretKey = getTheKey();
        client = new OpenAiService(secretKey);
    }

    private String getTheKey() throws IllegalStateException {
        Dotenv dotenv = Dotenv.load();
        // Retrieve the secret key
        String secretKey = dotenv.get("OPENAI_SECRET_KEY");

        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalStateException("OPENAI_SECRET_KEY is not set in the .env file.");
        }
        return secretKey;
    }

    public String getResponse(String prompt) {
        String instructions = "Find a suitable title for the product described in provided comments. Keep it short. Use simple words. If it's a book, then book title is enough. If it's vacation somewhere, just 'Vacation in Somewhere'. If it's a piece of clothing, then type and color as title are enough, etc.\n";
        String complete_prompt = instructions + "COMMENTS: " + prompt;
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(complete_prompt)
                .model("gpt-3.5-turbo-instruct")
                .maxTokens(64)
                .build();
        StringBuilder response = new StringBuilder();
        client.createCompletion(completionRequest).getChoices().forEach(choice -> {
            response.append(choice.getText());
        });
        return response.toString().strip();
    }

}
