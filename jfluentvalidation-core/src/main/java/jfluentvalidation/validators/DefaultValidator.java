package jfluentvalidation.validators;

import com.google.common.base.Splitter;
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

// QUESTION: FluentValidator has an AbstractValidator and then an InlineValidator while we rolled it into one DefaultValidator
// Does this matter? Is there a trade-off? Advantages/disadvantages between the two?
public class DefaultValidator<T> implements Validator<T> {

    // TODO: I would prefer to not include guava so lets create our own splitter
    private static final Splitter RULESET_SPLITTER = Splitter.on(',').omitEmptyStrings().trimResults();

    public T proxy; // source
    Class<T> type;

    // TODO: will likely need a wrapper/container around a subject
    private List<Subject<?, ?>> subjects = new ArrayList<>();
    private RuleCollection<T> rules = new RuleCollection<>();

    // QUESTION:  Should we wrap in a Locale aware interpolator? What does spring do?
    // private MessageInterpolator messageInterpolator = new ResourceBundleMessageInterpolator();

    // AggregateResourceBundleLocator, CachingResourceBundleLocator, DelegatingResourceBundleLocator, PlatformResourceBundleLocator
    // springs MessageSourceResourceBundleLocator
    // Spring validationMessageSource
    // private ResourceBundleLocator resourceBundleLocator = new ResourceBundleLocator();

    // private final Errors proxyErrors = new Errors();
    // private final Errors errors = new Errors();

    /**
     *
     * @param clazz
     */
    public DefaultValidator(Class<T> clazz) {
        this.type = clazz;
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
    }

    /**
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> DefaultValidator<T> forClass(Class<T> clazz) {
        return new DefaultValidator<>(clazz);
    }

    /**
     *
     */
    protected DefaultValidator() {
        this.type = (Class<T>) TypeResolver.resolveRawArguments(DefaultValidator.class, getClass())[0];
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
    }

    /**
     *
     * @param func
     * @return
     */
    public BooleanSubject ruleForBoolean(Function<T, Boolean> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Boolean> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new BooleanSubject(rule);
    }

    // TODO: ruleForByteArray

    /**
     *
     * @param func
     * @return
     */
    public ByteSubject ruleForByte(Function<T, Byte> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Byte> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ByteSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public CalendarSubject ruleForCalendar(Function<T, Calendar> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Calendar> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new CalendarSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public DateSubject ruleForDate(Function<T, Date> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Date> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new DateSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public DoubleSubject ruleForDouble(Function<T, Double> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Double> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new DoubleSubject(rule);
    }

    // TODO: ruleForFile

    public FloatSubject ruleForFloat(Function<T, Float> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Float> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new FloatSubject(rule);
    }

    // TODO: ruleForInputStream

    // TODO: how to encapsulate type/proxy/propertyName/Subject?
    // stackify overcoming type erasure

    /**
     *
     * @param func
     * @return
     */
    public IntegerSubject ruleForInteger(Function<T, Integer> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Integer> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new IntegerSubject(rule);
    }


    // TODO: how do we think we should implement a way to add constraints for each item in a collection?
    // One idea...

    /**
     *
     * @param func
     * @param <E>
     * @return
     */
    public <E> IterableSubject<E> ruleForIterable(Function<T, Iterable<E>> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        IterablePropertyRule<T, E> iterablePropertyRule = new IterablePropertyRule<>(func, propertyName);
        rules.add(iterablePropertyRule);
        return new IterableSubject(iterablePropertyRule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LocalDateSubject ruleForLocalDate(Function<T, LocalDate> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, LocalDate> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LocalDateSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LocalDateTimeSubject ruleForLocalDateTime(Function<T, LocalDateTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, LocalDateTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LocalDateTimeSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LocalTimeSubject ruleForLocalTime(Function<T, LocalTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, LocalTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LocalTimeSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LongSubject ruleForLong(Function<T, Long> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Long> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LongSubject(rule);
    }

    /**
     *
     * @param func
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> MapSubject<K, V> ruleForMap(Function<T, Map<K, V>> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        MapPropertyRule<T, K, V> rule = new MapPropertyRule<>(func, propertyName);
        rules.add(rule);
        return new MapSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ObjectSubject ruleForObject(Function<T, Object> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Object> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ObjectSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public OffsetDateTimeSubject ruleForOffsetDateTime(Function<T, OffsetDateTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, OffsetDateTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new OffsetDateTimeSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public OffsetTimeSubject ruleForOffsetTime(Function<T, OffsetTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, OffsetTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new OffsetTimeSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ShortSubject ruleForShort(Function<T, Short> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Short> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ShortSubject(rule);
    }

    // TODO: should cache bytebuddy proxies either via a hashmap or bytebuddy TypeCache
    /**
     *
     * @param func
     * @return
     */
    public StringSubject ruleForString(Function<T, String> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, String> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new StringSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public AbstractCharSequenceSubject<?, ? extends CharSequence> ruleForStringBuilder(Function<T, StringBuilder> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, StringBuilder> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new CharSequenceSubject(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public AbstractCharSequenceSubject<?, ? extends CharSequence> ruleForStringBuffer(Function<T, StringBuffer> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, StringBuffer> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new CharSequenceSubject(rule);
    }

    // TODO: UriSubject

    // TODO: UrlSubject

    /**
     *
     * @param func
     * @return
     */
    public ZonedDateTimeSubject ruleForZonedDateTime(Function<T, ZonedDateTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, ZonedDateTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ZonedDateTimeSubject(rule);
    }

    /**
     *
     * @param validator
     */
    public void include(Validator<T> validator) {
        rules.add(new IncludeRule<T>(validator));
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
        List<Rule<T, ?>> rulesToUpdate = new ArrayList<>();

        // QUESTION: would the following be a better way of doing this? Run is a terrible method name
        // rules.run(runnable, rulesToUpdate::add)
        // This would register the callback, run the runnable and then de-register

        rules.registerItemAddedCallback(rulesToUpdate::add);
        runnable.run();
        rules.deregisterItemAddedCallback();

        // TODO: update each rule in rulesToUpdate with predicate
        // TODO: is there a way we can group these all under something instead of iterating through rules?
        // Is that even a good idea?
        for (Rule<T, ?> rule : rulesToUpdate) {
            rule.applyCondition(predicate);
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

    /**
     *
     * @param entity
     * @return
     */
    public List<ValidationFailure> validate(T entity) {
        return validate(new ValidationContext<>(entity));
    }

    /**
     *
     * @param entity
     * @param ruleSet
     * @return
     */
    public List<ValidationFailure> validate(T entity, String ruleSet) {
        return validate(entity, RULESET_SPLITTER.splitToList(ruleSet));
    }

    /**
     *
     * @param entity
     * @param ruleSet
     * @return
     */
    public List<ValidationFailure> validate(T entity, List<String> ruleSet) {
        return validate(new ValidationContext<>(entity), ruleSet);
    }

    public List<ValidationFailure> validate(ValidationContext validationContext) {
        return validate(validationContext, RuleSet.DEFAULT_LIST);
    }

    /**
     * Performs validation and then throws an exception if validation fails.
     * @param validationContext
     * @param ruleSet a ruleset when need to validate against.
     * @return A list of validation failures A ValidationResult object containing any validation failures.
     */
    public List<ValidationFailure> validate(ValidationContext validationContext, List<String> ruleSet) {
        Ensure.notNull(validationContext, "Cannot pass null context to Validate.");

        // TODO: add preValidate method?

        Ensure.notNull(validationContext.getInstanceToValidate(), "Cannot pass null model to Validate");

        List<ValidationFailure> failures = new ArrayList<>();
        for (Rule<?, ?> rule : rules) {
            if (RuleSet.canExecute(ruleSet, rule)) {
                failures.addAll(rule.validate(validationContext));
            }
        }

        return failures;
    }

    /**
     *
     * @param entity
     */
    public void validateAndThrow(T entity) {
        validateAndThrow(new ValidationContext<>(entity));
    }

    /**
     *
     * @param entity
     * @param ruleSet
     */
    public void validateAndThrow(T entity, String ruleSet) {
        validateAndThrow(entity, RULESET_SPLITTER.splitToList(ruleSet));
    }

    /**
     *
     * @param entity
     * @param ruleSet
     */
    public void validateAndThrow(T entity, List<String> ruleSet) {
        validateAndThrow(new ValidationContext<>(entity), ruleSet);
    }

}
