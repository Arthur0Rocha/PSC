public class Divisao extends Calculadora{
    
    public Divisao(double pv, double sv){
        super(pv, sv);
    }
    
    public void dividir(double pv,double sv){
        this.setPrimeiroValor(pv);
        this.setSegundoValor(sv);
    }
    
    public double resultado(){
        return this.getPrimeiroValor()/this.getSegundoValor();
    }
    
    @Override
    public String toString(){
    return "Resultado: "+this.resultado()+".\n";
  }
    
}
