public interface IGereVendasController {

    void setModel(IGereVendasModel model);

    void setView(InterfGereVendasView view);

    /**
     * Metodo que trata do menu inicial.
     */
    void run();
}
