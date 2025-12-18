package com.mycompany.main;

import com.mycompany.main.ui.HelpUI;
import com.mycompany.main.utils.Task;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task task = Task.HELP;
        do {
            System.out.println("작업을 선택하세요(종료: 99, 도움말: 0): ");
            int input = scanner.nextInt();
            task = Task.getOrdinal(input);
            switch (task) {
                case HELP -> HelpUI.displayHelp();
            }
        } while (task != Task.QUIT);
    }
}
