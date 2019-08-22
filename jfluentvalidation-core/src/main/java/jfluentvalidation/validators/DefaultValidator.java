package jfluentvalidation.validators;

import com.google.common.base.Splitter;
import jfluentvalidation.SerializableFunction;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.core.*;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.rules.*;
import net.jodah.typetools.TypeResolver;

import javax.validation.ClockProvider;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.time.*;
import java.util.*;
import java.util.function.Predicate;

//TODO: check these out
//import javax.validation.Constraint;
//import javax.validation.ConstraintValidator;

// TODO: how to encapsulate type/proxy/propertyName/Subject?
// stackify overcoming type erasure

// TODO: should cache bytebuddy proxies either via a hashmap or bytebuddy TypeCache

// QUESTION: FluentValidator has an AbstractValidator and then an InlineValidator while we rolled it into one DefaultValidator
// Does this matter? Is there a trade-off? Advantages/disadvantages between the two?
// I guess it allows you to do the following which is cool
//    var validator = new InlineValidator<TestObject> {
//        v => v.RuleFor(x => x.SomeProperty).NotNull()
//    };
//but I think we can already do this will double brace initialization
//    Validator<Person> v = new DefaultValidator<Person>() {{
//        ruleForString(p -> p.getName()).isNotEmpty().startsWith("s").length(0, 4);
//    }};
public class DefaultValidator<T> implements Validator<T> {

    // TODO: I would prefer to not include guava so lets create our own splitter
    private static final Splitter RULESET_SPLITTER = Splitter.on(',').omitEmptyStrings().trimResults();

    private T proxy; // source
    private Class<T> type;
    private RuleCollection<T> rules = new RuleCollection<>();
    private ClockProvider clockProvider = ValidatorOptions.CLOCK_PROVIDER;
    private FailureMode failureMode = FailureMode.CONTINUE;
    private Duration temporalValidationTolerance = ValidatorOptions.TEMPORAL_VALIDATION_TOLERANCE;

    public ClockProvider getClockProvider() {
        return clockProvider;
    }

    public void setClockProvider(ClockProvider clockProvider) {
        this.clockProvider = clockProvider;
    }

    public FailureMode getFailureMode() {
        return failureMode;
    }

    public void setFailureMode(FailureMode failureMode) {
        this.failureMode = failureMode;
    }

    public Duration getTemporalValidationTolerance() {
        return temporalValidationTolerance;
    }

    public void setTemporalValidationTolerance(Duration temporalValidationTolerance) {
        this.temporalValidationTolerance = temporalValidationTolerance;
    }

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
     * @param clazz  The class of the instance to validate
     */
    public DefaultValidator(Class<T> clazz) {
        this.type = clazz;
        //this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
    }

    /**
     *
     * @param clazz The class of the instance to validate
     * @param <T> The type of the instance to validate
     * @return
     */
    public static <T> DefaultValidator<T> forClass(Class<T> clazz) {
        return new DefaultValidator<>(clazz);
    }

    /**
     *
     */
    protected DefaultValidator() {
        // TODO: can we remove dependency on typetools and roll this ourselves?
        this.type = (Class<T>) TypeResolver.resolveRawArguments(DefaultValidator.class, getClass())[0];
    }

    private <P> PropertyRule<T, P> addNewPropertyRule(SerializableFunction<T, P> propertyFunc) {
        RuleOptions ruleOptions = new RuleOptions();
        ruleOptions.setClockProvider(clockProvider);
        ruleOptions.setFailureMode(failureMode);
        ruleOptions.setTemporalValidationTolerance(temporalValidationTolerance);

        PropertyRule<T, P> rule = new PropertyRule<>(type, propertyFunc, ruleOptions);
        rules.add(rule);
        return rule;
    }

    private <P, E> CollectionPropertyRule<T, P, E> addNewCollectionPropertyRule(SerializableFunction<T, P> propertyFunc) {
        RuleOptions ruleOptions = new RuleOptions();
        ruleOptions.setClockProvider(clockProvider);
        ruleOptions.setFailureMode(failureMode);
        ruleOptions.setTemporalValidationTolerance(temporalValidationTolerance);

        CollectionPropertyRule<T, P, E> rule = new CollectionPropertyRule<>(type, propertyFunc, ruleOptions);
        rules.add(rule);
        return rule;
    }

    private <K, V> MapPropertyRule<T, K, V> addNewMapPropertyRule(SerializableFunction<T, Map<K, V>> propertyFunc) {
        RuleOptions ruleOptions = new RuleOptions();
        ruleOptions.setClockProvider(clockProvider);
        ruleOptions.setFailureMode(failureMode);
        ruleOptions.setTemporalValidationTolerance(temporalValidationTolerance);

        MapPropertyRule<T, K, V> rule = new MapPropertyRule<>(type, propertyFunc, ruleOptions);
        rules.add(rule);
        return rule;
    }

    private IncludeRule<T> addNewIncludeRule(Validator<T> validator) {
        RuleOptions ruleOptions = new RuleOptions();
        ruleOptions.setClockProvider(clockProvider);
        ruleOptions.setFailureMode(failureMode);
        ruleOptions.setTemporalValidationTolerance(temporalValidationTolerance);

        IncludeRule<T> rule = new IncludeRule<>(validator, ruleOptions);
        rules.add(rule);
        return rule;
    }


    /**
     *
     * @param func The Function representing the property to validate
     * @return the created subject.
     */
    public BigDecimalSubject<T> ruleForBigDecimal(SerializableFunction<T, BigDecimal> func) {
        PropertyRule<T, BigDecimal> rule = addNewPropertyRule(func);
        return new BigDecimalSubject<>(rule);
    }

    /**
     *
     * @param func The Function representing the property to validate
     * @return the created subject.
     */
    public BigIntegerSubject<T> ruleForBigInteger(SerializableFunction<T, BigInteger> func) {
        PropertyRule<T, BigInteger> rule = addNewPropertyRule(func);
        return new BigIntegerSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public BooleanSubject<T> ruleForBoolean(SerializableFunction<T, Boolean> func) {
        PropertyRule<T, Boolean> rule = addNewPropertyRule(func);
        return new BooleanSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public BooleanArraySubject<T> ruleForBooleanArray(SerializableFunction<T, boolean[]> func) {
        PropertyRule<T, boolean[]> rule = addNewPropertyRule(func);
        return new BooleanArraySubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public ByteSubject<T> ruleForByte(SerializableFunction<T, Byte> func) {
        PropertyRule<T, Byte> rule = addNewPropertyRule(func);
        return new ByteSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public ByteArraySubject<T> ruleForByteArray(SerializableFunction<T, byte[]> func) {
        PropertyRule<T, byte[]> rule = addNewPropertyRule(func);
        return new ByteArraySubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public CalendarSubject<T> ruleForCalendar(SerializableFunction<T, Calendar> func) {
        PropertyRule<T, Calendar> rule = addNewPropertyRule(func);
        return new CalendarSubject<>(rule);
    }

    /**
     * Defines a validation rule for a specify property.
     *
     * @param func  Function representing the property to validate
     * @return the created subject.
     */
    public CharArraySubject<T> ruleForCharArray(SerializableFunction<T, char[]> func) {
        PropertyRule<T, char[]> rule = addNewPropertyRule(func);
        return new CharArraySubject<>(rule);
    }

    /**
     * Defines a validation rule for a specify property.
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public DateSubject<T> ruleForDate(SerializableFunction<T, Date> func) {
        PropertyRule<T, Date> rule = addNewPropertyRule(func);
        return new DateSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public DoubleSubject<T> ruleForDouble(SerializableFunction<T, Double> func) {
        PropertyRule<T, Double> rule = addNewPropertyRule(func);
        return new DoubleSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public DoubleArraySubject<T> ruleForDoubleArray(SerializableFunction<T, double[]> func) {
        PropertyRule<T, double[]> rule = addNewPropertyRule(func);
        return new DoubleArraySubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public FileSubject<T> ruleForFile(SerializableFunction<T, File> func) {
        PropertyRule<T, File> rule = addNewPropertyRule(func);
        return new FileSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public FloatSubject<T> ruleForFloat(SerializableFunction<T, Float> func) {
        PropertyRule<T, Float> rule = addNewPropertyRule(func);
        return new FloatSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public FloatArraySubject<T> ruleForFloatArray(SerializableFunction<T, float[]> func) {
        PropertyRule<T, float[]> rule = addNewPropertyRule(func);
        return new FloatArraySubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public InputStreamSubject<T> ruleForInputStream(SerializableFunction<T, InputStream> func) {
        PropertyRule<T, InputStream> rule = addNewPropertyRule(func);
        return new InputStreamSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public IntegerSubject<T> ruleForInteger(SerializableFunction<T, Integer> func) {
        PropertyRule<T, Integer> rule = addNewPropertyRule(func);
        return new IntegerSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public IntArraySubject<T> ruleForIntArray(SerializableFunction<T, int[]> func) {
        PropertyRule<T, int[]> rule = addNewPropertyRule(func);
        return new IntArraySubject<>(rule);
    }


    // TODO: how do we think we should implement a way to add constraints for each item in a collection?
    // One idea...

    // TODO: confirm this can support sub/super types
    /**
     *
     * @param func  The Function representing the property to validate
     * @param <E>
     * @return the created subject.
     */
    public <E> IterableSubject<T, E> ruleForIterable(SerializableFunction<T, Iterable<? super E>> func) {
        CollectionPropertyRule<T, Iterable<? super E>, E> iterablePropertyRule = addNewCollectionPropertyRule(func);
        return new IterableSubject<>(iterablePropertyRule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public LocalDateSubject<T> ruleForLocalDate(SerializableFunction<T, LocalDate> func) {
        PropertyRule<T, LocalDate> rule = addNewPropertyRule(func);
        return new LocalDateSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public LocalDateTimeSubject<T> ruleForLocalDateTime(SerializableFunction<T, LocalDateTime> func) {
        PropertyRule<T, LocalDateTime> rule = addNewPropertyRule(func);
        return new LocalDateTimeSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public LocalTimeSubject<T> ruleForLocalTime(SerializableFunction<T, LocalTime> func) {
        PropertyRule<T, LocalTime> rule = addNewPropertyRule(func);
        return new LocalTimeSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public LongSubject<T> ruleForLong(SerializableFunction<T, Long> func) {
        PropertyRule<T, Long> rule = addNewPropertyRule(func);
        return new LongSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public LongArraySubject<T> ruleForLongArray(SerializableFunction<T, long[]> func) {
        PropertyRule<T, long[]> rule = addNewPropertyRule(func);
        return new LongArraySubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @param <K>
     * @param <V>
     * @return the created subject.
     */
    public <K, V> MapSubject<T, K, V> ruleForMap(SerializableFunction<T, Map<K, V>> func) {
        MapPropertyRule<T, K, V> rule = addNewMapPropertyRule(func);
        return new MapSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public ObjectSubject<T> ruleForObject(SerializableFunction<T, Object> func) {
        PropertyRule<T, Object> rule = addNewPropertyRule(func);
        return new ObjectSubject<>(rule);
    }

    // TODO: try a ruleForArray that takes a generic

    /**
     * TODO: add a note this is does not work for primitives and to see other methods
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public <E> ObjectArraySubject<T, E> ruleForObjectArray(SerializableFunction<T, E[]> func) {
        CollectionPropertyRule<T, E[], E> arrayPropertyRule = addNewCollectionPropertyRule(func);
        return new ObjectArraySubject<>(arrayPropertyRule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public OffsetDateTimeSubject<T> ruleForOffsetDateTime(SerializableFunction<T, OffsetDateTime> func) {
        PropertyRule<T, OffsetDateTime> rule = addNewPropertyRule(func);
        return new OffsetDateTimeSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public OffsetTimeSubject<T> ruleForOffsetTime(SerializableFunction<T, OffsetTime> func) {
        PropertyRule<T, OffsetTime> rule = addNewPropertyRule(func);
        return new OffsetTimeSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public ShortSubject<T> ruleForShort(SerializableFunction<T, Short> func) {
        PropertyRule<T, Short> rule = addNewPropertyRule(func);
        return new ShortSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public ShortArraySubject<T> ruleForShortArray(SerializableFunction<T, short[]> func) {
        PropertyRule<T, short[]> rule = addNewPropertyRule(func);
        return new ShortArraySubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public StringSubject<T> ruleForString(SerializableFunction<T, String> func) {
        PropertyRule<T, String> rule = addNewPropertyRule(func);
        return new StringSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public AbstractCharSequenceSubject<?, T, ? extends CharSequence> ruleForStringBuilder(SerializableFunction<T, StringBuilder> func) {
        PropertyRule<T, StringBuilder> rule = addNewPropertyRule(func);
        return new CharSequenceSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public AbstractCharSequenceSubject<?, T, ? extends CharSequence> ruleForStringBuffer(SerializableFunction<T, StringBuffer> func) {
        PropertyRule<T, StringBuffer> rule = addNewPropertyRule(func);
        return new CharSequenceSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public UriSubject<T> ruleForUri(SerializableFunction<T, URI> func) {
        PropertyRule<T, URI> rule = addNewPropertyRule(func);
        return new UriSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public UrlSubject<T> ruleForUrl(SerializableFunction<T, URL> func) {
        PropertyRule<T, URL> rule = addNewPropertyRule(func);
        return new UrlSubject<>(rule);
    }

    /**
     *
     * @param func  The Function representing the property to validate
     * @return the created subject.
     */
    public ZonedDateTimeSubject<T> ruleForZonedDateTime(SerializableFunction<T, ZonedDateTime> func) {
        PropertyRule<T, ZonedDateTime> rule = addNewPropertyRule(func);
        return new ZonedDateTimeSubject<>(rule);
    }

//    TODO: include validator for a different type

    /**
     *
     * @param validator
     */
    public void include(Validator<T> validator) {
        rules.add(addNewIncludeRule(validator));
    }


//    Fluentvalidation AbstractValidator#228
//    When(x => x.Address != null, () => {
//        RuleFor(x => x.Address.Postcode).NotNull();
//        RuleFor(x => x.Address.Country.Name).NotNull().When(x => x.Address.Country != null);
//        RuleFor(x => x.Address.Line1).NotNull().When(x => x.Address.Line2 != null);
//    });
    /**
     * Defines a condition that applies to several rules
     * @param predicate  The condition that should apply to multiple rules
     * @param runnable  Action that encapsulates the rules.
     */
    public void when(Predicate<T> predicate, Runnable runnable) {
        List<Rule<T, ?>> rulesToUpdate = new ArrayList<>();
        rules.run(runnable, rulesToUpdate::add);

        // TODO: update each rule in rulesToUpdate with predicate
        // TODO: is there a way we can group these all under something instead of iterating through rules?
        // Is that even a good idea?
        for (Rule<T, ?> rule : rulesToUpdate) {
            rule.applyCondition(predicate, true);
        }
    }

//    unless(x => x.Address == null, () => {
//        RuleFor(x => x.Address.Postcode).NotNull();
//        RuleFor(x => x.Address.Country.Name).NotNull().When(x => x.Address.Country != null);
//        RuleFor(x => x.Address.Line1).NotNull().When(x => x.Address.Line2 != null);
//    });
    /**
     * Defines an inverse condition that applies to several rules
     * @param predicate  The condition that should be applied to multiple rules
     * @param runnable  Action that encapsulates the rules.
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
     * @param ruleSetName  The name of the ruleset.
     * @param runnable  Action that encapsulates the rules in the ruleset.
     */
    public void ruleSet(String ruleSetName, Runnable runnable) {
        // TODO: this sucks. How can we improve this?
        List<String> ruleSet = Collections.singletonList(ruleSetName);
        rules.run(runnable, (rule) -> rule.setRuleSet(ruleSet));
    }


    // TODO: do we need/want this many validate methods?
    // TODO: should this return ValidationResult or List<ValidationFailure>?
    // FluentValidator has IValidationRule return IEnumerable<ValidationFailure> while IValidator returns ValidationResult

    /**
     * Validates the specified instance
     * @param entity  The object to validate
     * @return A list of validation failures / A ValidationResult object containing any validation failures.
     */
    public List<ValidationFailure> validate(T entity) {
        return validate(new ValidationContext<>(entity));
    }

    /**
     * Validates the specified instance
     * @param entity  The object to validate
     * @param ruleSet  a ruleset when need to validate against.
     * @return A list of validation failures / A ValidationResult object containing any validation failures.
     */
    public List<ValidationFailure> validate(T entity, String ruleSet) {
        // TODO: will need to find an alternative if/when we get rid of guava
        return validate(entity, RULESET_SPLITTER.splitToList(ruleSet));
    }

    /**
     * Validates the specified instance
     * @param entity  The object to validate
     * @param ruleSet  a ruleset when need to validate against.
     * @return A list of validation failures / A ValidationResult object containing any validation failures.
     */
    public List<ValidationFailure> validate(T entity, List<String> ruleSet) {
        return validate(new ValidationContext<>(entity), ruleSet);
    }

    /**
     *
     * @param validationContext  Validation Context
     * @return A list of validation failures / A ValidationResult object containing any validation failures.
     */
    public List<ValidationFailure> validate(ValidationContext validationContext) {
        return validate(validationContext, RuleSet.DEFAULT_LIST);
    }

    /**
     * Performs validation and then throws an exception if validation fails.
     * @param validationContext  Validation Context
     * @param ruleSet  a ruleset when need to validate against.
     * @return A list of validation failures / A ValidationResult object containing any validation failures.
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
     * Validates the specified instance and then throws an exception if validation fails.
     * @param entity  The object to validate
     */
    public void validateAndThrow(T entity) {
        validateAndThrow(new ValidationContext<>(entity));
    }

    /**
     * Validates the specified instance and then throws an exception if validation fails.
     * @param entity  The object to validate
     * @param ruleSet  a ruleset when need to validate against.
     */
    public void validateAndThrow(T entity, String ruleSet) {
        validateAndThrow(entity, RULESET_SPLITTER.splitToList(ruleSet));
    }

    /**
     * Validates the specified instance and then throws an exception if validation fails.
     * @param entity  The object to validate
     * @param ruleSet  a ruleset when need to validate against.
     */
    public void validateAndThrow(T entity, List<String> ruleSet) {
        validateAndThrow(new ValidationContext<>(entity), ruleSet);
    }

}
