public class Historico {
    double valores[] = new double[5];
    public Historico(){
        
    }

    public double[] getValores() {
        return this.valores;
    }

    public void setValores(double a,double b,double c,double d,double e) {
        this.valores[0]=a;
        this.valores[1]=b;
        this.valores[2]=c;
        this.valores[3]=d;
        this.valores[4]=e;
    }
    @Override
    public String toString(){
    return "\n1 - "+this.valores[0]+"\n2 - "+this.valores[1]+"\n3 - "+this.valores[2]+"\n4 - "+this.valores[3]+"\n5 - "+this.valores[4]+"\n";
  }
}
