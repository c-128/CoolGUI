# CoolGUI
```
Current Status: Meme
```

A quick way to make GUIs using Minestom using reactive stats!

## Examples
```java
public class CounterFragment extends Fragment {

    StateHook<Integer> counter = useState(Integer.class, 0);

    public ExampleFragment() {
        addFragment(ButtonFragment.builder()
                .slot(0, 0)
                .item(() -> ItemStack.builder(Material.GOLD_BLOCK).displayName(Component.text(counter.get())).build())
                .onClick(onClick -> {
                    counter.set(counter.get() + 1);
                })
                .build());
    }

    @Override
    protected Component getTitle() {
        return Component.text(counter.get());
    }
}
```
More examples can be found here: https://github.com/c-128/CoolGUI/tree/main/src/test/java/com/coolgui/test