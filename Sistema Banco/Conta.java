class Conta {
    private int numero;
    private Titular titular;
    private double saldo;

    public Conta(int numero, Titular titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public Titular getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public boolean transferir(Conta destino, double valor) {
        if (sacar(valor)) {
            destino.depositar(valor);
            return true;
        } else {
            return false;
        }
    }
}