public abstract class Calculadora {
    double primeiroValor;
    double segundoValor;

    public Calculadora(double pv, double sv){
        this.primeiroValor=pv;
        this.segundoValor=sv;
    }
    
    public double getPrimeiroValor() {
        return primeiroValor;
    }

    public void setPrimeiroValor(double pv) {
        this.primeiroValor = pv;
    }

    public double getSegundoValor() {
        return segundoValor;
    }

    public void setSegundoValor(double sv) {
        this.segundoValor = sv;
    }
    
}
