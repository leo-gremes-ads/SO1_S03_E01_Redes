package view;

import controller.*;
import javax.swing.JOptionPane;

public class Main
{
    public static void main(String[] args)
    {
        RedesController redes = new RedesController();
        int opc = 0;
        do {
            opc = menu();
            if (opc == 0)
                redes.ip();
            if (opc == 1)
                redes.ping();            
        } while (opc != 2);
    }

    public static int menu()
    {
        String[] opcs = {"IP", "Ping", "Sair"};
        return JOptionPane.showOptionDialog(
            null, "Selecione a opção desejada", "Redes",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
            opcs, opcs[2]);
    }
}
