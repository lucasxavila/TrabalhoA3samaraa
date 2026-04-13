package com.banco;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Aula {

    private static final int OPCAO_CADASTRAR_CLIENTE = 1;
    private static final int OPCAO_ACESSAR_CLIENTE = 2;
    private static final int OPCAO_SAIR = 3;

    private static final int OP_DEPOSITAR = 1;
    private static final int OP_SACAR = 2;
    private static final int OP_VER_SALDO = 3;
    private static final int OP_VOLTAR = 4;

    private static ArrayList<Pessoa> clientes = new ArrayList<>();
    private static ArrayList<Agencia> agencias = new ArrayList<>();

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        agencias.add(new Agencia(101));
        agencias.add(new Agencia(202));
        agencias.add(new Agencia(303));

        System.out.println("--- BEM-VINDO AO SISTEMA BANCÁRIO ---");

        while (true) {
            exibirMenuPrincipal();
            int opcao = lerOpcao(entrada);

            switch (opcao) {
                case OPCAO_CADASTRAR_CLIENTE:
                    cadastrarNovoCliente(entrada);
                    break;
                case OPCAO_ACESSAR_CLIENTE:
                    acessarClienteExistente(entrada);
                    break;
                case OPCAO_SAIR:
                    System.out.println("Obrigado por usar o sistema. Até logo!");
                    entrada.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Cadastrar Novo Cliente");
        System.out.println("2. Acessar Cliente Existente");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarNovoCliente(Scanner entrada) {
        System.out.println("\n--- Cadastro de Novo Cliente ---");
        Pessoa novoCliente = cadastrarCliente(entrada);
        if (novoCliente != null) {
            clientes.add(novoCliente);
            System.out.println(">> Cliente '" + novoCliente.getNome() + "' cadastrado com sucesso!");
        }
    }
    
    private static void acessarClienteExistente(Scanner entrada) {
        System.out.println("\n--- Acessar Cliente ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Por favor, cadastre um cliente primeiro.");
            return;
        }

        System.out.println("Clientes disponíveis:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ": " + clientes.get(i).getNome());
        }
        System.out.print("Escolha o número do cliente que deseja acessar: ");
        int escolha = lerOpcao(entrada) - 1;

        if (escolha >= 0 && escolha < clientes.size()) {
            Pessoa clienteSelecionado = clientes.get(escolha);
            menuDeOperacoes(entrada, clienteSelecionado);
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private static void menuDeOperacoes(Scanner entrada, Pessoa cliente) {
        while (true) {
            System.out.println("\n--- Menu de Operações para o Cliente: " + cliente.getNome() + " ---");
            if (cliente.getListaContas().isEmpty()) {
                System.out.println("Este cliente não possui contas. Vamos criar uma.");
                Agencia ag = selecionarAgencia(entrada);
                criarContasParaCliente(entrada, cliente, ag);
                // Se ainda não tiver contas, não há o que fazer.
                if (cliente.getListaContas().isEmpty()) continue;
            }
            
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Ver Saldo de uma conta");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = lerOpcao(entrada);

            if (opcao == OP_VOLTAR) break;
            if (opcao < 1 || opcao > 3) {
                System.out.println("Opção de operação inválida.");
                continue;
            }

            Conta contaSelecionada = selecionarConta(entrada, cliente);
            if (contaSelecionada == null) continue;

            switch (opcao) {
                case OP_DEPOSITAR:
                    System.out.print("Digite o valor para depósito: R$");
                    double valorDeposito = lerDouble(entrada);
                    contaSelecionada.depositar(valorDeposito);
                    System.out.println(">> Depósito realizado.");
                    break;
                case OP_SACAR:
                    System.out.print("Digite o valor para saque: R$");
                    double valorSaque = lerDouble(entrada);
                    int resultado = contaSelecionada.sacar(valorSaque);
                    if (resultado == 1) {
                        System.out.println(">> Saque de R$" + valorSaque + " realizado com sucesso.");
                    } else {
                        System.out.println(">> Falha no saque. Saldo insuficiente ou valor inválido.");
                    }
                    break;
                case OP_VER_SALDO:
                    System.out.println(">> O saldo da conta " + contaSelecionada.getNumero() + " é: R$" + contaSelecionada.getSaldo());
                    break;
            }
        }
    }

    private static Conta selecionarConta(Scanner entrada, Pessoa cliente) {
        if (cliente.getListaContas().size() == 1) {
            return cliente.getListaContas().get(0);
        }

        System.out.println("Selecione a conta para a operação:");
        for (int i = 0; i < cliente.getListaContas().size(); i++) {
            Conta c = cliente.getListaContas().get(i);
            System.out.print((i + 1) + ": Conta N. " + c.getNumero());
            c.imprimir(); // Mostra detalhes da conta (CC ou CP)
        }
        System.out.print("Escolha o número da conta: ");
        int escolhaConta = lerOpcao(entrada) - 1;

        if (escolhaConta >= 0 && escolhaConta < cliente.getListaContas().size()) {
            return cliente.getListaContas().get(escolhaConta);
        } else {
            System.out.println("Escolha de conta inválida.");
            return null;
        }
    }
    
    private static Agencia selecionarAgencia(Scanner entrada) {
        System.out.println("\nAgências disponíveis:");
        for (Agencia ag : agencias) {
            System.out.println("- Agência " + ag.getNumero());
        }
        
        while (true) {
            System.out.print("Informe o número da agência desejada: ");
            int numAgencia = lerOpcao(entrada);
            for (Agencia ag : agencias) {
                if (ag.getNumero() == numAgencia) {
                    return ag;
                }
            }
            System.out.println("Agência inválida. Tente novamente.");
        }
    }

    private static Pessoa cadastrarCliente(Scanner entrada) {
        System.out.println("A conta é para: \n 1: Pessoa Jurídica \n 2: Pessoa Física");
        System.out.print("Digite a opção: ");
        int opcao = lerOpcao(entrada);
        entrada.nextLine(); // Consome o "Enter" pendente

        System.out.print("\nInforme o nome completo da Pessoa: ");
        String nome = entrada.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Informe o CNPJ: ");
                String cnpj = entrada.next();
                return new PessoaJuridica(nome, cnpj);

            case 2:
                System.out.print("Informe o CPF: ");
                String cpf = entrada.next();
                return new PessoaFisica(nome, cpf);

            default:
                System.out.println("Opção inválida. Cadastro cancelado.");
                return null;
        }
    }

    private static void criarContasParaCliente(Scanner entrada, Pessoa cliente, Agencia agencia) {
        Random random = new Random();
        final int OPCAO_SIM = 1;

        System.out.print("\nDeseja criar conta corrente? (1-Sim / Outro número-Não): ");
        if (lerOpcao(entrada) == OPCAO_SIM) {
            System.out.print("Informe o salário: R$");
            double salario = lerDouble(entrada);
            int numConta = 10000 + random.nextInt(90000);
            
            ContaCorrente cc = new ContaCorrente(numConta, salario, agencia);
            cliente.adicionarConta(cc);
            System.out.println(">> Conta Corrente criada! Número: " + cc.getNumero());
        }

        System.out.print("Deseja criar conta poupança? (1-Sim / Outro número-Não): ");
        if (lerOpcao(entrada) == OPCAO_SIM) {
            System.out.print("Informe o rendimento (%): ");
            double rendimento = lerDouble(entrada);
            int numConta = 10000 + random.nextInt(90000);
            
            ContaPoupanca cp = new ContaPoupanca(numConta, rendimento, agencia);
            cliente.adicionarConta(cp);
            System.out.println(">> Conta Poupança criada! Número: " + cp.getNumero());
        }
    }

    // Funções auxiliares para ler entrada do usuário e evitar erros
    private static int lerOpcao(Scanner entrada) {
        try {
            return entrada.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Por favor, digite um número.");
            entrada.next(); // Limpa a entrada inválida
            return -1; // Retorna um valor inválido para forçar nova tentativa
        }
    }

    private static double lerDouble(Scanner entrada) {
        try {
            return entrada.nextDouble();
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Por favor, digite um número (ex: 5000.0).");
            entrada.next(); // Limpa a entrada inválida
            return -1;
        }
    }
}