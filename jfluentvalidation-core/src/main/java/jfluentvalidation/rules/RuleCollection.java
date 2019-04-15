package jfluentvalidation.rules;

import java.util.ArrayList;
import java.util.function.Consumer;

// TODO: should this use generics?
// TODO: ugh I think the name should just be Rule(s)Collection or Rule(s)List
/**
 *
 * @param <T>
 */
public class RuleSetCollection<T> extends ArrayList<Rule<T, ?>> {

    // TODO: do we want to use a weakreference
    // private WeakReference<Consumer<Rule<?, ?>>> itemAddedCallback;
    private Consumer<Rule<T, ?>> itemAddedCallback;

    /**
     *
     * @param itemAddedCallback
     */
    public void registerItemAddedCallback(Consumer<Rule<T, ?>> itemAddedCallback) {
        this.itemAddedCallback = itemAddedCallback;
    }

    /**
     *
     */
    public void deregisterItemAddedCallback() {
        this.itemAddedCallback = null;
    }

    // Terrible name but is this better??
    /**
     *
     * @param runnable
     * @param itemAddedCallback
     */
    public void run(Runnable runnable, Consumer<Rule<T, ?>> itemAddedCallback) {
        this.itemAddedCallback = itemAddedCallback;
        runnable.run();
        this.itemAddedCallback = null;
    }

    @Override
    public boolean add(Rule<T, ?> rule) {
        if (itemAddedCallback != null) {
            itemAddedCallback.accept(rule);
        }
        return super.add(rule);
    }

}
