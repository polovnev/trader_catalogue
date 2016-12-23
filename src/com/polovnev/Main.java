package com.polovnev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.polovnev.constants.Constants;
import com.polovnev.exception.ClientErrorException;
import com.polovnev.exception.HttpException;
import com.polovnev.exception.HttpExceptionHandler;
import com.polovnev.exception.ServerErrorException;
import com.polovnev.rest.HttpConnection;
import com.polovnev.trader.Trader;
import com.polovnev.util.RandomValueGenerator;

public class Main {

	private static List<Trader> getTraders() {
		int traderCount = RandomValueGenerator.getInt(1, Constants.TRADER_COUNT_RESTRICTION);
		List<Trader> traders = new ArrayList<Trader>(traderCount);
		for (int i = 0; i < traderCount; i++) {
			traders.add(new Trader());
		}
		return traders;
	}

	private static void printTraders(List<Trader> traders) {
		String leftAlignFormat = "| %-2d | %-20s | %-6d | %-6s |  %-7s |%n";
		System.out.format("+----+----------------------+--------+--------+----------+%n");
		System.out.format("| ID | NAME                 | PROFIT | GENDER | COUNTRY  |%n");
		System.out.format("+----+----------------------+--------+--------+----------+%n");
		for (int i = 0; i < traders.size(); i++) {
			Trader trader = traders.get(i);
			String traderName = new StringBuilder(trader.getFirstName().toString()).append(" ")
					.append(trader.getLastName()).toString();
			System.out.format(leftAlignFormat, i, traderName, trader.getProfit(), trader.getGender(),
					trader.getCountry());
		}
		System.out.format("+----+----------------------+--------+--------+----------+%n");
	}

	private static void sendMessage(String message, Trader trader) throws ClientErrorException, ServerErrorException {
		HttpConnection httpConnection = new HttpConnection();
		try {
			httpConnection.sendMessage(message, trader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void getMessages(Trader trader) throws ClientErrorException, ServerErrorException {
		HttpConnection httpURLConnection = new HttpConnection();
		try {
			httpURLConnection.getMessages(trader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Trader currentTrader = null;
		Scanner scanner = new Scanner(System.in);
		List<Trader> traders = getTraders();
		int traderSize = traders.size();
		printTraders(traders);

		while (true) {
			System.out.print("\nEnter id to select trader: ");
			int id = scanner.nextInt();
			if (id < traderSize) {
				currentTrader = traders.get(id);
				break;
			} else {
				System.out.println("Id not correct!");
			}
		}

		System.out.println("Do you want write message to trader? (y/n)");
		String response = scanner.next();
		try {
			if (response.equals("y")) {
				System.out.print("Fill up the message to trader: ");
				String message = scanner.next();
				sendMessage(message, currentTrader);
			} else {
				getMessages(currentTrader);
			}
		} catch (HttpException e) {
			HttpExceptionHandler exceptionHandler = new HttpExceptionHandler();
			exceptionHandler.handle(e);
		} finally {
			scanner.close();
		}

	}

}
