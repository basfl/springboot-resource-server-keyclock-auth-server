package com.web.client.app.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.web.client.app.response.AlbumRest;

@Controller
public class AlbumsController {
	
	@Autowired
	OAuth2AuthorizedClientService oauth2ClientService;

	@GetMapping("/albums")
	public String getAlbums(Model model, @AuthenticationPrincipal OidcUser princliple) {

		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
		
		OAuth2AuthorizedClient oauth2Client = oauth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), 
				oauthToken.getName());
		
		System.out.println("oauth2Client"+oauth2Client);
		
		String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		System.out.println("jwtAccessToken = " + jwtAccessToken);

		System.out.println("*******" + princliple);
		OidcIdToken idToken = princliple.getIdToken();
		String token = idToken.getTokenValue();
		System.out.println("Token is " + token);
		AlbumRest albumRest1 = new AlbumRest();
		{
			albumRest1.setAlbumId("album1");
			albumRest1.setAlbumTitle("first album!!");
			albumRest1.setAlbumUrl("http://localhost:8082/album/1");
		}
		AlbumRest albumRest2 = new AlbumRest();
		{
			albumRest2.setAlbumId("album2");
			albumRest2.setAlbumTitle("second album!!");
			albumRest2.setAlbumUrl("http://localhost:8082/album/2");
		}
		List<AlbumRest> returnValue = Arrays.asList(albumRest1, albumRest2);
		model.addAttribute("albums", returnValue);
		return "albums";
	}

}
