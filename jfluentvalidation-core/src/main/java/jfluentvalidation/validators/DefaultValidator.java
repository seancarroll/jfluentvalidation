package jfluentvalidation.validators;

import com.google.common.base.Splitter;
import jfluentvalidation.ValidationException;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.core.*;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.rules.*;
import net.jodah.typetools.TypeResolver;

import java.time.*;
import java.util.*;
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
    private RuleSetCollection rules = new RuleSetCollection();

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


    public BooleanSubject ruleForBoolean(Function<T, Boolean> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        BooleanSubject subject = new BooleanSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    // TODO: ruleForByteArray

    public ByteSubject ruleForByte(Function<T, Byte> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        ByteSubject subject = new ByteSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public CalendarSubject ruleForCalendar(Function<T, Calendar> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        CalendarSubject subject = new CalendarSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public CharSequenceSubject<?, ? extends CharSequence> ruleForCharSequence(Function<T, CharSequence> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        StringSubject subject = new StringSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public DateSubject ruleForDate(Function<T, Date> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        DateSubject subject = new DateSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public DoubleSubject ruleForDouble(Function<T, Double> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        DoubleSubject subject = new DoubleSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    // TODO: ruleForFile

    public FloatSubject ruleForFloat(Function<T, Float> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        FloatSubject subject = new FloatSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    // TODO: ruleForInputStream

    // TODO: how to encapsulate type/proxy/propertyName/Subject?
    // stackify overcoming type erasure
    public IntegerSubject ruleForInteger(Function<T, Integer> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        IntegerSubject subject = new IntegerSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
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

    public LocalDateSubject ruleForLocalDate(Function<T, LocalDate> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        LocalDateSubject subject = new LocalDateSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public LocalDateTimeSubject ruleForLocalDateTime(Function<T, LocalDateTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        LocalDateTimeSubject subject = new LocalDateTimeSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public LocalTimeSubject ruleForLocalTime(Function<T, LocalTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        LocalTimeSubject subject = new LocalTimeSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public LongSubject ruleForLong(Function<T, Long> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        LongSubject subject = new LongSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public <K, V> MapSubject<K, V> ruleForMap(Function<T, Map<K, V>> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        MapSubject<K, V> subject = new MapSubject<>(func, propertyName);
        subjects.add(subject);
        rules.add(new MapPropertyRule<>(subject));
        return subject;
    }

    public ObjectSubject ruleForObject(Function<T, Object> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        ObjectSubject subject = new ObjectSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public OffsetDateTimeSubject ruleForOffsetDateTime(Function<T, OffsetDateTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        OffsetDateTimeSubject subject = new OffsetDateTimeSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public OffsetTimeSubject ruleForOffsetTime(Function<T, OffsetTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        OffsetTimeSubject subject = new OffsetTimeSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public ShortSubject ruleForShort(Function<T, Short> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        ShortSubject subject = new ShortSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    // TODO: should cache bytebuddy proxies either via a hashmap or bytebuddy TypeCache
    public StringSubject ruleForString(Function<T, String> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        StringSubject subject = new StringSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    // TODO: do we need a StringBuilderSubject?
    public CharSequenceSubject ruleForStringBuilder(Function<T, StringBuilder> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        StringSubject subject = new StringSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    // TODO: do we need a StringBufferSubject?
    public CharSequenceSubject ruleForStringBuffer(Function<T, StringBuffer> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        StringSubject subject = new StringSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    // TODO: UriSubject

    // TODO: UrlSubject

    public ZonedDateTimeSubject ruleForZonedDateTime(Function<T, ZonedDateTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        ZonedDateTimeSubject subject = new ZonedDateTimeSubject(func, propertyName);
        subjects.add(subject);
        rules.add(new PropertyRule<>(subject));
        return subject;
    }

    public void include(Validator<T> validator) {
        rules.add(new IncludeRule<>(validator));
    }


//    Fluentvalidation AbstractValidator#228
//    When(x => x.Address != null, () => {
//        RuleFor(x => x.Address.Postcode).NotNull();
//        RuleFor(x => x.Address.Country.Name).NotNull().When(x => x.Address.Country != null);
//        RuleFor(x => x.Address.Line1).NotNull().When(x => x.Address.Line2 != null);
//    });
    /**
     * Defines a condition that applies to several rules
     * @param predicate The condition that should apply to multiple rules
     * @param runnable Action that encapsulates the rules.
     */
    public void when(Predicate<T> predicate, Runnable runnable) {
        List<Rule<?, ?>> rulesToUpdate = new ArrayList<>();
        rules.registerItemAddedCallback(rulesToUpdate::add);
        runnable.run();
        rules.deregisterItemAddedCallback();

        // TODO: update each rule in rulesToUpdate with predicate
        // TODO: is there a way we can group these all under something instead of iterating through rules?
        // Is that even a good idea?
        for (Rule<?, ?> rule : rulesToUpdate) {
            System.out.println(rule);
        }
    }

//    unless(x => x.Address == null, () => {
//        RuleFor(x => x.Address.Postcode).NotNull();
//        RuleFor(x => x.Address.Country.Name).NotNull().When(x => x.Address.Country != null);
//        RuleFor(x => x.Address.Line1).NotNull().When(x => x.Address.Line2 != null);
//    });
    /**
     * Defines an inverse condition that applies to several rules
     * @param predicate The condition that should be applied to multiple rules
     * @param runnable Action that encapsulates the rules.
     */
    public void unless(Predicate<T> predicate, Runnable runnable) {
        // The `Unless` method is simply the opposite of `When`
        when(predicate.negate(), runnable);
    }

//    RuleSet("Names", () => {
//        RuleFor(x => x.Surname).NotEqual("foo");
//        RuleFor(x => x.Forename).NotEqual("foo");
//    });
    /**
     * Defines a RuleSet that can be used to group together several validators.
     * @param ruleSetName The name of the ruleset.
     * @param runnable Action that encapsulates the rules in the ruleset.
     */
    public void ruleSet(String ruleSetName, Runnable runnable) {
        // TODO: this sucks. How can we improve this?
        List<String> ruleSet = Arrays.asList(ruleSetName);
        rules.registerItemAddedCallback((rule) -> rule.setRuleSet(ruleSet));
        runnable.run();
        rules.deregisterItemAddedCallback();
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
        Ensure.notNull(validationContext, "Cannot pass null context to Validate.");

        // TODO: add preValidate method?

        Ensure.notNull(validationContext.getInstanceToValidate(), "Cannot pass null model to Validate");

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
