package net.samit.spring_ai_mcp_stdio_consumer;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class SpringAiMcpStdioConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiMcpStdioConsumerApplication.class, args);
	}

	@Bean
	ChatClient chatClient(ChatClient.Builder chatclientBuilder) {
		return chatclientBuilder.build();
	}


}

@Controller
class ApplicationRouter {

	@Autowired
	ChatClient chatClient;
	@Autowired
	ToolCallbackProvider toolCallbackProvider;

	@GetMapping("/chat")
	@ResponseBody
	String chat(@RequestParam String prompt) {
		return chatClient
				.prompt()
				.toolCallbacks(toolCallbackProvider)
				.user(prompt)
				.call()
				.content();
	}
}
