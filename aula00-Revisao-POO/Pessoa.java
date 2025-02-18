public class Pessoa {
    private String nome;
    private int idade;
    private long cpf;

    public Pessoa(){
        this("",0,0);
    }
    
    public Pessoa(String nome, int idade, long cpf){
        this.nome = nome;
        this.idade = idade;
        his.cpf = cpf;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getIdade(){
        return this.idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }
    public long getCpf(){
        return this.cpf;
    }

    public void setCpf(long cpf){
        this.cpf = cpf;
    }


    @Override
    public toString(){
        return "Nome: " + getNome() + "Idade: " + getIdade() + "Cpf: " + getCpf();
    }


}