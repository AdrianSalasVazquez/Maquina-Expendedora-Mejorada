public class MaquinaExpendedoraMejorada {

    // El precio del billete
    private int precioBillete;
    // La cantidad de dinero que lleva metida el cliente actual
    private int balanceClienteActual;
    // El total de dinero almacenado en la maquina desde su ultimo vaciado
    private int totalDineroAcumulado;
    // El origen del billete
    private String estacionOrigen;
    // El destino del billete
    private String estacionDestino;
    // N�mero de billetes vendidos
    private int billetesVendidos;
    // Si se quiere o no maquina de premio
    private boolean maquinaPremio;
    // N�mero de billetes para vender
    private int billetesMaximos;
    // Hace la cuenta hasta 3
    private int cuentaTres;

    /**
     * Crea una maquina expendedora de billetes de tren con el 
     * precio del billete y el origen y destino dados. Se asume que el precio
     * del billete que se recibe es mayor que 0.
     */
    public MaquinaExpendedoraMejorada(int precioDelBillete, String origen, String destino, boolean maquinaDePremio, int numeroBilletesMaximos) {
        precioBillete = precioDelBillete;
        balanceClienteActual = 0;
        totalDineroAcumulado = 0;
        estacionOrigen = origen;
        estacionDestino = destino;
        billetesVendidos = 0;
        maquinaPremio = maquinaDePremio;
        billetesMaximos = numeroBilletesMaximos;
        cuentaTres = 0;
    }

    public int vaciarDineroDeLaMaquina() {
        int valorDevuelto;
        if(balanceClienteActual == 0) {
            int dineroVaciado;
            dineroVaciado = totalDineroAcumulado;
            totalDineroAcumulado = 0;
            valorDevuelto = dineroVaciado;
        }
        else {
            System.out.println("No se puede vaciar la m�quina ya que un cliente esta us�ndola.");
            valorDevuelto = -1;
        }
        return valorDevuelto;
    }

    public int getNumeroBilletesVendidos() {
        return billetesVendidos;
    }
    
    public void imprimeNumeroBilletesVendidos() {
        System.out.println("N�mero de billetes vendidos: " + billetesVendidos);
    }
    
    /**
     * Devuelve el precio del billete
     */
    public int getPrecioBillete() {
        return precioBillete;
    }

    /**
     * Devuelve la cantidad de dinero que el cliente actual lleva introducida
     */
    public int getBalanceClienteActual() {
        return balanceClienteActual;
    }

    /**
     * Simula la introduccion de dinero por parte del cliente actual
     */
    public void introducirDinero(int cantidadIntroducida) {
        if (billetesVendidos < billetesMaximos) {
            if (cantidadIntroducida > 0) {
                balanceClienteActual = balanceClienteActual + cantidadIntroducida;
            }
            else {
                System.out.println(cantidadIntroducida + " no es una cantidad de dinero valida.");
            }  
        }
        else {
            System.out.println("No se puede introducir dinero ya que no hay mas billtes disponibles.");
        }
    }

    /**
     * Imprime un billete para el cliente actual
     */
    public void imprimirBillete() {
        int cantidadDeDineroQueFalta;
        cantidadDeDineroQueFalta = precioBillete - balanceClienteActual;
        if (billetesVendidos < billetesMaximos) {
            if (cantidadDeDineroQueFalta <= 0) {        
                // Simula la impresion de un billete
                System.out.println("##################");
                System.out.println("# Billete de tren:");
                System.out.println("# De " + estacionOrigen + " a " + estacionDestino);
                System.out.println("# " + precioBillete + " euros.");
                System.out.println("##################");
                System.out.println();         
    
                // Actualiza el total de dinero acumulado en la maquina
                totalDineroAcumulado = totalDineroAcumulado + precioBillete;
                // Reduce el balance del cliente actual dejandole seguir utilizando la maquina
                balanceClienteActual = balanceClienteActual - precioBillete;
                // Aumenta el numero de billetes vendidos
                billetesVendidos = billetesVendidos + 1;
                
                if (maquinaPremio == true) {
                    cuentaTres = cuentaTres + 1;
                    if (cuentaTres == 3) {
                        double precioDescuento;
                        precioDescuento = (precioBillete*10.0)/100;
                        System.out.println("Tiene un descuento de " + precioDescuento + "� en cualquier comercio");
                        cuentaTres = 0;
                    }
                }
            }
            else {
                System.out.println("Necesitas introducir " + cantidadDeDineroQueFalta + " euros mas!");
    
            }
        }
        else {
            System.out.println("No hay billetes en la m�quina.");
        }
    }

    /**
     * Cancela la operacion de compra del cliente actual y le
     * devuelve al cliente el dinero que ha introducido hasta el momento
     */
    public int cancelarOperacionYDevolverDinero() {
        int cantidadDeDineroADevolver;
        cantidadDeDineroADevolver = balanceClienteActual;
        balanceClienteActual = 0;
        return cantidadDeDineroADevolver;
    } 
}
