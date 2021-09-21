package siit.sevices.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import siit.model.Alimente.PerecheNumeInteger;
import siit.model.User;
import siit.sevices.User.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class SmsRequest {
    private static User user;
    private final String phoneNumber;
    private final String message;

    public SmsRequest(int days, int personsNumber, List<PerecheNumeInteger> perecheNumeIntegersTotal) {
        this.phoneNumber = "+4520777182";
        this.message = message(days, personsNumber, perecheNumeIntegersTotal);

    }

    private String message(int days, int personsNumber, List<PerecheNumeInteger> perecheNumeIntegers) {

        return "In urma analizei situatiei curente \n" +
                "s-au solicitat de catre pompier " + user.getName() +"\n" +
                "urmatoarele ajutoare alimentare\n" +
                 products(perecheNumeIntegers) +
                "pentru un numar de " + personsNumber +
                " persoane \n" +
                "pentru o perioada de " + daysFormat(days) + "\n" +
                "pentru detalii puteti folosi numarul " +
                 user.getPhoneNumber() + "\n" +
                "Data: " + date();

    }
    private String date(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(dateTimeFormatter);
    }


    private String products(List<PerecheNumeInteger> perecheNumeIntegers) {
        StringBuilder strb = new StringBuilder();
        for (PerecheNumeInteger pereche : perecheNumeIntegers) {
            strb.append("< "+pereche.getName()).append(" ").append(pereche.getValue()+" >").append("\n");
        }
        return strb.toString();
    }


    private String daysFormat(int days) {
        if (days == 1) {
            return " o zi ";
        } else {
            return (days + " zile ");
        }
    }


    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber=" + phoneNumber +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmsRequest that = (SmsRequest) o;
        return phoneNumber == that.phoneNumber && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, message);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SmsRequest.user = user;
    }
}
