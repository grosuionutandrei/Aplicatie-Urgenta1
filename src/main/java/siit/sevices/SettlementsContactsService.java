package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.ContactDao;


@Service
public class SettlementsContactsService {
    @Autowired
    ContactDao contactDao;




}
