package siit.sevices.PeopleAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.PeopleDao;
import siit.model.*;
import siit.sevices.SettlementService;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleAdapterEditService {
    @Autowired
    PeopleDao peopleDao;
    @Autowired
    HttpSession session;
    @Autowired
    SettlementService settlementService;

    private List<People> allPeople() {
        return peopleDao.getPeoples(session.getAttribute("selected_commune").toString());
    }

    private List<IsolatedPeople> getIsolatedPeoplesEdit() {
        return peopleDao.getIsolatedPeople(session.getAttribute("selected_commune").toString());
    }

    private List<HandicapPeople> getHandicapPeopleEdit() {
        return peopleDao.getHandicapPeople(session.getAttribute("selected_commune").toString());
    }

    public PeopleAdapterEdit updatePeople(int id) {
        String birthDate = peopleDao.getBirthDate(id).toString();
        PeopleAdapterEdit adapterEdit = new PeopleAdapterEdit();
        Optional<HandicapPeople> people = this.getHandicapPeopleEdit().stream().filter(e -> e.getLocalnicId() == id).findFirst();
        Optional<IsolatedPeople> people2 = this.getIsolatedPeoplesEdit().stream().filter(e -> e.getLocalnicId() == id).findFirst();
        People people3 = this.allPeople().stream().filter(e -> e.getId() == id).findFirst().get();
        adapterEdit.setId(id);
        adapterEdit.setName(people3.getName());
        adapterEdit.setObservatii(people3.getObservatii());
        adapterEdit.setMijlocDeAcces(people2.orElse(new IsolatedPeople()).getMijlocDeAcces());
        adapterEdit.setTipHandicap(people.orElse(new HandicapPeople()).getType());
        adapterEdit.setBirthDate(birthDate);
        adapterEdit.setLocalitate(peopleDao.getSettlement(id));
        adapterEdit.setState(people3.getState());
        System.out.println(adapterEdit);
        return adapterEdit;
    }


    public PeopleAdapterEdit editPeople(
            int id, String name, String state, String observatii,
            String date, String settlement, String mijlocDeAcces, String handicap,
            String mijlocDeAccesEdit, String tipHandicapEdit, boolean isIzolat, boolean isHandicap
    ) {
        PeopleAdapterEdit adapterEdit = new PeopleAdapterEdit();
        adapterEdit.setId(id);
        adapterEdit.setName(name);
        adapterEdit.setState(state);
        adapterEdit.setObservatii(observatii);
        adapterEdit.setBirthDate(date);
        adapterEdit.setLocalitate(settlement);
        adapterEdit.setMijlocDeAcces(mijlocDeAcces);
        adapterEdit.setTipHandicap(handicap);
        if (isIzolat) {
            updateAdapterEditIzolat(adapterEdit, mijlocDeAccesEdit);
        }
        if (isHandicap) {
            updateAdapterEditHandicap(adapterEdit, tipHandicapEdit);
        }
        return adapterEdit;
    }

    public void updateDeleteInsert(PeopleAdapterEdit editedPeople, boolean isHandicap, boolean isIzolat, boolean isNotHandicap, boolean isNotIzolat) {
        this.editInsert(editedPeople, isHandicap, isIzolat);
        this.editDelete(editedPeople, isNotHandicap, isNotIzolat);
        this.editPeopleSettlement(editedPeople);
        peopleDao.editUpdatePeople(editedPeople);
    }

    private void editInsert(PeopleAdapterEdit editedPeople, boolean isHandicap, boolean isIzolat) {
        if (isHandicap) {
            this.editInsertIntoHandicap(editedPeople);
        } else if (isIzolat) {
            this.editInsertIntoIsolated(editedPeople);
        } else if (isHandicap && isIzolat) {
            this.editInsertIntoHandicap(editedPeople);
            this.editInsertIntoIsolated(editedPeople);
        }
    }

    private void editInsertIntoHandicap(PeopleAdapterEdit editedPeople) {
        peopleDao.insertIntoHandicap(editedPeople.getId(), editedPeople.getTipHandicap());
    }

    private void editInsertIntoIsolated(PeopleAdapterEdit editedPeople) {
        peopleDao.insertIntoIsolated(editedPeople.getId(), editedPeople.getMijlocDeAcces());
    }

    private void editDelete(PeopleAdapterEdit editedPeople, boolean isNotHandicap, boolean isNotIzolat) {
        if (isNotIzolat) {
            this.editDeleteFromIsolatedPeople(editedPeople);
        } else if (isNotHandicap) {
            this.editDeleteFromHandicapPeople(editedPeople);
        } else if (isNotIzolat && isNotHandicap) {
            this.editDeleteFromIsolatedPeople(editedPeople);
            this.editDeleteFromHandicapPeople(editedPeople);
        }
    }

    private void editDeleteFromIsolatedPeople(PeopleAdapterEdit editedPeople) {
        peopleDao.deleteFromIsolated(editedPeople.getId());
    }

    private void editDeleteFromHandicapPeople(PeopleAdapterEdit editedPeople) {
        peopleDao.deleteFromHandicap(editedPeople.getId());
    }


    private void updateAdapterEditIzolat(PeopleAdapterEdit adapterEdit, String mijlocDeAccesEdit) {
        adapterEdit.setMijlocDeAcces(mijlocDeAccesEdit);
    }

    private void updateAdapterEditHandicap(PeopleAdapterEdit adapterEdit, String tipHandicapEdit) {
        adapterEdit.setTipHandicap(tipHandicapEdit);
    }

    private void editPeopleSettlement(PeopleAdapterEdit adapterEdit) {
        int localitateId = settlementService.getSettlementId(adapterEdit.getLocalitate());
        peopleDao.editPeopleSettlement(localitateId, adapterEdit.getId());
    }

}
