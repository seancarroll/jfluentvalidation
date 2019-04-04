package jfluentvalidation.rules;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 */
public class RuleSetCollection extends ArrayList<Rule<?, ?>> {

    // TODO: do we want to use a weakreference
    // private WeakReference<Consumer<Rule<?, ?>>> itemAddedCallback;
    private Consumer<Rule<?, ?>> itemAddedCallback;

    /**
     *
     * @param itemAddedCallback
     */
    public void registerItemAddedCallback(Consumer<Rule<?, ?>> itemAddedCallback) {
        this.itemAddedCallback = itemAddedCallback;
    }

    /**
     *
     */
    public void deregisterItemAddedCallback() {
        this.itemAddedCallback = null;
    }

    @Override
    public boolean add(Rule rule) {
        if (itemAddedCallback != null) {
            itemAddedCallback.accept(rule);
        }
        return super.add(rule);
    }

}
