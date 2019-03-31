package jfluentvalidation.validators;

import com.google.common.base.Splitter;
import jfluentvalidation.ValidationException;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.core.*;
import jfluentvalidation.rules.*;
import net.jodah.typetools.TypeResolver;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

//TODO: check these out
//import javax.validation.Constraint;
//import javax.validation.ConstraintValidator;

public class DefaultValidator<T> implements Validator<T> {

    // TODO: I would prefer to not include guava so lets create our own splitter
    private static final Splitter RULESET_SPLITTER = Splitter.on(',').omitEmptyStrings().trimResults();

    public T proxy; // source
    Class<T> type;

    // TODO: will likely need a wrapper/container around a subject
    private List<Subject<?, ?>> subjects = new ArrayList<>();
    private List<Rule<?, ?>> rules = new ArrayList<>();

    // QUESTION:  Should we wrap in a Locale aware interpolator? What does spring do?
    // private MessageInterpolator messageInterpolator = new ResourceBundleMessageInterpolator();

    // AggregateResourceBundleLocator, CachingResourceBundleLocator, DelegatingResourceBundleLocator, PlatformResourceBundleLocator
    // springs MessageSourceResourceBundleLocator
    // Spring validationMessageSource
    // private ResourceBundleLocator resourceBundleLocator = new ResourceBundleLocator();

    // private final Errors proxyErrors = new Errors();
    // private final Errors errors = new Errors();

    public DefaultValidator(Class<T> clazz) {
        this.type = clazz;
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
    }

    public static <T> DefaultValidator<T> forClass(Class<T> clazz) {
        return new DefaultValidator<>(clazz);
    }

    // TODO: I dislike this because it forces derived classes to call super
    // I'd love to come up with a way to not require derived class to remember to call super.
    // Could a factory or static factory help here? Other potential solutions?
    protected DefaultValidator() {
        this.type = (Class<T>) TypeResolver.resolveRawArguments(DefaultValidator.class, getClass())[0];
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
    }

    // TODO: should cache bytebuddy proxies either via a hashmap or bytebuddy TypeCache
    public StringSubject ruleForString(Function<T, String> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        StringSubject subject = new StringSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public CharSequenceSubject<?, ? extends CharSequence> ruleForCharSequence(Function<T, CharSequence> charSequence) {

        return null;
    }

    public CharSequenceSubject<?, ? extends StringBuilder> ruleForStringBuilder(Function<T, StringBuilder> stringBuilder) {
        return null;
    }

    public CharSequenceSubject<?, ? extends CharSequence> ruleForStringBuffer(Function<T, StringBuffer> stringBuffer) {
        return null;
    }

    // TODO: how to encapsulate type/proxy/propertyName/Subject?
    // stackify overcoming type erasure
    public IntegerSubject ruleForInteger(Function<T, Integer> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        IntegerSubject subject = new IntegerSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public BooleanSubject ruleForBoolean(Function<T, Boolean> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        BooleanSubject subject = new BooleanSubject(func, propertyName);
        subjects.add(subject);
        return subject;
    }

    public ZonedDateTimeSubject ruleForZonedDateTime(Function<T, ZonedDateTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        ZonedDateTimeSubject subject = new ZonedDateTimeSubject(func, propertyName);
        subjects.add(subject);
        return subject;
    }

    public ObjectSubject ruleForObject(Function<T, Object> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        ObjectSubject subject = new ObjectSubject(func, propertyName);
        subjects.add(subject);
        return subject;
    }

    public <K, V> MapSubject<K, V> ruleForMap(Function<T, Map<K, V>> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        MapSubject<K, V> subject = new MapSubject<>(func, propertyName);
        subjects.add(subject);
        rules.add(new MapPropertyRule<>(subject));
        return subject;
    }

    // TODO: how do we think we should implement a way to add constraints for each item in a collection?
    // One idea...
    public <R> IterableSubject<R> ruleForIterable(Function<T, Iterable<R>> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        IterableSubject<R> subject = new IterableSubject<>(func, propertyName);
        subjects.add(subject);
        rules.add(new IterablePropertyRule<>(subject));
        return subject;
    }

    public void include(Validator<T> validator) {
        rules.add(new IncludeRule<>(validator));
    }


    //    TODO: a when clause similar to
//    When(x => x.Address != null, () => {
//        RuleFor(x => x.Address.Postcode).NotNull();
//        RuleFor(x => x.Address.Country.Name).NotNull().When(x => x.Address.Country != null);
//        RuleFor(x => x.Address.Line1).NotNull().When(x => x.Address.Line2 != null);
//    });
    public void when(Predicate<T> predicate, Consumer<T> consumer) {

    }

//    unless(x => x.Address == null, () => {
//        RuleFor(x => x.Address.Postcode).NotNull();
//        RuleFor(x => x.Address.Country.Name).NotNull().When(x => x.Address.Country != null);
//        RuleFor(x => x.Address.Line1).NotNull().When(x => x.Address.Line2 != null);
//    });
    public void unless(Predicate<T> predicate, Consumer<T> consumer) {

    }

//    RuleSet("Names", () => {
//        RuleFor(x => x.Surname).NotEqual("foo");
//        RuleFor(x => x.Forename).NotEqual("foo");
//    });
    public void ruleSet(String ruleSetName, Runnable runnable) {
        // rules.add(new RunnableRule(runnable, Arrays.asList(ruleSetName)));
        runnable.run();
    }

    // TODO: do we need/want this many validate methods?
    // TODO: should this return ValidationResult or List<ValidationFailure>?
    // FluentValidator has IValidationRule return IEnumerable<ValidationFailure> while IValidator returns ValidationResult
    public List<ValidationFailure> validate(T entity) {
        return validate(new ValidationContext(entity));
    }

    public List<ValidationFailure> validate(ValidationContext validationContext) {
        return validate(validationContext, RuleSet.DEFAULT_LIST);
    }

    public List<ValidationFailure> validate(T entity, String ruleSet) {
        return validate(entity, RULESET_SPLITTER.splitToList(ruleSet));
    }

    public List<ValidationFailure> validate(T entity, List<String> ruleSet) {
        return validate(new ValidationContext<>(entity), ruleSet);
    }

    public List<ValidationFailure> validate(ValidationContext validationContext, List<String> ruleSet) {
        List<ValidationFailure> failures = new ArrayList();
        for (Rule<?, ?> rule : rules) {
            // TODO: move this logic to a better place
            if (ruleSet.isEmpty() || ruleSet.stream().anyMatch(rule.getRuleSet()::contains)) {
                failures.addAll(rule.validate(validationContext));
            }
        }

        return failures;
    }


    public void validateAndThrow(T entity) {
        validateAndThrow(new ValidationContext(entity));
    }

    public void validateAndThrow(ValidationContext validationContext) {
        validateAndThrow(validationContext, RuleSet.DEFAULT_LIST);
    }

    public void validateAndThrow(T entity, String ruleSet) {
        validateAndThrow(entity, RULESET_SPLITTER.splitToList(ruleSet));
    }

    public void validateAndThrow(T entity, List<String> ruleSet) {
        validateAndThrow(new ValidationContext<>(entity), ruleSet);
    }

    public void validateAndThrow(ValidationContext validationContext, List<String> ruleSet) {
        List<ValidationFailure> failures = new ArrayList();
        for (Rule<?, ?> rule : rules) {
            // TODO: move this logic to a better place
            if (ruleSet.isEmpty() || ruleSet.stream().anyMatch(rule.getRuleSet()::contains)) {
                failures.addAll(rule.validate(validationContext));
            }
        }

        if (!failures.isEmpty()) {
            throw new ValidationException(failures);
        }
    }

}
