package be.desorted.inbox;

import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import be.desorted.inbox.folders.Folder;
import be.desorted.inbox.folders.FolderRepository;

@SpringBootApplication
public class InboxApp {

	@Autowired
	private FolderRepository folderRepository;

	public static void main(String[] args) {
		SpringApplication.run(InboxApp.class, args);
	}

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

	@PostConstruct
	public void init() {
		folderRepository.save(new Folder("WillemDeRoover", "Inbox", "blue"));
		folderRepository.save(new Folder("WillemDeRoover", "Sent", "green"));
		folderRepository.save(new Folder("WillemDeRoover", "Important", "yellow"));
	}

}
