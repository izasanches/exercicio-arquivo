package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> list = new ArrayList<>();

		System.out.println("Entre com caminho do arquivo:");
		String caminhoArquivoStr = sc.nextLine();

		File caminhoArquivo = new File(caminhoArquivoStr);
		String caminhoPastaStr = caminhoArquivo.getParent();

		boolean success = new File(caminhoPastaStr + "\\saida").mkdir();

		String arquivoDestinoStr = caminhoPastaStr + "\\saida\\soma.csv";

		try(BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoStr))){
			String itemCsv = br.readLine();
			while (itemCsv != null){
				String[] campos = itemCsv.split(",");
				String nome = campos[0];
				double preco = Double.parseDouble(campos[1]);
				int quantidade = Integer.parseInt(campos[2]);

				list.add(new Product(nome, preco, quantidade));
				itemCsv = br.readLine();

			}

			try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoDestinoStr))){

				for (Product item : list) {

					bw.write(item.getNome() + "," + String.format("%.2f", item.total()));
					bw.newLine();

				}

				System.out.println("Arquivo gerado!!");

			} catch (IOException e) {
				System.out.println("Erro ao gerar o arquivo: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}

		sc.close();		

	}

}
