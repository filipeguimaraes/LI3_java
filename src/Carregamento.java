import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Carregamento, contém métodos para guardar e ler ficheiro de objetos
 *
 * @author Beatriz Rocha A84003
 * @author Filipe Guimarães A85308
 * @author Gonçalo Ferreira A84073
 */
public class Carregamento {

    /**
     * Método para escrever o estado atual da aplicação num ficheiro objeto
     * @param model estado atual
     * @param path Caminho do ficheiro
     */
    public static void escreverFicheiroOjeto(IGereVendasModel model, String path) throws IOException {
        File arq = new File(path);
        arq.delete();
        arq.createNewFile();
        ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
        objOutput.writeObject(model);
    }

    /**
     * Método para ler um ficheiro objeto
     * @param path Caminho do ficheiro
     */
    public static GereVendasModel lerFicheiroObjeto(String path) throws IOException, ClassNotFoundException {
        GereVendasModel  model = new GereVendasModel ();
        File arq = new File(path);
        if (arq.exists()) {
            ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
            model = (GereVendasModel )objInput.readObject();
            objInput.close();
        }
        return (model);
    }
}

