package api;

import model.SMS;
import java.time.LocalDateTime;

public class VivoAdapter implements SMSSender {

    private VivoService vivoService;

    public VivoAdapter(VivoService vivoService) {
        this.vivoService = vivoService;
    }

    @Override
    public boolean sendSMS(SMS sms) {
        String[] msgs;
        int count;
        String origem = sms.getOrigem();
        String destino = sms.getDestino();
        LocalDateTime time = sms.getTimestamp();
        String texto = sms.getTexto();
        count = texto.length();
        int startIndex = 0;
        int i = 0;
        int numSubstrings = (int) Math.ceil((double) count / 10);
        msgs = new String[numSubstrings];

        while (startIndex < count) {
            int endIndex = Math.min(startIndex + 10, count);
            msgs[i] = texto.substring(startIndex, endIndex);

            startIndex += 10;
            i++;
        }



        try {
            vivoService.enviarSMS(origem, destino, time, msgs);
            return true;
        } catch (SMSException e) {
            return false;
        }
    }
}
