package fr.esgi.twitter.client.ihm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Request;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.*;
import org.scribe.model.*;

import fr.esgi.twitter.client.consts.Consumer;
import lombok.Getter;

public class MainWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";
	Consumer consumer ;

	@Getter
	private JLabel icone; // Icone

	@Getter
	private JTextField tweet; // Tweet

	@Getter
	private JList<String> timeline; // TimeLine
	public JTextField txtTweet;
	public JButton btnUpdate;

	public MainWindow() {
		setTitle("Twitter SOA");
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(407, 727, 67, 23);
		btnUpdate.addActionListener(this);	
		
		updateStatus();
		
		getContentPane().setLayout(null);
		getContentPane().add(btnUpdate);

		txtTweet = new JTextField();
		txtTweet.setBounds(71, 727, 326, 23);
		getContentPane().add(txtTweet);
		txtTweet.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Benjamin\\Desktop\\default_logo.png")); // FIXME
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBackground(Color.BLUE);
		lblNewLabel.setBounds(10, 702, 48, 48);
		getContentPane().add(lblNewLabel);

		JList list = new JList();
		list.setBounds(10, 11, 464, 680);
		getContentPane().add(list);

		JPanel timelinePlanel = new JPanel();
		
		setResizable(false);
		setSize(500, 800);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnUpdate)) {

		}

	}
private void updateStatus()
	{
	//	btnUpdate = new JButton("update");	
		btnUpdate.addActionListener(new ActionListener() {		
		String tweet = null;
			public void actionPerformed(ActionEvent e) {
				
			    OAuthService service = new ServiceBuilder()
			                                .provider(TwitterApi.Authenticate.class)
			                                .apiKey(consumer.KEY)
			                                .apiSecret(consumer.SECRET)
			                                .build();
			    Scanner scanner = new Scanner(System.in);
			    
			    Token RToken = service.getRequestToken();
			    Verifier verif = new Verifier(scanner.nextLine());
			    Token AToken = service.getAccessToken(RToken, verif);	
			    
				try {
					tweet = URLEncoder.encode("First Tweet","UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
			    String urlTweet=PROTECTED_RESOURCE_URL+txtTweet.getText();
			    System.out.println("request: "+ txtTweet.getText());		  //Just For Test    
			    OAuthRequest request = new OAuthRequest(Verb.POST, urlTweet);
		        request.addBodyParameter("status",urlTweet);
		        service.signRequest(AToken, request);
		        System.out.println("REQUEST: " + request.getUrl());   		// Just For Test     
		        Response response = request.send();
		        System.out.println(response.getBody());						//Just For Test
			}
		}); 
			
	
	

		
	}
}

 
