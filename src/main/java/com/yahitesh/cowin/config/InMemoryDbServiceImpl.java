/* Apache License 2.0
 * A permissive license whose main conditions require preservation of copyright and license notices.
 * Contributors provide an express grant of patent rights. 
 * Licensed works, modifications, and larger works may be distributed under different terms and without source code.
 */

package com.yahitesh.cowin.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yahitesh.cowin.model.Notification;

/**
 * @author yaHitesh
 * @since 1.0.0
 */
@Component
public class InMemoryDbServiceImpl {
	
	private static final String LOCK = "LOCK";

	public void saveRecord(Notification notification) {
		try {
			synchronized (LOCK) {
				FileWriter writer = new FileWriter(new File("src/main/resources/notification.txt"), true);
				String str = notification.getEmail() + "!" + notification.getPincodes() + "!"
						+ notification.getNotify();
				writer.write(str);
				writer.write(System.getProperty("line.separator"));
				writer.flush();
				writer.close();
			}
		} catch (IOException e) {
			System.out.println("An error occurred while writeInAFile");
			e.printStackTrace();
		}
	}

	public void updateRecord(Notification notification) {
		try {
			synchronized (LOCK) {
				BufferedReader reader = new BufferedReader(
						new FileReader(new File("src/main/resources/notification.txt")));
				FileWriter writer = new FileWriter(new File("src/main/resources/notification.txt"), true);
				StringBuilder sb = new StringBuilder();
				for (String line; (line = reader.readLine()) != null;) {
					if (line.contains(notification.getEmail())) {
						String str = notification.getEmail() + "!" + notification.getPincodes() + "!"
								+ notification.getNotify();
						sb.append(str);
					} else {
						sb.append(line);
					}
					sb.append(System.getProperty("line.separator"));
				}
				clearTheFile();
				writer.write(sb.toString());
				writer.flush();
				writer.close();

			}
		} catch (IOException e) {
			System.out.println("An error occurred while writeInAFile");
			e.printStackTrace();
		}
	}

	public List<Notification> readFromFile() {

		List<Notification> list = new ArrayList<Notification>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(new File("src/main/resources/notification.txt")));
			for (String line; (line = reader.readLine()) != null;) {
				String[] strArr = line.split("!");
				Notification notification = new Notification();
				notification.setEmail(strArr[0]);
				notification.setPincodes(strArr[1]);
				notification.setNotify(strArr[2]);
				list.add(notification);
			}
		} catch (IOException e) {
			System.out.println("An error occurred while readFromFile");
			e.printStackTrace();
		}

		return list;
	}

	public static void clearTheFile() {
		FileWriter fwOb;
		try {
			fwOb = new FileWriter("src/main/resources/notification.txt", false);
			PrintWriter pwOb = new PrintWriter(fwOb, false);
			pwOb.flush();
			pwOb.close();
			fwOb.close();
		} catch (IOException e) {
			System.out.println("An error occurred while clearTheFile");
			e.printStackTrace();
		}
	}

}
