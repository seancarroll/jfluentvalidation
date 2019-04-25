package jfluentvalidation.validators;

import com.google.common.base.Splitter;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.core.*;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.rules.*;
import net.jodah.typetools.TypeResolver;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
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
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
    }

    /**
     *
     * @param func The Function representing the property to validate
     * @return
     */
    public BooleanSubject<T> ruleForBoolean(Function<T, Boolean> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Boolean> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new BooleanSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ByteSubject<T> ruleForByte(Function<T, Byte> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Byte> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ByteSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ByteArraySubject<T> ruleForByteArray(Function<T, byte[]> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, byte[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ByteArraySubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public CalendarSubject<T> ruleForCalendar(Function<T, Calendar> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Calendar> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new CalendarSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public DateSubject<T> ruleForDate(Function<T, Date> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Date> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new DateSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public DoubleSubject<T> ruleForDouble(Function<T, Double> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Double> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new DoubleSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public DoubleArraySubject<T> ruleForDoubleArray(Function<T, double[]> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, double[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new DoubleArraySubject<>(rule);
    }

    public FileSubject<T> ruleForFile(Function<T, File> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, File> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new FileSubject<>(rule);
    }

    public FloatSubject<T> ruleForFloat(Function<T, Float> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Float> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new FloatSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public FloatArraySubject<T> ruleForFloatArray(Function<T, float[]> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, float[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new FloatArraySubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public InputStreamSubject<T> ruleForInputStream(Function<T, InputStream> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, InputStream> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new InputStreamSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public IntegerSubject<T> ruleForInteger(Function<T, Integer> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Integer> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new IntegerSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public IntArraySubject<T> ruleForIntArray(Function<T, int[]> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
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
    public <E> IterableSubject<T, ? super E> ruleForIterable(Function<T, Iterable<? super E>> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Iterable<? super E>> iterablePropertyRule = new PropertyRule<>(func, propertyName);
        rules.add(iterablePropertyRule);
        return new IterableSubject<>(iterablePropertyRule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LocalDateSubject<T> ruleForLocalDate(Function<T, LocalDate> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, LocalDate> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LocalDateSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LocalDateTimeSubject<T> ruleForLocalDateTime(Function<T, LocalDateTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, LocalDateTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LocalDateTimeSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LocalTimeSubject<T> ruleForLocalTime(Function<T, LocalTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, LocalTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LocalTimeSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LongSubject<T> ruleForLong(Function<T, Long> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Long> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new LongSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public LongArraySubject<T> ruleForLongArray(Function<T, long[]> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
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
    public <K, V> MapSubject<T, K, V> ruleForMap(Function<T, Map<K, V>> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Map<K, V>> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new MapSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ObjectSubject<T> ruleForObject(Function<T, Object> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Object> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ObjectSubject<>(rule);
    }

    /**
     * TODO: add a note this is does not work for primitives and to see other methods
     * @param func
     * @return
     */
    public <E> ObjectArraySubject<T, E[]> ruleForObjectArray(Function<T, E[]> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, E[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ObjectArraySubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public OffsetDateTimeSubject<T> ruleForOffsetDateTime(Function<T, OffsetDateTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, OffsetDateTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new OffsetDateTimeSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public OffsetTimeSubject<T> ruleForOffsetTime(Function<T, OffsetTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, OffsetTime> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new OffsetTimeSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ShortSubject<T> ruleForShort(Function<T, Short> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, Short> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ShortSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ShortArraySubject<T> ruleForShortArray(Function<T, short[]> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, short[]> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new ShortArraySubject<>(rule);
    }

    // TODO: should cache bytebuddy proxies either via a hashmap or bytebuddy TypeCache
    /**
     *
     * @param func
     * @return
     */
    public StringSubject<T> ruleForString(Function<T, String> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, String> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new StringSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public AbstractCharSequenceSubject<?, T, ? extends CharSequence> ruleForStringBuilder(Function<T, StringBuilder> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, StringBuilder> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new CharSequenceSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public AbstractCharSequenceSubject<?, T, ? extends CharSequence> ruleForStringBuffer(Function<T, StringBuffer> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, StringBuffer> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new CharSequenceSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public UriSubject<T> ruleForUri(Function<T, URI> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, URI> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new UriSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public UrlSubject<T> ruleForUrl(Function<T, URL> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
        PropertyRule<T, URL> rule = new PropertyRule<>(func, propertyName);
        rules.add(rule);
        return new UrlSubject<>(rule);
    }

    /**
     *
     * @param func
     * @return
     */
    public ZonedDateTimeSubject<T> ruleForZonedDateTime(Function<T, ZonedDateTime> func) {
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
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
