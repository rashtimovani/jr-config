# jrashack-external-values
Simple java library for managing configuration values as constants.

## Declare value that is read from external resources like this

If you need to declare in code variable that accesses to some external value from resources do following:<br />
`External<Integer> someExternalValue;` 

You then access seamlessly to value from external resources:<br />
`someExternalValue.value()` 

External objects are instantiated in following way:<br />
`External<Integer> someExternalValue = Externals.typed(Integer.class).buildForKey("integerValue")`
This external will read integer specified in external resources under the key *integerValue*.

Values in external resources are provided as strings, so if you want to convert it in some special way, you may provide your custom converter:<br />
`External<Integer> someExternalValue = Externals.converted(Integer::valueOf).buildForKey("convertedIntegerValue")`
This external will read integer specified in external resources under the key *convertedIntegerValue*, but it will use `Integer::valueOf` method to parse that integer.

Values can also have default values if there's no value for that key specified in external resources:<br />
`External<Integer> someExternalValue = Externals.converted(Integer::valueOf).defaultValue(4).buildForKey("convertedIntegerValue")`
