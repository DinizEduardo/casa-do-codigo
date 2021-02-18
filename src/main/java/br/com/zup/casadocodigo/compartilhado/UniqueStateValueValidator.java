package br.com.zup.casadocodigo.compartilhado;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueStateValueValidator implements ConstraintValidator<UniqueStateValue, Object> {

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueStateValue params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery(
                "select 1 from " + klass.getName()
                        + " where " + domainAttribute
                        + " = :value and pais_id = :id");

        String nome = value.toString().split(":")[0];
        String id = value.toString().split(":")[1];

        query.setParameter("value", nome);
        query.setParameter("id", Integer.parseInt(id));

        List<?> list = query.getResultList();

        Assert.isTrue(list.size() <=1, "Foi encontrado mais de um " + klass + " com o atributo" + domainAttribute);

        return list.isEmpty();
    }
}
