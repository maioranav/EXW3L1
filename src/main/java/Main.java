import com.epicode.connector.DBConnection;
import com.epicode.model.Cliente;
import com.epicode.model.Fornitore;
import com.epicode.model.Studente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            System.out.println("\n\t SECONDA PARTE!!!  >  *** ESERCIZIO 1");
            Studente s1 = new Studente("Vincenzo", "Maiorana", "M", LocalDate.of(1993, 10, 01));
            //db.insertStudent(s1);

            System.out.println("\n\t SECONDA PARTE!!!  >  *** ESERCIZIO 2");
            HashMap<String, Object> voti = new HashMap<>();
            voti.put("avg", 7);
            voti.put("min_vote", 6);
            voti.put("max_vote", 7);
            db.updateStudent(1, voti);

            System.out.println("\n\t SECONDA PARTE!!!  >  *** ESERCIZIO 3");
            db.deleteStudent(5);

            System.out.println("\n\t SECONDA PARTE!!!  >  *** ESERCIZIO 4");
            db.getBest();

            System.out.println("\n\t SECONDA PARTE!!!  >  *** ESERCIZIO 5");
            db.getVoteRange(5, 7);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}