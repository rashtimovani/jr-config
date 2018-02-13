# jrashack-external-values
Simple java library for managing configuration values as constants.

## When you wan't to access value specified in one external resource under some key, just declare variable of type External

If you need to declare in code variable that accesses to some external value from resources do following:

```java
External<Integer> someExternalValue;
```
 
You then access seamlessly to value from external resources:<br />

```java
someExternalValue.value();
```

External objects are instantiated in following way:

```java
External<Integer> someExternalValue = Externals.typed(Integer.class).buildForKey("integerValue");
```
This external will read integer specified in external resources under the key *integerValue*.
All supported types for now are
- `Boolean` (and primitive `boolean`)
- `Byte` (and primitive `byte`)
- `Short` (and primitive `short`)
- `Integer` (and primitive `int`)
- `Long` (and primitive `long`)
- `Float` (and primitive `float`)
- `Double` (and primitive `double`)
- `Character` (and primitive `char`)
- `String`
- All `enum` types.

Values in external resources are provided as strings,
so if predefined types from above are not enough for you and you want to convert it in some special way,
you may provide your custom converter:

```java
External<Integer> someExternalValue = Externals.converted(Integer::valueOf).buildForKey("convertedIntegerValue");
```
This external will read integer specified in external resources under the key *convertedIntegerValue*,
but it will use `Integer::valueOf` method to parse that integer.

Values can also have default values if there's no value for that key specified in external resources:

```java
External<Integer> someExternalValue = Externals.converted(Integer::valueOf).defaultValue(4).buildForKey("convertedIntegerValue")
```

## You can specify from where external values are read

You should do that when your app is bootstrapping. Your main class could look like:

```java
public class Runner {
	public static void main() {
		Externals.loadFrom("properties://app.properties");
		// rest of your code goes here
	}
}
```

For now, Externals come with following predefined value providers:

- Standard java properties, specified with URI: `properties://filename.extension`

## You can customize how external values are read

By implementing interface `ValueProvider` you can customize how external values are read, if predefined ways are not enough for you.

If you want to read from `csv` file, you can implement class called `CSVProvider`. It neads to have a factory method that receives URI that specifies where csv file is found.

```java
public class CSVProvider implements ValueProvider {

	public static CSVProvider forURI(URI uri) {
		// initialization goes here
	}
	
	// rest of implementation goes here
}
```

You can then register it like this:

```java
public class Runner {
	public static void main() {
		Externals.registerValueProvider("csv", CSVProvider::forURI);
		Externals.loadFrom("properties://app.properties", "csv://specific-values.csv");
		// rest of your code goes here
	}
}
```

Now, external values will be read from that csv file, in way that you specified.
