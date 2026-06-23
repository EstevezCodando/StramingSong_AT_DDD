package EstevezAlvarez.StramingSong.shared.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected void registrarEvento(DomainEvent evento) {
        domainEvents.add(evento);
    }

    public List<DomainEvent> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void limparEventos() {
        domainEvents.clear();
    }
}
