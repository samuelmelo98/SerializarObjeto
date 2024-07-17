package model;

import java.util.Objects;

public class Pessoa implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String sobreNome;

    public Pessoa(Integer id, String nome, String sobreNome) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
    }

    public Pessoa() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return id.equals(pessoa.id) &&
                nome.equals(pessoa.nome) &&
                sobreNome.equals(pessoa.sobreNome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobreNome);
    }
}
