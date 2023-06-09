import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoFilmes();


        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-12-12&end_date=2023-03-03";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();


        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // Exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extrairConteudos(json);

        var geradora = new GeradorDeImagens();
        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            var diretorio = new File("figurinhas/");
            diretorio.mkdir();

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "figurinhas/" + conteudo.titulo().replace(":","-") + ".png";
            
            geradora.cria(inputStream, nomeArquivo, conteudo.titulo());
            
            System.out.println(conteudo.titulo());
            System.out.println();
        }

    }
}
