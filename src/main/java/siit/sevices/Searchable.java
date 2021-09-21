package siit.sevices;

import siit.model.People;

import java.util.List;

public interface Searchable {
    List<People> search(String name);
}
