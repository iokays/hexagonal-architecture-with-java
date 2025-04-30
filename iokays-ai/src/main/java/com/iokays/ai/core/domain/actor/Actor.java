package com.iokays.ai.core.domain.actor;

import com.iokays.common.domain.jpa.IdentifiedValueObject;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "t_actor")
public class Actor extends IdentifiedValueObject<Actor> {

    private String act;
    private String prompt;

    @Override
    public boolean sameValueAs(Actor other) {
        return Objects.equals(act, other.act)
                && Objects.equals(prompt, other.prompt);
    }
}