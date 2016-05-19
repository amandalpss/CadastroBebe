package aulaandroid.amanda.cadastrobebe.model;

import java.util.Date;

/**
 * Created by amanda on 07/03/16.
 */
public class Bebe {

        private int id_bebe;
        private String nome;
        private Double peso;
        private Date datanasc;
        private Double altura;
        private String sexo;
        private byte[] imagem;

        public Bebe() { super();
        }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPeso() {return peso; }

    public void setPeso(Double peso) {this.peso = peso;}

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Integer getId_bebe() {
        return id_bebe;
    }

    public void setId_bebe(Integer id_bebe) {
        this.id_bebe = id_bebe;
    }

    public String getSexo() {return sexo;}

    public void setSexo(String sexo) {this.sexo = sexo;}

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }
}
