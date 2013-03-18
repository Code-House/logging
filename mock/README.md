# Mock binding for slf4j
We all know that our code should not be dependent on log calls. However since this small library is an abstraction built on top of slf4j we
must mock logging backend to test calls. Sorry.

## How it's done?
The mocking is pretty easy. Before you will get the mocked logger you must register it by calling ```EasyMockLoggerFactory.expectLogger```

## Powermock?
We don't use powermock here since it's easy to bound our mock implementation to slf4j, also we are not happy with another set of dependencies to manage.