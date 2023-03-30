package imersaoJavaAlura;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) throws Exception {

		// fazer uma conexão HTTP e buscar os top 250 filmes

		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();

		// System.out.println(body);

		// extrair somente os dados que interessam (título, poster, classificação)

		var parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);

		// exibir e manipular os dados

		/*
		 * for (Map<String, String> filme : listaDeFilmes) {
		 * System.out.println(ChangeConsoleColor.WHITE_BOLD +
		 * ChangeConsoleColor.GREEN_BACKGROUND + filme.get("title") +
		 * ChangeConsoleColor.RESET); System.out.println(filme.get("image"));
		 * System.out.println(filme.get("imDbRating")); int rating =
		 * (int)filme.get("imDbRating"); PrintStars.printStars(filme.get("imDbRating"));
		 * System.out.println(filme.get("crew")); System.out.println(); }
		 */

		for (Map<String, String> filme : listaDeFilmes) {
			System.out.println(ChangeConsoleColor.WHITE_BOLD + ChangeConsoleColor.GREEN_BACKGROUND + filme.get("title") + ChangeConsoleColor.RESET);
			System.out.println("\u001b[1mURL da Imagem: \u001b[m" + filme.get("image"));
			System.out.println("\u001b[1mDireção: \u001b[m" + filme.get("crew"));
			double rating = Double.parseDouble(filme.get("imDbRating"));
			int stars = (int) rating;
			for (int n = 1; n <= stars; n++) {
				System.out.print("\u2605");
			}
			System.out.println();
			System.out.println();
		}
	}
}
