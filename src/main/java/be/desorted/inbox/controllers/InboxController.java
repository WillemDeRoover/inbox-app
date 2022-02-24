package be.desorted.inbox.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import be.desorted.inbox.folders.Folder;
import be.desorted.inbox.folders.FolderRepository;

@Controller
public class InboxController {

	@Autowired
	private FolderRepository folderRepository;

	@GetMapping(value = "/")
	public String homePage(@AuthenticationPrincipal OAuth2User principal, Model model) {
		String userID = principal.getAttribute("login");
		if(principal == null || !StringUtils.hasText(userID))  {
			return "index";
		} else {
			List<Folder> userFolders = folderRepository.findAllById(userID);
			model.addAttribute("userFolders", userFolders);
			return "inbox-page";
		}

	}
}
