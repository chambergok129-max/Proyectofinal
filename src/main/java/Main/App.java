package Main;

import GUI.Login;

public class App {
    public static void main(String[] args) {
        //CajeroAutomatico cajero = new CajeroAutomatico("ATM-001", "Sucursal Lima Centro");
        //cajero.iniciar();
        Login log = new Login();
        log.setVisible(true);
    }
}
