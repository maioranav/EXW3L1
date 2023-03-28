package com.epicode.connector;

import com.epicode.model.*;
import org.checkerframework.checker.units.qual.A;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnection {
    private String url = "jdbc:postgresql://localhost:3309/";
    private String dbName = "EXW3L1";
    private String username = "EXW3L1";
    private String password = "root";
    Statement st;

    public DBConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url + dbName, username, password);
        st = conn.createStatement();
        creaTabStudenti();
    }

    public void creaTabStudenti() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS school_students (" +
                "    id SERIAL PRIMARY KEY," +
                "    name VARCHAR NOT NULL," +
                "    lastname VARCHAR NOT NULL," +
                "    gender VARCHAR NOT NULL," +
                "    avg INT DEFAULT '0'," +
                "    min_vote INT DEFAULT '0'," +
                "    max_vote INT DEFAULT '0'," +
                "    birthdate date NOT NULL)";
        this.st.executeUpdate(sql);
        System.out.println("Ho creato la tabella STUDENTI");
    }

    public void creaCliente(Cliente cl) throws SQLException {
        String sql = "INSERT INTO clienti (nome, cognome, datadinascita, regioneresidenza) " +
                "VALUES ('" + cl.getNome() + "', '" + cl.getCognome() + "', '" + cl.getDatadinascita() + "', '" + cl.getRegioneresidenza() + "')";
        this.st.executeUpdate(sql);
        System.out.println("Creato: " + cl.toString());
    }

    public void creaFornitore(Fornitore f) throws SQLException {
        String sql = "INSERT INTO fornitori (denominazione, regioneresidenza) " +
                "VALUES ('" + f.getDenominazione() + "', '" + f.getRegioneresidenza() + "')";
        this.st.executeUpdate(sql);
        System.out.println("Creato: " + f.toString());
    }

    public void findClienteByYear(Integer anno) throws SQLException {
        ArrayList<Cliente> clienti = new ArrayList<>();
        String sql = "SELECT c.id_cliente, c.nome, c.cognome, c.datadinascita, c.regioneresidenza FROM clienti c WHERE EXTRACT('Year' FROM datadinascita) = " + anno;
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) {
            int idcliente = rs.getInt("id_cliente");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            LocalDate datadinascita = rs.getDate("datadinascita").toLocalDate();
            String regione = rs.getString("regioneresidenza");
            Cliente cliente = new Cliente(idcliente, nome, cognome, datadinascita, regione);
            clienti.add(cliente);
        }
        if (clienti.size() > 0) {
            System.out.println("Ci sono " + clienti.size() + " clienti del " + anno);
            clienti.forEach(cliente -> System.out.println(cliente.toString()));
        } else {
            System.out.println("Non ci sono clienti del " + anno);
        }
    }

    public void findFattureIva(double iva) throws SQLException {
        ArrayList<Fattura> fatture = new ArrayList<>();
        String sql = "SELECT * FROM FATTURE WHERE iva = " + iva;
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("num_fattura");
            String tipo = rs.getString("tipologia");
            double importo = rs.getDouble("importo");
            LocalDate data = rs.getDate("datafattura").toLocalDate();
            double ivatab = rs.getDouble("iva");
            int idcliente = rs.getInt("id_cliente");
            int idfornitore = rs.getInt("id_fornitore");
            Fattura fattura = new Fattura(id, tipo, importo, ivatab, data, idcliente, idfornitore);
            fatture.add(fattura);
        }
        if (fatture.size() > 0) {
            System.out.println("Ci sono " + fatture.size() + " fatture con l'iva al " + iva * 100 + "%");
            fatture.forEach(fatt -> System.out.println(fatt.toString()));
        }
    }

    public void getFattureAnnue() throws SQLException {
        String sql = "SELECT DISTINCT SUM(importo) as importi, EXTRACT('Year' FROM datafattura) as anno " +
                "FROM FATTURE GROUP BY EXTRACT('Year' FROM datafattura)";
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) {
            double importi = rs.getDouble("importi");
            int anno = rs.getInt("anno");
            System.out.println("Nell'anno " + anno + " sono state emesse fatture per " + importi + "€.");
        }
    }

    public void getActivefromYear(int anno) throws SQLException {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        String sql = "SELECT * FROM prodotti WHERE EXTRACT('Year' from data_attiv) = " + anno + " " +
                "AND (in_prod = TRUE or in_comm = true)";
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id_prod");
            String desc = rs.getString("descrizione");
            boolean inprod = rs.getBoolean("in_prod");
            boolean incomm = rs.getBoolean("in_comm");
            LocalDate datattiv = rs.getDate("data_attiv").toLocalDate();
            Prodotto prod = new Prodotto(id, desc, inprod, incomm, datattiv);
            prodotti.add(prod);
        }
        if (prodotti.size() > 0) {
            System.out.println("Ci sono " + prodotti.size() + " prodotti attivi dal " + anno);
            prodotti.forEach(prodotto -> System.out.println(prodotto.toString()));
        } else {
            System.out.println("Non ci sono prodotti attivi dal " + anno);
        }
    }

    public void getFattureAnnueByIva(double iva) throws SQLException {
        String sql = "SELECT DISTINCT COUNT(num_fattura) as qty, " +
                "EXTRACT('Year' FROM datafattura) as anno FROM fatture " +
                "WHERE iva = " + iva + " GROUP BY EXTRACT('Year' FROM datafattura)";
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) {
            int qty = rs.getInt("qty");
            int anno = rs.getInt("anno");
            System.out.println("Nell'anno " + anno + " sono state emesse " + qty + " fatture con iva al " + iva * 100 + "%.");
        }
    }

    public void morethanInvoices(String tipo, int min) throws SQLException {
        String sql = "SELECT DISTINCT COUNT(num_fattura) as fattno, " +
                "EXTRACT('Year' FROM datafattura) as anno " +
                "FROM FATTURE WHERE tipologia = '" + tipo + "' " +
                "GROUP BY EXTRACT('Year' FROM datafattura) " +
                "HAVING COUNT(tipologia) > " + min;
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) {
            int qty = rs.getInt("fattno");
            int anno = rs.getInt("anno");
            System.out.println("Nell'anno " + anno + " sono state emesse " + qty + " fatture di tipo " + tipo + ".");
        }
    }

    public void fattIntestaione() throws SQLException {
        String sql = "SELECT fa.num_fattura, fa.importo, fa.datafattura, fo.denominazione FROM fatture fa INNER JOIN fornitori fo ON fo.id_fornitore = fa.id_fornitore";
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) {
            int idfatt = rs.getInt("num_fattura");
            double importo = rs.getDouble("importo");
            LocalDate data = rs.getDate("datafattura").toLocalDate();
            String nome = rs.getString("denominazione");
            System.out.println("ID=" + idfatt + "\t DENOM=" + nome + "\t\t IMPORTO=" + importo + "\t DEL=" + data);
        }
    }

    public void fattByRegione() throws SQLException {
        String sql = "SELECT SUM(fatture.importo) as importi, clienti.regioneresidenza FROM fatture INNER JOIN clienti ON fatture.id_cliente = clienti.id_cliente GROUP BY clienti.regioneresidenza";
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) {
            double importo = rs.getDouble("importi");
            String regioni = rs.getString("regioneresidenza");
            System.out.println("I clienti che vengono da " + regioni + " hanno fatture per " + importo + "€.");
        }
    }


    public void fattAltoEta(int importo, int anno) throws SQLException {
        String sql = "SELECT COUNT(c.id_cliente) as numclienti FROM fatture f INNER JOIN clienti c ON f.id_cliente = c.id_cliente  WHERE f.importo > " + importo + " AND EXTRACT('Year' FROM c.datadinascita) = " + anno;
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) {
            int numclienti = rs.getInt("numclienti");
            System.out.println(numclienti + " clienti del " + anno + " hanno fatture superiori a " + importo + "€.");
        }
    }

    public void abitantidi(String regione) throws SQLException {
        String sql = "SELECT CONCAT(nome, '-', cognome) as denominazione FROM clienti WHERE regioneresidenza = '" + regione + "'";
        ResultSet rs = this.st.executeQuery(sql);
        while (rs.next()) {
            String denominazione = rs.getString("denominazione");
            System.out.println(denominazione);
        }
    }


    public void insertStudent(Studente s) throws SQLException {
        String sql = "INSERT INTO school_students (name, lastname, gender, birthdate) " +
                "VALUES ('" + s.getName() + "', '" + s.getLastname() + "', '" + s.getGender() + "', '" + s.getBirthdate() + "')";
        this.st.executeUpdate(sql);
        System.out.println("Creato: " + s.toString());
    }

    public void updateStudent(int id, HashMap<String, Object> s) throws SQLException {
        String sql = "UPDATE school_students SET avg = " + s.get("avg") + " , min_vote = " + s.get("min_vote") + " , max_vote = " + s.get("max_vote") + " WHERE id = " + id;
        this.st.executeUpdate(sql);
        System.out.println("Aggiornato l'id " + id + ": " + s.toString());
    }

    public void deleteStudent(int id) throws SQLException {
        String sql1 = "SELECT * FROM school_students WHERE id = " + id;
        ResultSet rs = this.st.executeQuery(sql1);
        if (rs.next()) {
            String sql2 = "DELETE FROM school_students WHERE id = " + id;
            this.st.executeUpdate(sql2);
            System.out.println("Ho eliminato l'id " + id);
        } else {
            System.out.println("L'id " + id + " non esiste nel DB");
        }
    }


    public void getBest() throws SQLException {
        String sql = "SELECT * FROM school_students ORDER BY avg DESC LIMIT 1";
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String lastname = rs.getString("lastname");
            String gender = rs.getString("gender");
            LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
            int avg = rs.getInt("avg");
            int min_vote = rs.getInt("min_vote");
            int max_vote = rs.getInt("max_vote");
            System.out.println(new Studente(id, name, lastname, gender, birthdate, avg, min_vote, max_vote));
        }
    }

    public void getVoteRange(int min, int max) throws SQLException {
        List<Studente> studenti = new ArrayList<>();
        String sql = "SELECT * FROM school_students WHERE min_vote > " + (min - 1) + " AND max_vote < " + (max + 1) + " ORDER BY avg DESC";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String lastname = rs.getString("lastname");
            String gender = rs.getString("gender");
            LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
            int avg = rs.getInt("avg");
            int min_vote = rs.getInt("min_vote");
            int max_vote = rs.getInt("max_vote");
            Studente stud = new Studente(id, name, lastname, gender, birthdate, avg, min_vote, max_vote);
            studenti.add(stud);
        }
        System.out.println("Elenco studenti con voti compresi tra " + min + " e " + max);
        studenti.forEach(stud -> System.out.println(stud.toString()));
    }


}
