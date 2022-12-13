public class Adicao extends Calculadora{
    
    public Adicao(double pv, double sv){
        super(pv, sv);
    }
    
    public void adicionar(double pv,double sv){
        this.setPrimeiroValor(pv);
        this.setSegundoValor(sv);
    }
    
    public double resultado(){
        return this.getPrimeiroValor()+this.getSegundoValor();
    }
    @Override
    public String toString(){
    return "Resultado: "+(this.resultado())+".\n";
  }
}
