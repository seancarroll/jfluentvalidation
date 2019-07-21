package jfluentvalidation.validators;

import com.google.common.base.Splitter;
import jfluentvalidation.PropertyNameExtractor;
import jfluentvalidation.SerializableFunction;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.core.*;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.rules.*;
import net.jodah.typetools.TypeResolver;

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
// Does this matter? Is there a trade-off? Advantages/disadvantages hasLengthBetween the two?
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
     * @param clazz The class of the instance to validate
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
        //this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
    }

    /**
     *
     * @param func The Function representing the property to validate
     * @return
     */
    public BigDecimalSubject<T> ruleForBigDecimal(SerializableFunction<T, BigDecimal> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, BigDecimal> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new BigDecimalSubject<>(rule);
    }

    /**
     *
     * @param func The Function representing the property to validate
     * @return
     */
    public BigIntegerSubject<T> ruleForBigInteger(SerializableFunction<T, BigInteger> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, BigInteger> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new BigIntegerSubject<>(rule);
    }

    /**
     *
     * @param func The Function representing the property to validate
     * @return
     */
    public BooleanSubject<T> ruleForBoolean(SerializableFunction<T, Boolean> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, Boolean> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new BooleanSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public BooleanArraySubject<T> ruleForBooleanArray(SerializableFunction<T, boolean[]> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, boolean[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new BooleanArraySubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ByteSubject<T> ruleForByte(SerializableFunction<T, Byte> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, Byte> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ByteSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ByteArraySubject<T> ruleForByteArray(SerializableFunction<T, byte[]> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, byte[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ByteArraySubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public CalendarSubject<T> ruleForCalendar(SerializableFunction<T, Calendar> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, Calendar> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new CalendarSubject<>(rule);
    }

    /**
     * Defines a validation rule for a specify property.
     *
     * @param func  Function representing the property to validate
     * @return
     */
    public CharArraySubject<T> ruleForCharArray(SerializableFunction<T, char[]> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, char[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new CharArraySubject<>(rule);
    }

    /**
     * Defines a validation rule for a specify property.
     *
     * @param func
     * @return
     */
    public DateSubject<T> ruleForDate(SerializableFunction<T, Date> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, Date> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new DateSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public DoubleSubject<T> ruleForDouble(SerializableFunction<T, Double> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, Double> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new DoubleSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public DoubleArraySubject<T> ruleForDoubleArray(SerializableFunction<T, double[]> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, double[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new DoubleArraySubject<>(rule);
    }

    public FileSubject<T> ruleForFile(SerializableFunction<T, File> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, File> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new FileSubject<>(rule);
    }

    public FloatSubject<T> ruleForFloat(SerializableFunction<T, Float> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, Float> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new FloatSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public FloatArraySubject<T> ruleForFloatArray(SerializableFunction<T, float[]> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, float[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new FloatArraySubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public InputStreamSubject<T> ruleForInputStream(SerializableFunction<T, InputStream> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, InputStream> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new InputStreamSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public IntegerSubject<T> ruleForInteger(SerializableFunction<T, Integer> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, Integer> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new IntegerSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public IntArraySubject<T> ruleForIntArray(SerializableFunction<T, int[]> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, int[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new IntArraySubject<>(rule);
    }


    // TODO: how do we think we should implement a way to add constraints for each item in a collection?
    // One idea...

    // TODO: confirm this can support sub/super types
    /**
     *
     * @param func
     * @param <E>
     * @return
     */
    public <E> IterableSubject<T, E> ruleForIterable(SerializableFunction<T, Iterable<? super E>> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        CollectionPropertyRule<T, Iterable<? super E>, E> iterablePropertyRule = new CollectionPropertyRule<>(func, propertyName);
        rules.add(iterablePropertyRule);
        return new IterableSubject<>(iterablePropertyRule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LocalDateSubject<T> ruleForLocalDate(SerializableFunction<T, LocalDate> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, LocalDate> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LocalDateSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LocalDateTimeSubject<T> ruleForLocalDateTime(SerializableFunction<T, LocalDateTime> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, LocalDateTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LocalDateTimeSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LocalTimeSubject<T> ruleForLocalTime(SerializableFunction<T, LocalTime> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, LocalTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LocalTimeSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LongSubject<T> ruleForLong(SerializableFunction<T, Long> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, Long> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LongSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LongArraySubject<T> ruleForLongArray(SerializableFunction<T, long[]> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, long[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LongArraySubject<>(rule);
    }

    /**
     *
     * @param func
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> MapSubject<T, K, V> ruleForMap(SerializableFunction<T, Map<K, V>> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        MapPropertyRule<T, K, V> rule = new MapPropertyRule<>(func, propertyName);
        rules.add(rule);
        return new MapSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ObjectSubject<T> ruleForObject(SerializableFunction<T, Object> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, Object> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ObjectSubject<>(rule);
    }

    // TODO: try a ruleForArray that takes a generic

    /**
     * TODO: add a note this is does not work for primitives and to see other methods
     * @param func
     * @return
     */
    public <E> ObjectArraySubject<T, E> ruleForObjectArray(SerializableFunction<T, E[]> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        CollectionPropertyRule<T, E[], E> arrayPropertyRule = new CollectionPropertyRule<>(func, propertyName);
        //PropertyRule<T, Object[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(arrayPropertyRule);
        return new ObjectArraySubject<>(arrayPropertyRule);
    }

    /**
     *
     * @param func
     * @return
     */
    public OffsetDateTimeSubject<T> ruleForOffsetDateTime(SerializableFunction<T, OffsetDateTime> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, OffsetDateTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new OffsetDateTimeSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public OffsetTimeSubject<T> ruleForOffsetTime(SerializableFunction<T, OffsetTime> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, OffsetTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new OffsetTimeSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ShortSubject<T> ruleForShort(SerializableFunction<T, Short> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, Short> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ShortSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ShortArraySubject<T> ruleForShortArray(SerializableFunction<T, short[]> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, short[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ShortArraySubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public StringSubject<T> ruleForString(SerializableFunction<T, String> func) {
        // String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, String> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new StringSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public AbstractCharSequenceSubject<?, T, ? extends CharSequence> ruleForStringBuilder(SerializableFunction<T, StringBuilder> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, StringBuilder> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new CharSequenceSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public AbstractCharSequenceSubject<?, T, ? extends CharSequence> ruleForStringBuffer(SerializableFunction<T, StringBuffer> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, StringBuffer> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new CharSequenceSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public UriSubject<T> ruleForUri(SerializableFunction<T, URI> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, URI> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new UriSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public UrlSubject<T> ruleForUrl(SerializableFunction<T, URL> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, URL> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new UrlSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ZonedDateTimeSubject<T> ruleForZonedDateTime(SerializableFunction<T, ZonedDateTime> func) {
        String propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, func);
        PropertyRule<T, ZonedDateTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ZonedDateTimeSubject<>(rule);
    }

//    TODO: include validator for a different type

    /**
     *
     * @param validator
     */
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
        rules.run(runnable, (rule) -> rule.setRuleSet(ruleSet));
    }


    // TODO: do we need/want this many validate methods?
    // TODO: should this return ValidationResult or List<ValidationFailure>?
    // FluentValidator has IValidationRule return IEnumerable<ValidationFailure> while IValidator returns ValidationResult

    /**
     * Validates the specified instance
     * @param entity The object to validate
     * @return A list of validation failures / A ValidationResult object containing any validation failures.
     */
    public List<ValidationFailure> validate(T entity) {
        return validate(new ValidationContext<>(entity));
    }

    /**
     * Validates the specified instance
     * @param entity The object to validate
     * @param ruleSet a ruleset when need to validate against.
     * @return A list of validation failures / A ValidationResult object containing any validation failures.
     */
    public List<ValidationFailure> validate(T entity, String ruleSet) {
        // TODO: will need to find an alternative if/when we get rid of guava
        return validate(entity, RULESET_SPLITTER.splitToList(ruleSet));
    }

    /**
     * Validates the specified instance
     * @param entity The object to validate
     * @param ruleSet a ruleset when need to validate against.
     * @return A list of validation failures / A ValidationResult object containing any validation failures.
     */
    public List<ValidationFailure> validate(T entity, List<String> ruleSet) {
        return validate(new ValidationContext<>(entity), ruleSet);
    }

    /**
     *
     * @param validationContext Validation Context
     * @return A list of validation failures / A ValidationResult object containing any validation failures.
     */
    public List<ValidationFailure> validate(ValidationContext validationContext) {
        return validate(validationContext, RuleSet.DEFAULT_LIST);
    }

    /**
     * Performs validation and then throws an exception if validation fails.
     * @param validationContext Validation Context
     * @param ruleSet a ruleset when need to validate against.
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
     * @param entity The object to validate
     */
    public void validateAndThrow(T entity) {
        validateAndThrow(new ValidationContext<>(entity));
    }

    /**
     * Validates the specified instance and then throws an exception if validation fails.
     * @param entity The object to validate
     * @param ruleSet a ruleset when need to validate against.
     */
    public void validateAndThrow(T entity, String ruleSet) {
        validateAndThrow(entity, RULESET_SPLITTER.splitToList(ruleSet));
    }

    /**
     * Validates the specified instance and then throws an exception if validation fails.
     * @param entity The object to validate
     * @param ruleSet a ruleset when need to validate against.
     */
    public void validateAndThrow(T entity, List<String> ruleSet) {
        validateAndThrow(new ValidationContext<>(entity), ruleSet);
    }

}
