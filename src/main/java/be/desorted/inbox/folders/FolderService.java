package be.desorted.inbox.folders;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FolderService {

	public List<Folder> fetchDefaultFolders(String userId) {
		return List.of(
				new Folder(userId, "Inbox", "blue"),
				new Folder(userId, "SentItems", "green"),
				new Folder(userId, "Important", "red")
		);
	}
}
