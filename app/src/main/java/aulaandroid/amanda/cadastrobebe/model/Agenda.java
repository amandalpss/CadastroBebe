package aulaandroid.amanda.cadastrobebe.model;

import java.util.Date;

/**
 * Created by amanda on 11/05/16.
 */
public class Agenda {
    

    private Integer id_compromisso;
    private String descricao;
    private Date data;
    private String horario;


    public Integer getId_compromisso() {return id_compromisso;}

    public void setId_compromisso(Integer id_compromisso) {this.id_compromisso = id_compromisso;}

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}

    public Date getData() {return data;}

    public void setData(Date data) {this.data = data;}

    public String getHorario() {return horario;}

    public void setHorario(String horario) {this.horario = horario;}
}
