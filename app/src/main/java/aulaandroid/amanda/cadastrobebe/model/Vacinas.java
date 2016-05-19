package aulaandroid.amanda.cadastrobebe.model;

/**
 * Created by amanda on 23/03/16.
 */
public class Vacinas {

    private int id_vacina;
    private String idade;
    private String vacinas;
    private String doses;
    private String doenca_evitada;
    private String realizada;

    public int getId_vacina() {
        return id_vacina;
    }

    public void setId_vacina(int id_vacina) {
        this.id_vacina = id_vacina;
    }

    public String getIdade() {return idade;}

    public void setIdade(String idade) {this.idade = idade;}

    public String getVacinas() {return vacinas;}

    public void setVacinas(String vacinas) {this.vacinas = vacinas;}

    public String getDoses() {return doses;}

    public void setDoses(String doses) {this.doses = doses;}

    public String getDoenca_evitada() {return doenca_evitada;}

    public void setDoenca_evitada(String doenca_evitada) {this.doenca_evitada = doenca_evitada;}

    public String getRealizada() {
        return realizada;
    }

    public void setRealizada(String realizada) {
        this.realizada = realizada;
    }
}
