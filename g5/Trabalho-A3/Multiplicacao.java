public class Multiplicacao extends Calculadora{
    
    public Multiplicacao(double pv, double sv){
        super(pv, sv);
    }
    
    public void multiplicar(double pv,double sv){
        this.setPrimeiroValor(pv);
        this.setSegundoValor(sv);
    }
    
    public double resultado(){
        return this.getPrimeiroValor()*this.getSegundoValor();
    }
    @Override
    public String toString(){
    return "Resultado: "+(this.resultado())+".\n";
  }
    
}
