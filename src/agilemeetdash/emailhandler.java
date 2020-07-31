package agilemeetdash;

import com.sendgrid.*;
import java.io.IOException;

public class emailhandler {
  public static void sendmail(String emailto ) throws IOException {
	
	  
	String apikey = "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";
    Email from = new Email("jjsschiddarwar@gmail.com");
    String subject = "You have new task from VSOFT Agile Meeting Dashboard";
    Email to = new Email(emailto);
    Content content = new Content("text/plain", "<h3>Here is link to your new task</h3><p><a href='localhost:8080/agilemeetdash/'> Click here to see </a></p>");
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(apikey);
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      throw ex;
    }
  }
}