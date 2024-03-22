import java.util.ArrayList;
import java.util.Scanner;

public class SistemaBanco {
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    exibirExtrato();
                    break;
                case 3:
                    sacar();
                    break;
                case 4:
                    depositar();
                    break;
                case 5:
                    transferir();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Criar uma nova conta");
        System.out.println("2. Exibir o saldo ou extrato de uma conta");
        System.out.println("3. Sacar um valor de uma conta");
        System.out.println("4. Depositar um valor em uma conta");
        System.out.println("5. Transferir valores de uma conta para outra");
        System.out.println("Escolha uma opção:");
    }

    private static void criarConta() {
        System.out.println("\n=== Criar nova conta ===");
        System.out.println("Informe o número da conta:");
        int numeroConta = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        System.out.println("Informe o nome do titular:");
        String nomeTitular = scanner.nextLine();
        System.out.println("Informe o CPF do titular:");
        String cpfTitular = scanner.nextLine();
        System.out.println("Informe o saldo inicial da conta:");
        double saldoInicial = scanner.nextDouble();

        Titular titular = new Titular(nomeTitular, cpfTitular);
        Conta novaConta = new Conta(numeroConta, titular, saldoInicial);
        contas.add(novaConta);
        System.out.println("Conta criada com sucesso.");
    }

    private static void exibirExtrato() {
        System.out.println("\n=== Exibir extrato ===");
        System.out.println("Informe o número da conta:");
        int numeroConta = scanner.nextInt();
        Conta conta = buscarConta(numeroConta);
        if (conta != null) {
            System.out.println("Número da conta: " + conta.getNumero());
            System.out.println("Nome do titular: " + conta.getTitular().getNome());
            System.out.println("CPF do titular: " + conta.getTitular().getCpf());
            System.out.println("Saldo: " + conta.getSaldo());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void sacar() {
        System.out.println("\n=== Sacar ===");
        System.out.println("Informe o número da conta:");
        int numeroConta = scanner.nextInt();
        Conta conta = buscarConta(numeroConta);
        if (conta != null) {
            System.out.println("Informe o valor a sacar:");
            double valor = scanner.nextDouble();
            if (conta.sacar(valor)) {
                System.out.println("Saque efetuado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para realizar o saque.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void depositar() {
        System.out.println("\n=== Depositar ===");
        System.out.println("Informe o número da conta:");
        int numeroConta = scanner.nextInt();
        Conta conta = buscarConta(numeroConta);
        if (conta != null) {
            System.out.println("Informe o valor a depositar:");
            double valor = scanner.nextDouble();
            conta.depositar(valor);
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void transferir() {
        System.out.println("\n=== Transferir ===");
        System.out.println("Informe o número da conta de origem:");
        int numeroContaOrigem = scanner.nextInt();
        Conta contaOrigem = buscarConta(numeroContaOrigem);
        if (contaOrigem != null) {
            System.out.println("Informe o número da conta de destino:");
            int numeroContaDestino = scanner.nextInt();
            Conta contaDestino = buscarConta(numeroContaDestino);
            if (contaDestino != null) {
                System.out.println("Informe o valor a transferir:");
                double valor = scanner.nextDouble();
                if (contaOrigem.transferir(contaDestino, valor)) {
                    System.out.println("Transferência efetuada com sucesso.");
                } else {
                    System.out.println("Saldo insuficiente para realizar a transferência.");
                }
            } else {
                System.out.println("Conta de destino não encontrada.");
            }
        } else {
            System.out.println("Conta de origem não encontrada.");
        }
    }

    private static Conta buscarConta(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numeroConta) {
                return conta;
            }
        }
        return null;
    }
}
