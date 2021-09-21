package siit.sevices;

import org.apache.coyote.ContainerThreadMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.ContactDao;
import siit.model.Contact;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    ContactDao contactDao;
    @Autowired
    CommuneSettlementsService communeSettlementsService;
    @Autowired
    HttpSession session;

    public List<Contact> viewContacts(String name) {
        return contactDao.getContacts(name);
    }

    public List<Contact> pompieriContacts(String name) {
        return contactDao.getContacts(name).stream().filter(e -> e.getRol().equals("pompier")).collect(Collectors.toList());
    }

    public List<Contact> primarieContacts(String name) {
        return contactDao.getContacts(name).stream().filter(e -> (!e.getRol().equals("pompier"))).collect(Collectors.toList());
    }

    public void addContact(String name, String phoneNumber, String rol,String settlement){
        contactDao.insertNewContact(name,phoneNumber,rol,settlement,session.getAttribute("selected_commune").toString());
    }
    public void deleteContact(int id){
        contactDao.deleteFormContacteUtile(id);
    }
}
