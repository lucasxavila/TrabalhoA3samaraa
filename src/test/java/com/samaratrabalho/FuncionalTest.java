package com.samaratrabalho;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.banco.Agencia;
import com.banco.Conta;
import com.banco.ContaCorrente;
import com.banco.ContaPoupanca;
import com.banco.PessoaFisica;
import com.banco.PessoaJuridica;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta classe executa os casos de teste funcionais descritos no plano de testes.
 * Cada método simula um fluxo completo do usuário e verifica o resultado final.
 */
public class FuncionalTest {

    @Test
    @DisplayName("CTF-01: Cadastrar Pessoa Física e criar Conta Corrente")
    void testeFuncionalCadastroPessoaFisica() {
        System.out.println("--- EXECUTANDO CTF-01 ---");

        // Passos de Execução simulados
        System.out.println("1. Iniciar o programa.");
        System.out.println("2. Escolher a opção 'Pessoa Física'.");
        System.out.println("3. Informar nome e CPF.");
        PessoaFisica cliente = new PessoaFisica("Bastos", "123.456.789-00");
        
        System.out.println("4. Escolher criar 'Conta Corrente' e informar salário.");
        System.out.println("5. Escolher uma agência.");
        Agencia agencia = new Agencia(101);
        ContaCorrente cc = new ContaCorrente(54321, 3000.0, agencia);
        cliente.adicionarConta(cc);

        // Verificação do Resultado Esperado
        assertNotNull(cliente);
        assertEquals(1, cliente.getListaContas().size());
        assertEquals(54321, cliente.getListaContas().get(0).getNumero());
        assertEquals(101, cliente.getListaContas().get(0).getAgencia().getNumero());
        
        System.out.println(">> SUCESSO: O sistema confirmou o cadastro e a criação da conta, exibindo o número da conta e da agência.");
        System.out.println("--- FIM CTF-01 ---\n");
    }

    @Test
    @DisplayName("CTF-02: Realizar um depósito válido em uma conta")
    void testeFuncionalDepositoValido() {
        System.out.println("--- EXECUTANDO CTF-02 ---");

        // Pré-condição: Uma conta deve ter sido criada
        Agencia agencia = new Agencia(202);
        Conta conta = new ContaPoupanca(11111, 0.5, agencia);
        
        // Passos de Execução simulados
        System.out.println("1. Acessar uma conta.");
        System.out.println("2. Chamar o método depositar.");
        System.out.println("3. Informar um valor positivo (ex: 250.0).");
        conta.depositar(250.0);

        // Verificação do Resultado Esperado
        assertEquals(250.0, conta.getSaldo());

        System.out.println(">> SUCESSO: O saldo da conta foi atualizado para R$250.0");
        System.out.println("--- FIM CTF-02 ---\n");
    }

    @Test
    @DisplayName("CTF-03: Realizar um saque válido")
    void testeFuncionalSaqueValido() {
        System.out.println("--- EXECUTANDO CTF-03 ---");
        
        // Pré-condição: Conta com saldo (ex: R$250.0)
        Agencia agencia = new Agencia(303);
        Conta conta = new ContaCorrente(22222, 0, agencia);
        conta.depositar(250.0);

        // Passos de Execução simulados
        System.out.println("1. Acessar a conta com saldo.");
        System.out.println("2. Chamar o método sacar.");
        System.out.println("3. Informar valor menor que o saldo (ex: 100.0).");
        int resultado = conta.sacar(100.0);

        // Verificação do Resultado Esperado
        assertEquals(1, resultado, "O método deveria retornar 1 (sucesso).");
        assertEquals(150.0, conta.getSaldo(), "O novo saldo deveria ser R$150.0.");
        
        System.out.println(">> SUCESSO: Saque efetuado, método retornou 1 e novo saldo é R$150.0.");
        System.out.println("--- FIM CTF-03 ---\n");
    }

    @Test
    @DisplayName("CTF-04: Tentar saque com saldo insuficiente")
    void testeFuncionalSaqueInsuficiente() {
        System.out.println("--- EXECUTANDO CTF-04 ---");
        
        // Pré-condição: Conta com saldo (ex: R$150.0)
        Agencia agencia = new Agencia(101);
        Conta conta = new ContaCorrente(33333, 0, agencia);
        conta.depositar(150.0);
        
        // Passos de Execução simulados
        System.out.println("1. Acessar a conta com saldo.");
        System.out.println("2. Chamar o método sacar.");
        System.out.println("3. Informar valor maior que o saldo (ex: 200.0).");
        int resultado = conta.sacar(200.0);

        // Verificação do Resultado Esperado
        assertEquals(0, resultado, "O método deveria retornar 0 (falha).");
        assertEquals(150.0, conta.getSaldo(), "O saldo deveria permanecer R$150.0.");
        
        System.out.println(">> SUCESSO: Sistema retornou 0 (falha) e saldo permaneceu R$150.0.");
        System.out.println("--- FIM CTF-04 ---\n");
    }
    
    @Test
    @DisplayName("CTF-05: Cadastrar Pessoa Jurídica e duas contas")
    void testeFuncionalCadastroPessoaJuridica() {
        System.out.println("--- EXECUTANDO CTF-05 ---");
        
        // Passos de Execução simulados
        System.out.println("1. Iniciar programa e escolher 'Pessoa Jurídica'.");
        System.out.println("2. Informar nome e CNPJ.");
        PessoaJuridica cliente = new PessoaJuridica("Empresa Y", "98.765.432/0001-00");
        Agencia agencia = new Agencia(303);

        System.out.println("3. Criar uma Conta Corrente.");
        ContaCorrente cc = new ContaCorrente(98765, 10000.0, agencia);
        cliente.adicionarConta(cc);

        System.out.println("4. Criar uma Conta Poupança.");
        ContaPoupanca cp = new ContaPoupanca(98766, 0.05, agencia);
        cliente.adicionarConta(cp);

        // Verificação do Resultado Esperado
        assertEquals(2, cliente.getListaContas().size(), "A pessoa deveria ter duas contas em sua lista.");
        
        System.out.println(">> SUCESSO: O sistema criou ambas as contas e associou à pessoa jurídica.");
        System.out.println("--- FIM CTF-05 ---\n");
    }
}