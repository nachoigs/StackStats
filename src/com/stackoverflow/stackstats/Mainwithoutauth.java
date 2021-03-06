package com.stackoverflow.stackstats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class Mainwithoutauth {

	private static String first_url = "http://api.stackexchange.com/2.2/%s?site=stackoverflow&type=jsontext&key=lYUczCc)*Rgha4FEIJ4t*Q((";
	private static String opciones = " 1. Get the tags on the site. \n 2. Get tags by their names. \n 3. Get the tags that only moderators can use"
			+ "\n 4. Get the tags that fulfill required tag constraints. \n 5. Get all the tag synonyms. \n 6. Get frequently asked questions in a set of tags. \n"
			+ " 7. Get related tags, based on common tag pairings. \n 8. Get the synonyms for a specific set of tags. \n 9. Get the top answer posters in a specific tag in the last month. \n"
			+ " 10. Get the top answer posters in a specific tag for all time \n 11. Get the top question askers in a specific tag in the last month.\n"
			+ " 12. Get the top question askers in a specific tag for all the time \n 13. Get the wiki entries for a set of tags.";
	private static String get_url = "";
	
	public static void main(String[] args) throws IOException {
		String option = askOption();
	
		switch (option){
			case "1": get_url = String.format(first_url, "tags");
					break;
			case "2": System.out.println ("Write the tag name");
					final Scanner in = new Scanner(System.in, "UTF-8");
					String name = in.nextLine();
					get_url = String.format(first_url, "tags/{"+name+"}/info");
					break;
			case "3": get_url = String.format(first_url, "tags/moderator-only");
					break;
			case "4": get_url = String.format(first_url, "tags/required");
					break;
			case "5": get_url = String.format(first_url, "tags/synonyms");
					break;
			case "6": System.out.println ("Write the tag name");
					final Scanner in1 = new Scanner(System.in, "UTF-8");
					String name1 = in1.nextLine();
					get_url = String.format(first_url, "tags/{"+name1+"}/faq");
					break;
			case "7": System.out.println ("Write the tag name");
					final Scanner in2 = new Scanner(System.in, "UTF-8");
					String name2 = in2.nextLine();
					get_url = String.format(first_url, "tags/{"+name2+"}/related");
					break;
			case "8": System.out.println ("Write the tag name");
					final Scanner in3 = new Scanner(System.in, "UTF-8");
					String name3 = in3.nextLine();
					get_url = String.format(first_url, "tags/{"+name3+"}/synonyms");
					break;
			case "9": System.out.println ("Write the tag name");
					final Scanner in4 = new Scanner(System.in, "UTF-8");
					String name4 = in4.nextLine();
					get_url = String.format(first_url, "tags/{"+name4+"}/top-answerers/month");
					break;
			case "10": System.out.println ("Write the tag name");
					final Scanner in5 = new Scanner(System.in, "UTF-8");
					String name5 = in5.nextLine();
					get_url = String.format(first_url, "tags/{"+name5+"}/top-answerers/all_time");
					break;
			case "11": System.out.println ("Write the tag name");
					final Scanner in6 = new Scanner(System.in, "UTF-8");
					String name6 = in6.nextLine();
					get_url = String.format(first_url, "tags/{"+name6+"}/top-askers/month");
					break;
			case "12": System.out.println ("Write the tag name");
					final Scanner in7 = new Scanner(System.in, "UTF-8");
					String name7 = in7.nextLine();
					get_url = String.format(first_url, "tags/{"+name7+"}/top-askers/all_time");
					break;
			case "13": System.out.println ("Write the tag name");
					final Scanner in8 = new Scanner(System.in, "UTF-8");
					String name8 = in8.nextLine();
					get_url = String.format(first_url, "tags/{"+name8+"}/wikis");
					break;
			default: System.out.println("You didn't choose a valid option");
		}
		for (int i = 1; i<100;i++){
			getInformation(get_url+"&page="+i);
			System.out.println("Press enter to next page");
			Scanner inenter = new Scanner(System.in);
			inenter.nextLine();
		}
		
		
		
		
	}
	
	private static String askOption(){
		System.out.println(opciones);
		final Scanner in = new Scanner(System.in, "UTF-8");
		String opcion = in.nextLine();
		return opcion;
	}
	
	private static void getInformation(String urlcon) throws IOException{
		URL url = new URL(urlcon);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		
		int responseCode = connection.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpsURLConnection.HTTP_OK){
			BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(
                 connection.getInputStream())));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			System.out.println(response.toString());
		}
	
	}

}
