import java.util.Scanner;
import java.util.InputMismatchException;

class Main {

    public static void main(String[] args) {
        
        Scanner ler = new Scanner(System.in);
        int num;
        double pv, sv;
        
        Historico historico = new Historico();
        Divisao divisao = new Divisao(0.0,0.0);
        Adicao adicao = new Adicao(0.0,0.0);
        Subtracao subtracao = new Subtracao(0.0,0.0);
        Multiplicacao multiplicacao = new Multiplicacao(0.0,0.0);
        
        do{
          
        do{
          
          while(true){
          try{
            System.out.println("""
                               O que deseja fazer?
                               
                               1 - Divisao;
                               2 - Adicao;
                               3 - Multiplicacao;
                               4 - Subtracao;
                               5 - Mostrar historico;
                               6 - Sair do programa.
                               """);
            
            num = ler.nextInt();
            break;
          }catch(InputMismatchException ex){
            System.out.println("Opção inválida, digite uma das opções dadas.");
            ler.next();
          }
        }
        }while((num>6)||(num<1));
        while(true){
          try{
        switch(num){
            case 1:
                System.out.println("Digite o primeiro valor: ");
                pv = ler.nextDouble();
                do{
                    System.out.println("Digite o segundo valor, diferente de zero: ");
                    sv = ler.nextDouble();
                }while(sv==0);
                divisao.dividir(pv, sv);
                System.out.println(divisao.toString());
                historico.setValores(historico.valores[1],historico.valores[2],historico.valores[3],historico.valores[4], divisao.resultado());
                break;
            case 2:
                System.out.println("Digite o primeiro valor para a soma: ");
                pv = ler.nextDouble();
                System.out.println("Digite o segundo valor: ");
                sv = ler.nextDouble();
                adicao.adicionar(pv, sv);
                System.out.println(adicao.toString());
                historico.setValores(historico.valores[1],historico.valores[2],historico.valores[3],historico.valores[4], adicao.resultado());
                break;
            case 3:
                System.out.println("Digite o primeiro valor para a multiplicação: ");
                pv = ler.nextDouble();
                System.out.println("Digite o segundo valor para a multiplicação: ");
                sv = ler.nextDouble();
                multiplicacao.multiplicar(pv, sv);
                System.out.println(multiplicacao.toString());
                historico.setValores(historico.valores[1],historico.valores[2],historico.valores[3],historico.valores[4], multiplicacao.resultado());
                break;
            case 4:
                System.out.println("Digite o primeiro valor para a subtração: ");
                pv = ler.nextDouble();
                System.out.println("Digite o segundo valor para a subtração: ");
                sv = ler.nextDouble();
                subtracao.subtrair(pv, sv);
                System.out.println(subtracao.toString());
                historico.setValores(historico.valores[1],historico.valores[2],historico.valores[3],historico.valores[4], subtracao.resultado());
                break;
            case 5:
                System.out.println(historico.toString());
                break;
            default:
                System.out.println("Você encerrou o programa com sucesso.");
                break;
        }break;
            }catch(InputMismatchException ex){
            System.out.println("Opção inválida, digite uma das opções dadas.");
            ler.next();
          }
        }
        }while(num<6);
    } 
}