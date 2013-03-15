# Typesafe logging

This small project is aimed to provide tiny abstraction layer over slf4j api, thus it's not related to any particular log backend. You may use this library with logback or log4j. It's agnostic.

## YALF / Yet Another Logging Facade
Let's clarify. It's not facade. It has nothing common with covering APIs. It's just few annotations which let you use more "object oriented" syntax and re-use same logging messsages in different places. As said at the begining - library do not care about slf4j.

## What is wrong with slf4j API?
Nothing. Slf4j API is good enough. However, it solves only problem of backing logging implementation and provides some basic support for argument subsitution. The real problem is that your logging statements are still procedural. If you want put some information in log you usualy do:
```java
logger.info("Unexpected configuration entry {} at line {} in file {}", type, line, file);
```
Now imagine that type is an class, your log entry will look more or less like this:
```
INFO Unexpected configuration entry class com.example.Section at line 13 in file domain.xml
```
With this library you can do only this:
```
logger.unexpectedEntry(type, line, file);
```
## Where is message then?
Message is put as annotation on interface:
```java
public interface ConfigLogger {
    @Message("Unexpected configuration entry {} at line {} in file {}")
    void unexpectedEntry(Class<?> type, int line, String file);
}
```
Result will be the same as you saw before. 

Do you want change log level? Sure thing just use @Trace, @Debug, @Info (it's default), @Warning or @Error for critical messages. If you want skip entry and don't log just add @Ignore like you do on unit tests.

## Nonsense
Yes, you are right. Logging is too simple to complicate it. But we did!

As we are one moment before calling slf4j we may add additional features. The simplest is @Adapter. This small annotation allow to adapt method argument before sending to log:
```
public interface ConfigLogger {
    @Message("Unexpected configuration entry {} at line {} in file {}")
    void unexpectedEntry(@Adapter(ClassAdapter.class) Class<?> type, int line, String file);
}
```
The @Adapter annotation may be specified on class level. Your object may also optionally implement Adaptable interface. It will let you keep toString for other user cases if you wish.

## Forwarding
Can you ever had a need to use some kind of "composite" logger which could be re-used from more than one place? We didn't however, because we complicated such simple thing so much we went even further. We let you use @Category annotation on loggers:
```
@Category(ParentLogger.class)
public interface ConfigLogger {
    @Message("Unexpected configuration entry {} at line {} in file {}")
    void unexpectedEntry(Class<?> type, int line, String file);
}
```
Then your ConfigLogger will use ParentLogger as backing logger. It's not lucky sometimes to show class name, so if you would like use only package name as category you may do `@Category(value=ParentLogger.class, usePackage=true)`

# Codes
Some serious solutions uses error codes or codes to provide tips for system administrators. We think it's generally good idea, thats why we allow you to use @Code annotation
```java
public interface ConfigLogger {
    @Code("123")
    @Message("Unexpected configuration entry {} at line {} in file {}")
    void unexpectedEntry(Class<?> type, int line, String file);
}
```
@Code value is not appended to message, it's put into diagnostic context, thus you will need include it separately in your pattern layout using %X{logging.code} (for log4j).

As big systems may grow and grow without limits we also support @Pattern on logger level which will let you group @Codes in logical units:
```java
@Pattern("CFG-{}")
public interface ConfigLogger {
    @Code("123")
    @Message("Unexpected configuration entry {} at line {} in file {}")
    void unexpectedEntry(Class<?> type, int line, String file);
}
```

# That's all
If you don't like the idea after description you've read - just try using it. The staring point is to use `org.code_house.logging.core.LoggerFactory` instead slf4j factory.

This project is initial idea, in case of problems, feature requests or bugs - raise issue on github or ping me on twitter @ldywicki.
