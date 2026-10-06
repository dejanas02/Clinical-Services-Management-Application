package thread;

import controller.ServerController;
import domain.Pacijent;
import domain.Racun;
import domain.Lekar;
import domain.StrucnaSprema;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import operacije.Operacije;
import static operacije.Operacije.LEKAR_GET_ALL;
import operacije.Status;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class ThreadClient extends Thread {

    private final Socket socket;

    public ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtev req = (KlijentskiZahtev) in.readObject();

                ServerskiOdgovor resp = handle(req);

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(resp);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor handle(KlijentskiZahtev request) {
        ServerskiOdgovor response = new ServerskiOdgovor(null, null, Status.Success);
        try {
            Operacije op = request.getOperacija();
            Object p = request.getParam();

            switch (op) {
                case LOGIN: {
                    Lekar creds = (Lekar) p;
                    Lekar ulogovani = ServerController.getInstance().login(creds);
                    response.setOdgovor(ulogovani);
                    break;
                }
                case LEKAR_GET_ALL:
                    response.setOdgovor(ServerController.getInstance().getAllLekar());
                    break;

                case PACIJENT_KREIRAJ:  ServerController.getInstance().kreirajPacijenta((Pacijent) p); break;
                case PACIJENT_PRETRAZI: response.setOdgovor(ServerController.getInstance().pretraziPacijente(p)); break;
                case PACIJENT_IZMENI:   ServerController.getInstance().izmeniPacijenta((Pacijent) p); break;
                case PACIJENT_OBRISI:   ServerController.getInstance().obrisiPacijenta((Pacijent) p); break;

                case RACUN_KREIRAJ: ServerController.getInstance().kreirajRacun((Racun) p); break;
                case RACUN_PRETRAZI:response.setOdgovor(ServerController.getInstance().pretraziRacune(p)); break;
                case RACUN_IZMENI:  ServerController.getInstance().izmeniRacun((Racun) p); break;

                case STRUCNA_SPREMA_UNESI: ServerController.getInstance().unesiStrucnuSpremu((StrucnaSprema) p); break;

                case USLUGA_PRETRAZI:response.setOdgovor(ServerController.getInstance().getAllUsluga()); break;
                case RACUN_GET_ONE: {
                    int id = (int) p;
                    response.setOdgovor(ServerController.getInstance().getRacunById(id));
                    break;
                    }
                case DRZAVLJANSTVO_GET_ALL:
                        response.setOdgovor(ServerController.getInstance().getAllDrzavljanstvo());
                        break;
                case LOGOUT:
                    Lekar ulogovani = (Lekar) request.getParam();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    throw new UnsupportedOperationException("Nepodrzana operacija: " + op);
            }
        } catch (Exception ex) {
            response.setResponseStatus(Status.Error);
            response.setExc(ex);
        }
        return response;
    }
}
