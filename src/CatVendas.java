import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.reflect.Array.get;

public class CatVendas {
    private List<String> vendas;

    public CatVendas() {
        this.vendas = new ArrayList<>();
    }

    public CatVendas(List<String> vendas) {
        this.setVendas(vendas);
    }

    public CatVendas(CatVendas cv){
        this.vendas=cv.getVendas();
    }

    public List<String> getVendas() {
        return new ArrayList<String>(this.vendas.stream()
                          .collect(Collectors.toList()));
    }

    public void setVendas(List<String> v) {
        this.vendas =v;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatVendas catVendas = (CatVendas) o;
        return vendas.equals(catVendas.vendas);
    }

    public int hashCode() {
        return Objects.hash(vendas);
    }

    public String toString() {
        return "CatVendas{" +
                "vendas=" + vendas +
                '}';
    }

    public CatVendas clone(){
        return new CatVendas(this);
    }

    public void addVenda(String s){
        //Venda v = new Venda(s);
        //if(v.validaVenda()) {
            this.vendas.add(s);
        //}
    }

}
