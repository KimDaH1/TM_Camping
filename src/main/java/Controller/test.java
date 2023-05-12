package Controller;

import java.io.IOException;
import java.util.List;

import camping.dto.campzone;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mainController test = new mainController();
		try {
			List<campzone> arr = test.TestingApiThree();
			for (campzone item : arr) {
				System.out.println(item.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
