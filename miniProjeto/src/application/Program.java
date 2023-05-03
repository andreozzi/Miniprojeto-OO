package application;

import entities.Admin;
import entities.Cliente;
import product.Passagem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyy");
        LocalDate d01 = LocalDate.parse("22/03/2023",fmt1);
        LocalDate d02 = LocalDate.parse("25/04/2023",fmt1);
        LocalDate d03 = LocalDate.parse("11/08/2023",fmt1);
        LocalDate d04 = LocalDate.parse("16/07/2023",fmt1);
        LocalDate d05 = LocalDate.parse("02/06/2023",fmt1);
        LocalDate d06 = LocalDate.parse("21/02/2023",fmt1);

        Admin admin = new Admin("Admin");
        admin.criarPassagem("BSB","SP",d01,001,500.00);
        admin.criarPassagem("RJ","MT",d02,002,500.00);
        admin.criarPassagem("GO","RS",d03,003,500.00);
        admin.criarPassagem("CE","SC",d04,004,500.00);
        admin.criarPassagem("PE","BA",d05,005,500.00);
        admin.criarPassagem("AL","SP",d06,006,500.00);

        System.out.println("Digite \"1\" para ser cliente ou \"2\" para ser administrador.");
        int i = sc.nextInt();
        sc.nextLine();
        if(i==1){
            System.out.println("Digite seu nome: ");
            String nomeCliente = sc.nextLine();
            System.out.println("Id: ");
            int idCliente = sc.nextInt();
            sc.nextLine();
            System.out.println("Usuario: ");
            String clienteUser = sc.nextLine();
            System.out.println("Email: ");
            String emailCliente = sc.nextLine();

            Cliente cliente = new Cliente(nomeCliente,emailCliente,clienteUser,idCliente);
            admin.mostrarPassagens();
            System.out.println("Deseja filtrar as passagens por data?(s/n)");
            char r = sc.next().charAt(0);
            sc.nextLine();
            if(r == 's'){
                System.out.println("Digite a data da passagem(DD/MM/YYY): ");
                String dataString = sc.nextLine();
                LocalDate dataVoo = LocalDate.parse(dataString,fmt1);
                admin.buscarPassagensData(dataVoo);
                System.out.println("Digite o id da passagem: ");
                int idEscolhido = sc.nextInt();
                sc.nextLine();
                admin.agendarPassagem(idEscolhido);

            }
            else {
                System.out.println("Digite o id da passagem: ");
                int idEscolhido = sc.nextInt();
                sc.nextLine();
                admin.agendarPassagem(idEscolhido);
            }
        }
        else {
            System.out.println("Voce deseja criar, alterar ou deletar uma passagem? Digite (c/a/d)");
            char resposta = sc.next().charAt(0);
            sc.nextLine();
            if(resposta == 'c'){
                while (true){
                    System.out.println("Digite os dados da passagem");
                    System.out.println("Data da passagem(DD/MM/YYYY):");
                    String dataString = sc.nextLine();
                    LocalDate dataVoo = LocalDate.parse(dataString,fmt1);
                    System.out.println("Local de Origem(sigla): ");
                    String localOrigem = sc.nextLine();
                    System.out.println("Local de Destino(sigla): ");
                    String localDestino = sc.nextLine();
                    System.out.println("Digite o Id da passagem: ");
                    int idPassagem = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Digite o valor da passagem: ");
                    double valorPassagem = sc.nextDouble();
                    admin.criarPassagem(localOrigem,localDestino,dataVoo,idPassagem,valorPassagem);
                    System.out.println("Deseja criar outra passagem?(s/n)");
                    char resp = sc.next().charAt(0);
                    sc.nextLine();
                    if(resp=='n'){
                        admin.mostrarPassagens();
                        break;
                    }
                }
            }
            else if (resposta == 'a') {
                while (true){
                    admin.mostrarPassagens();
                    System.out.println("Digite o id da passagem a ser alterada: ");
                    int idantigo = sc.nextInt();
                    System.out.println("Digite os novos dados:");
                    System.out.println("Data da passagem(DD/MM/YYYY):");
                    String dataString = sc.nextLine();
                    LocalDate dataVoo = LocalDate.parse(dataString,fmt1);
                    System.out.println("Local de Origem(sigla): ");
                    String localOrigem = sc.nextLine();
                    System.out.println("Local de Destino(sigla): ");
                    String localDestino = sc.nextLine();
                    System.out.println("Digite o Id da passagem: ");
                    int idPassagem = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Digite o valor da passagem: ");
                    double valorPassagem = sc.nextDouble();
                    admin.atualizarPassagem( idantigo,localOrigem,localDestino,dataVoo,idPassagem,valorPassagem);
                    System.out.println("Deseja alterar outra passagem?(s/n)");
                    char respos = sc.next().charAt(0);
                    sc.nextLine();
                    if(respos=='n'){
                        admin.mostrarPassagens();
                        break;
                    }
                    else if (resposta == 'd') {
                        while (true){
                            System.out.println("Digite o id da passagem a ser deletada: ");
                            int idDel = sc.nextInt();
                            sc.nextLine();
                            admin.deletarPassagem(idDel);
                            admin.mostrarPassagens();
                            System.out.println("Deseja deletar outra passagem?(s/n)");
                            char respostinha = sc.next().charAt(0);
                            sc.nextLine();
                            if(respostinha=='n'){
                                admin.mostrarPassagens();
                                break;
                            }
                        }
                    }
                }
            }

        }


       sc.close();
    }




}
