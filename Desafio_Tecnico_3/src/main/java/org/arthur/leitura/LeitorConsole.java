package org.arthur.leitura;

import java.util.Scanner;

public class LeitorConsole {

    private final Scanner scanner = new Scanner(System.in);

    public double lerDecimal() {
        return scanner.nextDouble();
    }

    public String lerLinha() {
        return scanner.nextLine();
    }

    public void fechar() {
        scanner.close();
    }
}
