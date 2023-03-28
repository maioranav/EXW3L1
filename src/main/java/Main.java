import com.epicode.connector.DBConnection;
import com.epicode.model.Cliente;
import com.epicode.model.Fornitore;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            DBConnection db = new DBConnection();
            System.out.println("\n\t *** ESERCIZIO 1");
            db.findClienteByYear(1980);  //crea una lista di utenti nati nel 1980

            System.out.println("\n\t *** ESERCIZIO 2");
            db.findFattureIva(0.2); //elenca le fatture con iva al 20%

            System.out.println("\n\t *** ESERCIZIO 3");
            db.getFattureAnnue();

            System.out.println("\n\t *** ESERCIZIO 4");
            db.getActivefromYear(2017);

            System.out.println("\n\t *** ESERCIZIO 5");
            db.getFattureAnnueByIva(0.20);

            System.out.println("\n\t *** ESERCIZIO 6");
            db.morethanInvoices("A", 2);

            System.out.println("\n\t *** ESERCIZIO 7");
            db.fattIntestaione();

            System.out.println("\n\t *** ESERCIZIO 8");
            db.fattByRegione();

            System.out.println("\n\t *** ESERCIZIO 9");
            db.fattAltoEta(50, 1980);

            System.out.println("\n\t *** ESERCIZIO 10");
            db.abitantidi("Lombardia");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}