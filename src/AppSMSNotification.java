import api.SMSSender;
import api.TimService;
import api.VivoAdapter;
import api.VivoService;
import model.SMS;

public class AppSMSNotification {

	public static void main(String[] args) {
		SMSSender senderTIM = new TimService();
		SMSSender senderVIVO = new VivoAdapter(new VivoService());
		
		SMS message = new SMS("83988885544","83988221133","Bom dia. Seu boleto ja esta disponivel para pagamento");
		
		senderTIM.sendSMS(message);
		senderVIVO.sendSMS(message);

	}
}
