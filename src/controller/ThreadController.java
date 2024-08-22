package controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ThreadController extends Thread{
	String serv;
	
	public ThreadController(String serv) {
		this.serv = serv;
	}
	
	public void run() {
		int cont = 0;
		String com = "ping -4 -c 10";
		com = com + " " + serv;
		String[] ca = com.split(" ");
		try {
			Process p = Runtime.getRuntime().exec(ca);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.toString();
			while(linha != null) {
				if(linha.contains("=")) {
					if(linha.contains("avg")) {
						String[] ping2 = linha.split("/");
						System.out.println("No servidor "+serv+" a media das 10 iterações da operação ping foi de: "+ping2[4]);
						break;
					}
					String[] ping = linha.split("=");
					int ind = ping.length - 1;
					cont++;
					System.out.println("No servidor "+serv+" na iteração "+cont+" o ping foi de "+ping[ind]);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
}
	
