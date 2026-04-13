package com.samaratrabalho;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.banco.Agencia;
import com.banco.Conta;
import com.banco.ContaCorrente;
import com.banco.PessoaFisica;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de testes unitários para o sistema bancário.
 * Cobre as funcionalidades das classes Conta, Pessoa e suas subclasses.
 */
class BancoTest {

    private Agencia agencia;
    private Conta conta;

    // Este método é executado antes de CADA teste.
    // Garante que cada teste comece com um objeto "limpo".
    @BeforeEach
    void setUp() {
        agencia = new Agencia(101);
        // Usamos ContaCorrente como uma implementação concreta para testar a lógica de Conta
        conta = new ContaCorrente(12345, 5000.0, agencia);
    }

    @Test
    @DisplayName("Caso 1: Deve depositar um valor positivo e atualizar o saldo")
    void testeDepositoValorPositivo() {
        // Ação
        conta.depositar(250.75);
        
        // Verificação
        assertEquals(250.75, conta.getSaldo(), "O saldo deveria ser atualizado após o depósito.");
    }

    @Test
    @DisplayName("Caso 2: Deve realizar um saque com saldo suficiente")
    void testeSaqueComSaldoSuficiente() {
        // Preparação
        conta.depositar(1000.0);

        // Ação
        int resultado = conta.sacar(400.0);

        // Verificação
        assertEquals(1, resultado, "O método sacar deveria retornar 1 para sucesso.");
        assertEquals(600.0, conta.getSaldo(), "O saldo deveria ser subtraído corretamente após o saque.");
    }

    @Test
    @DisplayName("Caso 3: Deve impedir saque com saldo insuficiente")
    void testeSaqueComSaldoInsuficiente() {
        // Preparação
        conta.depositar(300.0);

        // Ação
        int resultado = conta.sacar(300.01);

        // Verificação
        assertEquals(0, resultado, "O método sacar deveria retornar 0 para falha.");
        assertEquals(300.0, conta.getSaldo(), "O saldo não deveria ser alterado em uma tentativa de saque inválida.");
    }

    @Test
    @DisplayName("Caso 4: Deve criar uma Pessoa Física com dados corretos")
    void testeCriacaoPessoaFisicaComDadosCorretos() {
        // Ação
        PessoaFisica cliente = new PessoaFisica("Bastos Junior", "123.456.789-00");
        
        // Verificação
        assertNotNull(cliente);
        assertEquals("Bastos Junior", cliente.getNome());
        assertEquals("123.456.789-00", cliente.getCpf());
        assertTrue(cliente.getListaContas().isEmpty());
    }
    
    @Test
    @DisplayName("Caso 5: Deve criar uma Conta Corrente com dados corretos")
    void testeCriacaoContaCorrenteComDadosCorretos() {
        // Ação (o objeto 'conta' já foi criado no método setUp)
        
        // Verificação
        assertEquals(12345, conta.getNumero());
        assertEquals(0.0, conta.getSaldo(), "Uma nova conta deve iniciar com saldo zero.");
        assertNotNull(conta.getAgencia());
        assertEquals(101, conta.getAgencia().getNumero());
        
        // Verificação específica da ContaCorrente
        // Para isso, precisamos fazer um "cast" do objeto
        if (conta instanceof ContaCorrente) {
            ContaCorrente cc = (ContaCorrente) conta;
            assertEquals(5000.0, cc.getSalario());
        } else {
            fail("O objeto de teste não é uma ContaCorrente.");
        }
    }

    @Test
    @DisplayName("Caso 6: Não deve alterar o saldo ao tentar depositar valor negativo")
    void testeDepositoComValorNegativoNaoDeveAlterarSaldo() {
        // Ação
        conta.depositar(-150.0);
        
        // Verificação
        assertEquals(0.0, conta.getSaldo(), "O saldo não deve ser alterado ao tentar depositar um valor negativo.");
    }
}